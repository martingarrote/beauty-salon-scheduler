package com.martingarrote.beauty_salon_scheduler.utils;

import com.martingarrote.beauty_salon_scheduler.exceptions.user.UserIdentificationException;
import com.martingarrote.beauty_salon_scheduler.user.User;
import org.springframework.security.core.Authentication;

public class AuthenticationUtils {

    public static Long getUserId(Authentication auth) {
        return getUser(auth).getId();
    }

    public static String getUserEmail(Authentication auth) {
        return getUser(auth).getEmail();
    }

    private static User getUser(Authentication auth) {
        if (auth.getPrincipal() instanceof User user) {
            return user;
        }

        throw new UserIdentificationException();
    }

}
