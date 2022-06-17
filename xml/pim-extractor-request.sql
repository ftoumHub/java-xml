SELECT XMLElement("personneImpliquee",
    XMLFOREST(
        pi.NIDEPIM as "identifiant", pi.NIDEPIMPRI as "identifiantPersonnePrincipale",
        pi.CROLPIM as "codeRole",
        pi.CQALPIM as "codeTypeInterlocuteur",
        pi.NSOC as "numeroSocietaire", pi.CPER as "codePersonne", pi.TPIM as "codeType",
        pi.CSTAPIM as "codeStatut",
        pi.CSTEADV as "codeAssureur", pi.LREF as "reference", pi.LNOM as "nom",
        pi.TADRPOS as "codeTypeAdresse",
        pi.LNUMVOI as "numeroVoie", pi.CPOS as "codePostal", pi.LCOM as "commune",
        pi.NPAY as "codePays",
        pi.TRSPPIM as "codeTypeResponsabilite", pi.NPAY as "codePays"
    ),
    XMLElement("ListeLienSinistrePims",
        (
            SELECT XMLAGG(
                XMLELEMENT("lienSinistrePim",
                    XMLFOREST(lobjpim.NIDELINOBJPIM as "identifiant"),
                    XMLELEMENT("objetSinistre",
                        XMLFOREST(objsin.NIDEOBJSIN as "identifiant"),
                        XMLELEMENT("vehiculeSinistre",
                            XMLForest(
                                vehsin.nidevehsin as "identifiant",
                                vehsin.tideveh as "typeIdentificationVehicule",
                                vehsin.LIDEVEH as "immatriculation",
                                vehsin.CREMVEH as "remorqueVehicule",
                                vehsin.NREPVEH as "numeroRepertoireVehicule",
                                vehsin.CINFVOL as "infoVol",
                                vehsin.LMAR as "libelleMarque",
                                vehsin.CNATOBS as "natureObstacle",
                                vehsin.LDENVEHLNG as "denominationVehiculeLongue"
                            ),
                            XMLELEMENT("vehiculePersonne",
                                (
                                    XMLFOREST(
                                        vehper.nidevehper as "identifiant",
                                        vehper.LNOM as "nom",
                                        vehper.LPRN as "prenom",
                                        vehper.csex as "sexe",
                                        vehper.CCIRAGG as "circonstanceAggravante",
                                        vehper.TCDDEVE as "typeConducteur",
                                        vehper.NCDD as "numeroConducteur"
                                    )
                                )
                            )
                        ),
                        XMLElement("ListeDetailObjetSinistres",
                            (
                                SELECT XMLAGG(
                                    XMLELEMENT(
                                        "detailObjetSinistres",
                                        XMLFOREST(detobjsin.NIDEDETOBJSIN as "identifiant", detobjsin.czoncho as "zoneChoc")
                                    )
                                )
                                FROM LINOBJPIM lobjpim, OBJSIN objsin, DETOBJSIN detobjsin
                                WHERE lobjpim.nidepim = pi.NIDEPIM
                                  AND lobjpim.nideobjsin = objsin.nideobjsin
                                  AND objsin.nideobjsin = detobjsin.nideobjsin
                                  AND detobjsin.czoncho IS not null
                            )
                        )
                    )
                )
            )
            FROM LINOBJPIM lobjpim, OBJSIN objsin, VEHSIN vehsin, VEHPER vehper
            WHERE lobjpim.nidepim = pi.NIDEPIM
              AND lobjpim.nideobjsin = objsin.nideobjsin
              AND objsin.NIDEOBJSIN = vehsin.NIDEOBJSIN
              AND vehsin.nidevehsin = vehper.nidevehsin
            )
        )
    ) as "XML"
FROM PIM pi
WHERE nideevt = '100012709862'
  AND ceta = '01';