package fr.rgohaut.ecocollect.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poubelle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String etat;
    private String type;
    private double pourcentage;
    private String emplacement;

    private String pays;
    private String region;
    private String depart;
    private String ville;

    @OneToMany(mappedBy = "poubelle", cascade = jakarta.persistence.CascadeType.ALL)
    private List<Collecte> collectes = new ArrayList<>();
}