import controladores.ClienteCtrl;
import controladores.EmpleadoCtrl;
import controladores.OficinaCtrl;
import repos.ClienteRepoImpl;
import repos.EmpleadoRepoImpl;
import repos.OficinaRepoImpl;

import java.util.Scanner;

public class JDBCInicio {
    public static void main(String[] args){

        ClienteCtrl clienteCtrl = new ClienteCtrl();
        EmpleadoCtrl empleadoCtrl = new EmpleadoCtrl();
        OficinaCtrl oficinaCtrl = new OficinaCtrl();

        elegirMenu(clienteCtrl, empleadoCtrl, oficinaCtrl);
    }


    public static void elegirMenu(ClienteCtrl clienteCtrl, EmpleadoCtrl empleadoCtrl, OficinaCtrl oficinaCtrl) {
        Scanner sc = new Scanner(System.in);

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
                menuClientes(clienteCtrl);
                break;
            case 2:
                menuEmpleados(empleadoCtrl);
                break;
            case 3:
                menuOficinas(oficinaCtrl);
                break;
            case 4:
                System.out.println("¡Hasta pronto!");
                break;
            default:
                System.out.println("Entrada inválida");
                break;
        }
    }


    public static void menuClientes(ClienteCtrl clienteCtrl) {
        Scanner sc = new Scanner(System.in);
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
                    6. Leer cliente
                    7. Salir""");
            System.out.print("Elige una opción (1-7): ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        clienteCtrl.crearCliente();
                        break;
                    case 2:
                        clienteCtrl.modificarCliente();
                        break;
                    case 3:
                        clienteCtrl.eliminarCliente();
                        break;
                    case 4:
                        clienteCtrl.listarClientes();
                        break;
                    case 5:
                        clienteCtrl.listarClientesConOficina();
                        break;
                    case 6:
                        clienteCtrl.leerCliente();
                        break;
                    case 7:
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


    public static void menuEmpleados(EmpleadoCtrl empleadoCtrl) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        System.out.println("Gestión de empleados");
        System.out.println("--------------------");

        while (!salir) {
            System.out.println("""
                    1. Crear empleado
                    2. Modificar empleado
                    3. Eliminar empleado
                    4. Listar empleados
                    5. Leer empleado
                    6. Salir""");
            System.out.print("Elige una opción (1-6): ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        empleadoCtrl.crearEmpleado();
                        break;
                    case 2:
                        empleadoCtrl.modificarEmpleado();
                        break;
                    case 3:
                        empleadoCtrl.eliminarEmpleado();
                        break;
                    case 4:
                        empleadoCtrl.listarEmpleados();
                        break;
                    case 5:
                        empleadoCtrl.leerEmpleado();
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


    public static void menuOficinas(OficinaCtrl oficinaCtrl) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        System.out.println("Gestión de oficinas");
        System.out.println("--------------------");

        while (!salir) {
            System.out.println("""
                    1. Crear oficina
                    2. Modificar oficina
                    3. Eliminar oficina
                    4. Listar oficinas
                    5. Leer oficina
                    6. Salir""");
            System.out.print("Elige una opción (1-6): ");

            int opcion;
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        oficinaCtrl.crearOficina();
                        break;
                    case 2:
                        oficinaCtrl.modificarOficina();
                        break;
                    case 3:
                        oficinaCtrl.eliminarOficina();
                        break;
                    case 4:
                        oficinaCtrl.listarOficinas();
                        break;
                    case 5:
                        oficinaCtrl.leerOficina();
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
}

