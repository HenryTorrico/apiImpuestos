package com.example.demo.service;


import com.example.demo.model.TokenModel;
import com.example.demo.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TokenServiceImpl   implements TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    @Qualifier(value = "tokenRepository")
    public void setTokenRepository(TokenRepository tokenRepository){
        this.tokenRepository=tokenRepository;
    }

    @Override
    public List<TokenModel> findToken() {
        List<TokenModel> listToken = tokenRepository.findAll();
        return listToken;
    }

    @Override
    public void saveToken(TokenModel token) {
        tokenRepository.save(token);
    }

    @Override
    public TokenModel updateToken(TokenModel token) throws Exception {
        List<TokenModel> listToken= findToken();
        TokenModel toTokenModel=listToken.get(0);
        mapToken(token,toTokenModel);
        return tokenRepository.save(toTokenModel);
    }

    protected void mapToken(TokenModel from,TokenModel to) {
        Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
        to.setTokenUsuario(from.getTokenUsuario());
        to.setDateCreated(ts);
    }
}
