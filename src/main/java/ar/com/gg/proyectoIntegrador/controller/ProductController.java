package ar.com.gg.proyectoIntegrador.controller;

import ar.com.gg.proyectoIntegrador.dto.ProductRequestDTO;
import ar.com.gg.proyectoIntegrador.dto.ProductResponseDTO;
import ar.com.gg.proyectoIntegrador.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> products = productService.getProducts();
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(products); // 204
        }
        return ResponseEntity.status(HttpStatus.OK).body(products); // 200
    }

    // Buscar productos por nombre
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchProductByName(@RequestParam String name) {
        List<ProductResponseDTO> products = productService.searchProductByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(products); // 404
        }
        return ResponseEntity.status(HttpStatus.OK).body(products); // 200
    }

    // Crear un nuevo producto con imagen y parámetros
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestParam MultipartFile image,  // Imagen del producto
            @RequestParam String name,          // Nombre del producto
            @RequestParam String description,   // Descripción
            @RequestParam Double price,         // Precio
            @RequestParam Integer stock) {      // Stock

        // Crear el DTO para el producto con los datos recibidos
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setImage(image); // Setear la imagen
        productRequestDTO.setName(name);
        productRequestDTO.setDescription(description);
        productRequestDTO.setPrice(price);
        productRequestDTO.setStock(stock);

        // Llamar al servicio para crear el producto
        ProductResponseDTO createdProduct = productService.createProduct(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct); // 201
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price,
            @RequestParam Integer stock) {

        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setImage(image); // puede ser null
        productRequestDTO.setName(name);
        productRequestDTO.setDescription(description);
        productRequestDTO.setPrice(price);
        productRequestDTO.setStock(stock);

        ProductResponseDTO updatedProduct = productService.updateProduct(id, productRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }


    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
    }
}
