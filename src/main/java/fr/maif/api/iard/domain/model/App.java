package fr.maif.api.iard.domain.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.vavr.API;
import io.vavr.Tuple3;
import io.vavr.control.Try;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static io.vavr.API.println;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.apache.commons.lang3.StringUtils.substringBetween;

public class App {

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

            String readContent = new String(Files.readAllBytes(Paths.get("xml/evt-M170623347J.xml")));

            final List<PersonnesImpliquees> pims = stream(readContent.split("</personneImpliquee>"))
                    .map(pi -> {
                        String pim = pi.concat("</personneImpliquee>");
                        String zoneChocs = substringBetween(pim, "<ListeDetailObjetSinistres>", "</ListeDetailObjetSinistres>");
                        String liensSinistrePim = substringBetween(pim, "<ListeLienSinistrePims>", "</ListeLienSinistrePims>");

                        return new Tuple3<>(pim, liensSinistrePim, zoneChocs);
                    })
                    .map(t -> {
                        final Set<LienSinistrePim> lienSinistrePims = parseLienSinistrePim(xmlMapper, t._2);
                        if (!(t._2).isEmpty()) {
                            lienSinistrePims.forEach(li -> li.getObjetSinistre()
                                    .setDetailObjetSinistres(parseDetailObjetSinistre(xmlMapper, t._3)));
                        }

                        PersonnesImpliquees pi = parsePersonneImpliquee(xmlMapper, t._1);
                        pi.setLienSinistrePims(lienSinistrePims);
                        return pi;
                    }).collect(toList());

            pims.forEach(API::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<LienSinistrePim> parseLienSinistrePim(XmlMapper xmlMapper, String liensSinistrePim) {
        return stream(liensSinistrePim.split("</ListeLienSinistrePims>"))
                .map(li -> {
                    final String completeZc = li.concat("</ListeLienSinistrePims>");
                    return Try.of(() -> xmlMapper.readValue(completeZc, LienSinistrePim.class)).getOrNull();
                })
                .collect(toSet());
    }

    private static Set<DetailObjetSinistre> parseDetailObjetSinistre(XmlMapper xmlMapper, String zoneChocs) {
        if (zoneChocs != null) {
            return stream(zoneChocs.split("</detailObjetSinistres>"))
                    .map(zc -> {
                        DetailObjetSinistre dos = null;
                        final String completeZc = zc.concat("</detailObjetSinistres>");
                        return Try.of(() -> xmlMapper.readValue(completeZc, DetailObjetSinistre.class)).getOrNull();
                    })
                    .collect(toSet());
        }
        return null;
    }

    private static PersonnesImpliquees parsePersonneImpliquee(XmlMapper xmlMapper, String completePim) {
        PersonnesImpliquees pi = null;
        try {
            pi = xmlMapper.readValue(completePim, PersonnesImpliquees.class);
        } catch (JsonProcessingException e) {
            println("Erreur de parsing : "+ e.getMessage());
        }
        return pi;
    }

    public static void main(String[] args) {
        println("\nDeserializing from XML...\n");
        deserializeFromXML();
    }
}
