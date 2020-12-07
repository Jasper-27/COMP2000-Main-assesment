import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static List<Item> storeStock = new ArrayList();
    public static List scannedItems = new ArrayList();
    public static String dataFile = "stock.txt";


    //public static Item[] item;


    public static void scanItem(String itemID){

    }
    public static void main(String[] args){




        loadFile(dataFile);

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

                    System.out.println("TempItem.id: "+ tempItem.id);

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
                System.out.println(outString);
                writer.append(outString);



            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
