import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static List<Item> storeStock = new ArrayList();
    public static List<Item> scannedItems = new ArrayList();
    public static String dataFile = "stock.txt";


    //public static Item[] item;


    public static boolean scanItem(String itemID){
        for (Item item : storeStock) {
            if (item.id.equals(itemID)){

                //Checks to see if the item has allready been scanned

                for (Item scannedItem : scannedItems){
                    //System.out.println(scannedItem.id + " : " + itemID);
                    if (scannedItem.id.equals(itemID)){
                        scannedItem.numInOrder += 1;
                        //System.out.println("repeat match found: " + scannedItem.numInOrder);
                        return true;
                    }
                }

                //System.out.println("Match found");
                item.numInOrder +=1;
                //System.out.println(item.numInOrder);
                scannedItems.add(item);
                return true;

                // If the item.stock is 0, then something should be done about that
            }
        }

        return false;
    }

    public static String genReceipt(){
        String receiptString = "";
        Float orderTotal = 0f;

        //gets a time to add to the receipt
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();

        receiptString += (dtf.format(now) +"\n");
        receiptString += "====================\n";

        for (Item item : scannedItems){
            receiptString += item.numInOrder + " * " + item.id + " | £" + item.price + "\n"; // It does seem like receipts don't compile multiple items into one price
            orderTotal += item.price * item.numInOrder;
        }
        receiptString += "====================\n";
        receiptString += "Total: £" + orderTotal;

        return receiptString;
    }



    public static void main(String[] args){

        loadFile(dataFile);


//        //This code will pobably be usefull
//        String scanTestItem = "Tree";
//        if (scanItem(scanTestItem) == true){
//            System.out.println("Mellon has been succesfully scanned");
//        }else {
//            System.out.println("The scan failed. \"" + scanTestItem+ "\" is not a product");
//        }

        scanItem("Mellon");
        scanItem("Mellon");
        scanItem("Mellon");
        scanItem("tree");
        scanItem("Jasper");
        scanItem("Jasper");
        scanItem("Carrot");


        System.out.println(genReceipt());

        saveFile(dataFile);




    }

    //This functions reads the file and returns the contents
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
