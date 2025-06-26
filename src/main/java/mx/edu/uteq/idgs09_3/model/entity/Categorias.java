package mx.edu.uteq.idgs09_3.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;


@Entity
@Data
public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String categoria;
    private String categoria_ant;
    private String categoria_fed;   
    private String categoria_est;
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")

    //@JsonIgnore
    private List<TipoRequisitos> tipoRequisitos;
    
}

