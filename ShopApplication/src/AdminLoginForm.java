import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminLoginForm {
    public JPanel panel1;
    private JPasswordField pw_password;
    private JTextField txt_username;
    private JButton Login;

    private Admins admins = new Admins();



    public AdminLoginForm() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void login(){
        admins.loadFile();

        String username = txt_username.getText();
        String password = pw_password.getText();

        System.out.println("CHECK_1");

        if (admins.checkAdmin(username) == false){
            JOptionPane.showMessageDialog(null,"Invalid username");
            return;
        }

        if (admins.verifyAdmin(username, password) == true){
            System.out.println("login");


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


