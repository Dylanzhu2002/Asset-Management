package model;

import java.util.ArrayList;
import java.util.List;

public class Eventlist {

    List<Event> li = new ArrayList<Event>();

    public Eventlist() {
        EventLog el = EventLog.getInstance();

        for (Event next : el) {
            li.add(next);
        }


        for (int i = 0; i < li.size(); i++) {
            System.out.println(li.get(i).getDescription());
        }
    }
}
