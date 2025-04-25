package fr.rgohaut.ecocollect.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CollecteDto {
    @NotNull
    private LocalDateTime heureDepart;

    private LocalDateTime heureFin;

    @NotBlank
    private String chauffeur;

    @NotBlank
    private String villeCible;

    @NotBlank
    private String typePoubelle; // 'papier', 'plastic', 'poubellePublic', 'verre'
}