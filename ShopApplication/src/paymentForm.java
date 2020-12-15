import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paymentForm {
    private JButton btn_cardPay;
    private JButton btn_cashPay;
    private JTextField tf_cashAmount;
    public JPanel pnl_payment;
    private JLabel lb_currentPrice;

    public static String paymentMethod;
    public static Float change = 0f;

    public paymentForm() {
        btn_cashPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashPay(tf_cashAmount.getText());


            }
        });

        lb_currentPrice.setText("Owed: £" + mainForm.currentPrice);
        btn_cardPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPay();
            }
        });
    }

    public static void main(String[] args) {
        JFrame fr_payment = new JFrame("Payment Window");
        fr_payment.setContentPane(new paymentForm().pnl_payment);
        fr_payment.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fr_payment.pack();
        //frame.setSize(500, 400);
        fr_payment.setResizable(false);
        fr_payment.setVisible(true);


    }


    public void cardPay(){

        if (mainForm.currentPrice < 0){
            JOptionPane.showMessageDialog(null,"Nothing for you to pay");
        }else {

            //Yes/no dialogue box
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm payment of: £" + mainForm.currentPrice, "Card payment", dialogButton);
            if(dialogResult == 0) {
                System.out.println("Yes");
                mainForm.currentPrice = 0;
                lb_currentPrice.setText("Owed: £" + mainForm.currentPrice);
                paymentMethod = "card";
                //receiptForm.main(null);

                getReceipt();


                //This is where the code to take the money out of their account would go


            } else {
                System.out.println("No");
            }
        }

        mainForm.saveFile();


    }

    public void cashPay(String cashText){

        float currentCash = 0f;

        try{
            currentCash = Float.parseFloat(cashText);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid cash input");
            tf_cashAmount.setText("");
            return;
        }



        change = currentCash - mainForm.currentPrice;
        mainForm.currentPrice = -change;

        System.out.println("Cash: " + currentCash + " CurrentPrice: " + mainForm.currentPrice + " change: " + change);



        if (change > 0){
            JOptionPane.showMessageDialog(null,"Here is your change: £" + change);
        }


        lb_currentPrice.setText("Owed: £" + mainForm.currentPrice);
        tf_cashAmount.setText("");


        if (mainForm.currentPrice <= 0){
            lb_currentPrice.setText("");
            getReceipt();
        }

        paymentMethod = "cash";
        mainForm.saveFile();
        //getReceipt();
    }



    public void getReceipt(){
        //receiptForm theReceipt = new receiptForm();

//        mainWindow.frame = new JFrame("Receipt Window");
//        //mainWindow.frame.setContentPane( new receiptForm().pnl_receipt);
//
//        mainWindow.frame.setContentPane(theReceipt.pnl_receipt);
//        mainWindow.frame.setVisible(true);
//        mainWindow.frame.setSize(500,400);


        mainForm.frame.setContentPane(new receiptForm().pnl_receipt);
        mainForm.frame.setTitle("Receipt");
        mainForm.frame.pack();
//



//        JFrame fr_receipt = new JFrame("Receipt Window");
//        fr_receipt.setContentPane(new receiptForm().pnl_receipt);
//        fr_receipt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        fr_receipt.pack();
//        fr_receipt.setSize(500, 400);
//        fr_receipt.setResizable(false);
//        fr_receipt.setVisible(true);


    }
}
