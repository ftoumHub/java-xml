package fr.maif.api.iard.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LienSinistrePim {
    private Long identifiant;

    private ObjetSinistre objetSinistre;

    private PersonnesImpliquees personnesImpliquees;

}
