package fr.rgohaut.ecocollect.controllers;

import fr.rgohaut.ecocollect.dtos.PoubelleDto;
import fr.rgohaut.ecocollect.models.Poubelle;
import fr.rgohaut.ecocollect.services.PoubelleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poubelles")
@RequiredArgsConstructor
public class PoubelleController {
    private final PoubelleService poubelleService;

    // CREATE
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Poubelle> createPoubelle(@Valid @RequestBody PoubelleDto poubelleDto) {
        Poubelle createdPoubelle = poubelleService.createPoubelle(poubelleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPoubelle);
    }

    // READ ALL
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Poubelle>> getAllPoubelles() {
        return ResponseEntity.ok(poubelleService.getAllPoubelles());
    }

    // READ BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Poubelle> getPoubelleById(@PathVariable Long id) {
        return ResponseEntity.ok(poubelleService.getPoubelleById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Poubelle> updatePoubelle(
            @PathVariable Long id,
            @Valid @RequestBody PoubelleDto poubelleDto) {
        return ResponseEntity.ok(poubelleService.updatePoubelle(id, poubelleDto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePoubelle(@PathVariable Long id) {
        poubelleService.deletePoubelle(id);
        return ResponseEntity.noContent().build();
    }

    // Recherche avec filtres combinés (tous optionnels)
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Poubelle>> searchPoubelles(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String departement,
            @RequestParam(required = false) String ville) {

        List<Poubelle> result = poubelleService.searchByCriteria(region, departement, ville);
        return ResponseEntity.ok(result);
    }
}