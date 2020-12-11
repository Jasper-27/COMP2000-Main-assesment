import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptForm {
    private JTextArea receiptOutput;
    public JPanel pnl_receipt;
    private JButton btnGenRecipt;

    //private static String receiptText = "";

    public receiptForm() {
        btnGenRecipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genReceipt();
            }
        });
    }

    public static void main(String[] args) {


    }


    public void genReceipt(){

        String receiptString = "";
        Float orderTotal = 0f;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString += (dtf.format(now) +"\n");
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
        receiptString += "Total : £" + orderTotal;

        //JOptionPane.showMessageDialog(null,receiptString);

        receiptOutput.setText(receiptString);



    }
}
