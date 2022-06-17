package com.flux;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.UpperCamelCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonNaming(UpperCamelCaseStrategy.class)
public class Tarif {

    private final String periodeAnnuelle;
    private final String periodeProrata;
    private final String cotisationAnnuelleParticipant;
    private final String cotisationProrataParticipant;
    private final String cotisationAnnuelleBeneficiaire;
    private final String cotisationProrataBeneficiaire;
    private final String cotisationAnnuelle;
    private final String cotisationProrata;
}
