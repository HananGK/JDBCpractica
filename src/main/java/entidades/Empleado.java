package entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
    private int codigoEmpleado;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String extension;
    private String email;
    private String codigoOficina;
    private Integer codigoJefe;
    private String puesto;


    public String toString(){
        return codigoEmpleado + " - " + nombre  + " - " + apellido1 + " - " + codigoOficina;
    }

    public String toFicha(){
        return """
                1 - Código %d
                2 - Nombre %s
                3 - Apellido 1 %s
                4 - Apellido 2 %s
                5 - Extensión %s
                6 - Email %s
                7 - Oficina %s
                8 - Jefe %d
                9 - Puesto %s""".formatted(
                codigoEmpleado,
                nombre,
                apellido1,
                apellido2,
                extension,
                email,
                codigoOficina,
                codigoJefe,
                puesto
        );
    }
}

