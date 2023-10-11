package com.example.menu.Models;

import com.example.menu.Parsers.JsonParser;


import java.util.ArrayList;
import java.util.List;

//@XmlRootElement(name = "animals")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Orders {
//    @XmlElement(name = "order")
    private List<Order> orders = new ArrayList<>();
    private transient List <JsonParser> parsers = new ArrayList<>();
    public void subscribe(JsonParser parser){
        parsers.add(parser);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void add(Order order){
        orders.add(order);
        for (JsonParser parser:
                parsers) {
            parser.jaxbObjectToXML(this);
        }
    }
}
