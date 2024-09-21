package edu.eci.cvds;

import edu.eci.cvds.Observer;
import edu.eci.cvds.Product;

/**
 * Agente que notifica la cantidad en stock de un producto cuando se actualiza y hay en stock menos de 5.
 */
public class StockAlert implements Observer {
    private boolean notified; //Dice si se notificó el último producto actualizado
    @Override
    /**
     * Notifica cuando un producto se actualiza y hay menos de 5 en stock
     */
    public void update(Product product){
        int inStock = product.getQuantity();
        if (inStock < 5){
            System.out.println("ALERT!!! The stock of the Product: "+ product.getName() + "is very low, only  "+ inStock+ " units left");
            setNotified(true);
        }else{
            setNotified(false);
        }
    }

    /**
     * Cambia el estado de notificacion del ultimo producto
     * @param wasNotified nuevo estado de notificacion
     */
    public void setNotified(boolean wasNotified){
        this.notified = wasNotified;
    }
    /**
     * Retorna el estado de notificacion del ultimo producto
     */
    public boolean getNotified(){
        return notified;
    }
}
