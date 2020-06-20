package edu.pucmm.josecl200.encuestacompose;

import edu.pucmm.josecl200.encuestacompose.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class EncuestacomposeApplication {
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(EncuestacomposeApplication.class, args);
        UsuarioServicios usuarioServicios = (UsuarioServicios) applicationContext.getBean("usuarioServicios");
        usuarioServicios.crearUsuarioAdmin();
    }
}
