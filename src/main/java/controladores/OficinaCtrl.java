package controladores;

import entidades.Oficina;
import repos.OficinaRepoImpl;

import java.sql.SQLException;
import java.util.Optional;
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

    public void leerOficina(OficinaRepoImpl repoOfi, Scanner sc) throws SQLException {
        System.out.println("Introduzca el código de la oficina a buscar: ");
        String id = sc.nextLine();
        Optional<Oficina> oficina = repoOfi.obtenerPorId(id);
        if (oficina.isPresent()){
            Oficina ofi = oficina.get();
            System.out.printf("\nCiudad [%s] País [%s] Región [%s] Dirección1 [%s] Dirección2 [%s]", ofi.getCiudad(), ofi.getPais(), ofi.getRegion(), ofi.getLineaDireccion1(), ofi.getLineaDireccion2());
        } else {
            System.out.println("No existe la oficina con el código de oficina " + id);
        }
    }

    public static void modificarOficina(OficinaRepoImpl repoOfi, Scanner sc) {
        System.out.println("------------- Modificar oficina ---------------");
        System.out.println("Código de la oficina a editar: ");
        String id  = sc.next();
        Optional<Oficina> oficina = repoOfi.obtenerPorId(id);

        if (oficina != null) {
            Oficina ofi = oficina.get();
            System.out.println("Oficina encontrada: ");
            System.out.println("0 - Terminar las modificaciones");
            System.out.println(ofi.toFicha());

            boolean flag = true;
            while(flag){
                System.out.println("\nIndique el número de campo que desea modificar: ");
                int numCampo = sc.nextInt();
                sc.nextLine();
                if (numCampo!=0) {
                    System.out.println("Introduzca el nuevo valor: ");
                    String nuevoValor = sc.nextLine();
                    switch (numCampo) {
                        case 1:
                            ofi.setCodigoOficina(nuevoValor);
                            break;
                        case 2:
                            ofi.setCiudad(nuevoValor);
                            break;
                        case 3:
                            ofi.setPais(nuevoValor);
                            break;
                        case 4:
                            ofi.setRegion(nuevoValor);
                            break;
                        case 5:
                            ofi.setCodigoPostal(nuevoValor);
                            break;
                        case 6:
                            ofi.setTelefono(nuevoValor);
                            break;
                        case 7:
                            ofi.setLineaDireccion1(nuevoValor);
                            break;
                        case 8:
                            ofi.setLineaDireccion2(nuevoValor);
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                } else {
                    flag = false;
                }
            }
            repoOfi.actualizar(ofi);
            System.out.println("\nOficina modificada:");
            System.out.println(ofi.toFicha());
        }else {
            System.out.println("No se encontró ningún empleado con ese código");
        }
    }

    public static void eliminarOficina(OficinaRepoImpl repoOfi, Scanner sc) {
        System.out.println("Introduce el código de la oficina a eliminar: ");
        String id = sc.nextLine();
        Optional<Oficina> ofi = repoOfi.obtenerPorId(id);
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
