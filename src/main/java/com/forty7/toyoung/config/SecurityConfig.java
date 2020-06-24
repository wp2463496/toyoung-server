package com.forty7.toyoung.config;

import com.forty7.toyoung.auth.JwtAuthError;
import com.forty7.toyoung.auth.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity //添加security过滤器
@EnableGlobalMethodSecurity(prePostEnabled = true) // 可以在controller方法上配置权限
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    // 加载用户信息
    @Autowired
    private UserDetailsService myUserDetailsService;

    // 权限不足错误信息处理，包含认证错误与鉴权错误处理
    @Autowired
    private JwtAuthError myAuthErrorHandler;

    //密码明文加密方式配置
    @Bean
    public PasswordEncoder myEncoder() {
        return new BCryptPasswordEncoder();
    }

    // jwt校验过滤器，从http头部Authorization字段读取token并校验
    @Bean
    public JwtAuthFilter myAuthFilter() throws Exception {
        return new JwtAuthFilter();
    }

    // 获取AuthenticationManager（认证管理器），可以在其他地方使用
    @Bean(name="authenticationManagerBean")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 认证用户时用户信息加载配置，注入myUserDetailsService
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

//    //认证用户时用户信息加载配置
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(myEncoder().encode("12345")).roles("ADMIN")
//                .and()
//                .withUser("user").password(myEncoder().encode("12345")).roles("USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //基于token，不需要csrf
                .csrf().disable()

                //基于token，不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // 设置myUnauthorizedHandler处理认证失败、鉴权失败
                .exceptionHandling().authenticationEntryPoint(myAuthErrorHandler).accessDeniedHandler(myAuthErrorHandler).and()

                //下面开始设置权限
                .authorizeRequests()

                //需要登录
                .antMatchers("/hello/hello1").authenticated()

                //需要登录
                .antMatchers("/toyoung/test2").authenticated()

                //需要角色权限
                .antMatchers("/hello/hello2").hasRole("ADMIN")

                // 除上面外的所有请求全部放开
                .anyRequest().permitAll();

                //添加JWT过滤器，JWT过滤器在用户名密码认证过滤器之前
                http.addFilterBefore(myAuthFilter(), UsernamePasswordAuthenticationFilter.class);

//                //禁用缓存
//                http.headers().cacheControl();

    }

    // 配置跨源访问(CORS)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}
