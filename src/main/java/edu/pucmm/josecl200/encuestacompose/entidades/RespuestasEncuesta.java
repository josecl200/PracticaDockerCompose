package edu.pucmm.josecl200.encuestacompose.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class RespuestasEncuesta implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Usuario usuario;
    private Integer charlas;
    private Integer expositores;
    private Integer instalaciones;
    private String resp4;
}
