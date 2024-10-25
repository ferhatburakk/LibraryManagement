package com.library.management.service;

import com.library.management.model.entity.Users;
import com.library.management.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(Users users){
        usersRepository.save(users);
    }
}
