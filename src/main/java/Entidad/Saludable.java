
package Entidad;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Saludable implements EstadoSalud, Serializable {
    @Id
    private Long id;
    
    @Override
    public void verificarSalud(Mascota mascota) {
        System.out.println("La mascota está saludable.");
    }

    @Override
    public String getDescripcion() {
        return "Saludable";
    }
}


