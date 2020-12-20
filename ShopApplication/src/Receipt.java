import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt extends Thread{
    public void run(){ //starts the loading of the file, and starts the listening thread.
        String receiptString = "";
        Float orderTotal = 0f;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString += (MainForm.company) +"\n";
        receiptString += (dtf.format(now) +"\n");
        receiptString += "Payment method: " + PaymentForm.paymentMethod + "\n";
        receiptString += "====================\n";


        //adding the items to the receipt string
        for(String ordered : MainForm.scannedItems){
            for (Item item : MainForm.stock.storeStock){
                if (ordered.equalsIgnoreCase(item.id)){
                    receiptString += item.id + " | £" + item.price + "\n";
                    orderTotal += item.price;
                }
            }
        }

        receiptString += "====================\n";
        receiptString += "Total : £" + orderTotal + "\n";

        if (PaymentForm.paymentMethod == "cash"){
            receiptString += "Change: £ " + PaymentForm.change;
        }

       ReceiptForm.receiptString = receiptString;
    }
}

