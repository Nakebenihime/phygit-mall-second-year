package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId", updatable = false, nullable = false)
    private Long categoryId;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "idProfil")
    private Profil Profil;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Profil getProfil() {
		return Profil;
	}

	public void setProfil(Profil idProfil) {
		this.Profil = idProfil;
	}

	public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
