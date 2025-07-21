package ar.com.gg.proyectoIntegrador.entity;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Product> products;

    public Pedido() {
        this.products = new ArrayList<>();
    }

    public void agregarProductoAPedido(Product product){
        this.products.add(product);
    }
}