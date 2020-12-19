import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admins {

    public List<Admin> admins = new ArrayList();
    public String dataFile = "Resources/admin.txt";


    public boolean verifyAdmin(String username, String password){
        System.out.println("CHECK_2");
        Admin admin = getAdmin(username);
        System.out.println("CHECK_4");

        if (admin.passwordHash.equals(hash(password))){
            return true;
        }else{
            return false;
        }
    }

    public Admin getAdmin(String username){
        int pos = -1;
        for(Admin admin : admins){
            if(admin.username.equals(username)){
                pos = admins.indexOf(admin);
            }
        }
        System.out.println(pos);

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


    public void loadFile(){
        System.out.println("Loading file:");

        //Item tempItem = new Item(null, null, null);
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

                    System.out.println(tempAdmin.username + " " + tempAdmin.passwordHash);
                    admins.add(tempAdmin);
                }catch(Exception e){
                    System.out.println(e);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) { //Prints an error if the file is not found
            e.printStackTrace();
        }

        System.out.println("File loading complete");
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
        return hash;
    }
}
