package com.maria.springboot.backend.apirest.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maria.springboot.backend.apirest.models.entity.Cliente;
import com.maria.springboot.backend.apirest.models.entity.Producto;
import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@GetMapping({"/index","/","","/home"})
	public String index() {
		return "index";
	}
	
	// CLIENTES
	
	@Autowired
	private ClienteRestController clienteController;
	
	@GetMapping("clientes/listar")
	public String listarClientes(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteController.index());
		return "clientes/listar";
	}
	
	@GetMapping("clientes/crear")
	public String mostrarCrearCliente(Cliente cliente) {
		return "clientes/crear";
	}
	
	@PostMapping("clientes/crear")
	public String crearCliente(@Valid Cliente cliente, BindingResult result) {
		clienteController.create(cliente, result);
        return "redirect:/app/clientes/listar";
	}
	
	@GetMapping("clientes/modificar/{id}")
	public String mostrarModificarCliente(@PathVariable("id") long id, Model model) {
		model.addAttribute("cliente", clienteController.show(id).getBody());
		model.addAttribute("clienteMod", new Cliente());
		return "clientes/modificar";
	}
	
	@PostMapping("clientes/modificar")
	public String modificarCliente(@Valid Cliente clienteMod, BindingResult result) {
	    Cliente cliente = (Cliente) clienteController.show(clienteMod.getId()).getBody();
	    clienteMod.setCreateAt(cliente.getCreateAt());
	    clienteController.update(clienteMod, result, clienteMod.getId());
	        return "redirect:/app/clientes/listar";
	}
	
	@GetMapping("clientes/eliminar/{id}")
	public String eliminarCliente(@PathVariable("id") long id) {
		clienteController.delete(id);
		return "redirect:/app/clientes/listar";
	}
	
	// PRODUCTOS
	
	@Autowired
	private ProductoRestController productoController;
	
	@GetMapping("productos/listar")
	public String listarProductos(Model model) {
		model.addAttribute("titulo", "Listado de productos");
		model.addAttribute("productos", productoController.index());
		return "productos/listar";
	}
	
	@GetMapping("productos/crear")
	public String mostrarCrearProducto(Producto producto) {
		return "productos/crear";
	}
	
	@PostMapping("productos/crear")
	public String crearProducto(@Valid Producto producto, BindingResult result) {
		productoController.create(producto, result);
        return "redirect:/app/productos/listar";
	}
	
	@GetMapping("productos/modificar/{codproducto}")
	public String mostrarModificarProducto(@PathVariable("codproducto") long codproducto, Model model) {
		model.addAttribute("producto", productoController.show(codproducto).getBody());
		model.addAttribute("productoMod", new Producto());
		return "productos/modificar";
	}
	
	@PostMapping("productos/modificar")
	public String modificarProducto(@Valid Producto productoMod, BindingResult result) {
		productoController.update(productoMod, result, productoMod.getCodproducto());
	        return "redirect:/app/productos/listar";
	}
	
	@GetMapping("productos/eliminar/{codproducto}")
	public String eliminarProducto(@PathVariable("codproducto") long codproducto) {
		productoController.delete(codproducto);
		return "redirect:/app/productos/listar";
	}
	
	// COMPRAS
	
	@Autowired
	private ProductoClienteRestController comprasController;
	
	@GetMapping("productos_clientes/listar")
	public String listarCompras(Model model) {
		model.addAttribute("titulo", "Listado de compras");
		model.addAttribute("compras", comprasController.index());
		return "productos_clientes/listar";
	}
	
	@GetMapping("productos_clientes/crear")
	public String mostrarCrearCompra(ProductoCliente compra) {
		return "productos_clientes/crear";
	}
	
	@PostMapping("productos_clientes/crear")
	public String crearCompra(@Valid ProductoCliente compra, BindingResult result) {
		comprasController.create(compra, result);
        return "redirect:/app/productos_clientes/listar";
	}
	
	@GetMapping("productos_clientes/modificar/{fechaCompra}/{idCliente}/{codProducto}")
	public String mostrarModificarCompra(@PathVariable("fechaCompra") Date fechaCompra, 
			@PathVariable("idCliente") Long idCliente, @PathVariable("codProducto") Long codProducto, Model model) {
		ProductoClienteID id =  new ProductoClienteID();
		id.setFechaCompra(fechaCompra);
		id.setCodProducto(codProducto);
		id.setIdCliente(idCliente);
		model.addAttribute("compra", comprasController.show(id).getBody());
		model.addAttribute("compraMod", new ProductoCliente());
		return "productos_clientes/modificar";
	}
	
	@PostMapping("productos_clientes/modificar")
	public String modificarCompra(@Valid ProductoCliente compraMod, BindingResult result) {
		comprasController.update(compraMod, result, compraMod.getId());
		return "redirect:/app/productos_clientes/listar";
	}
	
	@GetMapping("productos_clientes/eliminar/{fechaCompra}/{idCliente}/{codProducto}")
	public String eliminarCompra(@PathVariable("fechaCompra") Date fechaCompra, 
			@PathVariable("idCliente") Long idCliente, @PathVariable("codProducto") Long codProducto) {
		ProductoClienteID id =  new ProductoClienteID();
		id.setFechaCompra(fechaCompra);
		id.setCodProducto(codProducto);
		id.setIdCliente(idCliente);
		comprasController.delete(id);
		return "redirect:/app/productos_clientes/listar";
	}
}