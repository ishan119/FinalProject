package lk.ijse.gdse63.AADFnal.api;

import lk.ijse.gdse63.AADFnal.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin



public class UserApi {
    @GetMapping(value= "/id")

    public void search(@PathVariable String id){
        System.out.println("Search pressed");


    }

    @PostMapping
    public void save(@RequestBody UserDTO userDTO){
        System.out.println("Save pressed :" + userDTO);

    }

    @PutMapping
    public void update(@RequestBody UserDTO userDTO){
        System.out.println("Update pressed" +userDTO);

    }

    @DeleteMapping(value= "/id:\\d+")
    public void delete(@PathVariable int id){
        System.out.println("Delete pressed");

    }
}
