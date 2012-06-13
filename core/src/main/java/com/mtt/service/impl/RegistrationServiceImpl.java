package com.mtt.service.impl;

import com.mtt.domain.entity.User;
import com.mtt.domain.entity.UserActivationKey;
import com.mtt.event.NewUserRegisteredEvent;
import com.mtt.repository.UserActivationKeyRepository;
import com.mtt.service.KeyGeneratorService;
import com.mtt.service.RegistrationService;
import com.mtt.service.UserService;
import com.mtt.service.event.EventService;
import com.mtt.service.request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private KeyGeneratorService keyGeneratorService;

    @Autowired
    private UserActivationKeyRepository userActivationKeyRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Override
    public UserActivationKey registerUser(CreateUserRequest userRequest) {

        //Create the User
        User user = userService.create(userRequest);

        //Create an associated user activation key
        UserActivationKey activationKey = new UserActivationKey();
        activationKey.initialise(user, keyGeneratorService);

        UserActivationKey userActivationKey = userActivationKeyRepository.save(activationKey);

        //fire event to send an email to the user
        eventService.publishAfterTransactionCommitted(new NewUserRegisteredEvent(this, userActivationKey));

        //save to DB and return key
        return userActivationKey;
    }
}
