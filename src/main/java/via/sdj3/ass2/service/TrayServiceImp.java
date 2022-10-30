package via.sdj3.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import via.sdj3.ass2.model.Animal;
import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.model.Tray;
import via.sdj3.ass2.repository.TrayRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrayServiceImp implements TrayService {

    @Autowired
    TrayRepository trayRepository;

    @Override
    public List<Tray> findAll() {
        List<Tray> trays = new ArrayList<>();
        trayRepository.findAll().forEach(trays::add);
        return trays;
    }

    @Override
    public void savePartsInTrays(List<Part> parts) {
        for (Part part : parts
        ) {
            String animalType = part.getOgAnimal().getAnimalType();
            String partType = part.getPartType();
            Optional<Tray> belongingTrayExists = trayRepository.findTrayByAnimalTypeAndPartType(animalType, partType);
            Tray belongingTray = null;
            if (belongingTrayExists.isEmpty()) {
                Tray newTray = new Tray(100, animalType, partType);
                belongingTray = trayRepository.save(newTray);
            }
            else {
                belongingTray = belongingTrayExists.get();
            }
            belongingTray.addPart(part);
            trayRepository.save(belongingTray);
        }
    }
}
