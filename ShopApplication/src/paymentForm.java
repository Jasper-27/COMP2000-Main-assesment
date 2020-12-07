import javax.swing.*;

public class paymentForm {
    private JButton btn_cardPay;
    private JButton btn_cashPay;
    private JTextField tf_cashAmount;
    public JPanel pnl_payment;

    public static void main(String[] args) {


        JFrame frame = new JFrame("receiptWindow");
        frame.setContentPane(new paymentForm().pnl_payment);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        //frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);



    }
}
