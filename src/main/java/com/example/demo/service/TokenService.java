package com.example.demo.service;

import com.example.demo.model.TokenModel;

import java.util.List;

public interface TokenService {
    List<TokenModel> findToken();
    void saveToken(TokenModel token);
    TokenModel updateToken(TokenModel token) throws Exception;
}
