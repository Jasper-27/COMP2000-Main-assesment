import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AdminsTest {

    public Admins admins = new Admins();


    @Before
    public void setUp() throws Exception {
       admins.loadFile();
    }

    @After
    public void tearDown() throws Exception {
        admins.admins.clear();
    }

    @Test
    public void getAdmin() {
        Admin theAdmin = admins.getAdmin("user1");
        assertTrue(theAdmin.username.equals("user1"));
    }

    @Test
    public void checkAdmin() {
        assertTrue(admins.checkAdmin("user1"));
    }

    @Test
    public void hash() {
        String password = "password1";
        Admin theAdmin = admins.getAdmin("user1");
        assertTrue(admins.hash(password).equals(theAdmin.passwordHash));
    }
}