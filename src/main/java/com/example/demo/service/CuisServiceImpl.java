package com.example.demo.service;

import com.example.demo.model.CuisModel;
import com.example.demo.repository.CuisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Service

public class CuisServiceImpl implements CuisService{

    @Autowired
    CuisRepository cuisRepository;

    @Autowired
    @Qualifier(value = "cuisRepository")
    public void setCuisRepository(CuisRepository cuisRepository){
        this.cuisRepository=cuisRepository;
    }

    @Override
    public List<CuisModel> findCuis() {
        List<CuisModel> listCuis = cuisRepository.findAll();
        return listCuis;
    }

    @Override
    public void saveCuis(CuisModel cuisModel) {
        cuisRepository.save(cuisModel);
    }

    @Override
    public CuisModel updateCuis(CuisModel cuisModel) throws Exception {
        List<CuisModel> listCuis= findCuis();
        CuisModel toCuisModel=listCuis.get(0);
        mapCuis(cuisModel,toCuisModel);
        return cuisRepository.save(toCuisModel);
    }

    protected void mapCuis(CuisModel from,CuisModel to) {
        Date date= new Date();
        long time = date.getTime();
        System.out.println("Time in Milliseconds: " + time);
        Timestamp ts = new Timestamp(time);
        System.out.println("Current Time Stamp: " + ts);
        to.setCuis(from.getCuis());
        to.setDateCreated(ts);
    }
}
