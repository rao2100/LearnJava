package com.rao2100.generics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericsExampleTest {

    @Test
    void simpleList() {
        GenericsExample  ge = new GenericsExample();
        ge.simpleList();
    }

    @Test
    void genericMethod() {
        GenericsExample  ge = new GenericsExample();
        ge.genericMethod();
    }

    @Test
    void dynamicArgs() {
        DynamicArguments da = new DynamicArguments();
        da.printList();
    }

    @Test
    void substutionTest() {
        Substutition sub = new Substutition();
        sub.printSubstution();
    }
}