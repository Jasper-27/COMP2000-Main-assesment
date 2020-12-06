public class Item {

    static String id;
    static Float price;
    static Integer stock;

    //Constructor
    public Item(String in_id, Float in_price, Integer in_stock){
        id = in_id;
        price = in_price;
        stock =  in_stock;
    }

    public static String output(){
        return (id + price + stock);
    }


}
