package org.turkey.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreatePurchaseOrderController {
    @FXML private TextField quantityField_1, quantityField_2, quantityField_3;
    @FXML private TextField priceField_1, priceField_2, priceField_3;

    @FXML public void initialize() {
        quantityField_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityField_1.setText(oldValue);
                }
            }
        });
        quantityField_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityField_2.setText(oldValue);
                }
            }
        });
        quantityField_3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,3}?")) {
                    quantityField_3.setText(oldValue);
                }
            }
        });
        priceField_1.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField_1.setText(oldValue);
                }
            }
        });
        priceField_2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField_2.setText(oldValue);
                }
            }
        });
        priceField_3.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,2})?")) {
                    priceField_3.setText(oldValue);
                }
            }
        });
    }
}
