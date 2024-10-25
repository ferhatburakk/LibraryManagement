//package com.library.management.config;
//
//import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//
//@Component
//public class AppAuthenticationProvider implements AuthenticationProvider {
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = String.valueOf(authentication.getCredentials());
//
//        if ("ferhat".equals(username) && "123".equals(password)) {
//            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
//        } else {
//            throw new AuthenticationCredentialsNotFoundException("Can not login!");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authenticationType) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
//    }
//}
