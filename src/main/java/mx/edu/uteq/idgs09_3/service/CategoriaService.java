package mx.edu.uteq.idgs09_3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.uteq.idgs09_3.model.entity.Categorias;
import mx.edu.uteq.idgs09_3.model.repository.CategoriaRepo;
import mx.edu.uteq.idgs09_3.model.repository.TipoRequisitoRepo;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepo repo;
    @Autowired
    private TipoRequisitoRepo trRepo;

    @Transactional(readOnly = true)
    public List<Categorias> buscar(boolean soloActivos) {
        if (soloActivos) {
            return repo.findAll().stream()
                    .filter(Categorias::isActivo)
                    .toList();
        }
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categorias> buscarPorId(int id) {
        return repo.findById(id);
    }

    @Transactional
    public Categorias crear(Categorias categoria) {
        return repo.save(categoria);
    }

    @Transactional
    public Optional <Categorias> editar(int id, Categorias categoria) {
        Optional<Categorias> opt = repo.findById(id);
        if (opt.isPresent()) {
            Categorias d = opt.get();
            d.setCategoria(categoria.getCategoria());
            d.setCategoria_ant(categoria.getCategoria_ant());
            d.setCategoria_fed(categoria.getCategoria_fed());
            d.setCategoria_est(categoria.getCategoria_est());
            d.setActivo(categoria.isActivo());
            return Optional.of(repo.save(d));
        }
        return opt;
    }

    @Transactional
    public boolean borrar(int id) {
        Optional<Categorias> opt = repo.findById(id);
        if (opt.isPresent()){
            repo.deleteById(id);
            return true;
        }
        return false;
    }

}
