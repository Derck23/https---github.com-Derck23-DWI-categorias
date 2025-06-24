package mx.edu.uteq.idgs09_3.Service;

import org.springframework.beans.factory.annotation.Autowired;
//import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
//import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.edu.uteq.idgs09_3.model.entity.Requisito;
import mx.edu.uteq.idgs09_3.model.entity.Requisito;
import mx.edu.uteq.idgs09_3.model.repository.RequisitoRepo;
import java.util.List;
import java.util.Optional;
//import mx.edu.uteq.idgs09.idgs09_01.clients.ProfesorClientRest;
//import mx.edu.uteq.idgs09.idgs09_01.model.dto.Profesor;
//import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;


@Service
public class RequisitoService {
    @Autowired
    private RequisitoRepo repo;

    @Transactional(readOnly = true)
    public List<Requisito> buscar(boolean soloActivo) {
        if (soloActivo) {
            return repo.findAll().stream().filter(Requisito::isActivo).toList();
        }
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Requisito> buscarPorId(int id) {
        return repo.findById(id);
    }

    @Transactional
    public Requisito crear(Requisito requisito) {
        return repo.save(requisito);
    }

    @Transactional
    public Optional<Requisito> editar(int id, Requisito requisito) {
        Optional<Requisito> opt = repo.findById(id);
        if (opt.isPresent()) {
            Requisito r = opt.get();
            r.setNombre(requisito.getNombre());
            r.setTipoR(requisito.getTipoR());
            r.setCategoria(requisito.getCategoria());
            r.setActivo(requisito.isActivo());
            return Optional.of(repo.save(r));
        }
        return opt;
    }

    @Transactional
    public boolean borrar(int id) {
        Optional<Requisito> opt = repo.findById(id);
        if (opt.isPresent()){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

}
