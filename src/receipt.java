import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receipt {
    String receiptString = "";
    Float orderTotal = 0f;

    public void startReceipt(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString += (dtf.format(now) +"\n");
        receiptString += "====================\n";

    }
    public void addItem (Item item){
        receiptString += item.id + " | £" + item.price + "\n";

        orderTotal += item.price;

    }

    public void endReceipt(){
        receiptString += "====================\n";
        receiptString += "Total: £" + orderTotal;
    }
}
