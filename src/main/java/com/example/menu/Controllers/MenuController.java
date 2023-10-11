package com.example.menu.Controllers;

import com.example.menu.Models.Order;
import com.example.menu.Models.Orders;
import com.example.menu.Parsers.JsonParser;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class MenuController implements Initializable {
    private List<String> order = new ArrayList<>();
    private List<String> dishes = new ArrayList<>(Arrays.asList("а", "б", "в", "г", "д", "е"));
    @FXML
    private Button plusButton;
    @FXML
    private Button minusButton;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label counterLabel;
    private int counter = 1;
    @FXML
    private Label dishLabel;
    @FXML
    private ListView<String> dishList;
    @FXML
    private ListView<String> orderList;
    private Orders orders = new Orders();
    private JsonParser jsonParser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        jsonParser = new JsonParser("src\\file.json");
        try {
            orders = jsonParser.jaxbXmlFileToObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        orders.subscribe(jsonParser);

        dishList.getItems().addAll(dishes);
        dishList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String chosen =  dishList.getSelectionModel().getSelectedItem();
                dishLabel.setText(chosen);
                counterLabel.setText(Integer.toString(counter));
                plusButton.setVisible(true);
                minusButton.setVisible(true);
                addButton.setVisible(true);
            }
        });
        orderList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                deleteButton.setVisible(true);
            }
        });
    }
    public void deleteDish(ActionEvent event) {
        order.remove(orderList.getSelectionModel().getSelectedItem());
        orderList.getItems().clear();
        orderList.getItems().addAll(order);
        deleteButton.setVisible(false);
    }
    public void addDish(ActionEvent event) {
        String ordered = dishList.getSelectionModel().getSelectedItem() + " X" + Integer.toString(counter);
        order.add(ordered);
        orderList.getItems().clear();
        orderList.getItems().addAll(order);
        counterLabel.setText("");
        dishLabel.setText("");
        plusButton.setVisible(false);
        minusButton.setVisible(false);
        addButton.setVisible(false);
        counter = 1;
    }
    public void plus(ActionEvent event) {
        counter++;
        counterLabel.setText(Integer.toString(counter));
    }
    public void minus(ActionEvent event) {
        if (counter == 1){
            return;
        }
        counter--;
        counterLabel.setText(Integer.toString(counter));
    }
    public void endOrder(ActionEvent event) {
        if (order.isEmpty()){
            return;
        }
        Order clientOrder = new Order();
        clientOrder.setOrder(order);
        orders.add(clientOrder);
        order = new ArrayList<>();
        orderList.getItems().clear();
    }
    public void quit(ActionEvent event) {
        exit();
    }
}
