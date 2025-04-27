package controladores;

import entidades.Oficina;
import repos.OficinaRepoImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class OficinaCtrl {
    private final Scanner sc = new Scanner(System.in);
    private final OficinaRepoImpl repoOfi = new OficinaRepoImpl();

    public String crearOficina() throws SQLException {
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

        return repoOfi.guardar(nuevo);
    }

    public void leerOficina() throws SQLException {
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

    public void modificarOficina() throws SQLException {
        System.out.println("------------- Modificar oficina ---------------");
        System.out.println("Código de la oficina a editar: ");
        String id  = sc.next();
        Optional<Oficina> oficina = repoOfi.obtenerPorId(id);

        if (oficina.isPresent()) {
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
            repoOfi.guardar(ofi);
            System.out.println("\nOficina modificada:");
            System.out.println(ofi.toFicha());
        }else {
            System.out.println("No se encontró ninguna oficina con el código " + id);
        }
    }

    public void eliminarOficina() {
        System.out.println("Introduce el código de la oficina a eliminar: ");
        String id = sc.nextLine();
        Optional<Oficina> ofi = repoOfi.obtenerPorId(id);

        if (ofi.isEmpty()){
            System.out.println("Oficina no encontrada.");
            return;
        }

        System.out.println("\nDatos de la oficina a eliminar: ");
        System.out.println(ofi.get().toFicha());
        System.out.println("----------------------------------");
        System.out.println("¿Confimar borrado de la oficina? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")){
            repoOfi.eliminar(id);
        } else {
            System.out.println("Operación cancelada." );
        }
    }

    public void listarOficinas() {
        System.out.println("-------------- Lista de oficinas ---------------");
        repoOfi.listar().forEach(System.out::println);
    }
}
