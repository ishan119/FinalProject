package lk.ijse.gdse63.AADFnal.api;

import lk.ijse.gdse63.AADFnal.config.JwtUtil;
import lk.ijse.gdse63.AADFnal.dto.UserDTO;
import lk.ijse.gdse63.AADFnal.dto.seurity.ErrorResponse;
import lk.ijse.gdse63.AADFnal.dto.seurity.LoginRequest;
import lk.ijse.gdse63.AADFnal.dto.seurity.LoginResponse;
import lk.ijse.gdse63.AADFnal.exeption.CreateFailException;
import lk.ijse.gdse63.AADFnal.exeption.DeleteFailException;
import lk.ijse.gdse63.AADFnal.exeption.UpdateFailException;
import lk.ijse.gdse63.AADFnal.exeption.UserNotFoundException;
import lk.ijse.gdse63.AADFnal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserApi {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        try {
            Authentication authentication=
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            String email = authentication.getName();
            UserDTO user = userService.searchUserByEmail(email);
            String token = jwtUtil.createToken(user);
            LoginResponse loginResponse = new LoginResponse(email,token);
            return ResponseEntity.ok(loginResponse);
        }
        catch (BadCredentialsException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }

    @GetMapping(value = "/{id:\\d+}/{email}")

    public ResponseEntity search(@PathVariable String email){
        try {
            UserDTO userDTO = userService.searchUserByEmail(email);
            return ResponseEntity.ok(userDTO);
        }catch (UserNotFoundException e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
            return new ResponseEntity("Search Pressed" + email, HttpStatus.OK);
        }
    }




    @PostMapping(value = "/{id:\\d+}", consumes = "multipart/from-data")
    public ResponseEntity<UserDTO>save(@RequestPart(value = "profilePic",required = false) byte[] prfilePic,
                                       @RequestPart(value = "userName")String userName,
                                       @RequestPart(value = "password")String password,
                                       @RequestPart(value = "contact")String contact,
                                       @RequestPart(value = "email")String email,
                                       @RequestPart(value = "birthday")String birthday,
                                       @RequestPart(value = "nicFont")byte[] nicFont,
                                       @RequestPart(value = "nicRear")byte[] nicRear,
                                       @RequestPart(value = "gender")String gender,
                                       @RequestPart(value = "nicNo")String nicNo
    ){
        try {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(userName);
            userDTO.setPassword(password);
            userDTO.setContact(contact);
            userDTO.setEmail(email);
            userDTO.setUsernic(nicNo);
            //userDTO.setBirthday(birthday);
            userDTO.setGender(gender);
            userDTO.setNicFrontByte(nicFont);
            userDTO.setNicRearByte(nicRear);
            userDTO.setProfilePicByte(prfilePic);

            int id = userService.addUsers(userDTO);
            userDTO.setId(id);
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        }catch (CreateFailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id:\\d+}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO){
        try {
            userService.updateUser(userDTO);
            return new ResponseEntity<>(userDTO,HttpStatus.OK);
        }catch (UpdateFailException e){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/{id:\\d+}")
    public ResponseEntity delete(@RequestBody UserDTO userDTO){
        try {
            userService.deleteUser(userDTO.getEmail());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (DeleteFailException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

