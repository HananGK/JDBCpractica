package controladores;

import modelos.Cliente;
import repos.ClienteRepoImpl;

import java.sql.SQLException;
import java.util.Map;
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

    public static void modificarCliente(ClienteRepoImpl repoCli, Scanner sc){
        System.out.println("-------------- Modificar cliente --------------");
        System.out.println("Código del cliente a editar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Cliente cliente = repoCli.obtenerPorId(id);
        if (cliente != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("1. Código Cliente: " + cliente.getCodigoCliente());
            System.out.println("2. Nombre Cliente: " + cliente.getNombreCliente());
            System.out.println("3. Nombre Contacto: " + cliente.getNombreContacto());
            System.out.println("4. Apellido Contacto: " + cliente.getApellidoContacto());
            System.out.println("5. Teléfono: " + cliente.getTelefono());
            System.out.println("6. Fax: " + cliente.getFax());
            System.out.println("7. Línea Dirección 1: " + cliente.getLineaDireccion1());
            System.out.println("8. Línea Dirección 2: " + cliente.getLineaDireccion2());
            System.out.println("9. Ciudad: " + cliente.getCiudad());
            System.out.println("10. Región: " + cliente.getRegion());
            System.out.println("11. Pais: " + cliente.getPais());
            System.out.println("12. Código postal: " + cliente.getCodigoPostal());
            System.out.println("13. Código empleado representante de ventas: " + cliente.getCodigoEmpleadoRepVentas());
            System.out.println("14. Límite crédito: " + cliente.getLimiteCredito());

            System.out.println("\nIndique el número del campo que desea modificar: ");
            int numCampo = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduzca el nuevo valor: ");
            String nuevoValor = sc.nextLine();
            switch (numCampo) {
                case 1:
                    cliente.setCodigoCliente(Integer.parseInt(nuevoValor));
                    break;
                case 2:
                    cliente.setNombreCliente(nuevoValor);
                    break;
                case 3:
                    cliente.setNombreContacto(nuevoValor);
                    break;
                case 4:
                    cliente.setApellidoContacto(nuevoValor);
                    break;
                case 5:
                    cliente.setTelefono(nuevoValor);
                    break;
                case 6:
                    cliente.setFax(nuevoValor);
                    break;
                case 7:
                    cliente.setLineaDireccion1(nuevoValor);
                    break;
                case 8:
                    cliente.setLineaDireccion2(nuevoValor);
                    break;
                case 9:
                    cliente.setCiudad(nuevoValor);
                    break;
                case 10:
                    cliente.setRegion(nuevoValor);
                    break;
                case 11:
                    cliente.setPais(nuevoValor);
                    break;
                case 12:
                    cliente.setCodigoPostal(nuevoValor);
                    break;
                case 13:
                    cliente.setCodigoEmpleadoRepVentas(Integer.parseInt(nuevoValor));
                    break;
                case 14:
                    cliente.setLimiteCredito(Float.parseFloat(nuevoValor));
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }

            repoCli.actualizar(cliente);
            System.out.println("\nCliente modificado:");
            System.out.println("1. Código Cliente: " + cliente.getCodigoCliente());
            System.out.println("2. Nombre Cliente: " + cliente.getNombreCliente());
            System.out.println("3. Nombre Contacto: " + cliente.getNombreContacto());
            System.out.println("4. Apellido Contacto: " + cliente.getApellidoContacto());
            System.out.println("5. Teléfono: " + cliente.getTelefono());
            System.out.println("6. Fax: " + cliente.getFax());
            System.out.println("7. Línea Dirección 1: " + cliente.getLineaDireccion1());
            System.out.println("8. Línea Dirección 2: " + cliente.getLineaDireccion2());
            System.out.println("9. Ciudad: " + cliente.getCiudad());
            System.out.println("10. Región: " + cliente.getRegion());
            System.out.println("11. Pais: " + cliente.getPais());
            System.out.println("12. Código postal: " + cliente.getCodigoPostal());
            System.out.println("13. Código empleado representante de ventas: " + cliente.getCodigoEmpleadoRepVentas());
            System.out.println("14. Límite crédito: " + cliente.getLimiteCredito());

        } else {
            System.out.println("No se encontró ningún cliente con ese código.");
        }
    }

    public static void eliminarCliente(ClienteRepoImpl repoCli, Scanner sc){
        System.out.print("Introduce el código del cliente a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Cliente cli = repoCli.obtenerPorId(id);
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
