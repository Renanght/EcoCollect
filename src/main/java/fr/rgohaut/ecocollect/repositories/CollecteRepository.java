package fr.rgohaut.ecocollect.repositories;

import fr.rgohaut.ecocollect.models.Collecte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollecteRepository extends JpaRepository<Collecte, Long> {
    List<Collecte> findByPoubelleId(Long poubelleId);
}
