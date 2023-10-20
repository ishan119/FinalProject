package lk.ijse.gdse63.AADFnal.repo;


import lk.ijse.gdse63.AADFnal.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface UserRepo extends CrudRepository<User, Integer> {

    User findByEmail(String email);
    void deleteByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update user u set u.profile_pic = ? , u.nic_front_img = ? ," +
            "u.nic_rear_img = ? where u.email = ?",
            nativeQuery = true)
    void updateImages(String ProfilePic, String nicFrontImg,String nicRearImg, String email);

    @Modifying
    @Query("UPDATE User u SET u.username = :username, u.password = :password, u.usernic = :usernic, u.contact = :contact, u.birthday = :birthday, u.gender = :gender, u.remarks = :remarks WHERE  u.email = :email")
    void updateUserInfoByEmail(
            String username,
            String password,
            String usernic,
            String contact,
            String email,
            Date birthday,
            String gender,
            String remarks
    );
}
