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
public class Besoin {

    private final String nomBesoin;
    private final Integer niveauBesoin;
    private final Integer ordreAffichage;
    private final String libelleLigne1;
    private final String libelleLigne2;
    private final String libelleLigne3;
    private final String libelleLigne4;
}
