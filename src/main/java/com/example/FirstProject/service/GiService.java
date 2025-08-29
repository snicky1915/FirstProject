package com.example.FirstProject.service;

import com.example.FirstProject.common.CrudService;
import com.example.FirstProject.entity.Gi;
import com.example.FirstProject.entity.GiHistory;
import com.example.FirstProject.repository.GiHistoryRepository;
import com.example.FirstProject.repository.GiRepository;
import org.springframework.stereotype.Service;

@Service
public class GiService extends CrudService<Gi, GiHistory> {

    private final GiRepository giRepository;

    public GiService(GiRepository giRepository, GiHistoryRepository giHistoryRepository){
        super(giRepository,giHistoryRepository,GiHistory.class,Gi::getGiId);
        this.giRepository = giRepository;
    }
}
