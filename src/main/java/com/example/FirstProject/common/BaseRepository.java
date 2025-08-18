package com.example.FirstProject.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    // Viết thêm các hàm dùng chung ở đây
    // Ví dụ: soft delete
    default void softDelete(T entity) {
        if (entity instanceof BaseEntity) {
            ((BaseEntity) entity).setDelYn("Y");
            save(entity);
        }
    }
}
