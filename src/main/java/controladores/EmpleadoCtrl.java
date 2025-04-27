package controladores;

import entidades.Empleado;
import repos.EmpleadoRepoImpl;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class EmpleadoCtrl {

    private final Scanner sc = new Scanner(System.in);
    private final EmpleadoRepoImpl repoEmp = new EmpleadoRepoImpl();

    public Integer crearEmpleado() throws SQLException{
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

        return repoEmp.guardar(nuevo);
    }

    public void leerEmpleado() throws SQLException {
        System.out.println("Introduzca el código del empleado a buscar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Optional<Empleado> empleado = repoEmp.obtenerPorId(id);
        if (empleado.isPresent()){
            Empleado emp = empleado.get();
            System.out.printf("\nNombre [%s] Apellido1 [%s] Apellido2 [%s] Email [%s] Puesto [%s]", emp.getNombre(), emp.getApellido1(), emp.getApellido2(), emp.getEmail(), emp.getPuesto());
        } else {
            System.out.println("No existe el empleado con el código de empleado " + id);
        }
    }

    public void modificarEmpleado() throws SQLException {
        System.out.println("-------------- Modificar empleado ------------");
        System.out.println("Código del empleado a editar: ");
        int id =  sc.nextInt();
        sc.nextLine();
        Optional<Empleado> empleado = repoEmp.obtenerPorId(id);

        if (empleado.isPresent()) {
            Empleado emp = empleado.get();
            System.out.println("Empleado encontrado: ");
            System.out.println("0 - Terminar las modificaciones");
            System.out.println(emp.toFicha());

            boolean flag = true;
            while(flag){
                System.out.println("\nIndique el número de campo que desea modificar: ");
                int numCampo = sc.nextInt();
                sc.nextLine();
                if (numCampo != 0) {
                    System.out.println("Introduzca el nuevo valor: ");
                    String nuevoValor = sc.nextLine();
                    switch (numCampo) {
                        case 1:
                            emp.setCodigoEmpleado(Integer.parseInt(nuevoValor));
                            break;
                        case 2:
                            emp.setNombre(nuevoValor);
                            break;
                        case 3:
                            emp.setApellido1(nuevoValor);
                            break;
                        case 4:
                            emp.setApellido2(nuevoValor);
                            break;
                        case 5:
                            emp.setExtension(nuevoValor);
                            break;
                        case 6:
                            emp.setEmail(nuevoValor);
                            break;
                        case 7:
                            emp.setCodigoOficina(nuevoValor);
                            break;
                        case 8:
                            emp.setCodigoJefe(Integer.parseInt(nuevoValor));
                            break;
                        case 9:
                            emp.setPuesto(nuevoValor);
                            break;
                        default:
                            System.out.println("Opción no válida");
                            break;
                    }
                } else {
                    flag = false;
                }
            }
            repoEmp.guardar(emp);
            System.out.println("\nEmpleado modificado:");
            System.out.println(emp.toFicha());
        } else {
            System.out.println("No se encontró ningún empleado con el código " + id);
        }
    }

    public void eliminarEmpleado() {
        System.out.println("Introduce el código del empleado a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Optional<Empleado> emp = repoEmp.obtenerPorId(id);

        if (emp.isEmpty()){
            System.out.println("Empleado no encontrado");
            return;
        }

        System.out.println("\nDatos del empleado a eliminar: ");
        System.out.println(emp.get().toFicha());
        System.out.println("---------------------------------");
        System.out.println("¿Confirmar borrado del empleado? (S/N): ");
        String confirmacion = sc.nextLine();

        if (confirmacion.equalsIgnoreCase("S")){
            repoEmp.eliminar(id);
        } else {
            System.out.println("operación cancelada.");
        }
    }

    public void listarEmpleados() {
        System.out.println("------------- Lista de empleados -------------");
        repoEmp.listar().forEach(System.out::println);
    }
}
