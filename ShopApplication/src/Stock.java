import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stock {
    public List<Item> storeStock = new ArrayList();
    public String dataFile = "Resources/stock.txt";

    public void updateStock(String id, int stock){
        int pos = findItem(id);
        storeStock.get(pos).stock += stock;
    }

    public void order(List<String> toOrder){
        for(String string : toOrder){
            System.out.println(string);
            System.out.println(storeStock.get(findItem(string)).stock);
            storeStock.get(findItem(string)).stock += -1;
            System.out.println(storeStock.get(findItem(string)).stock);
        }
    }
    public int findItem(String id){
        int pos = -1;
        for(Item item : storeStock){
            if(item.id.equals(id)){
                pos = storeStock.indexOf(item);
            }
        }
        return pos; // Returning -1 shows that something went wrong, it should never actually happen
    }



    public void loadFile(){
        System.out.println("Loading file:");

        //Item tempItem = new Item(null, null, null);
        try {
            File myFile = new File(dataFile);
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
                    storeStock.add(tempItem);
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

    public void saveFile(){
        String outString;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(dataFile));

            for (Item item : storeStock)
            {
                outString = item.id + ";" + item.price + ";" + item.stock + "\n";
                //System.out.println(outString);
                writer.append(outString);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
