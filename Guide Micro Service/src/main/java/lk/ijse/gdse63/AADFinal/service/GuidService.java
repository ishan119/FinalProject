package lk.ijse.gdse63.AADFinal.service;

import lk.ijse.gdse63.AADFinal.dto.GuideDTO;
import lk.ijse.gdse63.AADFinal.exception.DeleteFailException;
import lk.ijse.gdse63.AADFinal.exception.SaveFailException;
import lk.ijse.gdse63.AADFinal.exception.SearchFailException;
import lk.ijse.gdse63.AADFinal.exception.UpdateFailException;

public interface GuidService {
    int saveGuide(GuideDTO guideDTO) throws SaveFailException;
    void updateGuide(GuideDTO guideDTO) throws UpdateFailException;
    void deleteGuide(int id) throws DeleteFailException;
    GuideDTO getGuide(int id) throws SearchFailException;
}
