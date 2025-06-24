/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package mx.edu.uteq.idgs09_3.entity;


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
    private String nombre;
    private String tipoR;
    private boolean categoria;
    
    
    /*@ManyToOne
    @JoinColumn(name = "id_division")
    @JsonIgnoreProperties("programaEducativos")*/
    //private Division division;

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "programa_educativo_id")
    private List<ProgramaEducativoProfesor> peProfesores;

    @Transient
    private List<Profesor> profesores;

    public void addProgramaEducativoProfesor(ProgramaEducativoProfesor peProf) {
        peProfesores.add(peProf);
    }

    public void removeProgramaEducativoProfesor(ProgramaEducativoProfesor peProf) {
        peProfesores.remove(peProf);
    }    */

}

