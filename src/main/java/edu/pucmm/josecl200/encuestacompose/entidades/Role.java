package edu.pucmm.josecl200.encuestacompose.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Role implements Serializable {
    @Id
    private String role;
}