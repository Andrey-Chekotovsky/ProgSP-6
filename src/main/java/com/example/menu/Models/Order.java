package com.example.menu.Models;

//import javax.xml.bind.annotation.XmlAccessType;
//import javax.xml.bind.annotation.XmlAccessorType;


import java.util.List;


//@XmlRootElement(name = "animal")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    List<String> order;

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }
}
