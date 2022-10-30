package via.sdj3.ass2.service;

import via.sdj3.ass2.model.Part;
import via.sdj3.ass2.model.Tray;

import java.util.List;

public interface TrayService {
    public List<Tray> findAll();
    public void savePartsInTrays(List<Part> parts);
}
