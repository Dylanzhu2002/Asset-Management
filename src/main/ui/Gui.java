package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.ConsumptionList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Gui extends JFrame {


    private static final String JSON_STORE = "./data/myFile.json";
    JPanel root = new JPanel();
    private JTextField amountText = new JTextField();
    private JTextField typeText = new JTextField();
    private JTextField dateText = new JTextField();
    ConsumptionList clist = new ConsumptionList("dylan's list");
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    JLabel l1 = new JLabel("date:");
    JLabel l2 = new JLabel("type:");
    JLabel l3 = new JLabel("amount:");
    JLabel l4 = new JLabel("0 $");
    JLabel l5 = new JLabel("");
    JButton b1 = new JButton("add");
    JButton b2 = new JButton("Total");
    JButton b3 = new JButton("save");
    JButton b4 = new JButton("lode");
    String[] titles = {"date","type","amount"};
    DefaultTableModel tableModel = new DefaultTableModel(titles, 0);
    JTable table = new JTable(tableModel);
    int cont = 0;


    private DefaultTableModel dtm = new DefaultTableModel();

    // EFFECTS: runs the Accounting application
    public Gui() {
        super("AccountingApp");
        this.setBounds(300,200,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        start();


    }

    // MODIFIES: this
    // EFFECTS: Setting the Format of pane
    public void start() {
        JScrollPane sp1 = new JScrollPane(setTable());
        final JSplitPane sp2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,setInfo(),sp1);
        sp2.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                sp2.setDividerLocation(0.4);

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        this.add(sp2);
    }

    // MODIFIES: this
    // EFFECTS: Setting the size and place of each element
    public JPanel setInfo() {

        root.setLayout(null);
        l1.setBounds(40,50,50,30);
        dateText.setBounds(25,80,60,30);
        l2.setBounds(140,50,50,30);
        typeText.setBounds(125,80,60,30);
        l3.setBounds(230,50,50,30);
        amountText.setBounds(225,80,60,30);
        l4.setBounds(240,130,50,30);
        l5.setBounds(10,230,1000,60);


        b1.setBounds(60,130,65,30);
        b1act();

        b2.setBounds(160,130,65,30);
        b2act();

        b3.setBounds(60,180,65,30);
        b3act();

        b4.setBounds(160,180,65,30);
        b4act();

        rootadd();
        return root;
    }

    // EFFECTS: add all elements in to the pane
    private void rootadd() {

        root.add(l1);
        root.add(dateText);
        root.add(l2);
        root.add(typeText);
        root.add(l3);
        root.add(amountText);
        root.add(b1);
        root.add(b2);
        root.add(b3);
        root.add(b4);
        root.add(l4);
        root.add(l5);

    }

    // MODIFIES: table
    // EFFECTS:  Add elements from the list to the table, or leave them unadded if they do not already exist
    private JTable setTable() {
        
       // for (int i = 0; i < clist.sizeCons();i++) {
       //     String date = clist.getCons(i).getDate();
       //     String type = clist.getCons(i).getType();
       //     Double amount = clist.getCons(i).getConsBalance();
       //
       //     Object[] data = {date, type, amount};
       //     tableModel.addRow(data);
       // }

        if (!(clist.sizeCons() == 0)) {
            String date = clist.getCons(cont).getDate();
            String type = clist.getCons(cont).getType();
            Double amount = clist.getCons(cont).getConsBalance();

            Object[] data = {date, type, amount};
            tableModel.addRow(data);
            this.cont++;
        }
        return table;
    }

    // MODIFIES: b1
    // EFFECTS:  Added the ability to read the data in the text box for b1
    private void b1act() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    clist.addCons(dateText.getText(), typeText.getText(), stod(amountText.getText()));
                    setTable();
                    amountText.setText("");
                    dateText.setText("");
                    typeText.setText("");
                    l5.setText("");
                }
            }
        });

    }

    // MODIFIES: b2
    // EFFECTS:  Added the ability to use consumptionSum()
    private void b2act() {
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b2) {
                    l4.setText(String.valueOf(clist.consumptionSum()) + " $");


                }
            }
        });
    }

    // MODIFIES: b3
    // EFFECTS:  Added the ability to save data
    private void b3act() {
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b3) {
                    saveConsumptionList();
                }
            }
        });
    }

    // MODIFIES: b3
    // EFFECTS:  Added the ability to load data
    private void b4act() {
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b4) {
                    loadConsumptionList();
                    for (int i = 0; i < clist.sizeCons();i++) {
                        String date = clist.getCons(i).getDate();
                        String type = clist.getCons(i).getType();
                        Double amount = clist.getCons(i).getConsBalance();

                        Object[] data = {date, type, amount};
                        tableModel.addRow(data);
                    }

                    cont = (clist.sizeCons());
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS:  switch string to double
    private double stod(String a) {
        double adouble = Double.valueOf(a);
        return adouble;
    }

    // MODIFIES: this
    // EFFECTS: saves the ConsumptionList to file
    private void saveConsumptionList() {
        try {
            jsonWriter.open();
            jsonWriter.write(clist);
            jsonWriter.close();
            l5.setText("saved " + clist.getName() + " to " + JSON_STORE + " successfully.");
        } catch (FileNotFoundException e) {
            l5.setText("Unable to write to file:" + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads ConsumptionList from file
    private void loadConsumptionList() {
        try {
            clist = jsonReader.read();
            l5.setText("Loaded " + clist.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            l5.setText("Unable to read from file: " + JSON_STORE);
        }


    }
}




















