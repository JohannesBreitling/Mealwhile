package de.johannesbreitling.mealwhile.controller.utils.converter;

import de.johannesbreitling.mealwhile.model.grocery.GroceryFlag;

import java.util.ArrayList;
import java.util.List;

public class GroceryConverter {

    public static List<GroceryFlag> convertFlagsFromArray(String[] flags) {
        List<GroceryFlag> result = new ArrayList<>();

        for (String flag : flags) {
            result.add(GroceryFlag.valueOf(flag));
        }

        return result;
    }



}
