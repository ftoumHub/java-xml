package fr.maif.api.iard.domain.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Cette objet peut contenir plusieurs type d'objets différents :
 * si codeRole = '01' : la référence sociétaire est renseignée et l'identifiant personne (Siebel) est null
 * si codeRole = '02' : la référence sociétaire et l'identifiant personne (Siebel) sont renseignés
 *
 *
 * si codeRole = null && codeTypeInterlocuteur = 2 et codeAssureur non null => Assureur
 * si codeRole = null && codeTypeInterlocuteur = 1  => Assureur
 */
@Getter
@Setter
@ToString
public class PersonnesImpliquees implements Serializable {

    private Long identifiant;
    private Long identifiantPersonnePrincipale;
    private Integer numero;
    private String codeRole;
    private Integer numeroSocietaire;
    private String codeTypeInterlocuteur;
    private String codeFonctionInterlocuteur;
    private Integer codeAssureur;
    private Integer numeroAuxiliaire;
    private String reference;
    private Integer codePersonne;
    private String identifiantPersonne;
    private Integer codeQualitePersonne;
    private String codeQualiteOccupant;
    private String codeType;
    private Integer codeStatut;
    private String codeTypeResponsabilite;
    private String codeTitre;
    private String nom;
    private String nomContact;
    private String nomComplementaire;
    private String prenom;
    private String numeroTelephone1;
    private String numeroTelephone2;
    private String numeroFax;
    private String email;
    private Integer codeTypeAdresse;
    private String numeroVoie;
    private String complementAdresse;
    private String immeuble;
    private String codePostal;
    private String codePostalEtranger;
    private String commune;
    private Integer codePays;
    private String codeBIC;
    private String codeIBAN;

    //@OneToMany(mappedBy = "personnesImpliquees", cascade = CascadeType.ALL)
    //@Where(clause = "CETA = '01'") // code état à EN_COURS
    //@JacksonXmlElementWrapper(localName = "ListeLienSinistrePims")
    private Set<LienSinistrePim> lienSinistrePims;
}
