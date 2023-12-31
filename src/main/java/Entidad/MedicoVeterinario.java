package Entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicoVeterinario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int idMedico;

    private String nombre;
    private String especialidad;
    private String telefono;

    @OneToMany(mappedBy = "medicoAsignado")
    private List<TurnoMedico> turnosAsignados = new ArrayList<>();

    public void aceptarRechazarTurno(TurnoMedico turno, boolean aceptar) {
        if (turnosAsignados.contains(turno)) {
            if (aceptar) {
                // Lógica para aceptar el turno (puede implicar actualizar el estado del turno, etc.)
                turno.setAceptado(true);
                System.out.println("Turno aceptado para la mascota: " + turno.getNombreMascota());
            } else {
                // Lógica para rechazar el turno (puede implicar eliminar el turno, etc.)
                turnosAsignados.remove(turno);
                System.out.println("Turno rechazado para la mascota: " + turno.getNombreMascota());
            }
        } else {
            System.out.println("Este turno no está asignado a este veterinario.");
        }
    }
    
}