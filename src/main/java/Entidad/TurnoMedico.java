
package Entidad;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoMedico implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private boolean aprobado;
    private String motivoConsulta;
    
    
  @ManyToOne
    private MedicoVeterinario medicoAsignado;

    @ManyToOne
    private Mascota mascota;

    public void setAceptado(boolean aceptado) {
        this.aprobado = aceptado;
    }

    public String getNombreMascota() {
        return mascota != null ? mascota.getNombre() : "Mascota no especificada";
    }

}
