//package mx.edu.uteq.idgs03.model.repository;
package mx.edu.uteq.idgs09_3.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.uteq.idgs09_3.model.entity.Categorias;

public interface CategoriaRepo  extends JpaRepository<Categorias, Integer> {
    // No additional methods are needed as JpaRepository provides basic CRUD operations
    
}
