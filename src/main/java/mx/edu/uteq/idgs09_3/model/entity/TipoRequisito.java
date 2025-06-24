package mx.edu.uteq.idgs09_3.model.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
@Data

public class TipoRequisito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "tipoRequisito")
    private List<Requisito> requisitos;
}
