package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deliveryhistory")

public class DeliveryHistory {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idHistory", updatable = false, nullable = false)
        private Long idHistory;
        private Date dateHistory= new Date();
        private String state;

        @ManyToOne
        @JoinColumn(name = "commandId")
        private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Long getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Long idHistory) {
        this.idHistory = idHistory;
    }

    public Date getDateHistory() {
        return dateHistory;
    }

    public void setDateHistory(Date dateHistory) {
        this.dateHistory = dateHistory;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DeliveryHistory{" +
                "idHistory=" + idHistory +
                ", dateHistory=" + dateHistory +
                ", state='" + state + '\'' +
                '}';
    }





}



