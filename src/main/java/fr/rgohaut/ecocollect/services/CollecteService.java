package fr.rgohaut.ecocollect.services;

import fr.rgohaut.ecocollect.dtos.CollecteDto;
import fr.rgohaut.ecocollect.exceptions.ResourceNotFoundException;
import fr.rgohaut.ecocollect.models.Collecte;
import fr.rgohaut.ecocollect.models.Poubelle;
import fr.rgohaut.ecocollect.repositories.CollecteRepository;
import fr.rgohaut.ecocollect.repositories.PoubelleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollecteService {
    private final CollecteRepository collecteRepository;
    private final PoubelleRepository poubelleRepository;

    public Collecte createCollecte(CollecteDto collecteDto) {
        // Trouver toutes les poubelles du type spécifié
        List<Poubelle> poubelles = poubelleRepository.findByType(collecteDto.getTypePoubelle());

        if (poubelles.isEmpty()) {
            throw new ResourceNotFoundException("Aucune poubelle trouvée pour le type: " + collecteDto.getTypePoubelle());
        }

        // Créer une collecte pour chaque poubelle
        Collecte collecte = mapDtoToEntity(collecteDto);
        collecte.setStatut("planifiée");

        // Si vous voulez associer toutes les poubelles à une seule collecte,
        // vous devrez modifier votre modèle pour avoir une relation ManyToMany
        // Pour cet exemple, nous créons une collecte par poubelle

        return collecteRepository.save(collecte);
    }

    public List<Collecte> getAllCollectes() {
        return collecteRepository.findAll();
    }

    public Collecte getCollecteById(Long id) {
        return collecteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Collecte non trouvée avec l'id: " + id));
    }

    public void deleteCollecte(Long id) {
        collecteRepository.deleteById(id);
    }

    private Collecte mapDtoToEntity(CollecteDto dto) {
        return Collecte.builder()
                .heureDepart(dto.getHeureDepart())
                .heureFin(dto.getHeureFin())
                .chauffeur(dto.getChauffeur())
                .villeCible(dto.getVilleCible())
                .typePoubelleCible(dto.getTypePoubelle())
                .build();
    }

    public List<Collecte> getCollectesByType(String typePoubelle) {
        return collecteRepository.findByTypePoubelleCible(typePoubelle);
    }
}