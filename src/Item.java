public class Item{

    String id;
    Float price;
    Integer stock;
    Integer numInOrder = 0;

    //Constructor
    public Item(String in_id, Float in_price, Integer in_stock){
        id = in_id;
        price = in_price;
        stock =  in_stock;
    }

    public String output(){
        return (id + price + stock);
    }


}
