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

        scannedItems.add("0548933340");
        scannedItems.add("0548618172");
        stock.loadFile();
    }

    @After
    public void after() throws Exception {
        scannedItems.clear();
        stock = new Stock();
        stock.loadFile();
    }

    @Test
    public void testLoadFile() throws Exception {
        assertTrue(stock.storeStock.size() > 0);
    }

    @Test
    public void testOrder() throws Exception {
        stock.order(scannedItems);
        Stock stock2 = new Stock();
        assertNotEquals(stock, stock2);
    }

} 
