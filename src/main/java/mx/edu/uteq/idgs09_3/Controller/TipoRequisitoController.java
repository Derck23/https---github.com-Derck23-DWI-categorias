package mx.edu.uteq.idgs09_3.Controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mx.edu.uteq.idgs09_3.model.entity.Categorias;
import mx.edu.uteq.idgs09_3.model.entity.TipoRequisitos;
import mx.edu.uteq.idgs09_3.model.repository.CategoriaRepo;
import mx.edu.uteq.idgs09_3.service.TipoRequisitoService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requisitos")
public class TipoRequisitoController {

    @Autowired 
    private TipoRequisitoService serv;

    @Autowired
    private CategoriaRepo dRepo;

    @GetMapping
    public List<TipoRequisitos> buscarTodos(@RequestParam boolean soloActivos) {
        if (soloActivos) {
            return serv.findAll().stream().filter(TipoRequisitos::isActivo).toList();
        }
        return serv.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        return serv.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestParam int idCategioria, @RequestBody TipoRequisitos pe) {
        Optional<Categorias> opt = dRepo.findById(idCategioria);
        if (opt.isPresent()) {
            Categorias d = opt.get();
            pe.setCategoria(d);
            return ResponseEntity.ok(serv.save(pe));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody TipoRequisitos pe) {
        Optional<TipoRequisitos> opt = serv.findById(id);
        if (opt.isPresent()) {
            TipoRequisitos p = opt.get();
            Optional<Categorias> catOpt = dRepo.findById(pe.getCategoria().getId());
            if (catOpt.isPresent()) {
                p.setRequisito(pe.getRequisito());
                p.setActivo(pe.isActivo());
                p.setCategoria(catOpt.get());
                return ResponseEntity.ok(serv.save(p));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Division no encontrada");
            }
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable int id) {
        Optional<TipoRequisitos> opt = serv.findById(id);
        if (opt.isPresent()) {
            serv.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }
}