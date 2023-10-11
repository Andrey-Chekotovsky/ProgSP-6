package com.example.menu.Parsers;

import com.example.menu.Models.Orders;
import com.fasterxml.jackson.databind.ObjectMapper;

//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.JAXBException;
//import javax.xml.bind.Marshaller;
//import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {

    static String filePath;
    private ObjectMapper objectMapper = new ObjectMapper();
    public JsonParser(String file)  {
        this.filePath = file;
    }
    public void jaxbObjectToXML(Orders orders)  {
        try(FileWriter file = new FileWriter(filePath))
        {
            String json = objectMapper.writeValueAsString(orders);
            file.write(json);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Orders jaxbXmlFileToObject() throws IOException {

        try(FileReader file = new FileReader(filePath)) {
            String str = new String(Files.readAllBytes(Paths.get(filePath)));
            Orders orders = objectMapper.readValue(str, Orders.class);
            return orders;
        } catch (IOException e){
            throw e;
        }
    }
}
