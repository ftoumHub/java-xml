package com.stackabuse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Main class to house the functions to serialize and deserialize our Java
 * objects.
 */
public class App {

    /**
     * This function serializes the Java object into XML and writes it into an XML
     * file.
     */
    public static void serializeToXML() {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            List<String> otherPhones = Arrays.asList("OnePlus 6T", "OnePlus 5T", "OnePlus 5");
            Manufacturer manufacturer = new Manufacturer("OnePlus", "China", otherPhones);
            // serialize our Object into XML string
            String xmlString = xmlMapper
                    .writeValueAsString(new PhoneDetails("OnePlus", "6.4", "6/64 GB", manufacturer));
            // write to the console
            System.out.println(xmlString);

            // write XML string to file
            File xmlOutput = new File("serialized.xml");
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

            // read file and put contents into the string
            String readContent = new String(Files.readAllBytes(Paths.get("to_deserialize.xml")));

            // deserialize from the XML into a PhoneDetails object
            PhoneDetails deserializedData = xmlMapper.readValue(readContent, PhoneDetails.class);

            // Print object details
            System.out.println("Deserialized data: ");
            System.out.println("\tName: " + deserializedData.getName());
            System.out.println("\tMemory: " + deserializedData.getMemory());
            System.out.println("\tDisplay Size: " + deserializedData.getDisplaySize());
            System.out.println("\tManufacturer Name: " + deserializedData.getManufacturer().getName());
            System.out.println("\tManufacturer Country: " + deserializedData.getManufacturer().getCountry());
            System.out.println(
                    "\tManufacturer Other Phones: " + deserializedData.getManufacturer().getPhone().toString());

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
