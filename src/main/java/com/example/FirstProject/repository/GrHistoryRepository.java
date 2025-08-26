package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseHistoryRepository;
import com.example.FirstProject.entity.GrHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface GrHistoryRepository extends BaseHistoryRepository<GrHistory,Long> {
}
