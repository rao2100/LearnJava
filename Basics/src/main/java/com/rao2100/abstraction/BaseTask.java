package com.rao2100.abstraction;

public abstract class BaseTask extends AbstractTask {

    protected String doAction() {        
        return "Do nothing";
    }

    public void start(){
        System.out.println(doAction());
    }
    
}