package com.eugene.home;

import com.eugene.home.clinch.Table;

public class App {

    public static void main(String[] args) {

        Table table = new Table();
        table.setCell("a1", "25");
        table.setCell("A2", "71");
        table.setCell("B1", "72");
        table.setCell("B2", "=A1+25-100*10 / 100 + B1");

        Double d = table.getCellValue("b2");

        System.out.println(d.intValue()); //67


    }
}
