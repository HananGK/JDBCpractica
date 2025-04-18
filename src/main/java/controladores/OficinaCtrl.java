package controladores;

import modelos.Oficina;
import repos.OficinaRepoImpl;

import java.util.Scanner;

public class OficinaCtrl {
    public static void crearOficina(OficinaRepoImpl repoOfi, Scanner sc) {
        Oficina nuevo = new Oficina();
        System.out.println("Código oficina: ");
        nuevo.setCodigoOficina(sc.nextLine());

        System.out.println("Ciudad: ");
        nuevo.setCiudad(sc.nextLine());

        System.out.println("País: ");
        nuevo.setPais(sc.nextLine());

        System.out.println("Región: ");
        nuevo.setRegion(sc.nextLine());

        System.out.println("Código postal: ");
        nuevo.setCodigoPostal(sc.nextLine());

        System.out.println("Teléfono: ");
        nuevo.setTelefono(sc.nextLine());

        System.out.println("Linea dirección 1: ");
        nuevo.setLineaDireccion1(sc.nextLine());

        System.out.println("Linea direccion 2: ");
        nuevo.setLineaDireccion2(sc.nextLine());

        repoOfi.crear(nuevo);
    }

    public static void modificarOficina(OficinaRepoImpl repoOfi, Scanner sc) {
        System.out.println("------------- Modificar oficina ---------------");
        System.out.println("Código de la oficina a editar: ");
        String id  = sc.next();
        Oficina oficina = repoOfi.obtenerPorId(id);

        if (oficina != null) {
            System.out.println("Oficina encontrada: ");
            System.out.println("1. Código oficina: " + oficina.getCodigoOficina());
            System.out.println("2. Ciudad: " + oficina.getCiudad());
            System.out.println("3. Pais: " + oficina.getPais());
            System.out.println("4. Region: " + oficina.getRegion());
            System.out.println("5. Código postal: " + oficina.getCodigoPostal());
            System.out.println("6. Teléfono: " + oficina.getTelefono());
            System.out.println("7. Linea direccion 1: " + oficina.getLineaDireccion1());
            System.out.println("8. Linea direccion 2: " + oficina.getLineaDireccion2());

            System.out.println("\nIndique el número de campo que desea modificar: ");
            int numCampo = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduzca el nuevo valor: ");
            String nuevoValor = sc.nextLine();
            switch (numCampo) {
                case 1:
                    oficina.setCodigoOficina(nuevoValor);
                    break;
                case 2:
                    oficina.setCiudad(nuevoValor);
                    break;
                case 3:
                    oficina.setPais(nuevoValor);
                    break;
                case 4:
                    oficina.setRegion(nuevoValor);
                    break;
                case 5:
                    oficina.setCodigoPostal(nuevoValor);
                    break;
                case 6:
                    oficina.setTelefono(nuevoValor);
                    break;
                case 7:
                    oficina.setLineaDireccion1(nuevoValor);
                    break;
                case 8:
                    oficina.setLineaDireccion2(nuevoValor);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            repoOfi.actualizar(oficina);
            System.out.println("\nOficina modificada:");
            System.out.println("1. Código oficina: " + oficina.getCodigoOficina());
            System.out.println("2. Ciudad: " + oficina.getCiudad());
            System.out.println("3. Pais: " + oficina.getPais());
            System.out.println("4. Region: " + oficina.getRegion());
            System.out.println("5. Código postal: " + oficina.getCodigoPostal());
            System.out.println("6. Teléfono: " + oficina.getTelefono());
            System.out.println("7. Linea direccion 1: " + oficina.getLineaDireccion1());
            System.out.println("8. Linea direccion 2: " + oficina.getLineaDireccion2());
        }else {
            System.out.println("No se encontró ningún empleado con ese código");
        }
    }

    public static void eliminarOficina(OficinaRepoImpl repoOfi, Scanner sc) {
        System.out.println("Introduce el código de la oficina a eliminar: ");
        String id = sc.nextLine();
        Oficina ofi = repoOfi.obtenerPorId(id);
        if (ofi != null) {
            repoOfi.eliminar(id);
        } else {
            System.out.println("No se encontró ninguna oficina con ese código.");
        }
    }

    public static void listarOficinas(OficinaRepoImpl repoOfi) {
        System.out.println("-------------- Lista de oficinas ---------------");
        repoOfi.listar().forEach(System.out::println);
    }
}
