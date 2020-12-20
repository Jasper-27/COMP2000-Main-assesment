import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Admin {
    public String username;
    public String passwordHash;

    //Constructor
    public Admin(String in_username, String in_Hash){
        username = in_username;
        passwordHash = in_Hash;
    }

}
