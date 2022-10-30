package via.sdj3.ass2.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="animal")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regNum;
    private String animalType;
    private LocalDateTime dateTime;
    private String farm;

    private double weight;

    public Animal(int regNum, String animalType, String farm, double weight) {
        this.regNum = regNum;
        this.animalType = animalType;
        this.dateTime = LocalDateTime.now();
        this.farm = farm;
        this.weight = weight;
    }

    public Animal() {

    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getRegNum() {
        return regNum;
    }

    public void setRegNum(int regNum) {
        this.regNum = regNum;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;

        if (regNum != animal.regNum) return false;
        if (!animalType.equals(animal.animalType)) return false;
        if (!dateTime.equals(animal.dateTime)) return false;
        return farm.equals(animal.farm);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "animalType='" + animalType + '\'' +
                ", dateTime=" + dateTime +
                ", regNum=" + regNum +
                ", farm='" + farm + '\'' +
                '}';
    }
}
