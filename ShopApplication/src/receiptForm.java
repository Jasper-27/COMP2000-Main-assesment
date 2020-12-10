import javax.swing.*;

public class receiptForm {
    private JTextArea receiptOutput;
    public JPanel pnl_receipt;


    public static void main(String[] args) {


        JFrame frame = new JFrame("Receipt Window");
        frame.setContentPane(new receiptForm().pnl_receipt);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);



    }
}
