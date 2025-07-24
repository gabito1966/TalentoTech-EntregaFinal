package ar.com.gg.proyectoIntegrador.service;

import ar.com.gg.proyectoIntegrador.dto.ProductRequestDTO;
import ar.com.gg.proyectoIntegrador.dto.ProductResponseDTO;
import ar.com.gg.proyectoIntegrador.entity.Product;
import ar.com.gg.proyectoIntegrador.exception.ProductNotFoundException;
import ar.com.gg.proyectoIntegrador.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ModelMapper modelMapper;
    private final Path imageUploadPath = Paths.get("uploads/images/");

    @Autowired
    public ProductService(ProductRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    // Crear un nuevo producto
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {
        Product product = modelMapper.map(dto, Product.class);

        MultipartFile image = dto.getImage();
        if (image != null && !image.isEmpty()) {
            try {
                if (!Files.exists(imageUploadPath)) {
                    Files.createDirectories(imageUploadPath);
                }

                String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path imagePath = imageUploadPath.resolve(imageName);
                Files.copy(image.getInputStream(), imagePath);

                product.setImage(imageName);
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen", e);
            }
        }

        repository.save(product);
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    // Obtener todos los productos
    public List<ProductResponseDTO> getProducts() {
        return repository.findAll()
                .stream()
                .map(this::mapperToDTO)
                .collect(Collectors.toList());
    }

    // Buscar productos por nombre
    public List<ProductResponseDTO> searchProductByName(String queryName) {
        List<Product> found = repository.findByNameContainingIgnoreCase(queryName);
        if (found.isEmpty()) {
            throw new ProductNotFoundException("No se encontraron productos con el nombre: " + queryName);
        }
        return found.stream().map(this::mapperToDTO).collect(Collectors.toList());
    }

    // Buscar por ID
    public ProductResponseDTO searchProductById(Long id) {
        return repository.findById(id)
                .map(this::mapperToDTO)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    // Actualizar producto
    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + id + " no encontrado"));

        MultipartFile newImage = dto.getImage();
        String imageName = existing.getImage(); // mantener la actual

        if (newImage != null && !newImage.isEmpty()) {
            try {
                if (!Files.exists(imageUploadPath)) {
                    Files.createDirectories(imageUploadPath);
                }

                // Eliminar imagen anterior
                if (imageName != null) {
                    Files.deleteIfExists(imageUploadPath.resolve(imageName));
                }

                // Guardar nueva imagen
                imageName = System.currentTimeMillis() + "_" + newImage.getOriginalFilename();
                Files.copy(newImage.getInputStream(), imageUploadPath.resolve(imageName));

            } catch (IOException e) {
                throw new RuntimeException("Error al actualizar la imagen", e);
            }
        }

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());
        existing.setImage(imageName); // actual o nueva

        repository.save(existing);
        return modelMapper.map(existing, ProductResponseDTO.class);
    }

    // Eliminar producto y su imagen
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Producto con ID " + id + " no encontrado"));

        // Eliminar imagen del disco
        if (product.getImage() != null) {
            try {
                Files.deleteIfExists(imageUploadPath.resolve(product.getImage()));
            } catch (IOException e) {
                System.err.println("No se pudo eliminar la imagen: " + product.getImage());
            }
        }

        repository.deleteById(id);
    }

    private ProductResponseDTO mapperToDTO(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }
}
