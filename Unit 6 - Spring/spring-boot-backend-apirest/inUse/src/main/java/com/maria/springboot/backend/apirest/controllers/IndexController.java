package com.maria.springboot.backend.apirest.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maria.springboot.backend.apirest.models.entity.Cliente;
import com.maria.springboot.backend.apirest.models.entity.Mail;
import com.maria.springboot.backend.apirest.models.entity.Producto;
import com.maria.springboot.backend.apirest.models.entity.ProductoCliente;
import com.maria.springboot.backend.apirest.models.entity.ProductoClienteID;
import com.maria.springboot.backend.apirest.models.entity.Usuario;
import com.maria.springboot.backend.apirest.models.services.ClienteServiceImpl;
import com.maria.springboot.backend.apirest.models.services.MailServiceImpl;
import com.maria.springboot.backend.apirest.models.services.ProductoClienteServiceImpl;
import com.maria.springboot.backend.apirest.models.services.UsuarioServiceImpl;

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
	
	@Autowired
	private ClienteServiceImpl clienteService;
	
	@GetMapping("clientes/listar")
	public String listarClientes(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteController.index());
		return "clientes/listar";
	}
	
	@GetMapping("clientes/crear")
	public String mostrarCrearCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "clientes/crear";
	}
	
	@PostMapping("clientes/crear")
	public String crearCliente(@Valid Cliente cliente, BindingResult result) {
		clienteService.save(cliente);
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
	
	@Autowired
	private ProductoClienteServiceImpl comprasService;
	
	@GetMapping("productos_clientes/listar")
	public String listarCompras(Model model) {
		model.addAttribute("titulo", "Listado de compras");
		model.addAttribute("compras", comprasController.index());
		return "productos_clientes/listar";
	}
	
	@GetMapping("productos_clientes/crear")
	public String mostrarCrearCompra(Model model) {
		model.addAttribute("productos", productoController.index());
		model.addAttribute("clientes", clienteController.index());
		model.addAttribute("compra", new ProductoCliente());
		return "productos_clientes/crear";
	}
	
	@PostMapping("productos_clientes/crear")
	public String crearCompra(@Valid ProductoCliente compra, BindingResult result) {
		comprasService.save(compra);
        return "redirect:/app/productos_clientes/listar";
	}
	
	@GetMapping("productos_clientes/modificar/{fechaCompra}/{idCliente}/{codProducto}")
	public String mostrarModificarCompra(@PathVariable String fechaCompra, @PathVariable Long idCliente, 
			@PathVariable Long codProducto, Model model) throws ParseException { 
		model.addAttribute("productos", productoController.index());
		model.addAttribute("clientes", clienteController.index());
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		ProductoClienteID id =  new ProductoClienteID();
		id.setFechaCompra(formato.parse(fechaCompra));
		id.setCodProducto(codProducto);
		id.setIdCliente(idCliente);
		ProductoCliente compra = (ProductoCliente) comprasController.show(id).getBody();
		model.addAttribute("compra", compra);
		model.addAttribute("compraMod", new ProductoCliente());
		model.addAttribute("fechaActualFormateada", formato.format(compra.getId().getFechaCompra()));
		comprasService.delete(compra.getId());
		return "productos_clientes/modificar";
	}
	
	@PostMapping("productos_clientes/modificar")
	public String modificarCompra(@Valid ProductoCliente compraMod, BindingResult result) {
		comprasService.save(compraMod);
		//comprasController.update(compraMod, result, compraMod.getId());
		return "redirect:/app/productos_clientes/listar";
	}
	
	@GetMapping("productos_clientes/eliminar/{fechaCompra}/{idCliente}/{codProducto}")
	public String eliminarCompra(@PathVariable("fechaCompra") String fechaCompra, 
			@PathVariable("idCliente") Long idCliente, @PathVariable("codProducto") Long codProducto) throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		ProductoClienteID id =  new ProductoClienteID();
		id.setFechaCompra(formato.parse(fechaCompra));
		id.setCodProducto(codProducto);
		id.setIdCliente(idCliente);
		comprasController.delete(id);
		return "redirect:/app/productos_clientes/listar";
	}
	
	// USUARIOS
	
	@Autowired
	private UsuarioServiceImpl usuarioService;
	
	@Autowired
	private MailServiceImpl mailService;
	
	@GetMapping("usuarios/listar")
	public String listarUsuarios(Model model) {
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarioService.findAll());
		return "usuarios/listar";
	}
	
	@GetMapping("usuarios/crear")
	public String mostrarCrearUsuario(Model model) {
		model.addAttribute("clientes", clienteController.index().stream()
				.filter(c -> c.getUsuario()==null).collect(Collectors.toList()));
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("email", new Mail());
		model.addAttribute("action","http://localhost:8080/app/usuarios/crear");
		return "usuarios/crear";
	}
	
	@PostMapping("usuarios/crear")
	public String crearUsuario(@Valid Usuario usuario, @Valid Mail mail, BindingResult result) {
		mailService.save(mail);
		Usuario aux = usuarioService.save(usuario);
		mail.setUsuario(aux);
        return "redirect:/app/usuarios/listar";
	}
	
	/*@GetMapping("usuarios/modificar/{id}")
	public String mostrarModificarUsuario(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioService.findById(id);
		model.addAttribute("usuario", usuario);
		model.addAttribute("usuarioMod", new Usuario());
		model.addAttribute("mail", new Mail());
		model.addAttribute("clientes", clienteController.index().stream()
				.filter(c -> c.getUsuario()==null || c.getId()==usuario.getCliente().getId())
				.collect(Collectors.toList()));
		return "usuarios/modificar";
	}
	
	@PostMapping("usuarios/modificar")
	public String modificarUsuario(@Valid Usuario usuarioMod, Mail mail, BindingResult result) {
		Usuario aux = usuarioService.save(usuarioMod);
		mail.setUsuario(aux);
		mailService.save(mail);
	    return "redirect:/app/usuarios/listar";
	}*/
	
	@GetMapping("usuarios/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id") long id) {
		usuarioService.delete(id);
		return "redirect:/app/usuarios/listar";
	}
	
	// CLIENTES + USUARIOS + MAILS
	
	@GetMapping("clientes_usuarios/listar")
	public String listarClientesUsuarios(Model model, @RequestParam(required=false) String nombre) {
		model.addAttribute("titulo", "Listado de clientes con sus usuarios");
		if(nombre==null) {
			model.addAttribute("clientes", clienteController.index());
		}
		else {
			model.addAttribute("clientes", clienteController.index().stream()
					.filter(c -> c.getNombre().toUpperCase().contains(nombre.toUpperCase()))
					.collect(Collectors.toList()));
		}
		return "clientes_usuarios/listar";
	}
	
	// CLIENTES SIN USUARIOS
	
	@GetMapping("clientes_usuarios/listarSinUsuario")
	public String listarClientesUsuarios(Model model) {
		model.addAttribute("titulo", "Listado de clientes sin usuario");
		model.addAttribute("clientes", clienteController.index().stream()
				.filter(c -> c.getUsuario()==null).collect(Collectors.toList()));
		return "clientes_usuarios/listarSinUsuario";
	}
	
	// USUARIO + PRODUCTOS
	
	@GetMapping("usuarios/productos/{id}")
	public String listarUsuarioProductos(Model model, @PathVariable("id") long id) {
		Usuario usuario = usuarioService.findById(id);
		Cliente cliente = usuario.getCliente();
		model.addAttribute("compras", cliente.getCompras());
		model.addAttribute("nombreUsuario", usuario.getName());
		return "usuarios/productos";
	}
}