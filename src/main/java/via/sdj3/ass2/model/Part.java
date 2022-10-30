package via.sdj3.ass2.model;

import javax.persistence.*;

@Entity
@Table(name="part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int partId;
    @ManyToOne
    @JoinColumn(name = "og_animal_reg_num")
    private Animal ogAnimal;
    private String partType;
    private double weight;

    public void setOgAnimal(Animal ogAnimal) {
        this.ogAnimal = ogAnimal;
    }

    public Part(Animal ogAnimal, String partType, double weight) {
        this.partId = 0;
        this.ogAnimal = ogAnimal;
        this.partType = partType;
        this.weight = weight;
    }

    public Part() {}

    public int getPartId() {
        return partId;
    }

    public Animal getOgAnimal() {
        return ogAnimal;
    }

    public String getPartType() {
        return partType;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Part{" +
                "partId=" + partId +
                ", ogAnimal=" + ogAnimal +
                ", partType='" + partType + '\'' +
                ", weight=" + weight +
                '}';
    }
}
