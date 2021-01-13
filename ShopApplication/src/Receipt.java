import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt extends Thread{
    public void run(){ //starts the loading of the file, and starts the listening thread.
        StringBuilder receiptString = new StringBuilder();
        Float orderTotal = 0f;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString.append(MainForm.company).append("\n");
        receiptString.append(dtf.format(now)).append("\n");
        receiptString.append("Payment method: ").append(PaymentForm.paymentMethod).append("\n");
        receiptString.append("====================\n");


        //adding the items to the receipt string
        for(String ordered : MainForm.scannedItems){
            for (Item item : MainForm.stock.storeStock){
                if (ordered.equalsIgnoreCase(item.code)){
                    receiptString.append(item.name).append(" | £").append(item.price).append("\n");
                    orderTotal += item.price;
                }
            }
        }

        receiptString.append("====================\n");
        receiptString.append("Total : £").append(orderTotal).append("\n");

        if (PaymentForm.paymentMethod.equals("cash")){
            receiptString.append("Change: £ ").append(PaymentForm.change);
        }

       ReceiptForm.receiptString = receiptString.toString();
    }
}

