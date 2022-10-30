package via.sdj3.ass2.model;

public class AnimalDTO {

    private String animalType;
    private double weight;
    private String farm;

    public AnimalDTO(String animalType, String farm, double weight) {
        this.weight = weight;
        this.animalType = animalType;
        this.farm = farm;
    }

    public double getWeight() {
        return weight;
    }

    public String getAnimalType() {
        return animalType;
    }

    public String getFarm() {
        return farm;
    }

    @Override
    public String toString() {
        return "AnimalDTO{" +
                "animalType='" + animalType + '\'' +
                ", weight=" + weight +
                ", farm='" + farm + '\'' +
                '}';
    }
}
