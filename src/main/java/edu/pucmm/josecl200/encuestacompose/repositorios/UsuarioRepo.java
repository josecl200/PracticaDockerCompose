package edu.pucmm.josecl200.encuestacompose.repositorios;


import edu.pucmm.josecl200.encuestacompose.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario,String> {
    Usuario findByUsername(String username);
}
