package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Gr;
import com.example.FirstProject.entity.GrHistory;
import com.example.FirstProject.repository.GrHistoryRepository;
import com.example.FirstProject.repository.GrRepository;
import org.springframework.stereotype.Service;

@Service
public class GrService extends CrudService<Gr, GrHistory> {

    private final GrRepository grRepository;
    public GrService(GrRepository grRepository, GrHistoryRepository grHistoryRepository){
        super(grRepository,grHistoryRepository, GrHistory.class,Gr::getGrId);
        this.grRepository=grRepository;
    }
}
