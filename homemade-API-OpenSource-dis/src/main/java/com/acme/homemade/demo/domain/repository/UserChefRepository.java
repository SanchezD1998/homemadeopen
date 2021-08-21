//Llamar al dominio de repositorio
package com.acme.homemade.demo.domain.repository;

//Importar al modelo de dominio de UserChef
import com.acme.homemade.demo.domain.model.UserChef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interfaz pública
@Repository
public interface UserChefRepository extends JpaRepository<UserChef, Long> {

}

