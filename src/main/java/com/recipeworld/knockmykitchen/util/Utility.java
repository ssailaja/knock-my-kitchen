package com.recipeworld.knockmykitchen.util;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static <T> List<T> iterableToCollection(Iterable<T> iterable) {
        List<T> collection = new ArrayList<>();
        iterable.forEach(collection::add);
        return collection;
    }


}
