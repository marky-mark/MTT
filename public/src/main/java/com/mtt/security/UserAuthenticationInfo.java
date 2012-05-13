package com.mtt.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public final class UserAuthenticationInfo implements AuthenticationInfo {

    public static String MTT_REALM_NAME = "mttRealm";

    private String username;

    private PrincipalCollection principalCollection;

    @Override
    public PrincipalCollection getPrincipals() {

        if (principalCollection == null) {

            Assert.hasLength(username);

            List principals = new ArrayList();
            principals.add(username);

            principalCollection = new SimplePrincipalCollection(principals, MTT_REALM_NAME);
        }
        return principalCollection;
    }

    /**
     * Populate this object with data pulled from the given {@link PrincipalCollection}.
     *
     * @param principalCollection the principal collection
     */
    public void fromPrincipals(PrincipalCollection principalCollection) {
        Object primaryPrincipal = principalCollection.getPrimaryPrincipal();
        Assert.isInstanceOf(String.class, primaryPrincipal);
        setUsername((String) primaryPrincipal);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
