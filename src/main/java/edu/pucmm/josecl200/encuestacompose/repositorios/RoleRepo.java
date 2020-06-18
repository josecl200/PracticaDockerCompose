package edu.pucmm.josecl200.encuestacompose.repositorios;

import edu.pucmm.josecl200.encuestacompose.entidades.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
}
