import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdminsTest {


    public Admins admins = new Admins();



    @Before
    public void Before() throws Exception {
        Admin admin1 = new Admin("admin", "af461d6ee8058210574d7eeb7a838bc14bd8396fc34d12cf9ad5f5fce448bc9d16d694e879ee593aba3d96b7b1036b10bcf33ab93fb13b3afa0a9756e5188887");
        Admin admin2 = new Admin("admin2", "af461d6ee8058210574d7eeb7a838bc14bd8396fc34d12cf9ad5f5fce448bc9d16d694e879ee593aba3d96b7b1036b10bcf33ab93fb13b3afa0a9756e5188887");

        admins.admins.add(admin1);
        admins.admins.add(admin2);
    }

    @After
    public void After() throws Exception {
        Admins admins = new Admins();
    }

    @Test
    public void getAdmin_test() {
        Admin theAdmin = admins.getAdmin("admin");
        assertTrue(theAdmin.username.equals("admin"));
    }

    @Test
    public void checkAdmin_test() {
        assertTrue(admins.checkAdmin("admin"));
    }

    @Test
    public void hash_test() {
        String correctHash = "af461d6ee8058210574d7eeb7a838bc14bd8396fc34d12cf9ad5f5fce448bc9d16d694e879ee593aba3d96b7b1036b10bcf33ab93fb13b3afa0a9756e5188887";
        String password = "thisString";
        assertTrue(admins.hash(password).equals(correctHash));
    }

    @Test
    public void verifyAdmin_test(){
        assertTrue(admins.verifyAdmin("admin2", "thisString"));
    }

    @Test
    public void verifyAdmin_test_wrongPassword(){
        assertFalse(admins.verifyAdmin("user2", "notThisString"));
    }

    @Test
    public void verifyAdmin_test_usernameWrong(){
        assertFalse(admins.verifyAdmin("NoOne", "notThisString"));
    }


}