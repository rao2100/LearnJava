package com.rao2100.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenericsExample {

    public void simpleList() {

        // without generics
        List names = new ArrayList();
        names.add("kelly");
        String name = (String) names.get(0);
        System.out.println("Name: " + name);

        // with generics
        List<String> names2 = new ArrayList();
        names2.add("kelly");
        String name2 = names2.get(0);
        System.out.println("Name: " + name2);

    }

    static Character[] charArray = {'h', 'e', 'l', 'l', 'o'};
    static Integer[] intArray = {1, 2, 3, 4, 5};
    static Boolean[] boolArray = {true, false, true};

    public void genericMethod() {
        List<Character> charList = arrayToListObject(charArray, new ArrayList<>());
        List<Integer> intList = arrayToListObject(intArray, new ArrayList<>());
        List<Boolean> boolList = arrayToListObject(boolArray, new ArrayList<>());
        //no compile time error here,  only runtime
        List<String> newList = arrayToListObject(boolArray, new ArrayList<>());
        // run time error here, java.lang.ClassCastException: java.lang.Boolean cannot be cast to java.lang.String
//        System.out.println(newList.get(0));

        List<Character> charListG = arrayToListGeneric(charArray, new ArrayList<>());
        List<Integer> intListG = arrayToListGeneric(intArray, new ArrayList<>());
        List<Boolean> boolListG = arrayToListGeneric(boolArray, new ArrayList<>());
        // compile time error here
//        List<String> newListG = arrayToListGeneric(boolArray, new ArrayList<>());
    }

    public List arrayToListObject(Object[] array, List<Object> list ) {

        for (Object object :  array) {
            list.add(object);
        }
        return list;
    }

    public <T> List<T> arrayToListGeneric(T[] array, List<T> list ) {

        for (T object :  array) {
            list.add(object);
        }
        return list;
    }

}
