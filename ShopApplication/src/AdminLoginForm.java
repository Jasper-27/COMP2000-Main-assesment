import javax.swing.*;

public class AdminLoginForm {
    public JPanel panel1;
    private JPasswordField pw_password;
    private JTextField txt_username;
    private JButton Login;

    private Admins admins = new Admins();

    public AdminLoginForm() {
        Login.addActionListener(e -> login());
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
            mainForm.adminFrame.setContentPane(adminForm.panel1);
            mainForm.adminFrame.pack();
            mainForm.adminFrame.setSize(500, 400);
            adminForm.startup();
        }else {
            JOptionPane.showMessageDialog(null,"Invalid password");
            return;
        }
    }
}


