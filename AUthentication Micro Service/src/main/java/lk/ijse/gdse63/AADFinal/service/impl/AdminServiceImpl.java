package lk.ijse.gdse63.AADFinal.service.impl;


import lk.ijse.gdse63.AADFinal.dto.AdminDTO;
import lk.ijse.gdse63.AADFinal.entity.Admin;
import lk.ijse.gdse63.AADFinal.repo.AdminRepo;
import lk.ijse.gdse63.AADFinal.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepository;

    private final ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepo adminRepository, ModelMapper modelMapper){
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin user = adminRepository.findByEmail(email);
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[0]))
                        .build();
        return userDetails;
    }

    @Override
    public AdminDTO searchUser(String email) {
        Admin byEmail = adminRepository.findByEmail(email);
        System.out.println(byEmail);
        return modelMapper.map(byEmail,AdminDTO.class);
    }
}
