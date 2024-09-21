package edu.eci.cvds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Stock de productos, permite agregar productos nuevos y actualizar la cantidad de productos disponibles.
 */
@Service
public class Stock {
    private ArrayList<Product> products;
    private ArrayList<edu.eci.cvds.Observer> agents;

    /**
     * Crea un nuevo inventario vacio con dos agentes predefinidos
     */
    @Autowired
    public Stock(){
        this.products = new ArrayList<>();
        this.agents = new ArrayList<>();
        StockLog log = new StockLog();
        StockAlert alert = new StockAlert();
        agents.add(log);
        agents.add(alert);
    }

    /**
     * Agrega un nuevo producto al inventario.
     * @param newName   nombre del producto
     * @param newPrice  precio del producto
     * @param newQuantity   cantidad en stock del producto
     * @param newCategory   categoria del producto
     */
    public void addProduct(String newName, int newPrice, int newQuantity, String newCategory){
        Product newProduct = new Product(newName, newPrice, newQuantity, newCategory);
        for (Observer agent:agents){
            newProduct.addObserver(agent);
        }
        products.add(newProduct);
    }

    /**
     * Actualiza el stock de un producto
     * @param productName   nombre del producto
     * @param newQuantity   nueva cantidad del producto
     * @return retorna si la actualizacion fue exitosa o no
     */
    public boolean updateProductStock(String productName, int newQuantity){
        Product product = findProductWithName(productName);
        if (product != null && newQuantity > 0){
            product.setQuantity(newQuantity);
            return true;
        }
        return false;
    }

    /**
     * Encuentra un producto por su nombre
     * @param name nombre del producto a encontrar
     * @return retorna el objeto Producto que tiene el nombre dado
     */
    public Product findProductWithName(String name){
        for (Product product:products){
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    /**
     * Retorna si un producto se encuentra en stock
     * @param name  Nombre del producto
     */
    public boolean inStock(String name){
        for (Product product:products){
            if (product.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna el agente Log
     */
    public Observer getLog(){
        for (Observer agent : agents){
            if (agent instanceof StockLog){
                return agent;
            }
        }
        return null;
    }

    /**
     * Retorna el agente alert
     */
    public Observer getAlert(){
        for (Observer agent : agents){
            if (agent instanceof StockAlert){
                return agent;
            }
        }
        return null;
    }
}
