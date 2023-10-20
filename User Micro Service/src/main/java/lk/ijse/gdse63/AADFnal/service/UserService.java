package lk.ijse.gdse63.AADFnal.service;

import lk.ijse.gdse63.AADFnal.dto.UserDTO;
import lk.ijse.gdse63.AADFnal.exeption.CreateFailException;
import lk.ijse.gdse63.AADFnal.exeption.DeleteFailException;
import lk.ijse.gdse63.AADFnal.exeption.UpdateFailException;
import lk.ijse.gdse63.AADFnal.exeption.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDTO searchUserByEmail(String email) throws UserNotFoundException;
    void  updateUser(UserDTO email) throws UpdateFailException;
    int addUsers(UserDTO email) throws CreateFailException;
    void deleteUser(String email) throws DeleteFailException;

    List<UserDTO> getAll(String email) throws UserNotFoundException;
}
