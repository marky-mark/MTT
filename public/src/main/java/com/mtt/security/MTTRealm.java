package com.mtt.security;

import com.mtt.domain.entity.User;
import com.mtt.exception.MissingPasswordException;
import com.mtt.exception.MissingUsernameAndPasswordException;
import com.mtt.exception.MissingUsernameException;
import com.mtt.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public final class MTTRealm extends AuthenticatingRealm {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        Assert.isInstanceOf(UsernamePasswordToken.class, authenticationToken);
        UsernamePasswordToken upToken = verifyUsernamePasswordToken((UsernamePasswordToken) authenticationToken);

        //Make sure the credentials are correct ....exception thrown if issue
        User user = userService.authenticate(upToken.getUsername(), new String(upToken.getPassword()));

        UserAuthenticationInfo authenticationInfo = new UserAuthenticationInfo();
        authenticationInfo.setUsername(user.getUsername());

        return authenticationInfo;
    }


    private UsernamePasswordToken verifyUsernamePasswordToken(UsernamePasswordToken usernamePasswordToken) {

        boolean usernameIsDefined = hasLength(usernamePasswordToken.getUsername());
        boolean passwordIsDefined = hasLength(charArrayToString(usernamePasswordToken.getPassword()));

        if (!usernameIsDefined && !passwordIsDefined) {
            throw new MissingUsernameAndPasswordException();
        } else if (!usernameIsDefined) {
            throw new MissingUsernameException();
        } else if (!passwordIsDefined) {
            throw new MissingPasswordException();
        }

        return usernamePasswordToken;
    }

    private boolean hasLength(String value) {
        try {
            Assert.hasLength(value);
        } catch (IllegalArgumentException ex) {
            return false;
        }
        return true;
    }

    private String charArrayToString(char[] chars) {
        if (chars != null) {
            return new String(chars);
        }
        return null;
    }
}
