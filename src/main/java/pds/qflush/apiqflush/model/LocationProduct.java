package pds.qflush.apiqflush.model;


import javax.persistence.*;

@Entity(name = "LocationProduct")
@Table(name = "LocationProduct")
public class LocationProduct {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String alle;
    @OneToOne(mappedBy = "locationProduct")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlle() {
        return alle;
    }

    public void setAlle(String alle) {
        this.alle = alle;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}