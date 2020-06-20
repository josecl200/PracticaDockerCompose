package edu.pucmm.josecl200.encuestacompose.servicios;

import edu.pucmm.josecl200.encuestacompose.entidades.Role;
import edu.pucmm.josecl200.encuestacompose.entidades.Usuario;
import edu.pucmm.josecl200.encuestacompose.repositorios.RoleRepo;
import edu.pucmm.josecl200.encuestacompose.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServicios implements UserDetailsService {
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private RoleRepo roleRepo;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public void crearUsuarioAdmin(){
        Role rolAdmin = new Role("ROLE_ADMIN");
        roleRepo.save(rolAdmin);
        Role rolCliente = new Role("ROLE_CLIENT");
        roleRepo.save(rolCliente);
        Usuario admin = new Usuario();
        admin.setUsername("root");
        admin.setPassword(bCryptPasswordEncoder.encode("toor"));
        admin.setRoles(new HashSet<>(Arrays.asList(new Role[]{rolAdmin})));
        usuarioRepo.save(admin);
    }

    public void registrarUsuario(Usuario usuario){
        String rawPass=usuario.getPassword();
        usuario.setPassword(bCryptPasswordEncoder.encode(rawPass));
        usuario.setRoles(new HashSet<>(Arrays.asList(new Role[]{roleRepo.findById("ROLE_CLIENT").get()})));
        usuarioRepo.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario user = usuarioRepo.findByUsername(usuario);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for "+ usuario + ".");
        }
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),true, true, true, true, grantedAuthorities);
    }
}
