public class Item{

    String code;
    String name;
    Float price;
    Integer stock;

    //Constructor
    public Item(String in_code, String in_id, Float in_price, Integer in_stock){
        code = in_code; 
        name = in_id;
        price = in_price;
        stock =  in_stock;
    }
}