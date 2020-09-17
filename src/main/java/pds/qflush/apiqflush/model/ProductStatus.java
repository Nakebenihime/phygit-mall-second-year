package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ProductStatus")
public class ProductStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productStatusId", updatable = false, nullable = false)
    private Long productStatusId;

    private int status;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<Product> products;

    private String name;

    public Long getProductStatusId() {
        return productStatusId;
    }

    public void setProductStatusId(Long productStatusId) {
        this.productStatusId = productStatusId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
