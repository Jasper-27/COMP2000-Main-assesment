import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class mainForm {
    private JTextField txt_scan;
    private JPanel panel1;
    private JButton btn_scan;
    private JButton btn_order;
    private JButton btn_admin;
    private JTextArea txt_mainOutput;
    private JLabel lb_currentPrice;
    public static float currentPrice;

    public String company = "Company";


    public static List<String> scannedItems = new ArrayList();
    public static JFrame frame = new JFrame("mainWindow");
    public static JFrame adminFrame = new JFrame("Admin Frame");


    public static Stock stock = new Stock();

    public mainForm() {
        btn_scan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, txt_scan.getText());
                scanItem(txt_scan.getText());
            }
        });

        btn_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stock.order(scannedItems);

                frame.setContentPane(new paymentForm().pnl_payment);
                frame.setTitle("Payment");
                frame.pack();
            }
        });

        btn_admin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAdminForm();
            }
        });
    }

    public void scanItem(String itemID){
        int stockLocation = stock.findItem(itemID);
        if (stockLocation > -1){        //Error checking to make sure the item was in stock
            System.out.println("Item scanned");
            currentPrice += stock.storeStock.get(stockLocation).price;
            scannedItems.add(itemID);
            txt_mainOutput.append(itemID + "\n");
            lb_currentPrice.setText("Total price: £" + String.valueOf(currentPrice));
            return;
        }

        JOptionPane.showMessageDialog(null,"could not find item in stock"); //Shows a message if their is no stock left
    }

    public static void main(String[] args) {

        stock.loadFile();

        frame.setContentPane(new mainForm().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public static void openAdminForm(){


        AdminLoginForm adminLoginForm = new AdminLoginForm();
        adminFrame.setContentPane(adminLoginForm.panel1);
        adminFrame.pack();
        adminFrame.setSize(300, 200);
        adminFrame.setResizable(false);
        adminFrame.setVisible(true);
//
        //adminForm.startup();
    }

}
