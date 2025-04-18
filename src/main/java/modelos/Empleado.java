package modelos;

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

}

