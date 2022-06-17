package fr.maif.api.iard.domain.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VehiculeSinistre implements Serializable {

    private static final long serialVersionUID = 2475875191375109480L;

    private Long identifiant;
    private ObjetSinistre objetSinistre;
    private Integer typeIdentificationVehicule;
    private String immatriculation;
    private Integer remorqueVehicule;
    private String numeroRepertoireVehicule;
    private String infoVol;
    private String numeroSerieVehicule;
    private String libelleMarque;
    private String denominationVehiculeLongue;
    private Integer natureObstacle;
    private VehiculePersonne vehiculePersonne;
}
