//package com.ep_movil.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder build) throws Exception {
//        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        //aca le voy a estar pasando las rutas de acceso público
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/templates/**", "/login", "/usuario/registrar",
//                        "/usuario/save", "/usuario/logear", "/css/**").permitAll()
//                .anyRequest().authenticated().and()
//                .formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll()
//                .defaultSuccessUrl("/").failureUrl("/login?error=true")
//                .usernameParameter("username").passwordParameter("password")
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
//
//    }
//}
