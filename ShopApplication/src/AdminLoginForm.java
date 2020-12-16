import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminLoginForm {
    public JPanel panel1;
    private JPasswordField pw_password;
    private JTextField txt_username;
    private JButton Login;

    public AdminLoginForm() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void login(){

        String in_username = txt_username.getText();
        char[] in_password = pw_password.getPassword();





        String name = "admin";
        String password = "password";


        System.out.println(password.hashCode());

    }

}
