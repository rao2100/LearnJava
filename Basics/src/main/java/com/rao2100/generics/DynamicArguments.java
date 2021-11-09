package com.rao2100.generics;

import java.util.LinkedList;

public class DynamicArguments {

    public void printList() {
        String item1 = "item1";
        String item2 = "item2";
        String item3 = "item3";
        String[] items = {item1, item2, item3};

        printShoppingList(item1, item2);
        printShoppingList(item1, item2, item3);
        printShoppingList(items);

    }

//    private void printShoppingList(String item1, String item2) {
//        System.out.println("ShopList");
//        System.out.println(item1);
//        System.out.println(item2);
//    }

//    private void printShoppingList(String item1, String item2, String item3) {
//        System.out.println("ShopList");
//        System.out.println(item1);
//        System.out.println(item2);
//        System.out.println(item3);
//    }

//    private void printShoppingList(String[] items) {
//        System.out.println("ShopList");
//        for (String item : items) {
//            System.out.println(item);
//        }
//    }

    // this replaces all the above
    private void printShoppingList(String... items) {
        System.out.println("ShopList");
        for (String item : items) {
            System.out.println(item);
        }
    }



}
