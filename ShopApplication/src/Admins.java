import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Admins {
    public List<Admin> admins = new ArrayList();
    public String dataFile = "Resources/admin.txt";

    private static String salt = "023uro2jd0qwjd0d3209dj0qwd312djasperwashere123123";  //Used to add a bt more security

    //Checks if the admin matches one in the file
    public boolean verifyAdmin(String username, String password){
        String hash1 = hash(password);
        String hash2 = getAdmin(username).passwordHash;

        if (hash1.equals(hash2)){
            return true;
        }else{
            return false;
        }
    }

    //Finds the admins username
    public Admin getAdmin(String username){
        int pos = -1;
        for(Admin admin : admins){
            if(admin.username.equals(username)){
                pos = admins.indexOf(admin);
            }
        }
        if (pos == -1){
            return  null;
        }else {
            return admins.get(pos);
        }
    }

    //Check if the admin exists
    public boolean checkAdmin(String username){
        if (getAdmin(username) == null){
            return false;
        }else {
            return true;
        }
    }

    //loads the admin file
    public void loadFile(){
        try {
            File myFile = new File(dataFile);
            Scanner myReader = new Scanner(myFile);

            //While there is another line to read, read it and output the data
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfStr = data.split(";", 2);

                try{
                    String username = arrOfStr[0];
                    String hash = arrOfStr[1];
                    Admin tempAdmin = new Admin(username, hash);
                    admins.add(tempAdmin);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) { //Prints an error if the file is not found
            e.printStackTrace();
        }
    }


    //Generates a password hash for securely saving the password
    public static String hash(String password) {
        StringBuilder hash = new StringBuilder();
        password = salt + password;

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-512");
            byte[] hashedBytes = sha.digest(password.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' ,'g','h','i','j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    'A', 'B', 'C', 'D', 'E', 'F' ,'G','H','I','J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            };
            for (int i = 0; i < hashedBytes.length; i++) {
                byte b = hashedBytes[i];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }

        return hash.toString();
    }
}
