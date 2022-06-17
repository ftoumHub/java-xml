package com.flux;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

/**
 * Main class to house the functions to serialize and deserialize our Java
 * objects.
 */
public class App {

    /**
     * This function serializes the Java object into XML and writes it into an XML file.
     */
    public static void serializeToXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            final FluxMetier fluxMetier = new FluxMetier(
                    "1-6G2-2566",
                    "00",
                    "272",
                    new Devis("1-I1ZGV4F",
                            LocalDate.of(2020, 4, 1),
                            LocalDate.of(2020, 4, 11),
                            LocalDate.of(2020, 12, 31),
                            "4"),
                    new Adherent("MOMONT",
                            "Carine",
                            LocalDate.of(1968, 7, 21),
                            "1-6DH-19693",
                            "5498409D",
                            "Général"),
                    Arrays.asList(
                            new Beneficiaire("Malo DUPRIEZ", LocalDate.of(2003, 9, 15)),
                            new Beneficiaire("Hugo DUPRIEZ", LocalDate.of(2001, 7, 17)),
                            new Beneficiaire("Lilou DUPRIEZ", LocalDate.of(2011, 9, 12)),
                            new Beneficiaire("Paul DUPRIEZ", LocalDate.of(2014, 4, 14))),
                    Arrays.asList(
                            new Besoin("Soins Courants", 0, 1, null, null, null, null),
                            new Besoin("Hospitalisation", 2, 1, "Honoraires médicaux et chirurgicaux tarifs maîtrisés : 100 % de la BRSS", "Honoraires médicaux et chirurgicaux tarifs libres : 100 % de la BRSS", "Chambre particulière en établissement conventionné : 'De 35 à 50 €/nuitée", "Chambre particulière en établissement non conventionné : 38,50 €/nuitée"),
                            new Besoin("Dentaire", 3, 1, "Couronnes céramo-céramiques sur dents visibles : Jusqu’à 394 €/couronne", "Couronnes céramo-céramiques sur molaires : Jusqu’à 245 €/couronne", "Implants : 250 €/implant", "Orthodontie : 410 €/semestre"),
                            new Besoin("Optique", 3, 1, "Lunettes adultes avec des verres multifocaux (classe B) : Jusqu’à 300 € (125 €/verre + 50 € de monture)", "Lentilles remboursées par la Sécurité Sociale (par œil et année civile) : Remboursement Sécurité Sociale + forfait 92 €", "Chirurgie réfractive (par œil et année civile) : 100 €", null),
                            new Besoin("Audio", 1, 1, "Personne de - ou = 20 ans ou en cas de cécité : 1 400 €", "Personne de plus de 20 ans (hors cécité) : 350 €", null, null)),
                    new Formule("02", "Evolution"),
                    new Formule("01", "Découverte"),
                    new Tarif(
                            "Du 01/01/2020 au 31/12/2020",
                            "Du 01/04/2020 au 31/12/2020",
                            "33",
                            "24.75",
                            "59",
                            "44.25",
                            "92",
                            "69")
            );
            // serialize our Object into XML string
            String xmlString = xmlMapper.writeValueAsString(fluxMetier);
            // write to the console
            System.out.println(xmlString);

            // write XML string to file
            File xmlOutput = new File("xml/serialized-flux-metier.xml");
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function deserializes the contents of an XML file into a Java Object
     * matching our PhoneDetails class.
     */
    public static void deserializeFromXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.registerModule(new Jdk8Module());
            xmlMapper.registerModule(new JavaTimeModule());
            xmlMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
            xmlMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);

            String readContent = new String(Files.readAllBytes(Paths.get("xml/impression_implicite.xml")));

            // deserialize from the XML into a PhoneDetails object
            FluxMetier fluxMetier = xmlMapper.readValue(readContent, FluxMetier.class);

            // Print object details
            System.out.println("Deserialized data: " + fluxMetier);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Serializing to XML...\n");
        serializeToXML();
        
        System.out.println("\nDeserializing from XML...\n");
        deserializeFromXML();
    }

}
