package com.example.demo.service;

import com.example.demo.model.CufdModel;
import com.example.demo.model.CuisModel;
import com.example.demo.model.TokenModel;

import java.util.List;

public interface CufdService {
    List<CufdModel> findCufd();
    void saveCufd(CufdModel cufdModel);
    CufdModel updateCufd(CufdModel cufdModel) throws Exception;
}
