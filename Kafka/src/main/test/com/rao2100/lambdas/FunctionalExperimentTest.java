package com.rao2100.lambdas;

import org.junit.jupiter.api.Test;

class FunctionalExperimentTest {

    @Test
    void funcInterface() {

        FunctionalExperiment fp = new FunctionalExperiment();
        fp.PrintMessage();

    }

    @Test
    void methodReference() {
        FunctionalExperiment fp = new FunctionalExperiment();
        fp.methodReference();
    }

    @Test
    void simpleLambda() {
        FunctionalExperiment fp = new FunctionalExperiment();
        fp.simpleLambda();
    }


}