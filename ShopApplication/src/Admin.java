public class Admin {
    private String username;
    private int passwordHash;


    //Constructor
    public Admin(String in_username, int in_passwordHash){
        username = in_username;
        passwordHash = in_passwordHash;
    }

    public boolean verify(String in_password){
        if (in_password.hashCode() == passwordHash){
            return true;
        }else {
            return false;
        }
    }
}
