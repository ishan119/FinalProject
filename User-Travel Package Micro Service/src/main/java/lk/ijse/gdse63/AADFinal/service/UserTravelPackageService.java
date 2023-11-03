package lk.ijse.gdse63.AADFinal.service;

import lk.ijse.gdse63.AADFinal.dto.UserTravelPackageDTO;
import lk.ijse.gdse63.AADFinal.exception.SaveFailException;
import org.springframework.stereotype.Service;


public interface UserTravelPackageService {
    int save(UserTravelPackageDTO ob) throws SaveFailException;

}
