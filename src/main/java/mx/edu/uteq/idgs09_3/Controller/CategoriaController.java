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
import mx.edu.uteq.idgs09_3.model.entity.Categorias;
import java.util.List;
import java.util.Optional;
import mx.edu.uteq.idgs09_3.Service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping()
    public List<Categorias> buscarTodos(@RequestParam boolean soloActivo) {
        return service.buscar(soloActivo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Categorias> buscarPorId(@PathVariable int id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<?> crear(@RequestBody Categorias c) {
        Categorias entity = service.crear(c);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("path")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody Categorias entity) {
        Optional<Categorias> optional = service.editar(id, entity);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
    }
    return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        if (service.borrar(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

    

}
