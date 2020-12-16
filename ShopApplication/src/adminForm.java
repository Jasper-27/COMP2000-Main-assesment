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

    public adminForm() {
        btn_updateStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
            }
        });
    }

    public void startup(){
        fillStock();
    }
    public void fillStock(){
        String stock = "";
        for(Item item : mainForm.storeStock){
            stock += item.id + "    |   " + item.stock + "\n";
        }
        textArea1.setText(stock);
    }


}
