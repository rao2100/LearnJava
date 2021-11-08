package com.rao2100.nashorn;

import javax.script.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NashornCalculate {

    public void runScript() {

        try {
            
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            File scriptFile = new File("./src/main/resources/Calculate.js");
            Object result = engine.eval(new FileReader(scriptFile));
            // Bindings bind = engine.getBindings(ScriptContext.ENGINE_SCOPE);
            // bind.put("amount", 100);
            // bind.put("percentage", 20);
            int amount = 100;
            int discount = 20;
            Invocable invocable = (Invocable) engine;
            Object funcResult = invocable.invokeFunction("calculate", amount, discount);            
            System.out.println("Amount: " + amount + " after discount:" + discount + "% = " + funcResult);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}