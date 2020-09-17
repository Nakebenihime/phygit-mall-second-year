package pds.qflush.apiqflush.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId", updatable = false, nullable = false)
    private Long productId;
    private String name;
    private double sizeWidth;
    private double sizeHeight;
    private double sizeLength;
    private double weight;
    private String type;
    private String brand;
    private String description;
    private int rayon;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "priceId")
    private Price price;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "statusId")
    private ProductStatus status;

    @ManyToOne
    @JoinColumn(name = "idEvenement")
    private Evenement idEvenement;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private List<StockProduct> stockProduct;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "location_of_product",
            joinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "productId") },
            inverseJoinColumns = { @JoinColumn(name = "location_id", referencedColumnName = "id") })
    private LocationProduct locationProduct;

    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL)
    private List<CommandLine> commandLines;

    public Product(String name, double sizeWidth, double sizeHeight, double sizeLength, double weight, String type, String brand, String description, int rayon, Price price, Category category, ProductStatus status, List<StockProduct> stockProduct) {
        this.name = name;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.sizeLength = sizeLength;
        this.weight = weight;
        this.type = type;
        this.brand = brand;
        this.description = description;
        this.rayon = rayon;
        this.price = price;
        this.category = category;
        this.status = status;
        this.stockProduct = stockProduct;
    }

    public Product() {
    }

    public Product(String brand, String name, String type, Price price){
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.price = price;
    }

    // Getters and Setters

    public int getRayon() {
        return rayon;
    }

    public void setRayon(int rayon) {
        this.rayon = rayon;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSizeWidth() {
        return sizeWidth;
    }

    public void setSizeWidth(double sizeWidth) {
        this.sizeWidth = sizeWidth;
    }

    public double getSizeHeight() {
        return sizeHeight;
    }

    public void setSizeHeight(double sizeHeight) {
        this.sizeHeight = sizeHeight;
    }

    public double getSizeLength() {
        return sizeLength;
    }

    public void setSizeLength(double sizeLength) {
        this.sizeLength = sizeLength;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public void setCommandLines(List<CommandLine> commandLines) {
        this.commandLines = commandLines;
    }

    public Evenement getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(Evenement idEvenement) {
        this.idEvenement = idEvenement;
    }

    public List<StockProduct> getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(List<StockProduct> stockProduct) {
        this.stockProduct = stockProduct;
    }

    public LocationProduct getLocationProduct() {
        return locationProduct;
    }

    public void setLocationProduct(LocationProduct locationProduct) {
        this.locationProduct = locationProduct;
    }
}
