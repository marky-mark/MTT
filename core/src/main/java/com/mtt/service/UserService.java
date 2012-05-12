package com.mtt.service;

import com.mtt.domain.entity.User;

public interface UserService {

    /**
     *
     * @param id of user
     * @return the user
     */
    User find(Long id);

    /**
     *
     * @param username of user
     * @return the user
     */
    User find(String username);
}
