package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class StockProduct   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long stockProductId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "storeId")
    private Store store;

    public StockProduct() {
    }

    public StockProduct(Product product, Store store, int qte) {
        this.product = product;
        this.store = store;
        this.qte = qte;
    }

    private int qte;

    public Long getStockProductId() {
        return stockProductId;
    }

    public void setStockProductId(Long stockProductId) {
        this.stockProductId = stockProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
