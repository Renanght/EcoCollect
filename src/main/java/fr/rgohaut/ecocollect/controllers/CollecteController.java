package fr.rgohaut.ecocollect.controllers;

import fr.rgohaut.ecocollect.dtos.CollecteDto;
import fr.rgohaut.ecocollect.models.Collecte;
import fr.rgohaut.ecocollect.services.CollecteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collectes")
@RequiredArgsConstructor
public class CollecteController {
    private final CollecteService collecteService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Collecte> scheduleCollecte(@RequestBody CollecteDto collecteDto) {
        return ResponseEntity.ok(collecteService.scheduleCollecte(collecteDto));
    }
}
