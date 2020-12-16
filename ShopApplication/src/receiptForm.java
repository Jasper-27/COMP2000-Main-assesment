import javax.swing.*;
import java.awt.event.*;

public class receiptForm{
    public JTextArea txt_receiptOutput;
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


    //This is an example of MVP because the user interacts with the button, this then tells the model (receipt) to start calculating,
    //Then it gets the calculation back and updates

    public void genReceipt(){
        receiptThread.start();

        //When that receipt has finished, populat the text box
        try{receiptForm.receiptThread.join();}
        catch(Exception e){;}
        finally{
            //sending the text to the text box
            txt_receiptOutput.setText(receiptString);
        }
    }

}
