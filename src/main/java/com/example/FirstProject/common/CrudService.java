package com.example.FirstProject.common;

import com.example.FirstProject.state.CrudState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
public class CrudService<T extends BaseEntity, H extends BaseHistoryEntity> {

    private final BaseRepository<T, Long> mainRepository;
    private final BaseHistoryRepository<H, Long> historyRepository;
    private final Class<H> historyClass;
    private final Function<T, Long> idGetter;

    public CrudService(BaseRepository<T, Long> mainRepository,
                       BaseHistoryRepository<H, Long> historyRepository,
                       Class<H> historyClass,
                       Function<T, Long> idGetter) {
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
            return fail("CREATE", e);
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
            return fail("UPDATE", e);
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
            if (id == null) {
                throw new IllegalArgumentException("ID bị null cho thao tác DELETE");
            }
            Optional<T> existingOpt = mainRepository.findById(id);
            if (existingOpt.isEmpty()) {
                return "FAIL: Không tìm thấy ID " + id + " để DELETE";
            }
            T existing = existingOpt.get();
            mainRepository.deleteById(id);
            saveHistory(existing, "DELETE");
            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return fail("DELETE", e);
        }
    }

    private Long requireId(T entity, String action) {
        Long id = getId(entity);
        if (id == null) throw new IllegalArgumentException("ID bị null cho thao tác " + action);
        return id;
    }

    protected void saveHistory(T source, String action) throws Exception {
        H h = historyClass.getDeclaredConstructor().newInstance();
        BeanUtils.copyProperties(source, h);
        h.setAction(action);
        h.setChangedBy(resolveChangedBy(source));
        historyRepository.save(h);
    }

    private String resolveChangedBy(T source) {
        if (source.getFinalUpdateId() != null && !source.getFinalUpdateId().isBlank()) {
            return source.getFinalUpdateId();
        }
        return source.getCreatedId();
    }

    private String fail(String action, Exception e) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        log.error("{} failed in CrudService", action, e);
        return "FAIL: " + e.getMessage();
    }
}
