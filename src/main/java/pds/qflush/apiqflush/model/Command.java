package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "command")
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commandId", updatable = false, nullable = false)
    private Long commandID;
    private String state;
    private double total;
    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private List<CommandLine> commandLines =new ArrayList<CommandLine>();
    @ManyToOne
    private ModeLivraison modeLivraison;
    @ManyToOne
    private Customer customer;
    private Date dateCommand = new Date();
    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    private List<DeliveryHistory> deliveryHistoryList =new ArrayList<DeliveryHistory>();

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }

    public Long getCommandID() {
        return commandID;
    }

    public void setCommandID(Long commandID) {
        this.commandID = commandID;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ModeLivraison getModeLivraison() {
        return modeLivraison;
    }

    public void setModeLivraison(ModeLivraison modeLivraison) {
        this.modeLivraison = modeLivraison;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateCommand() {
        return dateCommand;
    }

    public void setDateCommand(Date dateCommand) {
        this.dateCommand = dateCommand;
    }

    public List<DeliveryHistory> getDeliveryHistoryList() {
        return deliveryHistoryList;
    }

    public void setDeliveryHistoryList(List<DeliveryHistory> deliveryHistoryList) {
        this.deliveryHistoryList = deliveryHistoryList;
    }
}
