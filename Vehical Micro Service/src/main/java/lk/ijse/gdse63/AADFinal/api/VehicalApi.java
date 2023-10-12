package lk.ijse.gdse63.AADFinal.api;

import lk.ijse.gdse63.AADFinal.dto.VehicalDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehical")
@CrossOrigin
public class VehicalApi {

    @GetMapping(value= "/{id:\\d+}")

    public void search(@PathVariable String id){
        System.out.println("Search Vehical");


    }

    @PostMapping
    public void save(@RequestBody VehicalDTO vehicalDTO){
        System.out.println("Save vehical :" + vehicalDTO);

    }

    @PutMapping
    public void update(@RequestBody VehicalDTO vehicalDTO){
        System.out.println("Update vehical" +vehicalDTO);

    }

    @DeleteMapping(value= "/{id:\\d+}")
    public void delete(@PathVariable int id){
        System.out.println("Delete vehical");

    }
}
