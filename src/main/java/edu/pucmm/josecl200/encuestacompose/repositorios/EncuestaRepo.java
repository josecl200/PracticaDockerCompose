package edu.pucmm.josecl200.encuestacompose.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncuestaRepo extends JpaRepository<EncuestaRepo, Long> {

}
