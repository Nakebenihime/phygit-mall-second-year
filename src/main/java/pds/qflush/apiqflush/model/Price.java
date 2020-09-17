package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priceId", updatable = false, nullable = false)
    private Long priceId;

    private double refPrice;
    private double reductPrice;
    private int discount;
    private Date beginDate;
    private Date endDate;

    @OneToOne(mappedBy = "price")
    private Product product;

    public Price() {
    }

    public Price(double refPrice) {
        this.refPrice = refPrice;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public double getRefPrice() {
        return refPrice;
    }

    public void setRefPrice(double refPrice) {
        this.refPrice = refPrice;
    }

    public double getReductPrice() {
        return reductPrice;
    }

    public void setReductPrice(double reductPrice) {
        this.reductPrice = reductPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
