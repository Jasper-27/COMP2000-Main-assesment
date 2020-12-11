import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptForm {
    private JTextArea receiptOutput;
    public JPanel pnl_receipt;
    private JButton btnGenReceipt;

    //private static String receiptText = "";

    public receiptForm() {
        btnGenReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genReceipt();
            }
        });



    }

    public  void main(String[] args) {

        JFrame fr_receipt = new JFrame("Receipt Window");


        fr_receipt.setContentPane(new receiptForm().pnl_receipt);
        fr_receipt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr_receipt.pack();
        fr_receipt.setSize(500, 400);
        fr_receipt.setResizable(false);
        fr_receipt.setVisible(true);


    }


    public void genReceipt(){

        String receiptString = "";
        Float orderTotal = 0f;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString += (dtf.format(now) +"\n");
        receiptString += "Payment method: " + paymentForm.paymentMethod + "\n";
        receiptString += "====================\n";



        for(String ordered : mainWindow.scannedItems){
            for (Item item : mainWindow.storeStock){
                if (ordered.equalsIgnoreCase(item.id)){
                    receiptString += item.id + " | £" + item.price + "\n";
                    orderTotal += item.price;

                }
            }
        }

        receiptString += "====================\n";
        receiptString += "Total : £" + orderTotal + "\n";

        if (paymentForm.paymentMethod == "cash"){

            receiptString += "Change: £" + paymentForm.change;
        }

        //JOptionPane.showMessageDialog(null,receiptString);

        receiptOutput.setText(receiptString);



    }
}
