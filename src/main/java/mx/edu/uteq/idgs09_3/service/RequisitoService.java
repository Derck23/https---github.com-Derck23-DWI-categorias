package mx.edu.uteq.idgs09_3.Service;

import org.springframework.beans.factory.annotation.Autowired;
//import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativo;
//import mx.edu.uteq.idgs09.idgs09_01.model.repository.ProgramaEducativoRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.idgs09_3.model.entity.Requisito;

import java.util.List;
import java.util.Optional;
//import mx.edu.uteq.idgs09.idgs09_01.clients.ProfesorClientRest;
//import mx.edu.uteq.idgs09.idgs09_01.model.dto.Profesor;
//import mx.edu.uteq.idgs09.idgs09_01.model.entity.ProgramaEducativoProfesor;


@Service
public class RequisitoService {
    /*@Autowired
    private ProgramaEducativoRepo repo;

    @Autowired
    private ProfesorClientRest client;*/


    @Transactional(readOnly = true)
    public List<Requisito> findAll() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ProgramaEducativo> findById(int id) {
        return repo.findById(id);
    }

    @Transactional
    public ProgramaEducativo save(ProgramaEducativo pe) {
        return repo.save(pe);
    }

    @Transactional
    public void deleteById(int id) {
        repo.deleteById(id);
    }

    @Transactional
    public Optional<Profesor> asignarProfesor(Profesor p, int peId) {
        Optional<ProgramaEducativo> opt = repo.findById(peId);
        if (opt.isPresent()) {
            ProgramaEducativo pe = opt.get();

            Profesor profesorMsvc = client.buscarPorId(p.getId());
            profesorMsvc.setClavepe(pe.getClave());
            client.editarProfesor(p.getId(), profesorMsvc);

            ProgramaEducativoProfesor progEduProf = new ProgramaEducativoProfesor();
            progEduProf.setProfesorId(profesorMsvc.getId());

            pe.addProgramaEducativoProfesor(progEduProf);
            repo.save(pe);
            return Optional.of(profesorMsvc);
        }
        return Optional.empty();
    }

    @Transactional(readOnly = true)
    public ProgramaEducativo buscarPorClve(String clave) {
        return repo.findByClave(clave);
    }

}
