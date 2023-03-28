package com.eugene.home.clinch;

import com.eugene.home.clinch.utils.CurrentIndex;
import com.eugene.home.clinch.utils.FormulaResolver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {

    private Map<String, Cell> tableMap;

    public Table() {
        this.tableMap = new HashMap<>();
    }

    public void setCell(String cellCoordinates, String value) {
        cellCoordinates = cellCoordinates.toUpperCase();
        if(tableMap.containsKey(cellCoordinates)) {
            tableMap.get(cellCoordinates).setValue(value);
        } else {
            Cell cell = new Cell(cellCoordinates.charAt(0), cellCoordinates.charAt(1), value);
            tableMap.put(cellCoordinates, cell);
        }
    }

    public Double getCellValue(String cellCoordinates) {
        return getCellValue(cellCoordinates.toUpperCase(), new CurrentIndex(0));
    }

    public Double getCellValue(String cellCoordinates, CurrentIndex currentIndex) {
        if(tableMap.containsKey(cellCoordinates)) {
            String v = tableMap.get(cellCoordinates).getValue();
            if(isNumeric(v)) {
                return Double.parseDouble(v);
            } else {
                List<String> expression = FormulaResolver.resolveFormula(v);
                Double res = isNumeric(expression.get(0)) ? Double.valueOf(expression.get(0)) : getCellValue(expression.get(0), currentIndex);
                while (currentIndex.getIndex() < expression.size() -1) {
                    res = calcResult(res, expression, currentIndex);
                }
                return res;
            }
        } else {
            return null;
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private Double calcResult(Double result, List<String> expression, CurrentIndex currentIndex) {
        String opeator = expression.get(currentIndex.getIndex() + 1);
        String next = expression.get(currentIndex.getIndex() + 2);
        result = actionValue(result, opeator, next, currentIndex);
        currentIndex.setIndex(currentIndex.getIndex() + 2);
        return result;
    }

    private Double actionValue(Double res, String operator, String next, CurrentIndex currentIndex) {
        Double nextVal;
        if(isNumeric(next)) {
            nextVal = Double.parseDouble(next);
        } else {
            nextVal = getCellValue(next, currentIndex);
        }
        switch(operator) {
            case "+" :
               res = res.doubleValue() + nextVal.doubleValue();
               break;
            case "-" :
                res = res.doubleValue() - nextVal.doubleValue();
                break;
            case "*" :
                res = res.doubleValue() * nextVal.doubleValue();
                break;
            case "/":
                res = res.doubleValue() / nextVal.doubleValue();
                break;
        }
        return res;
    }
}
