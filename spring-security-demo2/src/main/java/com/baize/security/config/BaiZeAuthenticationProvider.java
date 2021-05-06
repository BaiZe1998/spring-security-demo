package com.baize.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class BaiZeAuthenticationProvider implements AuthenticationProvider {
    /**
     * 该验证器类实现：当黑客使用BaiZe这个账户登陆时，将获取获取USER和ADMIN的权限
     * 只要Authentication实例通过了一个过滤器的验证，就能将
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (username.equals("baize")) {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));  //ROLE_ADMIN写法是固定的
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));   //ROLE_USER写法是固定的
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        } else {
            return null;
        }
    }

    /**
     * support方法检查authentication的类型是不是这个AuthenticationProvider支持的
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
