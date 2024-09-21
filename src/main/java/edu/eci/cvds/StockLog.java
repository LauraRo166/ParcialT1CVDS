package edu.eci.cvds;

import edu.eci.cvds.Observer;
import edu.eci.cvds.Product;

/**
 * Agente que notifica la cantidad en stock de un producto cuando se actualiza.
 */
public class StockLog implements Observer {
    private boolean notified; //Dice si se notificó el último producto actualizado
    @Override
    /**
     * Notifica cuando un producto se actualiza
     */
    public void update(Product product){
        int inStock = product.getQuantity();
        System.out.println("Product: "+ product.getName() + " -> "+ inStock+ " units available");
        setNotified(true);
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
