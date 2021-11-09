package com.rao2100.lambdas;

import java.util.ArrayList;

public class FunctionalExperiment {

    public void PrintMessage() {

        // Anonymous inner class
        GreetMessage gm = new GreetMessage() {
            @Override
            public void greet(String name) {
                System.out.println("Hello " + name);
            }
        };
        gm.greet("Rao");

        // using lambdas
        GreetMessage gm2 = (name) -> {
            System.out.println("Hello " + name);
        };
        gm2.greet("Rao");


        MessagePrinter mp = () -> {
            System.out.println("This is a message");
        };
        mp.printMessage();
    }

    public void methodReference() {

        Square s = new Square(4);

        Shape shape  = (Square square) -> {
          return square.calculateArea();
        };
        System.out.println("Area: " + shape.getArea(s));

        Shape shape2  = Square::calculateArea;
        System.out.println("Area: " + shape2.getArea(s));

    }

    public void simpleLambda() {

        ArrayList<Book> books = Library.populateLibrary();
        books.stream().filter(book -> {
            return book.getAuthor().startsWith("J");
        }).filter(book -> {
            return book.getTitle().startsWith("E");
        }).forEach(System.out::println);

        books.parallelStream().filter(book -> {
            return book.getAuthor().startsWith("J");
        }).filter(book -> {
            return book.getTitle().startsWith("E");
        }).forEach(System.out::println);

    }



}
