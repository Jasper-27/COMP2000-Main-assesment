import javax.swing.*;

public class receiptForm {
    private JTextArea receiptOutput;
    private JPanel panel1;


    public static void main(String[] args) {


        JFrame frame = new JFrame("receiptWindow");
        frame.setContentPane(new receiptForm().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);



    }
}
