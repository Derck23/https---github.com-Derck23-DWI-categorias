package mx.edu.uteq.idgs09_3.service;

import java.util.List;
import java.util.Optional;
import mx.edu.uteq.idgs09_3.model.entity.TipoRequisito;
import mx.edu.uteq.idgs09_3.model.repository.TipoRequisitoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoRequisitoService {

    @Autowired
    private TipoRequisitoRepo tipoRequisitoRepo;

    public List<TipoRequisito> findAll() {
        return tipoRequisitoRepo.findAll();
    }

    public Optional<TipoRequisito> findById(Long id) {
        return tipoRequisitoRepo.findById(id);
    }

    public TipoRequisito save(TipoRequisito tipoRequisito) {
        return tipoRequisitoRepo.save(tipoRequisito);
    }

    public void deleteById(Long id) {
        tipoRequisitoRepo.deleteById(id);
    }
}
