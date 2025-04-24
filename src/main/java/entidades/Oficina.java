package entidades;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Oficina {
    private String codigoOficina;
    private String ciudad;
    private String pais;
    private String region;
    private String codigoPostal;
    private String telefono;
    private String lineaDireccion1;
    private String lineaDireccion2;

    public String toString(){
        return codigoOficina + " -  " + ciudad + " - " + pais;
    }

    public String toFicha(){
        return """
               1 - Código %s
               2 - Ciudad %s
               3 - Pais %s
               4 - Region %s
               5 - Codigo postal %s
               6 - Teléfono %s
               7 - Dirección 1 %s
               8 - Dirección 2 %s""".formatted(
                codigoOficina,
                ciudad,
                pais,
                region,
                codigoPostal,
                telefono,
                lineaDireccion1,
                lineaDireccion2
        );
    }
}
