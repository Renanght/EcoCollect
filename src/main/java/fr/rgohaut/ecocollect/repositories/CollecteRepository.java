package fr.rgohaut.ecocollect.repositories;

import fr.rgohaut.ecocollect.models.Collecte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollecteRepository extends JpaRepository<Collecte, Long> {
    @Query("SELECT c FROM Collecte c JOIN c.poubelle p WHERE p.type = :type")
    List<Collecte> findByTypePoubelleCible(@Param("type") String type);
}