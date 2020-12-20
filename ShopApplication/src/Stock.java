import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Stock {
    public List<Item> storeStock = new ArrayList();
    public String dataFile = "Resources/stock.txt";


    public void order(List<String> toOrder){
        for(String string : toOrder){
            storeStock.get(findItem(string)).stock += -1;
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
        mainForm.stock.storeStock.clear(); // clears the stock so it does not get duplicated
        try {
            File myFile = new File(dataFile);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) { //While there is another line to read, read it and output the data
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
    }

    public void saveFile(){
        String outString = "";
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(dataFile));

            for (Item item : storeStock) {
                outString = item.id + ";" + item.price + ";" + item.stock + "\n";
                writer.append(outString);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
