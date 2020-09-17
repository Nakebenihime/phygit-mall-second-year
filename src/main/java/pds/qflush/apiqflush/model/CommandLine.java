
package pds.qflush.apiqflush.model;

import javax.persistence.*;

@Entity
public class CommandLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commandLineId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "commandId")
    private Command command;

    private int quantity;
    private double totalPrice;

    public CommandLine(Product product, Command command, int quantity, double totalPrice) {
        this.product = product;
        this.command = command;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public CommandLine() {
    }

    public Long getCommandLineId() {
        return commandLineId;
    }

    public void setCommandLineId(Long commandLineId) {
        this.commandLineId = commandLineId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CommandLine{" +
                "commandLineId=" + commandLineId +
                ", product=" + product +
                ", command=" + command +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

}

