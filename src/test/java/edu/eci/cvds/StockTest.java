package edu.eci.cvds;
import edu.eci.cvds.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Pruebas de unidad para la clase Stock
 */
public class StockTest {
    private Stock stock;
    private Product productTest;
    private Observer log;
    private Observer alert;

    /**
     * Constructor predefinido
     */
    public StockTest(){
    }

    /**
     * Preparacion del entorno
     */
    @BeforeEach
    public void setUp() {
        stock = new Stock();
        log = stock.getLog();
        alert = stock.getAlert();
    }

    @Test
    public void shouldAddANewProduct(){
        stock.addProduct("xbox one s", 1500, 0, "Games");
        assertTrue(stock.inStock("xbox one s"));
    }

    @Test
    public void shouldUpdateProduct(){
        stock.addProduct("xbox one s", 1500, 0, "Games");
        productTest = stock.findProductWithName("xbox one s");
        boolean isOkToUpdate = stock.updateProductStock("xbox one s", 5);
        assertTrue(isOkToUpdate);
        assertEquals(5, productTest.getQuantity());
    }

    @Test
    public void shouldNotifyUpdateProduct(){
        stock.addProduct("xbox one s", 1500, 0, "Games");
        stock.updateProductStock("xbox one s", 5);
        assertTrue(log.getNotified());
        assertFalse(alert.getNotified());
    }

    @Test
    public void shouldNotifyUpdateProductAlert(){
        stock.addProduct("xbox one s", 1500, 0, "Games");
        stock.updateProductStock("xbox one s", 2);
        assertTrue(log.getNotified());
        assertTrue(alert.getNotified());
    }

    @Test
    public void shouldNotUpdateProductNegative(){
        stock.addProduct("xbox one s", 1500, 0, "Games");
        boolean isOkToUpdate = stock.updateProductStock("xbox one s", -10);
        productTest = stock.findProductWithName("xbox one s");
        assertFalse(isOkToUpdate);
        assertEquals(0, productTest.getQuantity());
    }

    @Test
    public void shouldNotUpdateProductNull(){
        boolean isOkToUpdate = stock.updateProductStock("xbox one s", -10);
        assertFalse(isOkToUpdate);
    }
}

