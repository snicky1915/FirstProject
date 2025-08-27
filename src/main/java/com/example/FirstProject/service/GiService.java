package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Gi;
import com.example.FirstProject.entity.GiHistory;
import com.example.FirstProject.repository.GiHistoryRepository;
import com.example.FirstProject.repository.GiRepository;

public class GiService extends CrudService<Gi, GiHistory> {

    private final GiRepository giRepository;

    public GiService(GiRepository giRepository, GiHistoryRepository giHistoryRepository){
        super(giRepository,giHistoryRepository,Gi.class,GiHistory.class);
        this.giRepository = giRepository;
    }
}
