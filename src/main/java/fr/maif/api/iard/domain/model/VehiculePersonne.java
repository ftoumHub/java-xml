package fr.maif.api.iard.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehiculePersonne implements Serializable {

    private static final long serialVersionUID = 3027385853294722355L;

    private Long identifiant;
    private String nom;
    private String prenom;
    private Integer sexe;
    private Integer numeroConducteur;
    private VehiculeSinistre vehiculeSinistre;
    private String circonstanceAggravante;
    private Integer typeConducteur;

}
