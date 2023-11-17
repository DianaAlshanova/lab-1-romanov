package com.example.lab1romanov.presentation;

import javafx.beans.property.SimpleObjectProperty;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class ExcelTableViewModel {

    private final String[][] table;
    public final SimpleObjectProperty<String>[][] tableValue;

    public ExcelTableViewModel(int size) {
        table = new String[size][size];
        tableValue = new SimpleObjectProperty[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tableValue[i][j] = new SimpleObjectProperty<>();
            }
        }
    }

    public void updateTableValue(String value, int i, int j) {
        tableValue[i][j].setValue(calculateValue(value));
    }

    private String calculateValue(String value) {
        try {
            Expression expression = new ExpressionBuilder(value).build();
            return String.valueOf(expression.evaluate());
        } catch (Exception ignored) {  }
        return value;
    }
}
