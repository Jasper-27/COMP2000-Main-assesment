import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Stock {
    public List<Item> storeStock = new ArrayList();
    public String dataFile = "Resources/stock.txt";


    public boolean order(String toOrder){
        if (storeStock.get(findItem(toOrder)).stock < 1){
            JOptionPane.showMessageDialog(null,"Item out of stock");
            return false;
        }else{
            storeStock.get(findItem(toOrder)).stock += -1;
            return true;
        }
    }

    public Item getItem(String code){
        for(Item item : storeStock){
            if(item.code.equals(code)){
                return item;
            }
        }
        return null;
    }

    //Returns the position of the item in the array
    public int findItem(String code){
        int pos = -1;
        for(Item item : storeStock){
            if(item.code.equals(code)){
                pos = storeStock.indexOf(item);
            }
        }
        return pos; // Returning -1 shows that something went wrong, it should never actually happen
    }

    public int findItemByName(String name){
        int pos = -1;
        for(Item item : storeStock){
            if(item.name.equals(name)){
                pos = storeStock.indexOf(item);
            }
        }
        return pos; // Returning -1 shows that something went wrong, it should never actually happen
    }

    public void loadFile(){
        MainForm.stock.storeStock.clear(); // clears the stock so it does not get duplicated
        try {
            File myFile = new File(dataFile);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) { //While there is another line to read, read it and output the data
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(";", 4);
                try{
                    Float price = Float.valueOf(arrOfStr[2]);
                    Integer stock = Integer.valueOf(arrOfStr[3]);
                    Item tempItem = new Item(arrOfStr[0], arrOfStr[1], price, stock);
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
                outString = item.code + ";" + item.name + ";" + item.price + ";" + item.stock + "\n";
                writer.append(outString);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
