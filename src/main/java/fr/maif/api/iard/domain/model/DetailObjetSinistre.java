package fr.maif.api.iard.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(exclude = "objetSinistre")
@ToString
public class DetailObjetSinistre implements Serializable {

    private static final long serialVersionUID = -6454386114159172443L;

    private Long identifiant;
    private String zoneChoc;
    private Long objetNiveau1;
    private Long objetNiveau2;
    private Integer etatObjet;
    private String etat;
    private String createur;
    //private Set<DeclinaisonPrejudice> declinaisonPrejudices = new HashSet<>();
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private String modificateur;
    private ObjetSinistre objetSinistre;
}
