package edu.pucmm.josecl200.encuestacompose.controladoras;

import edu.pucmm.josecl200.encuestacompose.entidades.RespuestasEncuesta;
import edu.pucmm.josecl200.encuestacompose.entidades.Usuario;
import edu.pucmm.josecl200.encuestacompose.repositorios.UsuarioRepo;
import edu.pucmm.josecl200.encuestacompose.servicios.EncuestaServicio;
import edu.pucmm.josecl200.encuestacompose.servicios.UsuarioServicios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RoutesController {
    @Autowired
    private EncuestaServicio encuestaServicio;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private UsuarioServicios usuarioServicios;

    @GetMapping("/")
    @Secured({"ROLE_ADMIN","ROLE_CLIENT"})
    public String index(Model model, HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("usrName",auth.getPrincipal());
        model.addAttribute("admin",false);
        for (GrantedAuthority role: auth.getAuthorities())
            if(role.getAuthority().equals("ROLE_ADMIN"))
                model.addAttribute("admin",true);
        return "encuesta";
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        System.out.println(error);
        return new ModelAndView("login", "error", error);
    }

    @Secured("ROLE_CLIENT")
    @PostMapping("/encuesta")
    public String saveEncuesta(@RequestParam("dominio") Integer expositores,@RequestParam("expectativas") Integer charlas,@RequestParam("confortables") Integer instalaciones, @RequestParam("comentario") String comentario){
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!encuestaServicio.alreadyFilled(usuarioRepo.findByUsername(userDetails.getUsername()))){
            RespuestasEncuesta respuestas = new RespuestasEncuesta();
            respuestas.setUsuario(usuarioRepo.findByUsername(userDetails.getUsername()));
            respuestas.setCharlas(charlas);
            respuestas.setExpositores(expositores);
            respuestas.setInstalaciones(instalaciones);
            respuestas.setResp4(comentario);
            encuestaServicio.saveEncuesta(respuestas);
            return "redirect:/";
        }
        return "redirect:/";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/registrar")
    public ModelAndView getRegisterPage(@RequestParam Optional<String> error) {
        System.out.println(error);
        return new ModelAndView("regex", "error", error);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/registrar")
    public String registrar(@RequestParam("user") String username, @RequestParam("pass") String password){
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuarioServicios.registrarUsuario(usuario);
        return "redirect:/";
    }
}
