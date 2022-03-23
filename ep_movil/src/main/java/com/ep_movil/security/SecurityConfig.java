package com.ep_movil.security;

import com.ep_movil.config.CustomAccessDeniedHandler;
import com.ep_movil.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //aca le voy a estar pasando las rutas de acceso público
        http.csrf().disable().authorizeRequests()
                .antMatchers("/", "/registrar", "/acceder", "/save", "/login", "/signin", "/tienda/tienda2", "/tienda/tienda2/*", "/admin/detalle/**", "/about", "/repara", "/tienda/OxNA/*", "/tienda/OxND/*", "/tienda/Ox-P/*", "/tienda/Ox+P/*", "/tienda/OxNA", "/tienda/OxND", "/tienda/Ox-P", "/tienda/Ox+P").permitAll()
                .antMatchers("/css/**", "/js/**", "/images/*", "/styles/*", "/templates/*", "/estilos/**").permitAll()
                .antMatchers("/tienda/productos/**", "/admin/guardar", "/admin/modificar/**", "/admin/eliminar/**").hasRole("ADMIN") //autorizo solo al admin el poder editar, agregar o eliminar

                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/signin").loginPage("/login").permitAll()
                .defaultSuccessUrl("/").failureUrl("/login?error=true")
                .usernameParameter("username").passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
    }
}