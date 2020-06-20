package edu.pucmm.josecl200.encuestacompose.repositorios;

import edu.pucmm.josecl200.encuestacompose.entidades.RespuestasEncuesta;
import edu.pucmm.josecl200.encuestacompose.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncuestaRepo extends JpaRepository<RespuestasEncuesta, Long> {
    public List<RespuestasEncuesta> findAllByUsuario(Usuario usuario);
}
