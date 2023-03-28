package com.eugene.home.clinch;

public class Cell {

    private char column;
    private int line;

    private String value;

    public Cell() {
    }

    public Cell(char column, int line, String value) {
        this.column = column;
        this.line = line;
        this.value = value;
    }

    public char getColumn() {
        return column;
    }

    public void setColumn(char column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
