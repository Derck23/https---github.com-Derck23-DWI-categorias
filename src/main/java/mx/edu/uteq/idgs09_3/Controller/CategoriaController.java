package mx.edu.uteq.idgs09_3.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import mx.edu.uteq.idgs03.model.Categoria;
import mx.edu.uteq.idgs03.repository.CategoriaRepo;
import java.util.List;
import java.util.Optional;
import mx.edu.uteq.idgs03.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepo service;

    @GetMapping()
    public List<Categoria> buscarTodos(@RequestParam boolean soloActivo) {
        return service.buscar(soloActivo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Categoria c) {
        Categoria entity = service.crear(c);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("path")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody Categoria entity) {
        Optional<Categoria> optional = service.editar(id, entity);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
    }
    return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (service.eliminar(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

    

}
