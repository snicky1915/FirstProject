package com.example.FirstProject.common;

import com.example.FirstProject.state.CrudState;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public class CrudService <T,H>{

    private final JpaRepository<T,Long> mainRepository;
    private final JpaRepository<H,Long> historyRepository;
    private final Class<T> mainClass;
    private final Class<H> historyClass;

    public CrudService(JpaRepository<T,Long>mainRepository,JpaRepository<H,Long> historyRepository,Class<T> mainClass,Class<H> historyClass ){
        this.mainRepository=mainRepository;
        this.historyRepository=historyRepository;
        this.mainClass=mainClass;
        this.historyClass=historyClass;
    }

    @Transactional
    public String create(T request) {
        try {
            //Lưu vào bảng chính
            T saved = mainRepository.save(request);

            //Lưu vào bảng history
            H requestHistory = historyClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(saved,requestHistory);
            requestHistory.getClass().getMethod("setAction", String.class).invoke(requestHistory, "INSERT");
            historyRepository.save(requestHistory);

            return CrudState.SUCCESS.name();

        } catch (Exception e) {
            return CrudState.FAIL.name();
        }
    }

    @Transactional
    public String update(T request) {
        try {
            //Update dữ liệu mới vào bảng chính
            T updated = mainRepository.save(request);

            //Lưu vào bảng history
            H requestHistory = historyClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(updated,requestHistory);
            requestHistory.getClass().getMethod("setAction", String.class).invoke(requestHistory, "UPDATE");
            historyRepository.save(requestHistory);
            return CrudState.SUCCESS.name();

        } catch (Exception e) {
            return CrudState.FAIL.name();
        }
    }
    @Transactional
    public String delete(T request) {
        try {
            // 1. Xoá dữ liệu ở bảng chính
            mainRepository.delete(request);

            // 2. Lưu vào bảng history
            H requestHistory = historyClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(request, requestHistory); // copy từ request luôn
            requestHistory.getClass().getMethod("setAction", String.class).invoke(requestHistory, "DELETE");
            historyRepository.save(requestHistory);

            return CrudState.SUCCESS.name();
        } catch (Exception e) {
            return CrudState.FAIL.name();
        }
    }
}
