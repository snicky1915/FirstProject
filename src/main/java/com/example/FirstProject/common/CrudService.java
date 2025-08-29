package com.example.FirstProject.common;

import com.example.FirstProject.state.CrudState;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.function.Function;

public class CrudService<T, H> {

    private final JpaRepository<T, Long> mainRepository;
    private final JpaRepository<H, Long> historyRepository;
    private final Class<H> historyClass;
    private final Function<T, Long> idGetter;

    public CrudService(JpaRepository<T, Long> mainRepository,
                       JpaRepository<H, Long> historyRepository,
                       Class<H> historyClass,
                       Function<T, Long> idGetter) { // <-- sửa dấu )
        this.mainRepository = mainRepository;
        this.historyRepository = historyRepository;
        this.historyClass = historyClass;
        this.idGetter = idGetter;
    }

    private Long getId(T entity) { return idGetter.apply(entity); }

    @Transactional
    public String createEntity(T request) {
        try {
            Long id = getId(request);
            if (id != null && mainRepository.existsById(id)) {
                return "FAIL: ID " + id + " đã tồn tại";
            }
            T saved = mainRepository.save(request);
            saveHistory(saved, "INSERT");
            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return "FAIL: " + e.getMessage();
        }
    }

    @Transactional
    public String updateEntity(T request) {
        try {
            Long id = requireId(request, "UPDATE");
            if (!mainRepository.existsById(id)) {
                return "FAIL: Không tìm thấy ID " + id + " để UPDATE";
            }
            T updated = mainRepository.save(request);
            saveHistory(updated, "UPDATE");
            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return "FAIL: " + e.getMessage();
        }
    }

//    @Transactional
//    public String deleteEntity(T request) {
//        try {
//            Long id = requireId(request, "DELETE");
//            Optional<T> existingOpt = mainRepository.findById(id);
//            if (existingOpt.isEmpty()) {
//                return "FAIL: Không tìm thấy ID " + id + " để DELETE";
//            }
//            T existing = existingOpt.get();
//            mainRepository.deleteById(id);
//            saveHistory(existing, "DELETE");
//            return CrudState.SUCCESS.name();
//        } catch (Exception e) {
//            return "FAIL: " + e.getMessage();
//        }
//    }

    @Transactional
    public String deleteById(Long id) {
        try {
            Optional<T> existingOpt = mainRepository.findById(id);
            if (existingOpt.isEmpty()) {
                return "FAIL: Không tìm thấy ID " + id + " để DELETE";
            }
            T existing = existingOpt.get();
            mainRepository.deleteById(id);
            saveHistory(existing, "DELETE");
            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return "FAIL: " + e.getMessage();
        }
    }

    private Long requireId(T entity, String action) {
        Long id = getId(entity);
        if (id == null) throw new IllegalArgumentException("ID bị null cho thao tác " + action);
        return id;
    }

    protected void saveHistory(Object source, String action) throws Exception {
        H h = historyClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(source, h);
        Method setAction = historyClass.getMethod("setAction", String.class);
        setAction.invoke(h, action);
        historyRepository.save(h);
    }
}
