package com.mtt.security;

import java.io.Serializable;

/**
 * Represents a Shiro Session
 */
public interface AuthenticatedUserSession extends Serializable {


    String getUsername();
}
