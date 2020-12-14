import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receiptForm{
    public JTextArea receiptOutput;
    public JPanel pnl_receipt;
    private JButton btn_genReceipt;
    private JButton btn_finish;

    public static String receiptString = "";

    static public receipt receipt = new receipt();
    static public Thread receiptThread = new Thread(receipt);



    //private static String receiptText = "";

    public receiptForm() {
        btn_genReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                genReceipt();
                btn_genReceipt.setEnabled(false);
            }
        });

    }


    public void genReceipt(){
        receiptThread.start();


        //When that receipt has finished, populat the text box
        try{receiptForm.receiptThread.join();}
        catch(Exception e){;}
        finally{
            //sending the text to the text box
            receiptOutput.setText(receiptString);
        }
    }

}
