import controladores.ClienteCtrl;
import controladores.EmpleadoCtrl;
import controladores.OficinaCtrl;
import repos.ClienteRepoImpl;
import repos.EmpleadoRepoImpl;
import repos.OficinaRepoImpl;

import java.util.Scanner;

public class JDBCInicio {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ClienteCtrl clienteCtrl = new ClienteCtrl();
        ClienteRepoImpl repoCli = new ClienteRepoImpl();


        EmpleadoCtrl empleadoCtrl = new EmpleadoCtrl();
        EmpleadoRepoImpl repoEmp = new EmpleadoRepoImpl();

        OficinaCtrl oficinaCtrl = new OficinaCtrl();
        OficinaRepoImpl repoOfi = new OficinaRepoImpl();

        elegirMenu(sc, clienteCtrl, repoCli, empleadoCtrl, repoEmp, oficinaCtrl, repoOfi);
    }


    public static void elegirMenu(Scanner sc, ClienteCtrl clienteCtrl, ClienteRepoImpl repoCli, EmpleadoCtrl empleadoCtrl, EmpleadoRepoImpl repoEmp, OficinaCtrl oficinaCtrl, OficinaRepoImpl repoOfi) {
        System.out.println("=========================================");
        System.out.println("Bienvenido a la aplicación de jardinería");
        System.out.println("=========================================");

        System.out.println("Elige que quieres gestionar(1-4): ");
        System.out.println("""
                1. Clientes
                2. Empleados
                3. Oficinas
                4. Salir""");

        int opcion = sc.nextInt();
        sc.nextLine();
        switch(opcion){
            case 1:
                menuClientes(clienteCtrl, repoCli, sc);
                break;
            case 2:
                menuEmpleados(empleadoCtrl, repoEmp, sc);
                break;
            case 3:
                menuOficinas(oficinaCtrl, repoOfi, sc);
                break;
            case 4:
                System.out.println("¡Hasta pronto!");
                break;
            default:
                System.out.println("Entrada inválida");
                break;
        }
    }


    public static void menuClientes(ClienteCtrl clienteCtrl, ClienteRepoImpl repoCli, Scanner sc) {
        boolean salir = false;

        System.out.println("Gestión de clientes");
        System.out.println("--------------------");

        while (!salir) {
            System.out.println("""
                    1. Crear cliente
                    2. Modificar cliente
                    3. Eliminar cliente
                    4. Listar clientes
                    5. Listar clientes con su oficina
                    6. Salir""");
            System.out.print("Elige una opción (1-6): ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        clienteCtrl.crearCliente(repoCli, sc);
                        break;
                    case 2:
                        clienteCtrl.modificarCliente(repoCli, sc);
                        break;
                    case 3:
                        clienteCtrl.eliminarCliente(repoCli, sc);
                        break;
                    case 4:
                        clienteCtrl.listarClientes(repoCli);
                        break;
                    case 5:
                        clienteCtrl.listarClientesConOficina(repoCli);
                        break;
                    case 6:
                        System.out.println("¡Hasta pronto!");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida, intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor introduce un número.");
                sc.nextLine();
            }
        }
    }


    public static void menuEmpleados(EmpleadoCtrl empleadoCtrl, EmpleadoRepoImpl repoEmp, Scanner sc) {
        boolean salir = false;

        System.out.println("Gestión de empleados");
        System.out.println("--------------------");

        while (!salir) {
            System.out.println("""
                    1. Crear empleado
                    2. Modificar empleado
                    3. Eliminar empleado
                    4. Listar empleados
                    5. Salir""");
            System.out.print("Elige una opción (1-5): ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        empleadoCtrl.crearEmpleado(repoEmp, sc);
                        break;
                    case 2:
                        empleadoCtrl.modificarEmpleado(repoEmp, sc);
                        break;
                    case 3:
                        empleadoCtrl.eliminarEmpleado(repoEmp, sc);
                        break;
                    case 4:
                        empleadoCtrl.listarEmpleados(repoEmp);
                        break;
                    case 5:
                        System.out.println("¡Hasta pronto!");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida, intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor introduce un número.");
                e.printStackTrace();
                sc.nextLine();
            }
        }

    }


    public static void menuOficinas(OficinaCtrl oficinaCtrl, OficinaRepoImpl repoOfi, Scanner sc) {
        boolean salir = false;

        System.out.println("Gestión de oficinas");
        System.out.println("--------------------");

        while (!salir) {
            System.out.println("""
                    1. Crear oficina
                    2. Modificar oficina
                    3. Eliminar oficina
                    4. Listar oficinas
                    5. Salir""");
            System.out.print("Elige una opción (1-5): ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        oficinaCtrl.crearOficina(repoOfi, sc);
                        break;
                    case 2:
                        oficinaCtrl.modificarOficina(repoOfi, sc);
                        break;
                    case 3:
                        oficinaCtrl.eliminarOficina(repoOfi, sc);
                        break;
                    case 4:
                        oficinaCtrl.listarOficinas(repoOfi);
                        break;
                    case 5:
                        System.out.println("¡Hasta pronto!");
                        salir = true;
                        break;
                    default:
                        System.out.println("Opción no válida, intenta de nuevo.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor introduce un número.");
                e.printStackTrace();
                sc.nextLine();
            }
        }

    }
}

