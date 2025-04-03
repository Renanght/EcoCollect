package fr.rgohaut.ecocollect.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoubelleDto {
    private String nom;
    private String etat;
    private String type;
    private double pourcentage;
    private String emplacement;
    private String pays;
    private String region;
    private String depart;
    private String ville;
}
