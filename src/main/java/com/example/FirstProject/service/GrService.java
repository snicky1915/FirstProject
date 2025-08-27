package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Gr;
import com.example.FirstProject.entity.GrHistory;
import com.example.FirstProject.repository.GrHistoryRepository;
import com.example.FirstProject.repository.GrRepository;

public class GrService extends CrudService<Gr, GrHistory> {

    private final GrRepository grRepository;
    public GrService(GrRepository grRepository, GrHistoryRepository grHistoryRepository){
        super(grRepository,grHistoryRepository, Gr.class,GrHistory.class);
        this.grRepository=grRepository;
    }
}
