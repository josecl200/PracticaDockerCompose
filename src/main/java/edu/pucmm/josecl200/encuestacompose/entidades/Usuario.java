package edu.pucmm.josecl200.encuestacompose.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Usuario implements Serializable {
    @Id
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
