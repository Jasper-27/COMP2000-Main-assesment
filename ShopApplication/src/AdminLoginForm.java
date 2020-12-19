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

    public AdminLoginForm() {
        Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }

    public void login(){

        String username = txt_username.getText();
        String password = pw_password.getText();
       
        System.out.println(hash(password));
        System.out.println(hash("password"));







    }

    public String hash(String password){
        byte[] salt = "1^�=,5Nsaf32324dfsdfJasperWuzHere����K4".getBytes();


        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));
        String hash = new String(hashedPassword, StandardCharsets.UTF_8);

        String stringSalt = new String(salt, StandardCharsets.UTF_8);
        //System.out.println("Salt: " + stringSalt);
        //System.out.println("Hash: " + hash);
        return hash;
    }







}


