package pds.qflush.apiqflush.model;

import javax.persistence.*;


@Entity
@Table(name = "suggestion")
public class Suggestion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idSuggestion", updatable = false, nullable = false)
        private Long idSuggestion;



        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "productId", referencedColumnName = "productId")
        private Product product;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "customerId", referencedColumnName = "customerId")
        private Customer customer;

    public Long getIdSuggestion() {
        return idSuggestion;
    }

    public void setIdSuggestion(Long idSuggestion) {
        this.idSuggestion = idSuggestion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
