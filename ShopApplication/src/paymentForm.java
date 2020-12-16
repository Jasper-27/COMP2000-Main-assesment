import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paymentForm {
    private JButton btn_cardPay;
    private JButton btn_cashPay;
    private JTextField txt_cashAmount;
    public JPanel pnl_payment;
    private JLabel lb_currentPrice;

    public static String paymentMethod;
    public static Float change = 0f;

    public paymentForm() {
        btn_cashPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cashPay(txt_cashAmount.getText());
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
                getReceipt();
                //This is where the code to take the money out of their account would go

            } else {
                System.out.println("No");
            }
        }
        mainForm.stock.saveFile();
    }

    public void cashPay(String cashText){

        float currentCash = 0f;

        try{
            currentCash = Float.parseFloat(cashText);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid cash input");
            txt_cashAmount.setText("");
            return;
        }

        change = currentCash - mainForm.currentPrice;
        mainForm.currentPrice = -change;

        System.out.println("Cash: " + currentCash + " CurrentPrice: " + mainForm.currentPrice + " change: " + change);


        if (change > 0){
            JOptionPane.showMessageDialog(null,"Here is your change: £" + change);
        }

        lb_currentPrice.setText("Owed: £" + mainForm.currentPrice);
        txt_cashAmount.setText("");


        if (mainForm.currentPrice <= 0){
            lb_currentPrice.setText("");
            getReceipt();
        }

        paymentMethod = "cash";
        mainForm.stock.saveFile();

    }

    public void getReceipt(){
        mainForm.frame.setContentPane(new receiptForm().pnl_receipt);
        mainForm.frame.setTitle("Receipt");
        mainForm.frame.pack();
    }
}
