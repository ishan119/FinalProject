package lk.ijse.gdse63.AADFinal.api;


import lk.ijse.gdse63.AADFinal.dto.TravelPackageDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/travelPackage")
@CrossOrigin
public class TravelPackgeApi {

    @GetMapping(value= "/{id:\\d+}")

    public void search(@PathVariable String id){
        System.out.println("Search travel");


    }

    @PostMapping
    public void save(@RequestBody TravelPackageDTO travelPackageDTO){
        System.out.println("Save travel :" + travelPackageDTO);

    }

    @PutMapping
    public void update(@RequestBody TravelPackageDTO travelPackageDTO){
        System.out.println("Update travael" +travelPackageDTO);

    }

    @DeleteMapping(value= "/{id:\\d+}")
    public void delete(@PathVariable int id){
        System.out.println("Delete travel");

    }
}
