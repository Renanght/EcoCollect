package fr.rgohaut.ecocollect.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private LocalDateTime dateCollecte;
    private String agent;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "poubelle_id")
    private Poubelle poubelle;
}
