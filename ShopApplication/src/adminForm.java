import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.constant.Constable;

public class adminForm {
    public JPanel panel1;
    private JTextArea textArea1;
    private JButton btn_updateStock;
    private JTextField txt_numToAdd;
    private JTextField txt_item;
    private JButton btn_save;

    public adminForm() {
        btn_updateStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStock();
            }
        });
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.stock.saveFile();
            }
        });
    }

    public void startup(){
        fillStock();
    }
    public void fillStock(){
        String stock = "";
        for(Item item : mainForm.stock.storeStock){
            stock += item.id + "    |   " + item.stock + "\n";
        }
        textArea1.setText(stock);
    }

    public void updateStock(){
        try{
            String StockString = txt_item.getText();
            int numberToAdd = Integer.parseInt(txt_numToAdd.getText());
            System.out.println(StockString + numberToAdd);

            int pos = findItem(StockString);

            mainForm.stock.storeStock.get(pos).stock += numberToAdd;

            fillStock();


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid input");
        }

        txt_numToAdd.setText("");
        txt_item.setText("");
    }

    public int findItem(String in) {

        for(Item item : mainForm.stock.storeStock){
            if(item.id.equals(in)){
                return mainForm.stock.storeStock.indexOf(item);
            }
        }
        return -1; // Returning -1 shows that something went wrong, it should never actually happen
    }

}
