
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Mascota")
public abstract class Mascota  implements Serializable{
   
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int idMascota;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente idCliente;
    @Basic
    private String nombre;
    private String sexo;
    private String especie;
    private String raza;
    private String colorPelo;
    private String historiaClinica;
    private String temperamento;
    private double peso;
    private double edad;
    private boolean tieneCicatrices;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estadoSalud_id")
    private EstadoSalud estadoSalud; 
    
    public void verificarSalud() {
       estadoSalud.verificarSalud(this);
    }

    public String getDescripcionEstadoSalud() {
        return estadoSalud.getDescripcion();
    }
}

    

  
