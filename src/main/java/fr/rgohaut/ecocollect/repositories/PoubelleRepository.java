package fr.rgohaut.ecocollect.repositories;

import fr.rgohaut.ecocollect.models.Poubelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PoubelleRepository extends JpaRepository<Poubelle, Long>, JpaSpecificationExecutor<Poubelle> {
    List<Poubelle> findByRegionIgnoreCase(String region);
    List<Poubelle> findByDepartIgnoreCase(String departement);
    List<Poubelle> findByVilleIgnoreCase(String ville);
    List<Poubelle> findByType(String type);
}
