package com.rao2100.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlasticTypeTest {

    @Test
    void getList() {

        System.out.println("TestEnums");
        System.out.println("PVC: " + PlasticType.PVC.getDesc());
        System.out.println("Type: " + PlasticType.valueOf("LDPE").getDesc());

        PlasticType ss = PlasticType.PET;

        System.out.println("ss: " + ss.getDesc());
        System.out.println("ss: " + ss.name());
        System.out.println("ss: " + ss.toString());

        if(ss.equals(PlasticType.PET)){
            System.out.println("this is PET");
        }

        System.out.println("ss size: " + ss.getList().size());
        System.out.println("ss size: " + PlasticType.getList().size());

        for (PlasticType arg : PlasticType.getList()) {
            System.out.println("arg: " + arg.getDesc());
        }
    }
}