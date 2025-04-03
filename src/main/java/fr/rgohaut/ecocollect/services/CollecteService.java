package fr.rgohaut.ecocollect.services;

import fr.rgohaut.ecocollect.dtos.CollecteDto;
import fr.rgohaut.ecocollect.models.Collecte;
import fr.rgohaut.ecocollect.models.Poubelle;
import fr.rgohaut.ecocollect.repositories.CollecteRepository;
import fr.rgohaut.ecocollect.repositories.PoubelleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollecteService {
    private final CollecteRepository collecteRepository;
    private final PoubelleRepository poubelleRepository;

    public Collecte scheduleCollecte(CollecteDto collecteDto) {
        Poubelle poubelle = poubelleRepository.findById(collecteDto.getPoubelleId())
                .orElseThrow(() -> new RuntimeException("Poubelle non trouvée"));

        Collecte collecte = Collecte.builder()
                .dateCollecte(collecteDto.getDateCollecte())
                .agent(collecteDto.getAgent())
                .poubelle(poubelle)
                .build();

        return collecteRepository.save(collecte);
    }
}
