package com.eugene.home.clinch.utils;

import java.util.*;

public class FormulaResolver {

    public static List<String> resolveFormula(String formula) {
        List<String> expression = new ArrayList<>();
        String[] formulaSplitted = formula.split(" ");
        for(String s : formulaSplitted) {
            if(!s.equals("=")) {
                if (s.startsWith("=")) {
                    s = s.substring(1);
                }
                String[] sSpliited = splitByMathSymbols(s);
                for(String ssub : sSpliited) {
                    expression.add(ssub);
                }
            }
        }
        return expression;
    }

    private static String[] splitByMathSymbols(String s) {
        String rx = "((?=\\*)|(?<=\\*)|(?=/)|(?<=/)|(?=\\+)|(?<=\\+)|(?=-)|(?<=-))";
        return s.split(rx);
    }
}
