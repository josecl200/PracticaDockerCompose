package edu.pucmm.josecl200.encuestacompose;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.web.WebFilter;
import edu.pucmm.josecl200.encuestacompose.servicios.UsuarioServicios;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
public class EncuestacomposeApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(EncuestacomposeApplication.class, args);
        UsuarioServicios usuarioServicios = (UsuarioServicios) applicationContext.getBean("usuarioServicios");
        usuarioServicios.crearUsuarioAdmin();
    }
    @Bean
    public Config config() {
        return new Config();
    }

    @Bean
    public WebFilter webFilter(HazelcastInstance hazelcastInstance) {

        Properties properties = new Properties();
        properties.put("instance-name", hazelcastInstance.getName());
        properties.put("sticky-session", "false");

        return new WebFilter(properties);
    }
}
