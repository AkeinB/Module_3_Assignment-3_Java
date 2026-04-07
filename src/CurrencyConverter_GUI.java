/*  Akein Bantin #2401011023
    Zhane Tomlin #2401010939
    Chevar Prince #
    Ryan Brown #
    Georgia Black Golding #1817002195
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public static class CurrencyConverter_GUI extends JFrame{

    private JTextField Input;
    private JTextField JMBResult;
    private JComboBox Currency_Combo;
    private JButton ConvertBtn, ClearBtn, SaveBtn;

    public CurrencyConverter_GUI() {
        //window settings
        setTitle("Currency Converter");
        setSize(600,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //gridlayout
        setLayout(new GridLayout(5,2,10,10));

        //components
        add(new JLabel("Input $:"));
        Input = new JTextField();
        add(Input);

        add(new JLabel("Currency Type:"));
        String[] Currency = {"USD", "CAN", "EURO"};
        Currency_Combo = new JComboBox(Currency);
        add(Currency_Combo);

        add(new JLabel("JMB Amount $:"));
        JMBResult = new JTextField();
        add(JMBResult);
        JMBResult.setEditable(false);

        ConvertBtn = new JButton("Convert");
        add(ConvertBtn);


//      ============== LOGIC ==============
        ConversionLG convert = new ConversionLG();

        ConvertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double result = Double.parseDouble(Input.getText());
                    String currency = (String) Currency_Combo.getSelectedItem();
                    double convertedAmount = convert.Converter(result, currency);
                    JMBResult.setText(String.format("%.2f", convertedAmount));

                    try(PrintWriter out = new PrintWriter(new FileWriter("conversion_history.txt", true))){
                        out.println("$" + JMBResult.getText() + " JMD" + " " + "Converted From " + "$" + Input.getText() + " " +
                                Currency_Combo.getSelectedItem());
                        JOptionPane.showMessageDialog(null, "Conversion saved successfully!");
                    }
                    catch(IOException ex){
                        JOptionPane.showMessageDialog(null, "Conversion save failed!");}
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null,
                            "Please enter numbers only!","Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        setVisible(true);

        ClearBtn = new JButton("Clear");
        add(ClearBtn);

//      return all fields to default values
            ClearBtn.addActionListener(e -> {
                Input.setText("");
                JMBResult.setText("");
                Currency_Combo.setSelectedIndex(0);
            });

    }

}

 static void main(String[] args) {
    try{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        // If setting the look and feel fails, we can just ignore it and use the default
    }

    new CurrencyConverter_GUI();
}