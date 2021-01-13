import javax.swing.*;

public class AdminLoginForm {
    public JPanel panel1;
    private JPasswordField pw_password;
    private JTextField txt_username;
    private JButton btn_login;

    private Admins admins = new Admins();

    public AdminLoginForm() {
        btn_login.addActionListener(e -> login());
    }

    public void login(){
        admins.loadFile();

        String username = txt_username.getText();
        String password = pw_password.getText();

        if (admins.checkAdmin(username) == false){
            JOptionPane.showMessageDialog(null,"Invalid username");
            return;
        }


        if (admins.verifyAdmin(username, password) == true){
            AdminForm adminForm = new AdminForm();
            MainForm.adminFrame.setContentPane(adminForm.panel1);
            MainForm.adminFrame.pack();
            MainForm.adminFrame.setSize(500, 400);

        }else {
            JOptionPane.showMessageDialog(null,"Invalid password");
            return;
        }
    }
}


