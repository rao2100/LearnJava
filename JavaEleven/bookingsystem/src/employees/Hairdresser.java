package employees;

import java.util.ArrayList;

public class Hairdresser {

    private String name;
    private ArrayList<String> daysAvailable = new ArrayList<>();

    public Hairdresser(String name) {
        this.name = name;
    }

    public void setDaysAvailable(String...days) {
        for(String day : days) {
            daysAvailable.add(day);
        }
    }

    public void removeDay(String day) {
        if(daysAvailable.contains(day)) {
            daysAvailable.remove(day);
        }
    }

    public ArrayList<String> getDaysAvailable() {
        return daysAvailable;
    }

    public String getName() {
        return name;
    }

}
