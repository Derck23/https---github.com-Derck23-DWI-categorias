/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.edu.uteq.idgs09_3.model.entity;

import jakarta.persistence.Transient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author erami
 */
@Entity
@Data

public class TipoRequisitos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String requisito;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    @JsonIgnoreProperties("tipoRequisitos")
    private Categorias categoria;


    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    @Transient
    public int getCategoriaId() {
        return categoria != null ? categoria.getId() : 0;
    }

}
