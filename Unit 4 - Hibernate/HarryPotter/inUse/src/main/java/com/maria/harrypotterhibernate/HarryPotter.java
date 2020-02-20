/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maria.harrypotterhibernate;

import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author maria
 */
public class HarryPotter {
    
    static Scanner sc;
    static Session sesion = NewHibernateUtil.getSessionFactory().openSession();
    
    public static Casas selectHouse(int id_casa) {
        
        Query consulta = sesion.createQuery("FROM Casas WHERE id=" + id_casa);
        List<Casas> casas = consulta.list();
        Casas casa = null;
        if (casas.size() > 0) {
            System.out.println("¿Es esta casa (S/N)? " 
                    + casas.get(0).getNombre());
            String opcion = sc.nextLine().toUpperCase();
            if (opcion.equals("S")) {
                casa = casas.get(0);
            }
        } else {
            System.out.println("No existe una casa con ese id");
        }
        return casa;
    }
    
    public static Personajes selectCharacter(int id_personaje) {
        
        Query consulta = sesion.createQuery("FROM Personajes WHERE id = " 
                + id_personaje);
        List<Personajes> personajes = consulta.list();
        Personajes personaje = null;
        if (personajes.size() > 0) {
            System.out.println("¿Es este personaje (S/N)? " 
                    + personajes.get(0).getNombre());
            String opcion = sc.nextLine().toUpperCase();
            if (opcion.equals("S")) {
                personaje = personajes.get(0);
            }
        } else {
            System.out.println("No existe un personaje con ese id");
        }
        return personaje;
    }

    public static void addCharacter() {

        System.out.print("Introduzca el id del personaje: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id del personaje: ");
            id = Integer.parseInt(sc.nextLine());
        }
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca la varita: ");
        String varita = sc.nextLine();
        System.out.print("Introduzca el id de la casa a la que pertenece: ");
        int id_casa;
        try {
            id_casa = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id de la casa a la que pertenece: ");
            id_casa = Integer.parseInt(sc.nextLine());
        }
        Casas casa = selectHouse(id_casa);
        
        Transaction trans = sesion.beginTransaction();
        Personajes p = new Personajes(id, casa, nombre, varita);
        sesion.save(p);
        trans.commit();
        System.out.println();
    }
    
    public static void addHouse() {
        
        System.out.print("Introduzca el id de la casa: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id de la casa: ");
            id = Integer.parseInt(sc.nextLine());
        }
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el animal: ");
        String animal = sc.nextLine();
        System.out.print("Introduzca el fantasma: ");
        String fantasma = sc.nextLine();
        
        Transaction trans = sesion.beginTransaction();
        Casas c = new Casas(id, nombre, animal, fantasma);
        sesion.save(c);
        trans.commit();
        System.out.println();
    }
    
    public static void addRole() {
        
        System.out.print("Introduzca el id del rol: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id del rol: ");
            id = Integer.parseInt(sc.nextLine());
        }
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        
        Transaction trans = sesion.beginTransaction();
        Rol r = new Rol(id, nombre);
        sesion.save(r);
        trans.commit();
        System.out.println();
    }
    
    public static void addPet() {
        
        System.out.print("Introduzca el id de la mascota: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id de la mascota: ");
            id = Integer.parseInt(sc.nextLine());
        }
        System.out.print("Introduzca el nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduzca el id del personaje al que pertenece: ");
        Personajes personaje = null;
        String id_nuevoPersonaje = sc.nextLine();
        if(!id_nuevoPersonaje.equals("")) {
            int id_personaje;
            try {
                id_personaje = Integer.parseInt(id_nuevoPersonaje);
            } catch(NumberFormatException e) {
                System.out.println("El id debe ser un número");
                System.out.print("Introduzca el id del personaje al que pertenece: ");
                id_personaje = Integer.parseInt(sc.nextLine());
            }
            personaje = selectCharacter(id_personaje);
        }
        
        Transaction trans = sesion.beginTransaction();
        Mascotas m = new Mascotas(id, personaje, nombre);
        sesion.save(m);
        trans.commit();
        System.out.println();
    }
    
    public static void modifyCharacter() {
        
        System.out.print("Introduzca el id del personaje a modificar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id del personaje a modificar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        Query consulta = sesion.createQuery("FROM Personajes WHERE id=" 
                + idBuscado);
        List resultados = consulta.list();
        Personajes personajeMod = (Personajes) resultados.get(0);
        
        Transaction trans = sesion.beginTransaction();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        if(!nuevoNombre.equals(""))
            personajeMod.setNombre(nuevoNombre);
        System.out.print("Nueva varita: ");
        String nuevaVarita = sc.nextLine();
        if(!nuevaVarita.equals(""))
            personajeMod.setVarita(nuevaVarita);
        System.out.print("Nueva casa (id): ");
        String id_nuevaCasa = sc.nextLine();
        if(!id_nuevaCasa.equals("")) {
            int id_casa;
            try {
                id_casa = Integer.parseInt(id_nuevaCasa);
            } catch(NumberFormatException e) {
                System.out.println("El id debe ser un número");
                System.out.print("Introduzca el id de la casa a la que pertenece: ");
                id_casa = Integer.parseInt(sc.nextLine());
            }
            personajeMod.setCasas(selectHouse(id_casa));
        }
        sesion.update(personajeMod);
        trans.commit();
        System.out.println();
    }
    
    public static void modifyHouse() {
        
        System.out.print("Introduzca el id de la casa a modificar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id de la casa a modificar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        Query consulta = sesion.createQuery("FROM Casas WHERE id=" + idBuscado);
        List resultados = consulta.list();
        Casas casaMod = (Casas) resultados.get(0);
        
        Transaction trans = sesion.beginTransaction();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        if(!nuevoNombre.equals(""))
            casaMod.setNombre(nuevoNombre);
        System.out.print("Nueva animal: ");
        String nuevoAnimal = sc.nextLine();
        if(!nuevoAnimal.equals(""))
            casaMod.setAnimal(nuevoAnimal);
        System.out.print("Nueva fantasma: ");
        String nuevoFantasma = sc.nextLine();
        if(!nuevoFantasma.equals(""))
            casaMod.setFantasma(nuevoFantasma);
        sesion.update(casaMod);
        trans.commit();
        System.out.println();
    }
    
    public static void modifyRole() {
        
        System.out.print("Introduzca el id del rol a modificar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id del rol a modificar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        Query consulta = sesion.createQuery("FROM Rol WHERE id=" + idBuscado);
        List resultados = consulta.list();
        Rol rolMod = (Rol) resultados.get(0);
        
        Transaction trans = sesion.beginTransaction();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        if(!nuevoNombre.equals(""))
            rolMod.setNombre(nuevoNombre);
        sesion.update(rolMod);
        trans.commit();
        System.out.println();
    }
    
    public static void modifyPet() {
        
        System.out.print("Introduzca el id de la mascota a modificar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Introduzca el id de la mascota a modificar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        Query consulta = sesion.createQuery("FROM Mascotas WHERE id=" 
                + idBuscado);
        List resultados = consulta.list();
        Mascotas mascotaMod = (Mascotas) resultados.get(0);
        
        Transaction trans = sesion.beginTransaction();
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = sc.nextLine();
        if(!nuevoNombre.equals(""))
            mascotaMod.setNombre(nuevoNombre);
        System.out.print("Nuevo personaje (id): ");
        String id_nuevoPersonaje = sc.nextLine();
        if(!id_nuevoPersonaje.equals("")) {
            int id_personaje;
            try {
                id_personaje = Integer.parseInt(id_nuevoPersonaje);
            } catch(NumberFormatException e) {
                System.out.println("El id debe ser un número");
                System.out.print("Introduzca el id del personaje a la que pertenece: ");
                id_personaje = Integer.parseInt(sc.nextLine());
            }
            mascotaMod.setPersonajes(selectCharacter(id_personaje));
        }
        sesion.update(mascotaMod);
        trans.commit();
        System.out.println();
    }
    
    public static void deleteCharacter() {
        
        System.out.print("Id del personaje a borrar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Id del personaje a borrar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        
        try {
            Query consulta = sesion.createQuery("FROM Personajes WHERE id = " 
                    + idBuscado);
            List<Personajes> personajes = consulta.list();
            if (personajes.size() > 0) {
                System.out.println("¿Es este personaje (S/N)? " 
                        + personajes.get(0).getNombre());
                String opcion = sc.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    Transaction trans = sesion.beginTransaction();
                    sesion.delete(personajes.get(0));
                    trans.commit();
                    System.out.println("Personaje borrado");
                }
            } else {
                System.out.println("No existe un personaje con ese id");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar el personaje");
        }
        System.out.println();
    }
    
    public static void deleteHouse() {
        
        System.out.print("Id de la casa a borrar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Id de la casa a borrar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        
        try {
            Query consulta = sesion.createQuery("FROM Casas WHERE id = " 
                    + idBuscado);
            List<Casas> casas = consulta.list();
            if (casas.size() > 0) {
                System.out.println("¿Es esta casa (S/N)? " 
                        + casas.get(0).getNombre());
                String opcion = sc.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    Transaction trans = sesion.beginTransaction();
                    sesion.delete(casas.get(0));
                    trans.commit();
                    System.out.println("Casa borrada");
                }
            } else {
                System.out.println("No existe una casa con ese id");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar la casa");
        }
        System.out.println();
    }
    
    public static void deleteRole() {
        
        System.out.print("Id del rol a borrar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Id del rol a borrar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        
        try {
            Query consulta = sesion.createQuery("FROM Rol WHERE id=" 
                    + idBuscado);
            List<Rol> roles = consulta.list();
            if (roles.size() > 0) {
                System.out.println("¿Es este rol (S/N)? " 
                        + roles.get(0).getNombre());
                String opcion = sc.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    Transaction trans = sesion.beginTransaction();
                    sesion.delete(roles.get(0));
                    trans.commit();
                    System.out.println("Rol borrado");
                }
            } else {
                System.out.println("No existe un rol con ese id");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar el rol");
        }
        System.out.println();
    }
    
    public static void deletePet() {
        
        System.out.print("Id de la mascota a borrar: ");
        int idBuscado;
        try {
            idBuscado = Integer.parseInt(sc.nextLine());
        } catch(NumberFormatException e) {
            System.out.println("El id debe ser un número");
            System.out.print("Id de la mascota a borrar: ");
            idBuscado = Integer.parseInt(sc.nextLine());
        }
        
        try {
            Query consulta = sesion.createQuery("FROM Mascotas WHERE id="
                    + idBuscado);
            List<Mascotas> mascotas = consulta.list();
            if (mascotas.size() > 0) {
                System.out.println("¿Es esta mascota (S/N)? " 
                        + mascotas.get(0).getNombre());
                String opcion = sc.nextLine().toUpperCase();
                if (opcion.equals("S")) {
                    Transaction trans = sesion.beginTransaction();
                    sesion.delete(mascotas.get(0));
                    trans.commit();
                    System.out.println("Mascota borrada");
                }
            } else {
                System.out.println("No existe una mascota con ese id");
            }
        } catch (Exception e) {
            System.out.println("Error al borrar la mascota");
        }
        System.out.println();
    }
    
    public static void seeHouses() {
        // Mostrar los nombres de las diferentes casas y los personajes asociados a ellas
        System.out.println("Mostrando todas las casas:");
        Query consulta = sesion.createQuery("from Casas");
        List resultados = consulta.list();

        for (Object resultado : resultados) {
            Casas casa = (Casas) resultado;
            System.out.print(casa.getId() + " - " + casa.getNombre() 
                    + "\nPersonajes: ");
            for (Object p : casa.getPersonajeses()) {
                System.out.print(((Personajes) p).getNombre() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void seeCharacters() {
        // Mostrar los nombres de los personajes con el rol que desempeñan y la casa a la que pertenecen.
        System.out.println("Mostrando todos los personajes:");
        Query consulta = sesion.createQuery("from Personajes");
        List resultados = consulta.list();

        for (Object resultado : resultados) {
            Personajes personaje = (Personajes) resultado;
            System.out.print(personaje.getId() + " - " + personaje.getNombre()
                + ". Casa: " + personaje.getCasas().getNombre() + ". Roles: ");
            for (Object r : personaje.getRols()) {
                System.out.print(((Rol) r).getNombre() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public static void seeRoles() {
        // Mostrar los roles y debajo sacar los personajes que lo desempeñan ordenados por nombre.
        System.out.println("Mostrando todos los roles:");
        Query consulta = sesion.createQuery("from Rol");
        List resultados = consulta.list();

        for (Object resultado : resultados) {
            Rol rol = (Rol) resultado;
            System.out.print(rol.getId() + " - " + rol.getNombre() 
                    + "\nPersonajes: ");
            for (Object p : rol.getPersonajeses()) {
                System.out.print(((Personajes) p).getNombre() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
            
    public static void seePets() {
        // Mostrar las mascotas con el nombre de sus dueños.
        System.out.println("Mostrando todas las mascotas:");
        Query consulta = sesion.createQuery("from Mascotas");
        List resultados = consulta.list();

        for (Object resultado : resultados) {
            Mascotas mascota = (Mascotas) resultado;
            System.out.print(mascota.getId() + " - " + mascota.getNombre());
            if(mascota.getPersonajes() != null)
                System.out.println(". Dueño: " + mascota.getPersonajes().getNombre());
            else
                System.out.println();
        }
        System.out.println();
    }
    
    public static void seeCharactersWithoutPet() {
        // Mostrar los personajes que no tienen mascotas.
        System.out.println("Mostrando todos los personajes que no tienen mascota:");
        Query consulta = sesion.createQuery("from Personajes as p where not exists (from Mascotas as m where m.personajes=p)");
        List resultados = consulta.list();

        for (Object resultado : resultados) {
            Personajes personaje = (Personajes) resultado;
            System.out.println(personaje.getId() + " - " + personaje.getNombre());
        }
        System.out.println();
    }
    
    public static void seePetsWithoutOwner() {
        // Mostrar las mascotas sin dueño.
        System.out.println("Mostrando todas las mascotas que no tiene dueño:");
        Query consulta = sesion.createQuery("from Mascotas where personajes=null");
        List resultados = consulta.list();

        for (Object resultado : resultados) {
            Mascotas mascota = (Mascotas) resultado;
            System.out.println(mascota.getId() + " - " + mascota.getNombre());
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        
        boolean terminado = false;
        boolean terminado2 = false;
        sc = new Scanner(System.in);
        do {
            System.out.println("Escoja una opción:");
            System.out.println("1. Personajes");
            System.out.println("2. Casas");
            System.out.println("3. Roles");
            System.out.println("4. Mascotas");
            System.out.println("5. Consultas");
            System.out.println("0. Salir");
            String opcion = sc.nextLine();

            switch(opcion) {
                case "1":
                    do {
                        System.out.println("1. Añadir un nuevo personaje");
                        System.out.println("2. Modificar un personaje");
                        System.out.println("3. Eliminar un personaje");
                        System.out.println("0. Volver al menú principal");
                        String opcion2 = sc.nextLine();
                        
                        if (opcion2.equals("1")) {
                            addCharacter();
                        } else if (opcion2.equals("2")) {
                            modifyCharacter();
                        } else if (opcion2.equals("3")) {
                            deleteCharacter();
                        } else if (opcion2.equals("0")) {
                            terminado2 = true;
                        }
                    } while(!terminado2);
                    break;
                    
                case "2":
                    do {
                        System.out.println("1. Añadir una nueva casa");
                        System.out.println("2. Modificar una casa");
                        System.out.println("3. Eliminar una casa");
                        System.out.println("0. Volver al menú principal");
                        String opcion2 = sc.nextLine();
                        
                        if (opcion2.equals("1")) {
                            addHouse();
                        } else if (opcion2.equals("2")) {
                            modifyHouse();
                        } else if (opcion2.equals("3")) {
                            deleteHouse();
                        } else if (opcion2.equals("0")) {
                            terminado2 = true;
                        }
                    } while(!terminado2);
                    break;
                    
                case "3": 
                    do {
                        System.out.println("1. Añadir un nuevo rol");
                        System.out.println("2. Modificar un rol");
                        System.out.println("3. Eliminar un rol");
                        System.out.println("0. Volver al menú principal");
                        String opcion2 = sc.nextLine();
                        
                        if (opcion2.equals("1")) {
                            addRole();
                        } else if (opcion2.equals("2")) {
                            modifyRole();
                        } else if (opcion2.equals("3")) {
                            deleteRole();
                        } else if (opcion2.equals("0")) {
                            terminado2 = true;
                        }
                    } while(!terminado2); 
                    break;
                    
                case "4": 
                    do {
                        System.out.println("1. Añadir una nueva mascota");
                        System.out.println("2. Modificar una mascota");
                        System.out.println("3. Eliminar una mascota");
                        System.out.println("0. Volver al menú principal");
                        String opcion2 = sc.nextLine();
                        
                        if (opcion2.equals("1")) {
                            addPet();
                        } else if (opcion2.equals("2")) {
                            modifyPet();
                        } else if (opcion2.equals("3")) {
                            deletePet();
                        } else if (opcion2.equals("0")) {
                            terminado2 = true;
                        }
                    } while(!terminado2); 
                    break;
                    
                case "5":
                    do {
                        System.out.println("1. Mostrar los nombres de las diferentes casas y los personajes asociados a ellas.");
                        System.out.println("2. Mostrar los nombres de los personajes con el rol que desempeñan y la casa a la que pertenecen.");
                        System.out.println("3. Mostrar los roles y debajo sacar los personajes que lo desempeñan ordenados por nombre.");
                        System.out.println("4. Mostrar las mascotas con el nombre de sus dueños.");
                        System.out.println("5. Mostrar los personajes que no tienen mascotas.");
                        System.out.println("6. Mostrar las mascotas sin dueño.");
                        System.out.println("0. Volver al menú principal");
                        String opcion2 = sc.nextLine();
                        
                        switch (opcion2) {
                            case "1": seeHouses(); break;
                            case "2": seeCharacters(); break;
                            case "3": seeRoles(); break;
                            case "4": seePets(); break;
                            case "5": seeCharactersWithoutPet(); break;
                            case "6": seePetsWithoutOwner(); break;
                            case "0": terminado2 = true; break;
                            default: System.out.println("Opción incorrecta"); break;
                        }
                    } while(!terminado2); 
                    break;
                    
                case "0": terminado = true; break;
                
                default: System.out.println("Opción incorrecta"); break;
            }
        } while (!terminado);
        sesion.close();
    }
}
