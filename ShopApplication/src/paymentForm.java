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
    }

    public static void main(String[] args) {


        JFrame frame = new JFrame("receiptWindow");
        frame.setContentPane(new paymentForm().pnl_payment);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);




    }


    public static void cardPay(){


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
