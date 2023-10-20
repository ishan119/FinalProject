package lk.ijse.gdse63.AADFnal.service.impl;


import lk.ijse.gdse63.AADFnal.dto.UserDTO;
import lk.ijse.gdse63.AADFnal.entity.User;
import lk.ijse.gdse63.AADFnal.exeption.CreateFailException;
import lk.ijse.gdse63.AADFnal.exeption.DeleteFailException;
import lk.ijse.gdse63.AADFnal.exeption.UpdateFailException;
import lk.ijse.gdse63.AADFnal.exeption.UserNotFoundException;
import lk.ijse.gdse63.AADFnal.repo.UserRepo;
import lk.ijse.gdse63.AADFnal.service.UserService;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("http://localhost:8080/api/v1/login")

    private String adminDataEndPoint;

    private final UserRepo userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepository, ModelMapper mapper){
        this.userRepository = userRepository;
        this.modelMapper=mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        List<String> roles = new ArrayList<>();
        roles.add("user");
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles(roles.toArray(new String[1]))
                        .build();
        return userDetails;
    }


    @Override
    public UserDTO searchUserByEmail(String email) throws UserNotFoundException {
        try {
            User byEmail = userRepository.findByEmail(email);
            System.out.println(byEmail);
            UserDTO map = modelMapper.map(byEmail, UserDTO.class);
            importImages(byEmail,map);
            return map;
        }catch (Exception e){
            e.printStackTrace();
            throw new UserNotFoundException("User Not Found");
        }
    }

    @Override
    public void updateUser(UserDTO email) throws UpdateFailException {
        try{
            User user = modelMapper.map(email, User.class);
            userRepository.updateUserInfoByEmail(user.getUsername(), user.getPassword(), user.getUsernic(),
                    user.getContact(), user.getEmail(), user.getBirthday(), user.getGender(), user.getRemarks());
        }catch (Exception e){
            throw new UpdateFailException("Operation Failed" ,e );
        }

    }

    @Override
    @Transactional
    public int addUsers(UserDTO userDTO) throws CreateFailException {
        try {
            User user = modelMapper.map(userDTO, User.class);
            User save = userRepository.save(user);
            exportImages(userDTO,user);
            userRepository.updateImages(save.getProfilePic(), save.getNicFrontImg(), save.getNicRearImg(), save.getEmail());
            return save.getId();
        }catch (Exception e ){
            throw new CreateFailException("Operation Failed" ,e );
        }
    }

    @Override
    public void deleteUser(String email) throws DeleteFailException {
        try {
            userRepository.deleteByEmail(email);
        }catch (Exception e){
            throw new DeleteFailException("Operation Failed" ,e );
        }

    }

    @Override
    public List<UserDTO> getAll(String email) throws UserNotFoundException {
        return null;
    }

    ArrayList<String> jsonStringToArray(String jsonString) throws JSONException {
        ArrayList<String> stringArray = new ArrayList<>();


        JSONArray jsonArray = new JSONArray(jsonString);

        for (int i =0; i<jsonArray.length();i++){
            stringArray.add(jsonArray.getString(i));
        }
        return stringArray;
    }

    public void exportImages(UserDTO userDTO, User user) throws Exception{
        String dt = LocalDate.now().toString().replace("-" , "_" ) + "__"
                + LocalTime.now().toString().replace(":" , "_");

        InputStream is = new ByteArrayInputStream(userDTO.getProfilePicByte());
        BufferedImage bi = ImageIO.read(is);
        File outputFile = new File("images/pto_pic/"+dt+ ".jpg");
        ImageIO.write(bi, "jpg", outputFile);
        user.setProfilePic(outputFile.getAbsolutePath());

        InputStream is1 = new ByteArrayInputStream(userDTO.getNicFrontByte());
        BufferedImage bi1 = ImageIO.read(is1);
        File outputFile1 = new File("images/nic_front/"+dt+ ".jpg");
        ImageIO.write(bi1, "jpg", outputFile1);
        user.setNicFrontImg(outputFile1.getAbsolutePath());

        InputStream is2 = new ByteArrayInputStream(userDTO.getNicRearByte());
        BufferedImage bi2 = ImageIO.read(is2);
        File outputFile2 = new File("images/nic_rear/"+dt+ ".jpg");
        ImageIO.write(bi2, "jpg", outputFile2);
        user.setNicRearImg(outputFile2.getAbsolutePath());

    }

    public void importImages(User user, UserDTO userDTO) throws IOException {

        BufferedImage read = ImageIO.read(new File(user.getProfilePic()));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(read, "jpg" , baos);
        byte[] bytes = baos.toByteArray();
        userDTO.setProfilePic(Base64.getEncoder().encodeToString(bytes));

        BufferedImage read1 = ImageIO.read(new File(user.getNicFrontImg()));
        ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
        ImageIO.write(read1, "jpg" , baos1);
        byte[] bytes1 = baos.toByteArray();
        userDTO.setProfilePic(Base64.getEncoder().encodeToString(bytes1));

        BufferedImage read2 = ImageIO.read(new File(user.getNicRearImg()));
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        ImageIO.write(read2, "jpg" , baos2);
        byte[] bytes2 = baos2.toByteArray();
        userDTO.setProfilePic(Base64.getEncoder().encodeToString(bytes2));

    }

}