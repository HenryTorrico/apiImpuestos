package com.example.demo.service;

import com.example.demo.model.CuisModel;
import com.example.demo.model.TokenModel;

import java.util.List;

public interface CuisService {
    List<CuisModel> findCuis();
    void saveCuis(CuisModel cuisModel);
    CuisModel updateCuis(CuisModel cuisModel) throws Exception;
}
