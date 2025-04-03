package fr.rgohaut.ecocollect.services;

import fr.rgohaut.ecocollect.dtos.PoubelleDto;
import fr.rgohaut.ecocollect.exceptions.ResourceNotFoundException;
import fr.rgohaut.ecocollect.models.Poubelle;
import fr.rgohaut.ecocollect.repositories.PoubelleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PoubelleService {
    private final PoubelleRepository poubelleRepository;

    // CREATE
    public Poubelle createPoubelle(PoubelleDto poubelleDto) {
        Poubelle poubelle = mapDtoToEntity(poubelleDto);
        return poubelleRepository.save(poubelle);
    }

    // READ
    public List<Poubelle> getAllPoubelles() {
        return poubelleRepository.findAll();
    }

    public Poubelle getPoubelleById(Long id) {
        return poubelleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poubelle non trouvée avec l'id : " + id));
    }

    // UPDATE
    public Poubelle updatePoubelle(Long id, PoubelleDto poubelleDto) {
        Poubelle existingPoubelle = getPoubelleById(id);
        updateEntityFromDto(existingPoubelle, poubelleDto);
        return poubelleRepository.save(existingPoubelle);
    }

    // DELETE
    public void deletePoubelle(Long id) {
        Poubelle poubelle = getPoubelleById(id);
        poubelleRepository.delete(poubelle);
    }

    public List<Poubelle> searchByCriteria(
            String region,
            String departement,
            String ville) {

        Specification<Poubelle> spec = Specification.where(null);

        if (region != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("region")), region.toLowerCase()));
        }

        if (departement != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("depart")), departement.toLowerCase()));
        }

        if (ville != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(cb.lower(root.get("ville")), ville.toLowerCase()));
        }

        return poubelleRepository.findAll(spec);
    }

    // Mappers
    private Poubelle mapDtoToEntity(PoubelleDto dto) {
        return Poubelle.builder()
                .nom(dto.getNom())
                .etat(dto.getEtat())
                .type(dto.getType())
                .pourcentage(dto.getPourcentage())
                .emplacement(dto.getEmplacement())
                .pays(dto.getPays())
                .region(dto.getRegion())
                .depart(dto.getDepart())
                .ville(dto.getVille())
                .build();
    }

    private void updateEntityFromDto(Poubelle entity, PoubelleDto dto) {
        entity.setNom(dto.getNom());
        entity.setEtat(dto.getEtat());
        // ... autres champs
    }
}
