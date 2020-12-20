package com.example.demo.service;


import com.example.demo.model.TokenModel;
import com.example.demo.model.User;
import com.example.demo.repository.TokenRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    @Qualifier(value = "userRepository")
    public void setUserRepository(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public List<User> findUser() {
        List<User> listUser = userRepository.findAll();
        return listUser;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
