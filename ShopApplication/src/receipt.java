import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class receipt extends Thread{
    public void run(){ //starts the loading of the file, and starts the listening thread.
        System.out.println("The thread is starting");

        //listeningThread listen = new listeningThread();


        String receiptString = "";
        Float orderTotal = 0f;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

        receiptString += (dtf.format(now) +"\n");
        receiptString += "Payment method: " + paymentForm.paymentMethod + "\n";
        receiptString += "====================\n";



        for(String ordered : mainWindow.scannedItems){
            for (Item item : mainWindow.storeStock){
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

        System.out.println(paymentForm.change);
        //JOptionPane.showMessageDialog(null,receiptString);

       receiptForm.receiptString = receiptString;
       System.out.println(receiptString);
    }
}

