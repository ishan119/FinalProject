package lk.ijse.gdse63.AADFinal.service;

import lk.ijse.gdse63.AADFinal.dto.DriverDTO;
import lk.ijse.gdse63.AADFinal.dto.VehicleDTO;
import lk.ijse.gdse63.AADFinal.exception.DeleteFailException;
import lk.ijse.gdse63.AADFinal.exception.NotFoundException;
import lk.ijse.gdse63.AADFinal.exception.SaveFailException;
import lk.ijse.gdse63.AADFinal.exception.UpdatefailException;

import java.util.List;

public interface VehicleService {
    int saveVehicle(VehicleDTO dto) throws SaveFailException;

    VehicleDTO searchVehicle(int id) throws NotFoundException;

    List<VehicleDTO> searchByCategory(String category) throws NotFoundException;

    void updateVehicle(VehicleDTO dto) throws UpdatefailException;

    void deleteVehicle(int id) throws NotFoundException, DeleteFailException;

}
