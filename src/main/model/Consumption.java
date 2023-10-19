package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents an Consumption having id, date, Consumption type and Spending (in dollars)
public class Consumption implements Writable {
    private static int nextConsId = 1; // The id of each purchase
    private final String date;               // Consumption date , in term of mouth/date (ex. 05/30)
    private final String type;               // Consumption type (Food, groceries, rent)
    private final double balance;            // Specific amount of consumption


    /*
     * EFFECTS: account id is a positive integer not assigned to any other account;
     *          balance can be  positive or negative. Positive numbers means incur
     *          expense, Negative numbers are income.
     */
    public Consumption(String consDate, String constype, double consbalance) {
        this.date = consDate;
        this.type = constype;
        this.balance = consbalance;

    }


    // EFFECTS: get the date of Consumption
    public String getDate() {
        return date;
    }

    // EFFECTS: get the type of Consumption
    public String getType() {
        return type;
    }

    // EFFECTS: get the balance of Consumption
    public double getConsBalance() {
        return balance;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("date",date);
        json.put("type",type);
        json.put("balance",balance);
        return json;
    }
}
