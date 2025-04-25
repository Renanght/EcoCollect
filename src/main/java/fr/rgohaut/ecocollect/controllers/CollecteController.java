package fr.rgohaut.ecocollect.controllers;

import fr.rgohaut.ecocollect.dtos.CollecteDto;
import fr.rgohaut.ecocollect.models.Collecte;
import fr.rgohaut.ecocollect.services.CollecteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collectes")
@RequiredArgsConstructor
public class CollecteController {
    private final CollecteService collecteService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Collecte> createCollecte(@Valid @RequestBody CollecteDto collecteDto) {
        Collecte createdCollecte = collecteService.createCollecte(collecteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCollecte);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Collecte>> getAllCollectes() {
        return ResponseEntity.ok(collecteService.getAllCollectes());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Collecte> getCollecteById(@PathVariable Long id) {
        return ResponseEntity.ok(collecteService.getCollecteById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCollecte(@PathVariable Long id) {
        collecteService.deleteCollecte(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-type")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Collecte>> getCollectesByType(@RequestParam String type) {
        return ResponseEntity.ok(collecteService.getCollectesByType(type));
    }
}