package modelos;

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
}
