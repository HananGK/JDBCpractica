package controladores;

import modelos.Empleado;
import repos.EmpleadoRepoImpl;

import java.util.Scanner;

public class EmpleadoCtrl {

    public static void crearEmpleado(EmpleadoRepoImpl repoEmp, Scanner sc) {
        Empleado nuevo =  new Empleado();
        System.out.println("Nombre del empleado: ");
        nuevo.setNombre(sc.nextLine());

        System.out.println("Primer apellido: ");
        nuevo.setApellido1(sc.nextLine());

        System.out.println("Segundo apellido: ");
        nuevo.setApellido2(sc.nextLine());

        System.out.println("Extension: ");
        nuevo.setExtension(sc.nextLine());

        System.out.println("Email: ");
        nuevo.setEmail(sc.nextLine());

        System.out.println("Código oficina(TAL-ES, MAD-ES, BCN-ES, PAR-FR, SFC-USA, BOS-USA, TOK-JP, LON-UK, SYD-AU): ");
        nuevo.setCodigoOficina(sc.nextLine());

        System.out.println("Código jefe: ");
        nuevo.setCodigoJefe(sc.nextInt());
        sc.nextLine();

        System.out.println("Puesto: ");
        nuevo.setPuesto(sc.nextLine());

        repoEmp.crear(nuevo);
    }

    public static void modificarEmpleado(EmpleadoRepoImpl repoEmp, Scanner sc) {
        System.out.println("-------------- Modificar empleado ------------");
        System.out.println("Código del empleado a editar: ");
        int id =  sc.nextInt();
        sc.nextLine();
        Empleado empleado = repoEmp.obtenerPorId(id);

        if (empleado != null) {
            System.out.println("Empleado encontrado: ");
            System.out.println("1. Código empleado: " + empleado.getCodigoEmpleado());
            System.out.println("2. Nombre del empleado: " + empleado.getNombre());
            System.out.println("3. Primer apellido: " + empleado.getApellido1());
            System.out.println("4. Segundo apellido: " + empleado.getApellido2());
            System.out.println("5. Extension: " + empleado.getExtension());
            System.out.println("6. Email: " + empleado.getEmail());
            System.out.println("7. Código oficina: " + empleado.getCodigoOficina());
            System.out.println("8. Código jefe: " + empleado.getCodigoJefe());
            System.out.println("9. Puesto: " + empleado.getPuesto());

            System.out.println("\nIndique el número de campo que desea modificar: ");
            int numCampo = sc.nextInt();
            sc.nextLine();
            System.out.println("Introduzca el nuevo valor: ");
            String nuevoValor = sc.nextLine();
            switch (numCampo) {
                case 1:
                    empleado.setCodigoEmpleado(Integer.parseInt(nuevoValor));
                    break;
                case 2:
                    empleado.setNombre(nuevoValor);
                    break;
                case 3:
                    empleado.setApellido1(nuevoValor);
                    break;
                case 4:
                    empleado.setApellido2(nuevoValor);
                    break;
                case 5:
                    empleado.setExtension(nuevoValor);
                    break;
                case 6:
                    empleado.setEmail(nuevoValor);
                    break;
                case 7:
                    empleado.setCodigoOficina(nuevoValor);
                    break;
                case 8:
                    empleado.setCodigoJefe(Integer.parseInt(nuevoValor));
                    break;
                case 9:
                    empleado.setPuesto(nuevoValor);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            repoEmp.actualizar(empleado);
            System.out.println("\nEmpleado modificado:");
            System.out.println("1. Código empleado: " + empleado.getCodigoEmpleado());
            System.out.println("2. Nombre del empleado: " + empleado.getNombre());
            System.out.println("3. Primer apellido: " + empleado.getApellido1());
            System.out.println("4. Segundo apellido: " + empleado.getApellido2());
            System.out.println("5. Extension: " + empleado.getExtension());
            System.out.println("6. Email: " + empleado.getEmail());
            System.out.println("7. Código oficina: " + empleado.getCodigoOficina());
            System.out.println("8. Código jefe: " + empleado.getCodigoJefe());
            System.out.println("9. Puesto: " + empleado.getPuesto());
        } else {
            System.out.println("No se encontró ningún empleado con ese código");
        }
    }

    public static void eliminarEmpleado(EmpleadoRepoImpl repoEmp, Scanner sc) {
        System.out.println("Introduce el código del empleado a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Empleado emp  = repoEmp.obtenerPorId(id);
        if (emp != null) {
            repoEmp.eliminar(id);
        } else {
            System.out.println("No se encontró ningún empleado con ese código.");
        }
    }

    public static void listarEmpleados(EmpleadoRepoImpl repoEmp) {
        System.out.println("------------- Lista de empleados -------------");
        repoEmp.listar().forEach(System.out::println);
    }
}
