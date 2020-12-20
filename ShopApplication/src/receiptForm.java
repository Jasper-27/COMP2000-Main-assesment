import javax.swing.*;

public class receiptForm{
    public JTextArea txt_receiptOutput;
    public JPanel pnl_receipt;
    private JButton btn_genReceipt;
    private JButton btn_finish;

    public static String receiptString = "";

    static public receipt receipt = new receipt();

    public receiptForm() {
        btn_genReceipt.addActionListener(e -> {
            genReceipt();
            btn_genReceipt.setEnabled(false);
        });
        btn_finish.addActionListener(e -> {
            mainForm.scannedItems.clear();
            mainForm.main(null);
            mainForm.currentPrice = 0f;
        });
    }

    //This is an example of MVP because the user interacts with the button, this then tells the model (receipt) to start calculating,
    //Then it gets the calculation back and updates

    public void genReceipt(){
        Thread receiptThread = new Thread(receipt);
        receiptThread.start();

        try{receiptThread.join();} //When that receipt has finished, populate the text box
        catch(Exception e){
            System.out.println(e);
        }
        finally{
            txt_receiptOutput.setText(receiptString);
        }
    }
}
