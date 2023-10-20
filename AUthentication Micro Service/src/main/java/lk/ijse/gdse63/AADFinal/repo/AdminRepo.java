package lk.ijse.gdse63.AADFinal.repo;


import lk.ijse.gdse63.AADFinal.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepo extends CrudRepository<Admin, String> {

    Admin findByEmail(String email);

}
