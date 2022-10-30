package via.sdj3.ass2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Product {
    public static String PRODUCT_TYPE_ANIMAL_HALF = "halfAnimal";
    public static String PRODUCT_TYPE_SAME_TYPE_PARTS = "sameTypeParts";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "part_in_product")
    Set<Part> parts;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tray_in_product")
    Set<Tray> trays;
    private String productType;

    public Product(String productType) {
        if (!(productType.equals(PRODUCT_TYPE_ANIMAL_HALF) || productType.equals(PRODUCT_TYPE_SAME_TYPE_PARTS)))
        {
            throw new IllegalArgumentException("The product type is invalid. Please use the static strings from Product class.");
        }
        this.productId = 0;
        this.parts = new HashSet<>();
        this.trays = new HashSet<>();
        this.productType = productType;
    }

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void addPart(Part part, Tray trayItCameFrom) {
        this.trays.add(trayItCameFrom);
        this.parts.add(part);
    }

    public void removePart(Part part, Tray trayItCameFrom) {
        this.parts.remove(part);
        this.trays.remove(trayItCameFrom);
    }

    public Set<Integer> getAllAnimalRegIdsInvolved() {
        Set<Integer> allRegIds = new HashSet<>();

        for (Part part : parts
        ) {
            allRegIds.add(part.getOgAnimal().getRegNum());
        }
        return allRegIds;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public Set<Tray> getTrays() {
        return trays;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", parts=" + parts +
                ", ogTrays=" + trays +
                ", productType='" + productType + '\'' +
                '}';
    }
}
