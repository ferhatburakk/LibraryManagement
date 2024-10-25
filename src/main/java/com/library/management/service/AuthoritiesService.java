package com.library.management.service;

import com.library.management.model.entity.Authorities;
import com.library.management.repository.AuthoritiesRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {

    private final AuthoritiesRepository authoritiesRepository;

    public AuthoritiesService(AuthoritiesRepository authoritiesRepository) {
        this.authoritiesRepository = authoritiesRepository;
    }

    public void save(Authorities authorities) {
        authoritiesRepository.save(authorities);
    }
}
