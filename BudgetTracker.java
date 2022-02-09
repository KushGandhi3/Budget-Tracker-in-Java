import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
public abstract class BudgetTracker implements ActionListener
{
    //Setting up for GUI
    private static JLabel name;
    private static JLabel starting_amountlabel;
    private static JLabel monthly_paylabel;
    private static JLabel Monthlygroceriescost;
    private static JLabel Monthlyutilitiescost;
    private static JLabel Monthlyrentcost;
    private static JLabel Monthlysaved;
    private static JLabel Monthlyexpenses;
    private static JLabel Purchase;
    private static JLabel PurchaseCost;
    private static JLabel Timeforpurchase;
    private static JLabel reviewing;
    private static JLabel copyright;
    private static JTextField inputfield0;
    private static JTextField inputfield1;
    private static JTextField inputfield2;
    private static JTextField inputfield3;
    private static JTextField inputfield4;
    private static JTextField inputfield5;
    private static JTextField inputfield6;
    private static JTextField inputfield7;
    private static JButton button;
    private static JButton button2;
    private static JButton button3;
    private static JButton button4;

    public static void main(String[] args)
    {
        //Setting up for user input and GUI
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(750,750);
        frame.setResizable(false);
        frame.setTitle("Budget Tracker Program");
        panel.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);
        panel.setLayout(null);

        //Configuring Inputs Labels
        name = new JLabel("Full Name : ");
        name.setBounds(10,20,200,25);
        panel.add(name);
        starting_amountlabel = new JLabel("Starting Amount : ");
        starting_amountlabel.setBounds(10,50,200,25);
        panel.add(starting_amountlabel);
        monthly_paylabel = new JLabel("Monthly Income : ");
        monthly_paylabel.setBounds(10,80,200,25);
        panel.add(monthly_paylabel);
        Monthlygroceriescost = new JLabel("Monthly Groceries Cost : ");
        Monthlygroceriescost.setBounds(10,110,200,25);
        panel.add(Monthlygroceriescost);
        Monthlyutilitiescost = new JLabel("Monthly Utilities Cost : ");
        Monthlyutilitiescost.setBounds(10,140,200,25);
        panel.add(Monthlyutilitiescost);
        Monthlyrentcost = new JLabel("Monthly Rent Cost : ");
        Monthlyrentcost.setBounds(10,170,200,25);
        panel.add(Monthlyrentcost);
        Purchase = new JLabel("What do you want to purchase? : ");
        Purchase.setBounds(10,200,250,25);
        panel.add(Purchase);
        PurchaseCost = new JLabel("How much does it cost? : ");
        PurchaseCost.setBounds(10,230,200,25);
        panel.add(PurchaseCost);
        reviewing = new JLabel("How satisfied were you with this program?");
        reviewing.setBounds(10,525,1000,25);
        panel.add(reviewing);
        copyright = new JLabel("© Kush Gandhi 2021");
        copyright.setBounds(10,700,200,25);
        panel.add(copyright);

        //Configuring Text Input
        inputfield0 = new JTextField();
        inputfield0.setBounds(300, 20, 165, 25);
        panel.add(inputfield0);
        inputfield1 = new JTextField();
        inputfield1.setBounds(300, 50, 165, 25);
        panel.add(inputfield1);
        inputfield2 = new JTextField();
        inputfield2.setBounds(300, 80, 165, 25);
        panel.add(inputfield2);
        inputfield3 = new JTextField();
        inputfield3.setBounds(300, 110, 165, 25);
        panel.add(inputfield3);
        inputfield4 = new JTextField();
        inputfield4.setBounds(300, 140, 165, 25);
        panel.add(inputfield4);
        inputfield5 = new JTextField();
        inputfield5.setBounds(300, 170, 165, 25);
        panel.add(inputfield5);
        inputfield6 = new JTextField();
        inputfield6.setBounds(300, 200, 165, 25);
        panel.add(inputfield6);
        inputfield7 = new JTextField();
        inputfield7.setBounds(300, 230, 165, 25);
        panel.add(inputfield7);

        //Configuring Output
        Monthlysaved = new JLabel("");
        Monthlysaved.setBounds(10, 330, 300, 25);
        panel.add(Monthlysaved);
        Monthlyexpenses = new JLabel("");
        Monthlyexpenses.setBounds(10, 355, 300, 25);
        panel.add(Monthlyexpenses);
        Timeforpurchase = new JLabel("");
        Timeforpurchase.setBounds(10, 380, 1500, 25);
        panel.add(Timeforpurchase);

        //Configuring Buttons
        button = new JButton(new AbstractAction("Enter") {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Declaring Variables
                String username;
                double start;
                double month;
                double groceries;
                double utilities;
                double rent;
                double purchasecost;
                double purchasecosttemp;
                double monthlyexpenses;
                double savedmonth;
                int numofmonthstosave = 0;
                double tempsavedmonth;
                String purchase;
                String temp;

                //Decimal Format
                DecimalFormat df = new DecimalFormat("$0.00");

                //Assigning Values
                temp = inputfield0.getText();
                username = temp;
                temp = inputfield1.getText();
                start = Double.parseDouble(temp);
                temp = inputfield2.getText();
                month = Double.parseDouble(temp);
                temp = inputfield3.getText();
                groceries = Double.parseDouble(temp);
                temp = inputfield4.getText();
                utilities = Double.parseDouble(temp);
                temp = inputfield5.getText();
                rent = Double.parseDouble(temp);
                purchase = inputfield6.getText();
                temp = inputfield7.getText();
                purchasecost = Double.parseDouble(temp);
                purchasecosttemp = purchasecost;

                //Calculating Monthly Expenses
                monthlyexpenses = groceries+utilities+rent;
                savedmonth = month - monthlyexpenses;

                //Calculating time needed to save up for purchase
                tempsavedmonth = savedmonth;
                purchasecosttemp -= start;
                while(purchasecosttemp>tempsavedmonth)
                {
                    numofmonthstosave += 1;
                    tempsavedmonth += savedmonth;
                }

                //Displaying Info
                Monthlysaved.setText("Amount Saved Each Month : $"+savedmonth);
                Monthlyexpenses.setText("Amount Spent Each Month : $"+monthlyexpenses);
                Timeforpurchase.setText("To purchase a "+purchase+" you need to continue saving $"+savedmonth+" each month for "+numofmonthstosave+" more month(s)");

                //Saving User Information
                try
                {
                    FileWriter myWriter = new FileWriter("User Data.txt", true);
                    myWriter.write("User's Full Name : "+username+"\n");
                    myWriter.write("Monthly Income :"+df.format(month)+"\n");
                    myWriter.write("Monthly Expenses :"+df.format(monthlyexpenses)+"\n");
                    myWriter.write("Amount Saved Each Month :"+df.format(savedmonth)+"\n");
                    myWriter.write("Wanting to purchase :"+purchase+" : "+df.format(purchasecost)+"\n");
                    myWriter.write("\n");
                    myWriter.close();
                }
                catch (IOException e1)
                {
                    System.out.println("Error occurred, please contact Kush Gandhi for assistance");
                }
            }
        });
        button.setBounds(325, 290, 100, 30);
        panel.add(button);
        frame.setVisible(true);
        button2 = new JButton(new AbstractAction("Dissatisfied") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dissatisfied();
            }
        });
        button2.setBounds(300, 525, 100, 30);
        panel.add(button2);
        frame.setVisible(true);
        button3 = new JButton(new AbstractAction("Satisfied") {
            @Override
            public void actionPerformed(ActionEvent e) {
                Satisfied();
            }
        });
        button3.setBounds(400, 525, 100, 30);
        panel.add(button3);
        frame.setVisible(true);
        button4 = new JButton(new AbstractAction("Totally Satisfied") {
            @Override
            public void actionPerformed(ActionEvent e) { TSatisfied();
            }
        });
        button4.setBounds(500, 525, 150, 30);
        panel.add(button4);
        frame.setVisible(true);
    }
    //Totally Satisfied
    static void TSatisfied()
    {
        try
        {
            FileWriter myWriter = new FileWriter("User Data.txt", true);
            myWriter.write("User Satisfaction Level : Totally Satisfied"+"\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error detected, contact Kush Gandhi for assistance");
        }
        JOptionPane.showMessageDialog(null, "Good Bye!");
        System.exit(0);

    }
    //Satisfied
    static void Satisfied()
    {
        try
        {
            FileWriter myWriter = new FileWriter("User Data.txt", true);
            myWriter.write("User Satisfaction Level : Satisfied"+"\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error detected, contact Kush Gandhi for assistance");
        }
        JOptionPane.showMessageDialog(null, "Good Bye!");
        System.exit(0);
    }
    //Dissatisfied
    static void Dissatisfied()
    {
        try
        {
            FileWriter myWriter = new FileWriter("User Data.txt", true);
            myWriter.write("User Satisfaction Level : dissatisfied"+"\n");
            myWriter.close();
        }
        catch (IOException e)
        {
            System.out.println("Error detected, contact Kush Gandhi for assistance");
        }
        JOptionPane.showMessageDialog(null, "Good Bye!");
        System.exit(0);
    }
}
