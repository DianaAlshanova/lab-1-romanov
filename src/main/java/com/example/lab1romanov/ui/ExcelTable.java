package com.example.lab1romanov.ui;

import com.example.lab1romanov.presentation.ExcelTableViewModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ExcelTable {

    private final HBox root;
    private final VBox tableView;
    private final ExcelTableViewModel viewModel;
    private TextField[][] table;

    public ExcelTable(int size) {
        viewModel = new ExcelTableViewModel(size);
        root = new HBox();
        tableView = new VBox();
        updateTable(size);
        setListeners();
        setObservers();
    }

    private void updateTable(int size) {
        table = new TextField[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = new TextField();
            }
        }
        setTableToNode();
    }

    private void setListeners() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                final int firstIndex = i;
                final int secondIndex = j;
                table[i][j].textProperty().addListener((observableValue, oldValue, newValue) -> {
                    viewModel.updateTableValue(newValue, firstIndex, secondIndex);
                });
            }
        }
    }

    private void setObservers() {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                final int firstIndex = i;
                final int secondIndex = j;
                viewModel.tableValue[i][j].addListener((observableValue, s, t1) -> {
                    try {
                        table[firstIndex][secondIndex].setText(t1);
                    } catch (Exception ignored) {  }
                });
            }
        }
    }

    private void setTableToNode() {
        HBox[] row = new HBox[table.length + 1];
        row[0] = new HBox(getHorizontalIndex());
        for (int i = 1; i < table.length + 1; i++) {
            row[i] = new HBox(table[i - 1]);
        }
        tableView.getChildren().addAll(row);
    }

    private TextField getEmptyUnactiveTextField() {
        TextField emptyField = new TextField();
        emptyField.setEditable(false);
        emptyField.setFocusTraversable(false);
        return emptyField;
    }

    private TextField[] getHorizontalIndex() {
        TextField[] horizontalIndex = new TextField[table.length];
        char ch = 'A';
        for (int i = 0; i < table.length; i++) {
            horizontalIndex[i] = new TextField(Character.toString(ch));
            horizontalIndex[i].setEditable(false);
            horizontalIndex[i].setFocusTraversable(false);
            ch++;
        }
        return horizontalIndex;
    }

    private TextField[] getVerticalIndex() {
        TextField[] verticalIndex = new TextField[table.length + 1];
        verticalIndex[0] = getEmptyUnactiveTextField();
        for (int i = 1; i < table.length + 1; i++) {
            verticalIndex[i] = new TextField(Integer.toString(i));
            verticalIndex[i].setEditable(false);
            verticalIndex[i].setFocusTraversable(false);
        }
        return verticalIndex;
    }

    public HBox getRoot() {
        VBox commandNumbersColumn = new VBox();
        commandNumbersColumn.getChildren().addAll(getVerticalIndex());
        root.getChildren().addAll(commandNumbersColumn, tableView);
        return root;
    }
}
