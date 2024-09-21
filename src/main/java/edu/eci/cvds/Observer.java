package edu.eci.cvds;

/**
 * Interfaz necesaria para implementar el patrón de diseño Observador
 */
public interface Observer {
    void update(Product product);
    void setNotified(boolean wasNotified);
    boolean getNotified();
}
