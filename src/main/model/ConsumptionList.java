package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of all Consumption
public class ConsumptionList implements Writable {
    private String name;
    private final List<Consumption> list;

    /*
     * MODIFIES: this
     * EFFECTS: Create an empty list of Consumption
     */
    public ConsumptionList(String name) {
        this.name = name;
        this.list = new ArrayList<>();

    }

    public  String getName() {
        return  name;
    }



    /*
     * MODIFIES: this
     * EFFECTS: Fetch all balances of Consumption in the list and sum them
     */
    public double consumptionSum() {

        EventLog.getInstance().logEvent(new Event("get total balance."));
        return list.stream().map(Consumption::getConsBalance).reduce(0.00, Double::sum);

    }


    /*
     * MODIFIES: this
     * EFFECTS: adds a new Consumption to the collection of consumptions to be completed
     */
    public boolean addCons(String consDate, String constype, double consbalance) {
        this.list.add(new Consumption(consDate, constype, consbalance));
        EventLog.getInstance().logEvent(new Event("addcons: "
                + "date: " + consDate + " type: " + constype + " amount: " + consbalance + "$"));
        return true;
    }

    /*
     * EFFECTS: get the number of Consumptions in the list of Consumptions
     */
    public int sizeCons() {
        return this.list.size();
    }

    // EFFECTS: get the Consumption in the list of Consumptions at n
    public Consumption getCons(int n) {
        return this.list.get(n);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new  JSONObject();
        json.put("name", name);
        json.put("list", listToJson());
        return json;
    }

    // EFFECTS: returns list in this ConsumptionList as a JSON array
    private JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Consumption c : list) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
