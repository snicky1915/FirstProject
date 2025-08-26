package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseRepository;
import com.example.FirstProject.entity.GoodsReceipt;
import org.springframework.stereotype.Repository;

@Repository
public interface GrRepository extends BaseRepository<GoodsReceipt,Long> {
}
