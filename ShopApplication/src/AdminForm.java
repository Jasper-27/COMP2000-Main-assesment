import javax.swing.*;

public class AdminForm {
    public JPanel panel1;
    private JTextArea textArea1;
    private JButton btn_updateStock;
    private JTextField txt_newStock;
    private JTextField txt_item;
    private JButton btn_save;

    public AdminForm() {
        btn_updateStock.addActionListener(e -> updateStock());
        btn_save.addActionListener(e -> MainForm.stock.saveFile());
        fillStock();
    }

    public void fillStock(){
        StringBuilder stock = new StringBuilder();
        for(Item item : MainForm.stock.storeStock){
            stock.append(item.name).append("    |   ").append(item.stock).append("\n");
        }
        textArea1.setText(stock.toString());
    }

    public void updateStock(){
        try{
            String StockString = txt_item.getText();
            int newStock = Integer.parseInt(txt_newStock.getText());
            int pos = MainForm.stock.findItemByName(StockString);
            MainForm.stock.storeStock.get(pos).stock = newStock;
            fillStock();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Invalid input");
        }
        txt_newStock.setText("");
        txt_item.setText("");
    }
}
