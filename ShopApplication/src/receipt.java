import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receipt extends Thread{
    public void run(){ //starts the loading of the file, and starts the listening thread.
        String receiptString = "";
        Float orderTotal = 0f;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString += (mainForm.company) +"\n";
        receiptString += (dtf.format(now) +"\n");
        receiptString += "Payment method: " + paymentForm.paymentMethod + "\n";
        receiptString += "====================\n";


        //adding the items to the receipt string
        for(String ordered : mainForm.scannedItems){
            for (Item item : mainForm.stock.storeStock){
                if (ordered.equalsIgnoreCase(item.id)){
                    receiptString += item.id + " | £" + item.price + "\n";
                    orderTotal += item.price;
                }
            }
        }

        receiptString += "====================\n";
        receiptString += "Total : £" + orderTotal + "\n";

        if (paymentForm.paymentMethod == "cash"){
            receiptString += "Change: £ " + paymentForm.change;
        }

       receiptForm.receiptString = receiptString;
    }
}

