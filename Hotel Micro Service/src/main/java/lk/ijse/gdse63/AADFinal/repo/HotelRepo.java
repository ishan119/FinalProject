package lk.ijse.gdse63.AADFinal.repo;

import lk.ijse.gdse63.AADFinal.entity.Hotel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepo extends CrudRepository<Hotel,Integer> {
    List<Hotel> findByStar(int star);
}
