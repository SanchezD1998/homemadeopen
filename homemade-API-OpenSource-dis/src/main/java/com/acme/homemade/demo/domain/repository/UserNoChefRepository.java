//Declarar paquete
package com.acme.homemade.demo.domain.repository;

//Declarar frameworks de spring y el domiminio de modelo de UserNoChef
import com.acme.homemade.demo.domain.model.UserNoChef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Interfaz pública extendida al repositorio de UserNoChef
@Repository
public interface UserNoChefRepository extends JpaRepository<UserNoChef, Long> {
}
