package com.rao2100.abstraction;

public class TestAbstraction {


    public void runTest(){

        BaseTask task = new RunTask();
        task.start();
        task = new JogTask();
        task.start();
        task = new SprintTask();
        task.start();

    }    
    
}