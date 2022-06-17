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
public class Formule {

    private final String codeFormule;
    private final String libelleLigneFormule;
}
