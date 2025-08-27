package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseHistoryRepository;
import com.example.FirstProject.entity.UserHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository extends BaseHistoryRepository<UserHistory,Long> {
}
