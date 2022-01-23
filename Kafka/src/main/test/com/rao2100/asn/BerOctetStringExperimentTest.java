package com.rao2100.asn;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

class BerOctetStringExperimentTest {

    @Test
    void hexConversion() {
        try {
            BerOctetStringExperiment.hexConversion();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}