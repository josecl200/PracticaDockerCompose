package edu.pucmm.josecl200.encuestacompose.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Usuario implements Serializable {
    @Id
    private String usuario;
    private String password;
    @ManyToMany
    private Set<Role> roles;
}
