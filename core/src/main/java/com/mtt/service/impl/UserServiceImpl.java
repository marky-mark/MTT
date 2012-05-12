package com.mtt.service.impl;

import com.mtt.domain.entity.User;
import com.mtt.domain.exception.UserNotFoundException;
import com.mtt.repository.UserRepository;
import com.mtt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to find users in the system
 */
@Transactional
@Service
public final class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User find(Long id) {
        User user = userRepository.findOne(id);

        if (user != null) {
            return user;
        }

        throw new UserNotFoundException(id);
    }

    @Override
    public User find(String username) {
         User user = userRepository.findByUserName(username);

        if (user != null) {
            return user;
        }

        throw new UserNotFoundException(username);
    }
}