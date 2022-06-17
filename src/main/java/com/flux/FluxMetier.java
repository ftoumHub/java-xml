package com.flux;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.UpperCamelCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonNaming(UpperCamelCaseStrategy.class)
public class FluxMetier {

    private final String identifiantPersonnePhysique;
    private final String codeContact;
    private final String codeSociete;
    private final Devis devis;
    private final Adherent adherent;
    @JacksonXmlElementWrapper(localName = "ListeBeneficiaire")
    private final List<Beneficiaire> beneficiaire;
    @JacksonXmlElementWrapper(localName = "ListeBesoins")
    private final List<Besoin> besoin;
    private final Formule formuleConseil;
    private final Formule formuleChoisie;
    private final Tarif tarif;
}
