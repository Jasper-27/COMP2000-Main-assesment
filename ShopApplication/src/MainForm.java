import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class MainForm {
    private JTextField txt_scan;
    private JPanel panel1;
    private JButton btn_order;
    private JButton btn_admin;
    private JTextArea txt_mainOutput;
    private JLabel lb_currentPrice;


    public static String company = "Company";
    public static List<String> scannedItems = new ArrayList();
    public static JFrame frame = new JFrame("mainWindow");
    public static JFrame adminFrame = new JFrame("Admin Frame");

    public static float currentPrice;
    public static Stock stock = new Stock();



    public MainForm() {

        txt_scan.requestFocus();


        btn_order.addActionListener(e -> {
            stock.order(scannedItems);
            frame.setContentPane(new PaymentForm().pnl_payment);
            frame.setTitle("Payment");
            frame.pack();
        });

        btn_admin.addActionListener(e -> openAdminForm());
        txt_scan.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (txt_scan.getText().length() == 10){
                    scanItem(txt_scan.getText());
                }
            }
        });
    }

    public void scanItem(String itemCode){
        System.out.println("scanned");
        int stockLocation = stock.findItem(itemCode);
        if (stockLocation > -1){        //Error checking to make sure the item was in stock
            currentPrice += stock.storeStock.get(stockLocation).price;
            scannedItems.add(itemCode);
            txt_mainOutput.append(stock.storeStock.get(stockLocation).name +" (£" +stock.storeStock.get(stockLocation).price + ")\n");
            lb_currentPrice.setText("Total price: £" + currentPrice);
            txt_scan.setText("");
            return;
        }

        txt_scan.setText("");
        JOptionPane.showMessageDialog(null,"could not find item in stock"); //Shows a message if their is no stock left
    }

    public static void main(String[] args) {
        stock.loadFile();

        frame.setContentPane(new MainForm().panel1);
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
    }
}
