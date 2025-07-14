package ar.com.gg.proyectoIntegrador.entity;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private int id;
    private List<ItemPedido> items;

    /**
     * Crea un nuevo pedido con un ID autogenerado.
     */
    public Pedido() {
        this.id = contadorId++;
        this.items = new ArrayList<>();
    }

    /**
     * Crea un nuevo pedido con un ID específico (útil para pruebas o restauración).
     */
    public Pedido(int id) {
        this.id = id;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItems() {
        return new ArrayList<>(items); // Devuelve una copia para evitar modificaciones externas
    }

    /**
     * Agrega un item al pedido.
     */
    public void agregarItem(ItemPedido item, int cantidad) {
        if (item == null || cantidad <= 0) return;
        items.add(new ItemPedido(item.getProducto(), cantidad));
    }

    /**
     * Calcula el total del pedido sumando los subtotales de los ítems.
     */
    public double calcularTotal() {
        return items.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido ID: " + id + "\n");
        for (ItemPedido item : items) {
            sb.append(item).append("\n");
        }
        sb.append(String.format("Total: $%.2f", calcularTotal()));
        return sb.toString();
    }


}
