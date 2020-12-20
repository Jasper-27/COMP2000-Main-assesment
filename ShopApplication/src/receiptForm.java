import javax.swing.*;
import java.awt.event.*;

public class receiptForm{
    public JTextArea txt_receiptOutput;
    public JPanel pnl_receipt;
    private JButton btn_genReceipt;
    private JButton btn_finish;

    public static String receiptString = "";

    static public receipt receipt = new receipt();
    //static public Thread receiptThread = new Thread(receipt);


    public receiptForm() {
        btn_genReceipt.addActionListener(e -> {
            genReceipt();
            btn_genReceipt.setEnabled(false);
        });
        btn_finish.addActionListener(e -> {
            mainForm.scannedItems = null;
            mainForm.main(null);
        });
    }


    //This is an example of MVP because the user interacts with the button, this then tells the model (receipt) to start calculating,
    //Then it gets the calculation back and updates

    public void genReceipt(){

        Thread receiptThread = new Thread(receipt);
        receiptThread.start();

        //When that receipt has finished, populate the text box
        try{receiptThread.join();}
        catch(Exception e){;}
        finally{
            //sending the text to the text box
            txt_receiptOutput.setText(receiptString);
        }
    }
}
