package edu.pucmm.josecl200.encuestacompose.config;

import edu.pucmm.josecl200.encuestacompose.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecConf extends WebSecurityConfigurerAdapter {

    //Opción JPA
    @Autowired
    private UsuarioServicios userDetailsService;

    /**
     * La autentificación de los usuarios.
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    /*
     * Permite configurar las reglas de seguridad.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Marcando las reglas para permitir unicamente los usuarios
        http
                .authorizeRequests()
                .antMatchers("/","/css/**", "/js/**", "/actuator/**").permitAll() //permitiendo llamadas a esas urls.
                .anyRequest().permitAll() //cualquier llamada debe ser validada
                .and()
                .formLogin()
                .loginPage("/login") //indicando la ruta que estaremos utilizando.
                .defaultSuccessUrl("/")
                .failureUrl("/login?error") //en caso de fallar puedo indicar otra pagina.
                .permitAll()
                .and()
                .logout()
                .permitAll();

        //deshabilitando las seguridad contra los frame internos.
        //Necesario para H2.
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}