package com.example.menubar;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VolumeWindow {
    @FXML
    private Label selection;

    @FXML
    protected void onHelloButtonClick() {
        selection.setText("Welcome to JavaFX Application!");
    }
}