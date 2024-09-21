package edu.eci.cvds;
import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representacion de un producto con su nombre, precio, cantidad en stock, categoria y agentes interesados
 */
@Document(collection = "products")
public class Product {
    private ArrayList<Observer> observers;
    @Id
    private String name;
    private int price;
    private int quantity;
    private String category;

    /**
     * Crea un nuevo producto
     * @param newName   nombre del producto
     * @param newPrice  precio del producto
     * @param newQuantity cantidad del producto
     * @param newCategory categoria del producto
     */
    public Product(String newName, int newPrice, int newQuantity, String newCategory){
        this.name = newName;
        this.price = newPrice;
        this.quantity = newQuantity;
        this.category = newCategory;
        this.observers = new ArrayList<>();
    }

    /**
     * Retorna el nombre del producto
     */
    public String getName(){
        return name;
    }
    /**
     * Retorna la cantidad en stock del producto
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Actualiza la cantidad en stock del producto
     * @param newQuantity nueva cantidad del producto
     */
    public void setQuantity(int newQuantity){
        this.quantity = newQuantity;
        notifyObservers();
    }

    /**
     * Agrega un nuevo observador para el producto (Patron de diseño)
     * @param newObserver nuevo observador del producto
     */
    public void addObserver(Observer newObserver){
        observers.add(newObserver);
    }

    /**
     * Elimina un observador del producto (Patron de diseño)
     * @param observer observador del producto
     */
    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    /**
     * Notifica a los observadores que hubo un cambio en el objeto
     */
    private void notifyObservers(){
        for (Observer observer : observers) {
            observer.update(this);
        }
    }


}
