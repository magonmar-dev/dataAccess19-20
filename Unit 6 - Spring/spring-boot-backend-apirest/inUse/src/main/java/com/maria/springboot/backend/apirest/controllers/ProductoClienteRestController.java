package com.maria.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;
import com.maria.springboot.backend.apirest.models.services.IProductoClienteService;

@CrossOrigin(origins={"http://localhost:4200"}) 
@RestController 
@RequestMapping("/api")
public class ProductoClienteRestController {

	@Autowired
	private IProductoClienteService productoClienteService;
	
	@GetMapping("/productosClientes") 
	public List<ProductoCliente> index() {
		return productoClienteService.findAll();
	}
	
	@GetMapping("/productosClientes/{id}") 
	public ResponseEntity<?> show(@PathVariable ProductoClienteID id) {

		ProductoCliente productoCliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoCliente = productoClienteService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en las base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(productoCliente == null) {
			response.put("mensaje", "La compra ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ProductoCliente>(productoCliente, HttpStatus.OK);
	}
	
	@PostMapping("/productosClientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody ProductoCliente productoCliente, BindingResult result) {
		
		ProductoCliente productoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			productoNew = productoClienteService.save(productoCliente);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al insertar la compra en las base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La compra ha sido creada con éxito!");
		response.put("producto", productoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/productosClientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody ProductoCliente productoCliente, BindingResult result, @PathVariable ProductoClienteID id) {
		
		ProductoCliente productoClienteActual = productoClienteService.findById(id);
		
		ProductoCliente productoClienteUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(productoClienteActual == null) {
			response.put("mensaje", "Error: no se puede editar la compra ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			productoClienteActual.setCliente(productoCliente.getCliente());
			productoClienteActual.setProducto(productoCliente.getProducto());
			productoClienteActual.setFechaCompra(productoCliente.getFechaCompra());
			
			productoClienteUpdated = productoClienteService.save(productoClienteActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la compra en las base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La compra ha sido actualizada con éxito!");
		response.put("producto", productoClienteUpdated);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/productos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable ProductoClienteID id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoClienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar la compra de las base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La compra ha sido eliminada con éxito!");
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
