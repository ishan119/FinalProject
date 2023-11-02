package lk.ijse.gdse63.AADFinal.service.impl;

import lk.ijse.gdse63.AADFinal.dto.TravelPackageDTO;
import lk.ijse.gdse63.AADFinal.entity.TravelPackage;
import lk.ijse.gdse63.AADFinal.exception.DeleteFailException;
import lk.ijse.gdse63.AADFinal.exception.NotFoundException;
import lk.ijse.gdse63.AADFinal.exception.SaveFailException;
import lk.ijse.gdse63.AADFinal.exception.UpdateFailException;
import lk.ijse.gdse63.AADFinal.repo.TravelPackageRepo;
/*import lk.ijse.gdse63.AADFinal.TravelPackageService;*/
import lk.ijse.gdse63.AADFinal.service.TravelPackageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;

@Service
public class TravelPackageServiceIMPL implements TravelPackageService {
    TravelPackageRepo travelPackageRepo;
    ModelMapper modelMapper;



    public TravelPackageServiceIMPL(TravelPackageRepo travelPackageRepo,
                                    ModelMapper modelMapper) {
        this.travelPackageRepo = travelPackageRepo;
        this.modelMapper = modelMapper;

    }

    @Override
    public String save(TravelPackageDTO obj) throws SaveFailException {
        try {
            String id = generateNextId();
            obj.setId(id);
            TravelPackage map = modelMapper.map(obj, TravelPackage.class);
            TravelPackage save = travelPackageRepo.save(map);
            return save.getId();
        }catch (Exception e){
            throw new SaveFailException("Operation Fail",e);
        }

    }

    @Override
    public void update(TravelPackageDTO obj) throws UpdateFailException {
        try{
            TravelPackage map = modelMapper.map(obj, TravelPackage.class);
            travelPackageRepo.save(map);
        }catch (Exception e){
            throw new UpdateFailException("Operation Fail",e);
        }

    }

    @Override
    public void delete(String id) throws DeleteFailException {
        try{
            travelPackageRepo.deleteById(id);
        }catch (Exception e){
            throw new DeleteFailException("Operation Fail",e);
        }

    }

    @Override
    public List<TravelPackageDTO> getPackagesByCategory(String category) {
        return null;
    }

    @Override
    public TravelPackageDTO fidById(String id) throws NotFoundException {
        Optional<TravelPackage> byId = travelPackageRepo.findById(id);
        if (byId.isPresent()) {
            return modelMapper.map(byId.get(), TravelPackageDTO.class);
        }else {
            throw new NotFoundException("Not Found");
        }
    }

    @Override
    public List<TravelPackageDTO> findByCategory(String category) throws NotFoundException {
        try{
            List<TravelPackage> byCategory = travelPackageRepo.findByCategory(category);
            ArrayList<TravelPackageDTO> list = modelMapper.map(byCategory, new TypeToken<ArrayList<TravelPackageDTO>>() {
            }.getType());
            if (list.isEmpty()) {
                throw new NotFoundException("Not Found");
            }
            return list;
        }catch (Exception e){
            throw new NotFoundException("Not Found",e);
        }

    }

    @Override
    public String generateNextId() {
        ArrayList<String> ids = new ArrayList<>();
        TreeSet<Integer> numarical = new TreeSet<>();
        List<TravelPackage> all = travelPackageRepo.findAll();
        all.stream().map(TravelPackage::getId).forEach(ids::add);
        ids.forEach(e->{
            numarical.add(Integer.parseInt(e.split("NEXT-")[1]));
        });

        return String.format("NEXT-%05d", numarical.last() + 1);

    }
}
