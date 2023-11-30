
package Entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Enfermo implements EstadoSalud, Serializable {

    @Id
    private Long id;
    @Override
    public void verificarSalud(Mascota mascota) {
        System.out.println("La mascota está enferma. Se recomienda consultar a un veterinario.");
    }

    @Override
    public String getDescripcion() {
        return "Enfermo";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
