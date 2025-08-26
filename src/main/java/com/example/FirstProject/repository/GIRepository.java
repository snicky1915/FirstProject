package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseRepository;
import com.example.FirstProject.entity.GoodsIssue;
import org.springframework.stereotype.Repository;

@Repository
public interface GIRepository extends BaseRepository<GoodsIssue,Long> {

}
