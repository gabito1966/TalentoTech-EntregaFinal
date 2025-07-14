package ar.com.gg.proyectoIntegrador.entity;


    public class ItemPedido {

        private final Producto producto;
        private final int cantidad;

        public ItemPedido(Producto producto, int cantidad) {
            if (producto == null) {
                throw new IllegalArgumentException("El producto no puede ser null.");
            }
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
            }
            this.producto = producto;
            this.cantidad = cantidad;
        }

        public Producto getProducto() {
            return producto;
        }

        public int getCantidad() {
            return cantidad;
        }

        /**
         * Calcula el subtotal del ítem (precio del producto x cantidad).
         */
        public double calcularSubtotal() {
            return producto.getPrecio() * cantidad;
        }

        @Override
        public String toString() {
            return producto.getNombre() + " x " + cantidad + " = $" +
                    String.format("%.2f", calcularSubtotal());
        }
}

