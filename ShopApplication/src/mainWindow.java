import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class mainWindow {
    private JTextField scanInput;
    private JPanel panel1;
    private JButton scanButtonButton;
    private JButton orderButton;
    private JButton adminButton;
    private JTextArea mainOutput;
    private JLabel lb_currentPrice;
    public static float currentPrice;


    public static List<Item> storeStock = new ArrayList();
    public static String dataFile = "Resources/stock.txt";
    public static List<String> scannedItems = new ArrayList();
    public static JFrame frame = new JFrame("mainWindow");



    public mainWindow() {
        scanButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, scanInput.getText());
                scanItem(scanInput.getText());

            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                order(scannedItems);

                //Changes to the payment window 
//                frame.setContentPane(new paymentForm().pnl_payment);
//                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                frame.pack();
//                //frame.setSize(500, 400);
//                frame.setResizable(false);
//                frame.setVisible(true);



//                JFrame fr_pay = new JFrame("Payment Window");
//                fr_pay.setContentPane(new paymentForm().pnl_payment);
//                fr_pay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                fr_pay.pack();
//                //frame.setSize(500, 400);
//                fr_pay.setResizable(false);
//                fr_pay.setVisible(true);


                //paymentForm.main(null);

                frame.setContentPane(new paymentForm().pnl_payment);
                frame.pack();


            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { }
        });


    }

    public static void order(List<String> itemsToOrder){

        String outMessage = "";


        for(String ordered : itemsToOrder){
            for (Item stockItem : storeStock){
                if (ordered.equalsIgnoreCase(stockItem.id)){
                    if (stockItem.stock > 0){
                        //System.out.println("The old stock: " + stockItem.stock);
                        stockItem.stock -= 1;
                        outMessage += stockItem.id + "\n";
                        //System.out.println("The new stock: " + stockItem.stock);
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: No more items in stock");
                    }


                }
            }

        }

        JOptionPane.showMessageDialog(null,outMessage);

        saveFile(dataFile);

    }


    //Lets add a check stock feature here. Remember we don't have to save the array till it is done
    public void scanItem(String itemID){
        for (Item item : storeStock) {
            if (item.id.equals(itemID)){
                System.out.println("Item scanned");

                currentPrice += item.price;

                //Checks to see if the item has allready been scanned

                scannedItems.add(itemID);
                mainOutput.append(itemID + "\n");

                lb_currentPrice.setText("Total price: £" + String.valueOf(currentPrice));
                return;

                // If the item.stock is 0, then something should be done about that
            }
        }

        JOptionPane.showMessageDialog(null,"could not find item in stock");

    }

    public static void main(String[] args) {
        loadFile(dataFile);


        frame.setContentPane(new mainWindow().panel1);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 400);
        frame.setVisible(true);


        //JFrame pay_frame = new JFrame("receiptWindow");



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


    public static void saveFile(String file){
        String outString;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));

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



}
