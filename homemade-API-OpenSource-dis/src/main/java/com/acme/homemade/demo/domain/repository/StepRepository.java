//Llamar al paquete de dominio del repositorio
package com.acme.homemade.demo.domain.repository;

//Importar el modelo de dominio del demo y los frameworks de spring
import com.acme.homemade.demo.domain.model.Step;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//Importar utilidad de java
//Llamar la interfaz pública de StepRepository


public interface StepRepository extends JpaRepository<Step, Long>{
     Page <Step> findById(Long Id, Pageable pageable);
     Page<Step> findByRecipeId(Long recipeId, Pageable pageable);
}
