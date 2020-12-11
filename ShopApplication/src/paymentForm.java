import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class paymentForm {
    private JButton btn_cardPay;
    private JButton btn_cashPay;
    private JTextField tf_cashAmount;
    public JPanel pnl_payment;
    private JLabel lb_currentPrice;

    public paymentForm() {
        btn_cashPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(cashPay(tf_cashAmount.getText()));
            }
        });

        lb_currentPrice.setText("Owed: £" + mainWindow.currentPrice);
        btn_cardPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPay();

                //receiptForm.main(null);



                JFrame fr_receipt = new JFrame("Receipt Window");
                fr_receipt.setContentPane(new receiptForm().pnl_receipt);
                fr_receipt.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                fr_receipt.pack();
                fr_receipt.setSize(500, 400);
                fr_receipt.setResizable(false);
                fr_receipt.setVisible(true);

                receiptForm.main(null);



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

        if (mainWindow.currentPrice < 0){
            JOptionPane.showMessageDialog(null,"Nothing for you to pay");
        }else {

            //Yes/no dialogue box
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Confirm payment of: £" + mainWindow.currentPrice, "Card payment", dialogButton);
            if(dialogResult == 0) {
                System.out.println("Yes");
                mainWindow.currentPrice = 0;
                lb_currentPrice.setText("Owed: £" + mainWindow.currentPrice);

                //This is where the code to take the money out of their account would go


            } else {
                System.out.println("No");
            }
        }


    }

    public float cashPay(String cashText){

        float change = Float.parseFloat(cashText) - mainWindow.currentPrice;
        mainWindow.currentPrice = -change;

        System.out.println("Cash: " + Float.parseFloat(cashText) + " CurrentPrice: " + mainWindow.currentPrice + " change: " + change);



        if (change > 0){
            JOptionPane.showMessageDialog(null,"Here is your change: £" + change);
        }


        lb_currentPrice.setText("Owed: £" + mainWindow.currentPrice);
        tf_cashAmount.setText("");


        if (mainWindow.currentPrice <= 0){
            lb_currentPrice.setText("");
        }

        return change;

    }

}
