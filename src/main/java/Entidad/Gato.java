
package Entidad;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Gato extends Mascota {

    private boolean esCazador;
    private int cantidadHorasDescanso;
    private boolean necesitaCajaArena;
    private boolean juegaConOtrosGatos;
    private String patronPelaje;
    
    
    /*@Override
    public boolean esCazador() {
        return this.esCazador;
    }
    
    // Implementación del método abstracto
    @Override
    public void ejecutarComportamientoEspecifico() {
        // Lógica específica para Gato
        if (esCazador()) {
            System.out.println("¡El gato " + getNombre() + " es un cazador!");
        } else {
            System.out.println("El gato " + getNombre() + " no es un cazador.");
        }
    }*/
}