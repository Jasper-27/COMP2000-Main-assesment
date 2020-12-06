import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static List<Item> storeStock = new ArrayList();
    public static List scannedItems = new ArrayList();

    public static void scanItem(String itemID){

    }
    public static void main(String[] args) {




        String out = loadFile("stock.txt");
        System.out.println(out);

        System.out.println(storeStock);




    }

    //This functions reads the file and returns the contents
    public static String loadFile(String file){

        Item tempItem = new Item(null, null, null);
        try {
            File myFile = new File(file);
            Scanner myReader = new Scanner(myFile);
            String outString = "";

            //While there is another line to read, read it and output the data
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] arrOfStr = data.split(";", 3);
                //System.out.println(arrOfStr);
                System.out.println(arrOfStr[0] + arrOfStr[1] +  arrOfStr[2]);

                try{
                    Float price = Float.valueOf(arrOfStr[1]);
                    Integer stock = Integer.valueOf(arrOfStr[2]);
/*                    System.out.println("ID is " + arrOfStr[0]);
                    System.out.println("Price is " + price);
                    System.out.println("Stock is " + stock);*/

                    tempItem = new Item(arrOfStr[0], price, stock);

                    storeStock.add(tempItem);


/*
                    System.out.println("ID: " + tempItem.id + " Price: £" + tempItem.price + " Number in stock: " + tempItem.stock);
*/





                    //System.out.println(tempItem.output());
                }catch(Exception e){
                    System.out.println(e);
                }



               //
                System.out.println("__");



                outString += data;
            }
            myReader.close();
            return outString;
        } catch (FileNotFoundException e) { //Prints an error if the file is not found
            e.printStackTrace();
            return "An error occurred.";
        }
    }
}
