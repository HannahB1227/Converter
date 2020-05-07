import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Converter {

    //GUI frame
    private JFrame frame;

    //Option lists for the JSpinners
    private String[] areaList = {"Square Kilometer", "Square Meter", "Square Mile", "Square Yard", "Square Foot", "Square Inch",
            "Hectare", "Acre"};
    private String[] dataTransferList = {"Bit per second", "Kilobit per second", "Kilobyte per second", "Kibibit per second",
            "Megabit per second", "Megabyte per second", "Mebibit per second", "Gigabit per second", "Gigabyte per second",
            "Gibibit per second", "Terabit per second", "Terabyte per second", "Tebibit per second"};
    private String[] dataStorageList = {"Bit", "Kilobit", "Kibibit", "Megabit", "Mebibit", "Gigabit", "Gibibit", "Terabit",
            "Tebibit", "Petabit", "Pebibit", "Byte", "Kilobyte", "Kibibyte", "Megabyte", "Mebibyte", "Gigabyte", "Gibibyte",
            "Terabyte", "Tebibyte", "Petabyte", "Pebibyte"};
    private String[] frequencyList = {"Hertz", "Kilohertz", "Megahertz", "Gigahertz"};
    private String[] lengthList = {"Kilometer", "Meter", "Centimeter", "Millimeter", "Micrometer", "Nanometer", "Mile",
            "Yard", "Foot", "Inch", "Nautical Mile"};
    private String[] massList = {"Metric Ton", "Kilogram", "Gram", "Milligram", "Imperial Ton", "US Ton",
            "Stone", "Pound", "Ounce"};
    private String[] temperatureList = {"Celsius", "Fahrenheit", "Kelvin"};
    private String[] volumeList = {"US Liquid Gallon", "US Liquid Quart", "US Liquid Pint", "US Legal Cup", "US Fluid Ounce",
            "US Tablespoon", "US Teaspoon", "Cubic Meter", "Liter", "Milliliter", "Imperial Gallon", "Imperial Quart", "Imperial Pint",
            "Imperial Cup", "Imperial Fluid Ounce", "Imperial Tablespoon", "Imperial Teaspoon", "Cubic Foot", "Cubic Inch"};

    //JSpinners for the GUI
    private JSpinner main, option1, option2;

    //Input fields for user to enter values
    private JTextField input1, input2;

    /*
        The default constructor for the Converter class which creates and launches a GUI
     */
    public Converter() {
        //Initialize frame and set desired properties
        frame = new JFrame("Distance Converter");
        Color c1 = Color.CYAN;
        frame.setBackground(c1);
        Color c2 = Color.GREEN;
        frame.setForeground(c2);
        frame.setLayout(null);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Declare and initialize title label and set properties
        JLabel title = new JLabel("Distance Converter");
        title.setBounds(200, 50, 200, 50);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(BorderFactory.createBevelBorder(0, c1, c2));
        Font f1 = new Font(Font.SERIF, Font.PLAIN, 18);
        title.setFont(f1);
        frame.add(title);

        //Declare and initialize the main spinner and set properties
        String[] mainList = {"Area", "Data Transfer", "Data Storage", "Frequency", "Length", "Mass", "Temperature", "Volume"};
        main = new JSpinner(new SpinnerListModel(mainList));
        main.setBounds(200, 100, 200, 50);
        main.addChangeListener(new SpinnerChangeListener());
        main.setFont(f1);
        frame.add(main);

        //Declare and initialize option lists for spinners
        SpinnerListModel list1 = new SpinnerListModel(areaList);
        SpinnerListModel list2 = new SpinnerListModel(areaList);

        //Initialize a spinner and set properties
        option1 = new JSpinner(list1);
        option1.setBounds(100, 200, 150, 50);
        Font f2 = new Font(Font.SERIF, Font.PLAIN, 14);
        option1.setFont(f2);
        frame.add(option1);

        //Initialize a text field and set properties
        input1 = new JTextField();
        input1.setBounds(100, 250, 150, 50);
        input1.addActionListener(new InputListener());
        input1.setFont(f2);
        frame.add(input1);

        //Declare and initialize swap button to swap values of spinners and text fields
        JButton swap = new JButton("Swap");
        swap.setBounds(275, 200, 50, 100);
        swap.setBorder(BorderFactory.createBevelBorder(0, c2, c1));
        swap.addActionListener(new SwapButtonListener());
        swap.setFont(f1);
        frame.add(swap);

        //Initialize a spinner and set properties
        option2 = new JSpinner(list2);
        option2.setBounds(350, 200, 150, 50);
        option2.setFont(f2);
        frame.add(option2);

        //Initialize a text field and set properties
        input2 = new JTextField();
        input2.setBounds(350, 250, 150, 50);
        input2.addActionListener(new InputListener());
        input2.setFont(f2);
        frame.add(input2);

        //Declare and initialize a label to provide instructions for the user
        JLabel instructions = new JLabel("Press Enter to convert value");
        instructions.setBounds(100, 450, 400, 50);
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setFont(f1);
        frame.add(instructions);

        frame.setFont(f1);
        frame.setVisible(true);
    } //Default Constructor

    /*
        An inner class which controls the actions performed when the Swap button is clicked by the user.
        This class implements the ActionListener class.
     */
    private class SwapButtonListener implements ActionListener {

        @Override
        /*
            This method swaps the values of the spinners and text fields
            @param e the event performed to call method
         */
        public void actionPerformed(ActionEvent e) {
            String tempInput = input1.getText();
            Object tempPosition = option1.getValue();
            input1.setText(input2.getText());
            option1.setValue(option2.getValue());
            input2.setText(tempInput);
            option2.setValue(tempPosition);
        } //actionPerformed
    } //ButtonListener

    /*
        This method handles the conversion of area values
        @param input the JTextField where the user entered text
     */
    private void areaResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Square Meter")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Mile")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Yard")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Foot")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Inch")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Hectare")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Acre")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Square Meter")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Mile")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Yard")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Foot")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Inch")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Hectare")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Acre")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //areaResult

    /*
        This method handles the conversion of data transfer values
        @param input the JTextField where the user entered text
     */
    private void dtResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Square Meter")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Mile")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Yard")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Foot")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Inch")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Hectare")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Acre")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Square Meter")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Mile")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Yard")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Foot")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Inch")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Hectare")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Acre")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //dtResult

    /*
        This method handles the conversion of data storage values
        @param input the JTextField where the user entered text
     */
    private void dsResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Square Meter")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Mile")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Yard")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Foot")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Inch")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Hectare")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Acre")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Square Meter")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Mile")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Yard")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Foot")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Inch")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Hectare")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Acre")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //dsResult

    /*
        This method handles the conversion of mass values
        @param input the JTextField where the user entered text
     */
    private void massResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Metric Ton")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1000000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1.016);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value * 1.102);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value * 157);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2205);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 35274);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Kilogram")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1016);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 907);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value / 6.35);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2.205);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 35.274);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Gram")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1016000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 907185);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value / 6350);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value / 454);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value / 28.35);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Milligram")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1000000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value / 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1016000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 907200000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value / 6350000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value / 453592);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value / 28350);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value * 1.016);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 1016);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 1016000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1016000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value * 1.12);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value * 160);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2240);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 35840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("US Ton")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1.102);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 907);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 907185);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 907200000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1.12);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value * 143);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 32000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Stone")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 157);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 6.35);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 6350);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 6350000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 160);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 143);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value * 14);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 224);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Pound")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 2205);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 2.205);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 454);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 453592);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 2240);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 2000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value / 14);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 16);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Ounce")) {
                if (option2Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 35274);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 35.274);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gram")) {
                    value = (value * 28.35);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 28350);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 35840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 32000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Stone")) {
                    value = (value / 224);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Pound")) {
                    value = (value / 16);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Ounce")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Metric Ton")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1000000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1.016);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value * 1.102);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value * 157);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2205);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 35274);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Kilogram")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1016);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 907);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value / 6.35);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2.205);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 35.274);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Gram")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1016000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 907185);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value / 6350);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value / 454);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value / 28.35);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Milligram")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1000000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value / 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1016000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 907200000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value / 6350000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value / 453592);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value / 28350);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Imperial Ton")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value * 1.016);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 1016);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 1016000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 1016000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value * 1.12);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value * 160);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2240);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 35840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("US Ton")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 1.102);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 907);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 907185);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 907200000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 1.12);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value * 143);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value * 2000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 32000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Stone")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 157);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value * 6.35);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 6350);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 6350000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 160);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 143);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value * 14);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 224);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Pound")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 2205);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 2.205);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 454);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 453592);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 2240);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 2000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value / 14);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    value = (value * 16);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Ounce")) {
                if (option1Value.equalsIgnoreCase("Metric Ton")) {
                    value = (value / 35274);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilogram")) {
                    value = (value / 35.274);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gram")) {
                    value = (value * 28.35);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Milligram")) {
                    value = (value * 28350);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Imperial Ton")) {
                    value = (value / 35840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("US Ton")) {
                    value = (value / 32000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Stone")) {
                    value = (value / 224);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Pound")) {
                    value = (value / 16);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Ounce")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //massResult

    /*
        This method handles the conversion of length values
        @param input the JTextField where the user entered text
     */
    private void lengthResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Square Meter")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Mile")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Yard")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Foot")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Inch")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Hectare")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Acre")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Square Meter")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Mile")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Yard")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Foot")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Inch")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Hectare")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Acre")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //lengthResult

    /*
        This method handles the conversion of temperature values
        @param input the JTextField where the user entered text
     */
    private void tempResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Celsius")) {
                if (option2Value.equalsIgnoreCase("Celsius")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Fahrenheit")) {
                    value = (value * (9/5)) + 32;
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Kelvin")) {
                    value = (value + 273.15);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Fahrenheit")) {
                if (option2Value.equalsIgnoreCase("Celsius")) {
                    value = (value - 32) * (5/9);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Fahrenheit")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Kelvin")) {
                    value = (value - 32) * (5/9) + 273.15;
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Kelvin")) {
                if (option2Value.equalsIgnoreCase("Celsius")) {
                    value = (value - 273.15);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Fahrenheit")) {
                    value = (value - 273.15) * (9/5) + 32;
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Kelvin")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Celsius")) {
                if (option1Value.equalsIgnoreCase("Celsius")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Fahrenheit")) {
                    value = (value * (9/5)) + 32;
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Kelvin")) {
                    value = (value + 273.15);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Fahrenheit")) {
                if (option1Value.equalsIgnoreCase("Celsius")) {
                    value = (value - 32) * (5/9);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Fahrenheit")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Kelvin")) {
                    value = (value - 32) * (5/9) + 273.15;
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Kelvin")) {
                if (option1Value.equalsIgnoreCase("Celsius")) {
                    value = (value - 273.15);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Fahrenheit")) {
                    value = (value - 273.15) * (9/5) + 32;
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Kelvin")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //tempResult

    /*
        This method handles the conversion of volume values
        @param input the JTextField where the user entered text
     */
    private void volumeResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);

            //Determine which types to convert between
            if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Square Meter")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Mile")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Yard")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Foot")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Square Inch")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Hectare")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Acre")) {
                if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Acre")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Square Kilometer")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2.59);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1196000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10760000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 100);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 247);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Square Meter")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Mile")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value * 2.59);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 2590000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value * 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Yard")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1196000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1.196);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 3098000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Foot")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 10760000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 10.764);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 27880000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 9);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Square Inch")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 1550000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value / 1550);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / (4.014 * 1000000000));
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value / 1296);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value / 144);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value / 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Hectare")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 100);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 10000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 259);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 11960);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 107639);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 15500000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    value = (value * 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Acre")) {
                if (option1Value.equalsIgnoreCase("Square Kilometer")) {
                    value = (value / 247);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Square Meter")) {
                    value = (value * 4047);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Mile")) {
                    value = (value / 640);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Yard")) {
                    value = (value * 4840);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Foot")) {
                    value = (value * 43560);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Square Inch")) {
                    value = (value * 6273000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Hectare")) {
                    value = (value / 2.471);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Acre")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //volumeResult

    /*
        This method handles the conversion of frequency values
        @param input the JTextField where the user entered text
     */
    private void frequencyResult(String input) {
        //Get the values of the option spinners
        String option1Value = (String) option1.getValue();
        String option2Value = (String) option2.getValue();

        //Determine which text field to change based on input parameter
        if (input.equalsIgnoreCase("input1")) {
            //Get the input value from the text field
            String valueString = input1.getText();
            double value = Double.parseDouble(valueString);
            if (option1Value.equalsIgnoreCase("Hertz")) {
                if (option2Value.equalsIgnoreCase("Hertz")) {
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilohertz")) {
                    value = (value / 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Megahertz")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gigahertz")) {
                    value = (value * 1000000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option1Value.equalsIgnoreCase("Kilohertz")) {
                if (option2Value.equalsIgnoreCase("Hertz")) {
                    value = (value * 1000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilohertz")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Megahertz")) {
                    value = (value / 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gigahertz")) {
                    value = (value / 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Megahertz")) {
                if (option2Value.equalsIgnoreCase("Hertz")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilohertz")) {
                    value = (value * 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Megahertz")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gigahertz")) {
                    value = (value / 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option1Value.equalsIgnoreCase("Gigahertz")) {
                if (option2Value.equalsIgnoreCase("Hertz")) {
                    value = (value * 1000000000);
                    input2.setText(String.valueOf(value));
                } //if
                else if (option2Value.equalsIgnoreCase("Kilohertz")) {
                    value = (value * 1000000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Megahertz")) {
                    value = (value * 1000);
                    input2.setText(String.valueOf(value));
                } //else-if
                else if (option2Value.equalsIgnoreCase("Gigahertz")) {
                    input2.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //if
        else if (input.equalsIgnoreCase("input2")) {
            String valueString = input2.getText();
            double value = Double.parseDouble(valueString);
            if (option2Value.equalsIgnoreCase("Hertz")) {
                if (option1Value.equalsIgnoreCase("Hertz")) {
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilohertz")) {
                    value = (value / 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Megahertz")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gigahertz")) {
                    value = (value / 1000000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (option2Value.equalsIgnoreCase("Kilohertz")) {
                if (option1Value.equalsIgnoreCase("Hertz")) {
                    value = (value * 1000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilohertz")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Megahertz")) {
                    value = (value / 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gigahertz")) {
                    value = (value / 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Megahertz")) {
                if (option1Value.equalsIgnoreCase("Hertz")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilohertz")) {
                    value = (value * 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Megahertz")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gigahertz")) {
                    value = (value / 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else if (option2Value.equalsIgnoreCase("Gigahertz")) {
                if (option1Value.equalsIgnoreCase("Hertz")) {
                    value = (value * 1000000000);
                    input1.setText(String.valueOf(value));
                } //if
                else if (option1Value.equalsIgnoreCase("Kilohertz")) {
                    value = (value * 1000000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Megahertz")) {
                    value = (value * 1000);
                    input1.setText(String.valueOf(value));
                } //else-if
                else if (option1Value.equalsIgnoreCase("Gigahertz")) {
                    input1.setText(String.valueOf(value));
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option in option2 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid option in option1 JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //else-if
        else {
            JOptionPane.showMessageDialog(frame, "Error: invalid input to areaResult function", "Error", JOptionPane.ERROR_MESSAGE);
        } //else
    } //frequencyResult

    /*
        An inner class which handles the actions performed when text is entered in a JTextField.
        This class implements the ActionListener class.
     */
    private class InputListener implements ActionListener {

        @Override
        /*
            This method performs conversion after the user enters a value in a text field
            @param e the event performed to call method
         */
        public void actionPerformed(ActionEvent e) {
            //Determine which input the user entered text in
            if (e.getSource().equals(input1)) {

                //Determine the value of the main spinner and call the relevant function
                if (main.getValue().equals("Area")) {
                    areaResult("input1");
                } //if
                else if (main.getValue().equals("Data Transfer")) {
                    dtResult("input1");
                } //else-if
                else if (main.getValue().equals("Data Storage")) {
                    dsResult("input1");
                } //else-if
                else if (main.getValue().equals("Frequency")) {
                    frequencyResult("input1");
                } //else-if
                else if (main.getValue().equals("Length")) {
                    lengthResult("input1");
                } //else-if
                else if (main.getValue().equals("Mass")) {
                    massResult("input1");
                } //else-if
                else if (main.getValue().equals("Temperature")) {
                    tempResult("input1");
                } //else-if
                else if (main.getValue().equals("Volume")) {
                    volumeResult("input1");
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option selected in main JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //if
            else if (e.getSource().equals(input2)) {
                if (main.getValue().equals("Area")) {
                    areaResult("input2");
                } //if
                else if (main.getValue().equals("Data Transfer")) {
                    dtResult("input2");
                } //else-if
                else if (main.getValue().equals("Data Storage")) {
                   dsResult("input2");
                } //else-if
                else if (main.getValue().equals("Frequency")) {
                   frequencyResult("input2");
                } //else-if
                else if (main.getValue().equals("Length")) {
                    lengthResult("input2");
                } //else-if
                else if (main.getValue().equals("Mass")) {
                    massResult("input2");
                } //else-if
                else if (main.getValue().equals("Temperature")) {
                    tempResult("input2");
                } //else-if
                else if (main.getValue().equals("Volume")) {
                    volumeResult("input2");
                } //else-if
                else {
                    JOptionPane.showMessageDialog(frame, "Error: Invalid option selected in main JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
                } //else
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid Action Performed in InputListener", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //actionPerformed
    } //InputListener

    /*
        An inner class which handles the actions performed when the user changes the value in the
        main JSpinner. This class implements the ChangeListener class.
     */
    private class SpinnerChangeListener implements ChangeListener {

        @Override
        /*
            This method changes the lists used for the option1 and option2 spinners depending on
            the value of the main spinner
            @param e the event that triggered the method
         */
        public void stateChanged(ChangeEvent e) {
            JSpinner spinner = (JSpinner) e.getSource();

            //Determine which list to assign to the spinners depending on the current value of the main spinner
            if (spinner.getValue().equals("Area")) {
                option1.setModel(new SpinnerListModel(areaList));
                option2.setModel(new SpinnerListModel(areaList));
            } //if
            else if (spinner.getValue().equals("Data Transfer")) {
                option1.setModel(new SpinnerListModel(dataTransferList));
                option2.setModel(new SpinnerListModel(dataTransferList));
            } //else-if
            else if (spinner.getValue().equals("Data Storage")) {
                option1.setModel(new SpinnerListModel(dataStorageList));
                option2.setModel(new SpinnerListModel(dataStorageList));
            } //else-if
            else if (spinner.getValue().equals("Frequency")) {
                option1.setModel(new SpinnerListModel(frequencyList));
                option2.setModel(new SpinnerListModel(frequencyList));
            } //else-if
            else if (spinner.getValue().equals("Length")) {
                option1.setModel(new SpinnerListModel(lengthList));
                option2.setModel(new SpinnerListModel(lengthList));
            } //else-if
            else if (spinner.getValue().equals("Mass")) {
                option1.setModel(new SpinnerListModel(massList));
                option2.setModel(new SpinnerListModel(massList));
            } //else-if
            else if (spinner.getValue().equals("Temperature")) {
                option1.setModel(new SpinnerListModel(temperatureList));
                option2.setModel(new SpinnerListModel(temperatureList));
            } //else-if
            else if (spinner.getValue().equals("Volume")) {
                option1.setModel(new SpinnerListModel(volumeList));
                option2.setModel(new SpinnerListModel(volumeList));
            } //else-if
            else {
                JOptionPane.showMessageDialog(frame, "Error: Invalid value in main JSpinner", "Error", JOptionPane.ERROR_MESSAGE);
            } //else
        } //stateChanged
    } //SpinnerChangeListener
} //main class
