package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de productos
 * Expone endpoints para operaciones CRUD
 */
@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API para gestión de productos del e-commerce")
public class ProductoController {

    private final ProductoService productoService;

    /**
     * GET /api/productos - Listar todos los productos
     */
    @Operation(
            summary = "Listar todos los productos",
            description = "Obtiene una lista completa de todos los productos disponibles en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de productos obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoResponseDTO.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarTodos() {
        List<ProductoResponseDTO> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    /**
     * GET /api/productos/{id} - Obtener producto por ID
     */
    @Operation(
            summary = "Obtener producto por ID",
            description = "Busca y retorna un producto específico mediante su identificador único"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto encontrado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(
            @Parameter(description = "ID del producto a buscar", required = true)
            @PathVariable Long id) {
        ProductoResponseDTO producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    /**
     * GET /api/productos/categoria/{categoria} - Filtrar por categoría
     */
    @Operation(
            summary = "Filtrar productos por categoría",
            description = "Obtiene todos los productos que pertenecen a una categoría específica"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Productos filtrados exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoResponseDTO.class)
                    )
            )
    })
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(
            @Parameter(
                    description = "Categoría a filtrar (ELECTRONICA, ROPA, ALIMENTOS, HOGAR, DEPORTES)",
                    required = true
            )
            @PathVariable Categoria categoria) {
        List<ProductoResponseDTO> productos = productoService.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(productos);
    }

    /**
     * POST /api/productos - Crear nuevo producto
     */
    @Operation(
            summary = "Crear nuevo producto",
            description = "Crea un nuevo producto en el sistema con los datos proporcionados. " +
                    "Todos los campos son validados antes de la creación."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Producto creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos (errores de validación)",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @Parameter(description = "Datos del producto a crear", required = true)
            @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoResponseDTO productoCreado = productoService.crearProducto(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
    }

    /**
     * PUT /api/productos/{id} - Actualizar producto completo
     */
    @Operation(
            summary = "Actualizar producto completo",
            description = "Actualiza todos los datos de un producto existente. " +
                    "Se debe enviar el objeto completo con todos los campos."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto actualizado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @Parameter(description = "ID del producto a actualizar", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos del producto", required = true)
            @Valid @RequestBody ProductoDTO productoDTO) {
        ProductoResponseDTO productoActualizado = productoService.actualizarProducto(id, productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    /**
     * PATCH /api/productos/{id}/stock - Actualizar solo el stock
     */
    @Operation(
            summary = "Actualizar stock del producto",
            description = "Actualiza únicamente el stock de un producto existente, " +
                    "dejando el resto de los datos sin modificar. " +
                    "Útil para operaciones de inventario."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stock actualizado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductoResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json")
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Stock inválido (debe ser mayor o igual a 0)",
                    content = @Content(mediaType = "application/json")
            )
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(
            @Parameter(description = "ID del producto", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nuevo valor del stock", required = true)
            @Valid @RequestBody ActualizarStockDTO stockDTO) {
        ProductoResponseDTO productoActualizado = productoService.actualizarStock(id, stockDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    /**
     * DELETE /api/productos/{id} - Eliminar producto
     */
    @Operation(
            summary = "Eliminar producto",
            description = "Elimina permanentemente un producto del sistema. " +
                    "Esta operación no se puede deshacer."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Producto eliminado exitosamente (No Content)"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json")
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto a eliminar", required = true)
            @PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
