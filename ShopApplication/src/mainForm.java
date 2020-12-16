import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static List<Item> storeStock = new ArrayList();
    public static String dataFile = "Resources/stock.txt";
    public static List<String> scannedItems = new ArrayList();
    public static JFrame frame = new JFrame("mainWindow");



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
                order(scannedItems);

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

    //Remove the items from the stock
    public static void order(List<String> itemsToOrder){
        for(String ordered : itemsToOrder){
            for (Item stockItem : storeStock){
                if (ordered.equalsIgnoreCase(stockItem.id)){
                    if (stockItem.stock > 0){
                        stockItem.stock -= 1;
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: No more items in stock");
                    }
                }
            }
        }
    }

    public void scanItem(String itemID){
        for (Item item : storeStock) {
            if (item.id.equals(itemID)){ //Checks to see if the item has already been scanned
                System.out.println("Item scanned");
                currentPrice += item.price;
                scannedItems.add(itemID);
                txt_mainOutput.append(itemID + "\n");
                lb_currentPrice.setText("Total price: £" + String.valueOf(currentPrice));
                return;
            }
        }
        JOptionPane.showMessageDialog(null,"could not find item in stock"); //Shows a message if their is no stock left
    }

    public static void main(String[] args) {
        loadFile(dataFile);

        frame.setContentPane(new mainForm().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setResizable(false);
        frame.setVisible(true);

    }



    //File stuff

    public static void loadFile(String file){
        System.out.println("Loading file:");

        //Item tempItem = new Item(null, null, null);
        try {
            File myFile = new File(file);
            Scanner myReader = new Scanner(myFile);
            String outString = "";

            //While there is another line to read, read it and output the data
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] arrOfStr = data.split(";", 3);

                try{
                    Float price = Float.valueOf(arrOfStr[1]);
                    Integer stock = Integer.valueOf(arrOfStr[2]);
                    Item tempItem = new Item(arrOfStr[0], price, stock);

                    //System.out.println("TempItem.id: "+ tempItem.id);

                    storeStock.add(tempItem);
                    //System.out.println(storeStock.get(1));

                }catch(Exception e){
                    System.out.println(e);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) { //Prints an error if the file is not found
            e.printStackTrace();
        }

        System.out.println("File loading complete");
    }


    public static void saveFile(){
        String outString;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dataFile));

            for (Item var : storeStock)
            {
                outString = var.id + ";" + var.price + ";" + var.stock + "\n";
                //System.out.println(outString);
                writer.append(outString);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void openAdminForm(){
        JFrame adminFrame = new JFrame("Admin Frame");

        adminForm adminForm = new adminForm();
        adminFrame.setContentPane(adminForm.panel1);
        adminFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        adminFrame.pack();
        adminFrame.setSize(500, 400);
        adminFrame.setResizable(false);
        adminFrame.setVisible(true);

        adminForm.startup();
    }

}
