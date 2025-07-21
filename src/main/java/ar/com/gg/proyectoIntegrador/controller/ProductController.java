package ar.com.gg.proyectoIntegrador.controller;

import ar.com.gg.proyectoIntegrador.dto.ProductRequestDTO;
import ar.com.gg.proyectoIntegrador.dto.ProductResponseDTO;
import ar.com.gg.proyectoIntegrador.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    // Crear un nuevo producto
    @PostMapping("/")
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.createProduct(requestDTO));
    }

    // Obtener listado de productos
    public List<ProductResponseDTO> getProducts() {
        return this.service.getProducts();
    }

    // Buscar productos por nombre
    @GetMapping("/search")
    public List<ProductResponseDTO> searchProductsByName(@RequestParam String queryName) {
        return this.service.searchProductByName(queryName);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ProductResponseDTO searchProductsById(@PathVariable Long id) {
        return this.service.searchProductById(id);
    }

    public ProductResponseDTO updateProduct(@PathVariable Long id, @RequestBody ProductRequestDTO requestDTO) {
        return this.service.updateProduct(id, requestDTO);
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ProductResponseDTO deleteProduct(@PathVariable Long id) {
        return this.service.deleteProduct(id);
    }
}
