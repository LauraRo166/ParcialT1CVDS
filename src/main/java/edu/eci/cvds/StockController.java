package edu.eci.cvds;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/stockPrincipal")
public class StockController {
    @Autowired
    private Stock stock;
    @PostMapping (value = "addProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProduct(String newName, int newPrice, int newQuantity, String newCategory) {
        stock.addProduct(newName, newPrice, newQuantity, newCategory);
    }
    @PostMapping("updateProductStock")
    public void updateProductStock(@RequestParam String productName,@RequestParam Integer newQuantity){
        stock.updateProductStock(productName, newQuantity);
    }
}

