package com.utn.productos_api.service;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.exception.ProductoNotFoundException;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// Servicio que contiene la lógica de negocio para gestionar productos

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Crea un nuevo producto
     * @param productoDTO DTO con los datos del producto
     * @return DTO del producto creado con su ID
     */
    @Transactional
    public ProductoResponseDTO crearProducto(ProductoDTO productoDTO) {
        Producto producto = convertirDTOaEntidad(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        return convertirEntidadAResponseDTO(productoGuardado);
    }

    /**
     * Obtiene todos los productos
     * @return Lista de productos
     */
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirEntidadAResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un producto por su ID
     * @param id ID del producto
     * @return DTO del producto encontrado
     * @throws ProductoNotFoundException si no existe el producto
     */
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
        return convertirEntidadAResponseDTO(producto);
    }

    /**
     * Obtiene productos por categoría
     * @param categoria Categoría a filtrar
     * @return Lista de productos de esa categoría
     */
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> obtenerPorCategoria(Categoria categoria) {
        return productoRepository.findByCategoria(categoria)
                .stream()
                .map(this::convertirEntidadAResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza completamente un producto
     * @param id ID del producto a actualizar
     * @param productoDTO DTO con los nuevos datos
     * @return DTO del producto actualizado
     * @throws ProductoNotFoundException si no existe el producto
     */
    @Transactional
    public ProductoResponseDTO actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        // Actualizar campos
        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setStock(productoDTO.getStock());
        productoExistente.setCategoria(productoDTO.getCategoria());

        Producto productoActualizado = productoRepository.save(productoExistente);
        return convertirEntidadAResponseDTO(productoActualizado);
    }

    /**
     * Actualiza solo el stock de un producto
     * @param id ID del producto
     * @param stockDTO DTO con el nuevo stock
     * @return DTO del producto actualizado
     * @throws ProductoNotFoundException si no existe el producto
     */
    @Transactional
    public ProductoResponseDTO actualizarStock(Long id, ActualizarStockDTO stockDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));

        producto.setStock(stockDTO.getStock());
        Producto productoActualizado = productoRepository.save(producto);
        return convertirEntidadAResponseDTO(productoActualizado);
    }

    /**
     * Elimina un producto
     * @param id ID del producto a eliminar
     * @throws ProductoNotFoundException si no existe el producto
     */
    @Transactional
    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        productoRepository.deleteById(id);
    }

    // ========== MÉTODOS AUXILIARES DE CONVERSIÓN ==========

    /**
     * Convierte ProductoDTO a entidad Producto
     */
    private Producto convertirDTOaEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());
        return producto;
    }

    /**
     * Convierte entidad Producto a ProductoResponseDTO
     */
    private ProductoResponseDTO convertirEntidadAResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getCategoria()
        );
    }
}
