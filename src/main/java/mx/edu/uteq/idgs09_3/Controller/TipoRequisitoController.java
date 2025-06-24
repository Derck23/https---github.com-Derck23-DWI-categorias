package mx.edu.uteq.idgs09_3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.edu.uteq.idgs09_3.model.entity.TipoRequisito;
import mx.edu.uteq.idgs09_3.service.TipoRequisitoService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipo-requisitos")
public class TipoRequisitoController {

    @Autowired
    private TipoRequisitoService tipoRequisitoService;

    @GetMapping
    public ResponseEntity<List<TipoRequisito>> getAll() {
        List<TipoRequisito> tipos = tipoRequisitoService.findAll();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRequisito> getById(@PathVariable Long id) {
        Optional<TipoRequisito> tipoOptional = tipoRequisitoService.findById(id);
        return tipoOptional.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoRequisito> create(@RequestBody TipoRequisito tipoRequisito) {
        TipoRequisito nuevoTipo = tipoRequisitoService.save(tipoRequisito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoRequisito> update(
            @PathVariable Long id, 
            @RequestBody TipoRequisito tipoRequisito) {
        if (!tipoRequisitoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoRequisito.setId(id);
        TipoRequisito tipoActualizado = tipoRequisitoService.save(tipoRequisito);
        return ResponseEntity.ok(tipoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!tipoRequisitoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        tipoRequisitoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}