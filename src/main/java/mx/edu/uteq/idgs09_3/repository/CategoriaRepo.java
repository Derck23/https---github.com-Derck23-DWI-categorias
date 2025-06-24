package mx.edu.uteq.idgs03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.uteq.idgs03.model.Categoria;

public interface CategoriaRepo  extends JpaRepository<Categoria, Integer> {
    // No additional methods are needed as JpaRepository provides basic CRUD operations
    
}
