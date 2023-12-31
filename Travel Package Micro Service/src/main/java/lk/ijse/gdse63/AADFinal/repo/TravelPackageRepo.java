package lk.ijse.gdse63.AADFinal.repo;

import lk.ijse.gdse63.AADFinal.entity.TravelPackage;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TravelPackageRepo extends CrudRepository<TravelPackage, String> {
    List<TravelPackage> findByCategory(String category);
    List<TravelPackage> findAll();

}
