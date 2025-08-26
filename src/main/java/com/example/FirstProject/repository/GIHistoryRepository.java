package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseHistoryRepository;
import com.example.FirstProject.entity.GiHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface GIHistoryRepository extends BaseHistoryRepository<GiHistory,Long> {
}
