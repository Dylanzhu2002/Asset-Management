package ui;

import java.io.FileNotFoundException;
import model.Eventlist;

public class Main {
    public static void main(String[] args) {
        //new AccountingApp()
        Gui i = new  Gui();
        i.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                new Eventlist();
            }
        });





    }
}