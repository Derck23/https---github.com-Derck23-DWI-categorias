package mx.edu.uteq.idgs09_3.Controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs09_3.Service.RequisitoService;
import mx.edu.uteq.idgs09_3.model.entity.Requisito;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/api/requisito")
public class RequistoController {

    @Autowired
    private RequisitoService serv;

    @GetMapping()
    public List<Requisito> buscartodos(@RequestParam boolean soloActivos) {
        return serv.buscar(soloActivos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Requisito> buscarPorId(@PathVariable int id) {
        return serv.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build()); 
    }
    /*@GetMapping("/{id}")
    public ResponseEntity<Division> buscarPorId(@PathVariable int id) {
        return repo.findById(id)
            .filter(Division::isActivo)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }*/


    /*@GetMapping("/{id}/programas")
    public ResponseEntity<List<ProgramaEducativo>> getProgramasPorDivision(@PathVariable int id) {
        //Optional<Division> division = repo.findById(id);
        /*if (division.isPresent()) {
            return ResponseEntity.ok(division.get().getProgramaEducativos());
        }*/
        /*return ResponseEntity.notFound().build();
    } */

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Requisito r) {
        Requisito entity = serv.crear(r);
        return ResponseEntity.ok(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody Requisito entity) {
        //Optional<Division> opt = repo.findById(id);
        /*if (opt.isPresent()) {
            Division d = opt.get();
            d.setClave(entity.getClave());
            d.setNombre(entity.getNombre());
            d.setActivo(entity.isActivo());
            return ResponseEntity.ok(repo.save(d));
        }*/
        Optional<Requisito> opt = serv.editar(id, entity);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
       /*  Optional<Division> opt = repo.findById(id);
        if (opt.isPresent()) {
            repo.deleteById(id);
            return ResponseEntity.ok().build();
        }*/
        if (serv.borrar(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

}