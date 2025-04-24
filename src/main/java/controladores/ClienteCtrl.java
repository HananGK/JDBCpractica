package controladores;

import entidades.Cliente;
import repos.ClienteRepoImpl;

import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class ClienteCtrl {

    public static void crearCliente(ClienteRepoImpl repoCli,  Scanner sc){
        Cliente nuevo = new Cliente();
        System.out.print("Nombre del cliente: ");
        nuevo.setNombreCliente(sc.nextLine());

        System.out.print("Nombre del contacto: ");
        nuevo.setNombreContacto(sc.nextLine());

        System.out.print("Apellido del contacto: ");
        nuevo.setApellidoContacto(sc.nextLine());

        System.out.print("Teléfono: ");
        nuevo.setTelefono(sc.nextLine());

        System.out.print("Fax: ");
        nuevo.setFax(sc.nextLine());

        System.out.print("Dirección 1: ");
        nuevo.setLineaDireccion1(sc.nextLine());

        System.out.print("Dirección 2: ");
        nuevo.setLineaDireccion2(sc.nextLine());

        System.out.print("Ciudad: ");
        nuevo.setCiudad(sc.nextLine());

        System.out.print("Región: ");
        nuevo.setRegion(sc.nextLine());

        System.out.print("País: ");
        nuevo.setPais(sc.nextLine());

        System.out.print("Código postal: ");
        nuevo.setCodigoPostal(sc.nextLine());

        System.out.println("Código representante de ventas: ");
        nuevo.setCodigoEmpleadoRepVentas(sc.nextInt());
        sc.nextLine();

        System.out.println("Límite de crédito: ");
        nuevo.setLimiteCredito(sc.nextFloat());
        sc.nextLine();

        repoCli.crear(nuevo);
    }

    public void leerCliente(ClienteRepoImpl repoCli, Scanner sc) throws SQLException {
        System.out.println("Introduzca el código del cliente a buscar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Optional<Cliente> cliente = repoCli.obtenerPorId(id);
        if(cliente.isPresent()){
            Cliente cli = cliente.get();
            System.out.printf("\nNombre [%s] Dirección1 [%s] Dirección 2 [%s] Ciudad [%s] CP [%s]", cli.getNombreCliente(),
                    cli.getLineaDireccion1(), cli.getLineaDireccion2(), cli.getCiudad(), cli.getCodigoPostal());
        } else {
            System.out.println("No existe el cliente con el código de cliente " + id);
        }
    }

    public static void modificarCliente(ClienteRepoImpl repoCli, Scanner sc){
        System.out.println("-------------- Modificar cliente --------------");
        System.out.println("Código del cliente a editar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Optional<Cliente> cliente = repoCli.obtenerPorId(id);
        if (cliente != null) {
            Cliente cli = cliente.get();
            System.out.println("Cliente encontrado:");
            System.out.println("0 - Terminar las modificaciones");
            System.out.println(cli.toFicha());

            boolean flag = true;
            while(flag){
                System.out.println("\nIndique el número del campo que desea modificar: ");
                int numCampo = sc.nextInt();
                sc.nextLine();
                if (numCampo!=0){
                    System.out.println("Introduzca el nuevo valor: ");
                    String nuevoValor = sc.nextLine();
                    switch (numCampo) {
                        case 1:
                            System.out.println("No se puede cambiar el código del cliente.");
                            break;
                        case 2:
                            cli.setNombreCliente(nuevoValor);
                            break;
                        case 3:
                            cli.setNombreContacto(nuevoValor);
                            break;
                        case 4:
                            cli.setApellidoContacto(nuevoValor);
                            break;
                        case 5:
                            cli.setTelefono(nuevoValor);
                            break;
                        case 6:
                            cli.setFax(nuevoValor);
                            break;
                        case 7:
                            cli.setLineaDireccion1(nuevoValor);
                            break;
                        case 8:
                            cli.setLineaDireccion2(nuevoValor);
                            break;
                        case 9:
                            cli.setCiudad(nuevoValor);
                            break;
                        case 10:
                            cli.setRegion(nuevoValor);
                            break;
                        case 11:
                            cli.setPais(nuevoValor);
                            break;
                        case 12:
                            cli.setCodigoPostal(nuevoValor);
                            break;
                        case 13:
                            cli.setCodigoEmpleadoRepVentas(Integer.parseInt(nuevoValor));
                            break;
                        case 14:
                            cli.setLimiteCredito(Float.parseFloat(nuevoValor));
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                } else {
                    flag = false;
                }
            }
            repoCli.actualizar(cli);
            System.out.println("\nCliente modificado:");
            System.out.println(cli.toFicha());

        } else {
            System.out.println("No se encontró ningún cliente con ese código.");
        }
    }

    public static void eliminarCliente(ClienteRepoImpl repoCli, Scanner sc){
        System.out.print("Introduce el código del cliente a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Optional<Cliente> cli = repoCli.obtenerPorId(id);
        if (cli != null) {
            repoCli.eliminar(id);
        } else {
            System.out.println("No se encontró ningún cliente con ese código.");
        }
    }

    public static void listarClientes(ClienteRepoImpl repoCli){
        System.out.println("-------------- Lista de clientes ------------");
        repoCli.listar().forEach(System.out::println);
    }

    public static void listarClientesConOficina(ClienteRepoImpl repoCli) throws SQLException {
        System.out.println("-------------- Lista de clientes con la oficina de ventas ------------");
        repoCli.listaDeClientesConOficina().forEach(System.out::println);
        System.out.println("-------------- Ficha de clientes con la  oficina de ventas ------------");
        imprimirFichasClientes(repoCli.fichaClienteConOficina());
    }

    public static void imprimirFichasClientes(Map<Integer, Map<String, String>> fichas) {
        System.out.println("=== LISTADO DE CLIENTES CON OFICINA ASOCIADA ===");

        for (Map.Entry<Integer, Map<String, String>> entrada : fichas.entrySet()) {
            int codigoCliente = entrada.getKey();
            Map<String, String> detalles = entrada.getValue();

            System.out.println("\n▬ Cliente #" + codigoCliente);
            System.out.println("├─ Nombre: " + detalles.get("nombre_cliente"));
            System.out.println("├─ Teléfono: " + detalles.get("telefono"));
            System.out.println("├─ Representante: " + detalles.get("representante_ventas"));
            System.out.println("├─ Código Oficina: " + detalles.get("oficina"));
            System.out.println("└─ Código Postal: " + detalles.get("codigo_postal"));
        }

        System.out.println("\nTotal clientes mostrados: " + fichas.size());
    }
}
