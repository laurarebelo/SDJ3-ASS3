package via.sdj3.ass2.model;

public class SpecificTrayDTO {
    private String animalType;
    private String partType;

    public String getAnimalType() {
        return animalType;
    }

    public String getPartType() {
        return partType;
    }

    public SpecificTrayDTO(String animalType, String partType) {
        this.animalType = animalType;
        this.partType = partType;
    }
}
