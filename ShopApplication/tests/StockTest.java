import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StockTest {
    static Stock stock = new Stock();
    static List<String> scannedItems = new ArrayList();

    @Before
    public void before() throws Exception {
        stock = new Stock();
        Item item1 = new Item("0548477052", "mug", 10f, 9);
        Item item2 = new Item("1548477052", "mug2", 121f, 9);
        Item item3 = new Item("2548427052", "milk", 13f, 9);
        Item item4 = new Item("3548477052", "pizza", 1033.23f, 9);

        stock.storeStock.add(item1);
        stock.storeStock.add(item2);
        stock.storeStock.add(item3);
        stock.storeStock.add(item4);
    }

    @After
    public void after() throws Exception {
        scannedItems.clear();
    }

    @Test
    public void testLoadFile_test() throws Exception {
        assertTrue(stock.storeStock.size() > 0);
    }

    @Test
    public void testOrder_test() throws Exception {
        stock.order("0548477052");
        Stock stock2 = new Stock();
        assertNotEquals(stock, stock2);
    }

    @Test
    public void findItemByName_test(){
        int pos = stock.findItemByName("mug");
        System.out.println(pos);
        assertTrue(pos == 0);
    }

    @Test
    public void findItem_test(){
        int pos = stock.findItem("0548477052");
        System.out.println(pos);
        assertTrue(pos == 0);
    }

    @Test
    public void getItem_test(){
        Item item = stock.getItem("2548427052");
        assertTrue(item.name == "milk");
    }


} 
