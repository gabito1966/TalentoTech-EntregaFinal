package ar.com.gg.proyectoIntegrador.service;

import ar.com.gg.proyectoIntegrador.dto.ProductRequestDTO;
import ar.com.gg.proyectoIntegrador.dto.ProductResponseDTO;
import ar.com.gg.proyectoIntegrador.entity.Product;
import ar.com.gg.proyectoIntegrador.exception.ProductNotFoundException;
import ar.com.gg.proyectoIntegrador.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    // Crear un nuevo producto
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = modelMapper.map(productRequestDTO, Product.class);
        this.repository.save(product);
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    // Obtener todos los productos (sin paginación)
    /*public List<ProductResponseDTO> getProducts() {
        return this.repository.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }*/

    public List<ProductResponseDTO> getProducts() {
        List<Product> products = this.repository.findAll();
        System.out.println("Productos recuperados: " + products.size());  // Agrega este log

        return products.stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .collect(Collectors.toList());
    }


    public ProductResponseDTO searchProductById(Long id) {
        return this.repository.findById(id)
                .map(this::mapperToDTO)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    // Buscar productos por nombre (sin paginación)
    public List<ProductResponseDTO> searchProductByName(String queryName) {
        List<Product> foundProducts = this.repository.findByNameContainingIgnoreCase(queryName);

        if (foundProducts.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con el nombre: " + queryName);
        }

        return foundProducts.stream()
                .map(this::mapperToDTO)
                .collect(Collectors.toList());
    }

    // Actualizar un producto existente
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO productRequestDTO) {
        Product product = this.repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + id + " no encontrado"));

        modelMapper.map(productRequestDTO, product);
        this.repository.save(product);
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    // Eliminar un producto
    public void deleteProduct(Long id) {
        if (!this.repository.existsById(id)) {
            throw new ProductNotFoundException("Producto con ID " + id + " no encontrado");
        }
        this.repository.deleteById(id);
    }

    private ProductResponseDTO mapperToDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

}
