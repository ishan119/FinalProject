package lk.ijse.gdse63.AADFinal.service;

import lk.ijse.gdse63.AADFinal.dto.AdminDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    AdminDTO searchUser(String email);
}
