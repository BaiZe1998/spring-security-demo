package com.baize.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *  Spring Security 配置类
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 在内存中创建一个名为 "user" 的用户，密码为 "123"，拥有 "USER" 权限，密码使用BCryptPasswordEncoder加密
         */
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("123")).roles("USER");
        /**
         * 在内存中创建一个名为 "admin" 的用户，密码为 "123"，拥有 "USER" 和"ADMIN"权限
         */
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123")).roles("USER","ADMIN");
    }

    /**
     * 给出了访问指定路径下的资源需要的角色（权限）
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login")    //用户未登录时，访问任何需要权限的资源都转跳到该路径，即登录页面，此时登陆成功后会继续跳转到第一次访问的资源页面（相当被拦截了一下）
                .defaultSuccessUrl("/user") //登录认证成功后默认转跳的路径，意思时admin登录后也跳转到user
                .and()
                .logout()
                .logoutUrl("/logout")   //退出登陆的路径，指定spring security拦截的注销url,退出功能是security提供的
                .logoutSuccessUrl("/login");//用户退出后要被重定向的url
    }
}
