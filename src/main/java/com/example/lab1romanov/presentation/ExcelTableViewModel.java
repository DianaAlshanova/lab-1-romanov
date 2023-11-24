package com.example.lab1romanov.presentation;

import javafx.beans.property.SimpleObjectProperty;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
        List<String> strings = new ArrayList<>(Arrays.stream(value.split("[-+*/()=]")).toList());
        for (int k = 0; k < strings.size(); k++) {
            if (strings.get(k).matches("[A-Z]+[0-9]+")) {
                String firstIndex = strings.get(k).replaceAll("\\D", "");
                String secondIndex = strings.get(k).replaceAll("\\d", "");
                strings.set(k,tableValue[Integer.parseInt(firstIndex) - 1][secondIndex.charAt(0) - 65].getValue());
            }
        }

        StringBuilder resString = new StringBuilder();
        resString.append(strings.get(0));
        strings.remove(0);
        for (int k = 0; k < value.length(); k++) {
            if (String.valueOf(value.charAt(k)).matches("[-+*/()]")) {
                resString.append(value.charAt(k)).append(strings.get(0));
                strings.remove(0);

            }
        }

        if (value.endsWith("=")) {
            System.out.println(resString);
            tableValue[i][j].setValue(calculateValue(resString.toString()));
        } else {
            tableValue[i][j].setValue(value);
        }
    }

    private String calculateValue(String value) {
        try {
            Expression expression = new ExpressionBuilder(value).build();
            return String.valueOf(expression.evaluate());
        } catch (Exception ignored) {  }
        return value;
    }
}
