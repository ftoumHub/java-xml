package fr.maif.api.iard.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ObjetSinistre implements Serializable {

    private static final long serialVersionUID = -2097175993600567805L;

    private Long identifiant;
    private Integer appartenanceAssure;
    private Integer assureConcurrence;
    private Long objet;
    private String reglementAutomatique;
    private Integer numeroObjetContrat;
    private String typeObjetSinistre;
    private VehiculeSinistre vehiculeSinistre;
    //private Set<DeclinaisonGarantie> declinaisonGaranties;
    //@JacksonXmlElementWrapper(localName = "ListeDetailObjetSinistres")
    private Set<DetailObjetSinistre> detailObjetSinistres;
    private Set<LienSinistrePim> lienSinistrePims;
}
