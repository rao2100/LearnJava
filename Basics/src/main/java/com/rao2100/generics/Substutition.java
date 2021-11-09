package com.rao2100.generics;

import java.util.ArrayList;
import java.util.List;

public class Substutition {

    public void printSubstution() {

        Building building = new Building();
        Office office = new Office();

        build(building);
        build(office);


        List<Building> buildings = new ArrayList<>();
        buildings.add(new Building());
        buildings.add(new Office());
        buildings.add(new Building());
        printBuildings(buildings);

        List<Office> offices = new ArrayList<>();
        offices.add(new Office());
        offices.add(new Office());
        // wont work, not compatible, can be solved using wildcards
//        printBuildings(offices);
        printBuildingsWild(offices);

        List<House> houses = new ArrayList<>();
        houses.add(new House());
        houses.add(new House());
        addHouse(houses);
        // wont work, not compatible, can be solved using wildcards
//        addHouse(buildings);
        // this works
        addHouseWild(buildings);


    }

    private void build(Building building) {
        System.out.println("Constructing : " + building.toString());
    }

    private void printBuildings(List<Building> buildings) {
        for (Building building : buildings) {
            System.out.println("Building type: " + building.toString());
        }
    }

    // wildcards used here to  pass in subclass
    private void printBuildingsWild(List<? extends Building> buildings) {
        for (Building building : buildings) {
            System.out.println("Building type: " + building.toString());
        }
    }

    private void addHouse(List<House> buildings) {
        buildings.add(new House());
    }

    // wildcards used here to  pass in superclass
    private void addHouseWild(List<? super House> buildings) {
        buildings.add(new House());
    }

}
