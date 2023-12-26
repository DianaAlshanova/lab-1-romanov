package com.example.lab1romanov.presentation;

import com.example.lab1romanov.ui.ExcelTable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ExcelViewModel implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ScrollPane scrollPane;

    private final ExcelTable table = new ExcelTable(8);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.setContent(table.getRoot());
    }
}