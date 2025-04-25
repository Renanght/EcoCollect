package fr.rgohaut.ecocollect.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Collecte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime heureDepart;
    private LocalDateTime heureFin;
    private String chauffeur;
    private String villeCible;
    private String statut; // "planifiée", "en cours", "terminée", "annulée"

    @ManyToOne
    @JoinColumn(name = "poubelle_id")
    private Poubelle poubelle;

    // Ou si vous voulez collecter plusieurs poubelles du même type
    @Transient // Ne sera pas persisté en base
    private String typePoubelleCible;
}