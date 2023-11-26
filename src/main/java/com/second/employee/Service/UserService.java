package com.second.employee.Service;

import org.springframework.stereotype.Service;

import com.second.employee.Model.UserModel;
import com.second.employee.Repository.UserRepository;

@Service

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserModel registerUser(String username,String fname,String sname,String email,String password,String phone){
        if(username==null || password==null)
        {
            return null;
        }else
        {
        UserModel userModel= new UserModel();
        userModel.setEmail(email);
        userModel.setFname(fname);
        userModel.setPassword(password);
        userModel.setUsername(username);
        userModel.setSname(sname);
        userModel.setPhone(phone);
        return userRepository.save(userModel);
        }
        


    }
    public UserModel authenticate(String username,String password)
    {
        return userRepository.findByUsernameAndPassword(username,password).orElse(null);

    }
}
