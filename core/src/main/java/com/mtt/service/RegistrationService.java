package com.mtt.service;

import com.mtt.domain.entity.UserActivationKey;
import com.mtt.service.request.CreateUserRequest;

public interface RegistrationService {

    UserActivationKey registerUser(CreateUserRequest userRequest);
}
