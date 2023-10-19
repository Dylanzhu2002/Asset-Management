package ui;


import model.ConsumptionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Accounting application
public class AccountingApp {
    private static final String JSON_STORE = "./data/myFile.json";
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private Scanner input;
    private   ConsumptionList clist;

    // EFFECTS: runs the Accounting application
    public AccountingApp() {
        input = new Scanner(System.in);
        clist = new ConsumptionList("Dylan's list");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // This code comes from the TellerApp class of the Teller project provided in edx/Phase 1.
    private void runApp() {
        boolean goOn = true;
        String letter;

        init();

        while (goOn) {
            homePage();
            letter = input.next();
            letter = letter.toLowerCase();

            if (letter.equals("q")) {
                goOn = false;
            } else {
                determineInput(letter);
            }
        }

        System.out.println("\nWelcome to come again! :)");
    }


    // MODIFIES: this
    // EFFECTS: initializes accounts consumption
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        //ConsumptionList clist = new ConsumptionList();
    }


    // EFFECTS: homePage of providing options to user
    // This code comes from the TellerApp class of the Teller project provided in edx/Phase 1.
    private void homePage() {
        System.out.println("\n\nSelect the option to execute:");
        System.out.println("\ti -> Input consumption/income");
        System.out.println("\tt -> Total consumption");
        System.out.println("\tp -> Past consumption");
        System.out.println("\ts -> save list");
        System.out.println("\tl -> load list");
        System.out.println("\tq -> Quit");
    }


    // MODIFIES: this
    // EFFECTS: determine what letter is typed in
    // This code comes from the TellerApp class of the Teller project provided in edx/Phase 1.
    private void determineInput(String letter) {
        switch (letter) {
            case "i":
                creatingNewCons();
                break;
            case "t":
                summation();
                break;
            case "p":
                outputList();
                break;
            case "s":
                saveConsumptionList();
                break;
            case "l":
                loadConsumptionList();
                break;
            default:
                System.out.println("Incorrect input, please try again :(");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: Create a new consumption and add it to Past consumption
    private void creatingNewCons() {
        System.out.print("Enter the date (mm/dd): ");
        String date = input.next();
        System.out.print("Enter the consumption type: ");
        String type = input.next();
        System.out.print("Enter amount of consumption: $");
        double amount = input.nextDouble();

        System.out.print("date: " + date + " type: " + type + " amount: " + amount + "$");

        clist.addCons(date, type, amount);
    }

    // MODIFIES: this
    // EFFECTS: Count the total amount of past consumption and output
    private void summation() {
        double sum = clist.consumptionSum();
        System.out.print("The total amount spent so far is: " + sum + "$");
    }

    // EFFECTS: Outputs all purchases on the past purchases list
    private void outputList() {
        for (int i = 0; i < clist.sizeCons();i++) {
            System.out.println("date: " + clist.getCons(i).getDate() + " | type: " + clist.getCons(i).getType()
                    + " | amount: " + clist.getCons(i).getConsBalance() + "$");
        }
    }

    // EFFECTS: saves the ConsumptionList to file
    private void saveConsumptionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(clist);
            jsonWriter.close();
            System.out.println("saved " + clist.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file:" + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ConsumptionList from file
    private void loadConsumptionList() {
        try {
            clist = jsonReader.read();
            System.out.println("Loaded" + clist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

