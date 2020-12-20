import javax.swing.*;

public class AdminForm {
    public JPanel panel1;
    private JTextArea textArea1;
    private JButton btn_updateStock;
    private JTextField txt_numToAdd;
    private JTextField txt_item;
    private JButton btn_save;

    public AdminForm() {
        btn_updateStock.addActionListener(e -> updateStock());
        btn_save.addActionListener(e -> mainForm.stock.saveFile());
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
            fillStock();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid input");
        }
        txt_numToAdd.setText("");
        txt_item.setText("");
    }
}
