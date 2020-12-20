package com.example.demo.service;


import com.example.demo.model.CufdModel;
import com.example.demo.model.CuisModel;
import com.example.demo.repository.CufdRepository;
import com.example.demo.repository.CuisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CufdServiceImpl implements CufdService{


    @Autowired
    CufdRepository cufdRepository;

    @Autowired
    @Qualifier(value = "cufdRepository")
    public void setCuisRepository(CufdRepository cufdRepository){
        this.cufdRepository=cufdRepository;
    }

    @Override
    public List<CufdModel> findCufd() {
        List<CufdModel> listaCufd = cufdRepository.findAll();
        return listaCufd;
    }

    @Override
    public void saveCufd(CufdModel cufdModel) {
        cufdRepository.save(cufdModel);
    }

    @Override
    public CufdModel updateCufd(CufdModel cufdModel) throws Exception {
        List<CufdModel> listCufd= findCufd();
        CufdModel toCufdModel=listCufd.get(0);
        mapCufd(cufdModel,toCufdModel);
        return cufdRepository.save(toCufdModel);
    }

    protected void mapCufd(CufdModel from,CufdModel to) {
        Date date= new Date();
        long time = date.getTime();
        System.out.println("Time in Milliseconds: " + time);
        Timestamp ts = new Timestamp(time);
        System.out.println("Current Time Stamp: " + ts);
        to.setCufd(from.getCufd());
        to.setDateCreated(ts);
    }
}
