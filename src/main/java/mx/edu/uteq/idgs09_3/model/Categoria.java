package mx.edu.uteq.idgs03.model;

import java.util.List;
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
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String anterior;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id")
    //private List<TipoRequisito> tipoRequisitos;
    
}
