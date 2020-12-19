import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.security.SecureRandom;
import java.util.Random;


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
        }else {
            JOptionPane.showMessageDialog(null,"Invalid password");
            return;
        }

        

    }









}


