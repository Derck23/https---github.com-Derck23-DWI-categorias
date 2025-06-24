/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.edu.uteq.idgs09.idgs09_01.model.entity;


import jakarta.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import mx.edu.uteq.idgs09.idgs09_01.model.dto.Profesor;
import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;




/**
 *
 * @author erami
 */
@Entity
@Data


public class Requisito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String clave;
    private String programaEducativo;
    private boolean activo;
    
    @ManyToOne
    @JoinColumn(name = "id_division")
    @JsonIgnoreProperties("programaEducativos")
    private Division division;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "programa_educativo_id")
    private List<ProgramaEducativoProfesor> peProfesores;

    @Transient
    private List<Profesor> profesores;

    public void addProgramaEducativoProfesor(ProgramaEducativoProfesor peProf) {
        peProfesores.add(peProf);
    }

    public void removeProgramaEducativoProfesor(ProgramaEducativoProfesor peProf) {
        peProfesores.remove(peProf);
    }    

}

