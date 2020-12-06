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

        String dataFile = "stock.txt";
        loadFile(dataFile);



        System.out.println(storeStock);
//        System.out.println(storeStock.get(0).id);

        





    }

    //This functions reads the file and returns the contents
    public static void loadFile(String file){
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


                try{
                    Float price = Float.valueOf(arrOfStr[1]);
                    Integer stock = Integer.valueOf(arrOfStr[2]);
                    tempItem = new Item(arrOfStr[0], price, stock);

                    storeStock.add(tempItem);

                }catch(Exception e){
                    System.out.println(e);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) { //Prints an error if the file is not found
            e.printStackTrace();
        }
    }


}
