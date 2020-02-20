/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maria.sistemaincidencias;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author maria
 */
public class SistemaIncidencias {
    
    static Scanner sc = new Scanner(System.in);
    static Session sesion = NewHibernateUtil.getSessionFactory().openSession();
    
    static Personas usuarioActual = null;
    static boolean administrador = false;
    
    private static void addUser() {
        
        System.out.print("Código: ");
        int codigo = Integer.parseInt(sc.nextLine());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Contraseña: ");
        String password = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        
        Transaction trans = sesion.beginTransaction();
        Personas p = new Personas(codigo, nombre, password, email);
        sesion.save(p);
        trans.commit();
        System.out.println();
    }
    
    private static void addDevice() {
        
        System.out.print("Código: ");
        int codigo = Integer.parseInt(sc.nextLine());
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ubicación: ");
        String ubicacion = sc.nextLine();
        
        Transaction trans = sesion.beginTransaction();
        Equipos e = new Equipos(codigo, nombre, ubicacion);
        sesion.save(e);
        trans.commit();
        System.out.println();
    }
    
    private static void searchUser() {
        
        boolean encontrado = false;
        
        System.out.print("Nombre del usuario a buscar: ");
        String nombreBuscado = sc.nextLine();
        
        Query consulta = sesion.createQuery("from Personas");
        List<Personas> resultados = consulta.list();
        for(Personas resultado : resultados) {
            if(resultado.getNombre().contains(nombreBuscado)) {
                encontrado = true;
                System.out.print(resultado.getCodigo() + " - "
                + resultado.getNombre());
                if(resultado.getEmail() != null)
                    System.out.println(", Email: " + resultado.getEmail());
                else
                    System.out.println("");
            }
        }
        
        if(!encontrado)
            System.out.println("No hay ningún usuario con ese nombre");
        System.out.println("");
    }
    
    private static void searchDevice() {
        
        String sql = "";
        
        System.out.println("¿Quiere buscar por nombre o código? (N/C)");
        String opcionBuscar = sc.nextLine().toUpperCase();
        
        if(opcionBuscar.equals("N")) {
            System.out.print("Nombre del equipo a buscar: ");
            String nombreBuscado = sc.nextLine();
            sql = "from Equipos where nombre like '%"+nombreBuscado+"%'";
        } else if(opcionBuscar.equals("C")) {
            System.out.print("Código del equipo a buscar: ");
            String codBuscado = sc.nextLine();
            sql = "from Equipos where codigo='"+codBuscado+"'";
        }
        
        Query consulta = sesion.createQuery(sql);
        List<Equipos> resultados = consulta.list();
        for(Equipos resultado : resultados) {
            System.out.println(resultado.getCodigo() + " - "
                + resultado.getNombre() + ", Ubicación: " + resultado.getUbicacion());
        }
        
        if(resultados.isEmpty())
            System.out.println("No hay ningún equipo con ese nombre/código");
        System.out.println("");
    }
    
    private static void editIssue() {
        
        Query consulta = sesion.createQuery("from Avisos");
        List<Avisos> resultados = consulta.list();
        int contador = 0;
        for(Avisos resultado : resultados) {
            System.out.println(contador + ". " 
                    + resultado.getId().getFechayhora() + " - " 
                    + resultado.getDescripcion());
            contador++;
        }
        System.out.println("");
        
        System.out.print("Selecciona la incidencia a modificar: ");
        int numIncidencia = Integer.parseInt(sc.nextLine());
        Avisos incidenciaMod = (Avisos) resultados.get(numIncidencia);
        
        Transaction trans = sesion.beginTransaction();
        System.out.print("Fecha de resolución: ");
        String fechaResol = sc.nextLine();
        if(!fechaResol.equals(""))
            incidenciaMod.setFechayhoraResolucion(fechaResol);
        System.out.print("Detalles resolución: ");
        String detallesResol = sc.nextLine();
        if(!detallesResol.equals(""))
            incidenciaMod.setDetallesResolucion(detallesResol);
        sesion.update(incidenciaMod);
        trans.commit();
        System.out.println();
    }
    
    private static void seePendingIssues() {
        
        Query consulta = sesion.createQuery("from Avisos where fechayhoraResolucion=null");
        List<Avisos> resultados = consulta.list();
        for(Avisos resultado : resultados) {
            System.out.println(resultado.getId().getFechayhora() + " - " 
                    + resultado.getDescripcion());
        }
        System.out.println("");
    }
    
    private static void searchIssue() {
        
        System.out.print("Descripción del aviso: ");
        String descBuscado = sc.nextLine();
        
        Query consulta = sesion.createQuery("from Avisos");
        List<Avisos> resultados = consulta.list();
        for(Avisos resultado : resultados) {
            if(resultado.getDescripcion().contains(descBuscado)) {
                System.out.println(resultado.getId().getFechayhora() + " - " 
                    + resultado.getDescripcion());
                if(resultado.getFechayhoraResolucion() == null) {
                    System.out.println("Sin resolver");
                }
                else {
                    System.out.println(resultado.getFechayhoraResolucion() + " - " 
                        + resultado.getDetallesResolucion());
                }
            }
        }
        System.out.println("");
    }
    
    private static void addIssue(Personas usuarioActual) {
        
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String fecha = hourdateFormat.format(date);
        System.out.print("Código del equipo: ");
        int cod_equipo = Integer.parseInt(sc.nextLine());
        System.out.print("Descripción del aviso: ");
        String descripcion = sc.nextLine();
        
        Transaction trans = sesion.beginTransaction();
        Avisos a = new Avisos();
        a.setId(new AvisosId(usuarioActual.getCodigo(), cod_equipo, fecha));
        a.setDescripcion(descripcion);
        sesion.save(a);
        trans.commit();
        System.out.println();
    }
    
    private static void seeNotices(Personas usuarioActual) {
        
        Query consulta = sesion.createQuery("from Avisos where personas.codigo=" 
                + usuarioActual.getCodigo());
        List<Avisos> resultados = consulta.list();
        for(Avisos resultado : resultados) {
            System.out.println(resultado.getId().getFechayhora() + " - " 
                    + resultado.getDescripcion());
            if(resultado.getFechayhoraResolucion() == null) {
                System.out.println("Sin resolver");
            }
            else {
                System.out.println(resultado.getFechayhoraResolucion() + " - " 
                    + resultado.getDetallesResolucion());
            }
        }
        System.out.println("");
    }
    
    private static void login() {
        
        boolean usuarioEncontrado = false;
        boolean terminadoLogin = false;
        
        do {
            System.out.print("Usuario: ");
            String usuario = sc.nextLine();
            System.out.print("Password: ");
            String password = sc.nextLine();
            
            if(usuario.equals("admin") && password.equals("7890"))
                administrador = true;
            
            Query consulta = sesion.createQuery("from Personas");
            List<Personas> resultados = consulta.list();
            for(Personas resultado : resultados) {
                if(resultado.getNombre().equals(usuario)
                        && resultado.getPassword().equals(password)) {
                    usuarioEncontrado = true;
                    usuarioActual = resultado;
                }
            }
            
            if(usuarioEncontrado) {
                System.out.println("Acceso concedido");
                terminadoLogin = true;
            } else {
                System.out.println("Usuario o contraseña incorrectos");
                    System.out.println("Acceso denegado");
            }
        } while (!terminadoLogin);
        System.out.println("");
    }
    
    private static void menu() {
        
        boolean terminado = false;
        
        do {
            System.out.println("MENÚ");
            String opcion;
            
            if(!administrador) {
                System.out.println("1. Introducir incidencia");
                System.out.println("2. Consultar avisos");
                System.out.println("0. Salir");
                opcion = sc.nextLine();
                System.out.println("");
                
                switch (opcion) {
                    case "1": addIssue(usuarioActual); break;
                    case "2": seeNotices(usuarioActual); break;
                    case "0": terminado = true; break;
                    default: System.out.println("Opción incorrecta"); break;
                }
            } else {
                System.out.println("1. Añadir usuario");
                System.out.println("2. Añadir equipo");
                System.out.println("3. Buscar usuarios por nombre");
                System.out.println("4. Buscar equipos por nombre o código");
                System.out.println("5. Editar incidencia");
                System.out.println("6. Ver incidencias pendientes");
                System.out.println("7. Buscar incidencias por texto del aviso");
                System.out.println("0. Salir");
                opcion = sc.nextLine();
                System.out.println("");
                
                switch (opcion) {
                    case "1": addUser(); break;
                    case "2": addDevice(); break;
                    case "3": searchUser(); break;
                    case "4": searchDevice(); break;
                    case "5": editIssue(); break;
                    case "6": seePendingIssues(); break;
                    case "7": searchIssue(); break;
                    case "0": terminado = true; break;
                    default: System.out.println("Opción incorrecta"); break;
                }
            }
        } while (!terminado);
    }

    public static void main(String[] args) {
        
        login();
        menu();
        sc.close();
    }
}