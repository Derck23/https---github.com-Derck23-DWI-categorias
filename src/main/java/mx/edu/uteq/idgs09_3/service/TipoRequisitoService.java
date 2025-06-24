package mx.edu.uteq.idgs09_3.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.idgs09_3.model.repository.TipoRequisitoRepo;
import mx.edu.uteq.idgs09_3.model.entity.TipoRequisitos; // Asegúrate que la ruta es correcta

@Service
public class TipoRequisitoService {
    @Autowired
    private TipoRequisitoRepo repo;

    @Transactional(readOnly = true)
    public List<TipoRequisitos> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<TipoRequisitos> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public TipoRequisitos save(TipoRequisitos pe) {
        return repo.save(pe);
    }

    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }

}