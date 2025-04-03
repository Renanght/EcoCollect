package fr.rgohaut.ecocollect.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollecteDto {
    private LocalDateTime dateCollecte;
    private String agent;
    private String notes;
    private Long poubelleId; // Pour lier à une poubelle existante
}
