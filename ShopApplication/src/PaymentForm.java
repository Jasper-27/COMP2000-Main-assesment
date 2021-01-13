import javax.swing.*;

public class PaymentForm {
    private JButton btn_cardPay;
    private JButton btn_cashPay;
    private JTextField txt_cashAmount;
    public JPanel pnl_payment;
    private JLabel lb_currentPrice;

    public static String paymentMethod;
    public static Float change = 0f;

    public PaymentForm() {
        btn_cashPay.addActionListener(e -> cashPay(txt_cashAmount.getText()));
        lb_currentPrice.setText("Owed: £" + MainForm.currentPrice);
        btn_cardPay.addActionListener(e -> cardPay());
    }

    public void cardPay(){

        if (MainForm.currentPrice < 0){
            JOptionPane.showMessageDialog(null,"Nothing for you to pay");
        }else {

            //Yes/no dialogue box
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm payment of: £" + MainForm.currentPrice, "Card payment", dialogButton);
            if(dialogResult == 0) {
                MainForm.currentPrice = 0;
                lb_currentPrice.setText("Owed: £" + MainForm.currentPrice);
                paymentMethod = "card";
                getReceipt();

                //This is where the code to take the money out of their account would go

            }else{
                JOptionPane.showMessageDialog(null, "Please select other payment method");
            }
        }
        MainForm.stock.saveFile();
    }

    public void cashPay(String cashText){
        float currentCash;
        try{
            currentCash = Float.parseFloat(cashText);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid cash input");
            txt_cashAmount.setText("");
            return;
        }

        change = currentCash - MainForm.currentPrice;
        MainForm.currentPrice = -change;

        if (change > 0){
            JOptionPane.showMessageDialog(null,"Here is your change: £" + change);
        }

        lb_currentPrice.setText("Owed: £" + MainForm.currentPrice);
        txt_cashAmount.setText("");

        if (MainForm.currentPrice <= 0){
            lb_currentPrice.setText("");
            getReceipt();
        }

        paymentMethod = "cash";
        MainForm.stock.saveFile();
    }

    public void getReceipt(){
        MainForm.frame.setContentPane(new ReceiptForm().pnl_receipt);
        MainForm.frame.setTitle("Receipt");
        MainForm.frame.pack();
    }
}
