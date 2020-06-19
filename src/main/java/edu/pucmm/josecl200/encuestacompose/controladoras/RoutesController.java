package edu.pucmm.josecl200.encuestacompose.controladoras;

import edu.pucmm.josecl200.encuestacompose.entidades.Role;
import edu.pucmm.josecl200.encuestacompose.entidades.Usuario;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RoutesController {
    @GetMapping("/")
    @Secured({"ROLE_ADMIN","ROLE_USER"})
    public String index(Model model, HttpSession session){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("usrName",auth.getPrincipal());
        for (GrantedAuthority role: auth.getAuthorities())
            if(role.getAuthority()=="ROLE_ADMIN")
                model.addAttribute("admin",true);
        return "encuesta";
        }


    @GetMapping("/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        System.out.println(error);
        return new ModelAndView("login", "error", error);
    }
}
