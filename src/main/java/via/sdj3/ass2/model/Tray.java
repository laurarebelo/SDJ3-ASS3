package via.sdj3.ass2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tray")
public class Tray {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trayId;
    private double maxWeight;
    private String animalType;
    private String partType;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "part_in_tray")
    private Set<Part> parts;

    public Tray(double maxWeight, String animalType, String partType) {
        this.trayId = 0;
        this.maxWeight = maxWeight;
        this.animalType = animalType;
        this.partType = partType;
        this.parts = new HashSet<>();
    }

    public Tray() {
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public int getTrayId() {
        return trayId;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getPartType() {
        return partType;
    }

    public void addPart(Part part){
            parts.add(part);
    }

    @Override
    public String toString() {
        return "Tray{" +
                "trayId=" + trayId +
                ", maxWeight=" + maxWeight +
                ", animalType='" + animalType + '\'' +
                ", partType='" + partType + '\'' +
                ", parts=" + parts +
                '}';
    }
}
