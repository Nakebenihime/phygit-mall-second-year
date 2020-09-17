package pds.qflush.apiqflush.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "Horaire")
public class Horaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHoraire", updatable = false, nullable = false)
    private long idHoraire;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date horaireDebut;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    private Date horaireFin;
    private String jour = null;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "store_id")
    private Store store;

    public long getIdHoraire() {
        return idHoraire;
    }

    public void setIdHoraire(long idHoraire) {
        this.idHoraire = idHoraire;
    }

    public  @DateTimeFormat(pattern = "HH:mm")Date getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(Date horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public  @DateTimeFormat(pattern = "HH:mm")Date getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(Date horaireFin) {
        this.horaireFin = horaireFin;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "idHoraire=" + idHoraire +
                ", horaireDebut=" + horaireDebut +
                ", horaireFin=" + horaireFin +
                ", jour='" + jour + '\'' +
                ", store=" + store +
                '}';
    }
}
