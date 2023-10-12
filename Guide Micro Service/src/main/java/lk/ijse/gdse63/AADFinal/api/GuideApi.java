package lk.ijse.gdse63.AADFinal.api;

import lk.ijse.gdse63.AADFinal.dto.GuideDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/guide")
@CrossOrigin

public class GuideApi {
    @GetMapping(value= "/{id:\\d+}")

    public void search(@PathVariable String id){
        System.out.println("Search Guide");


    }

    @PostMapping
    public void save(@RequestBody GuideDTO guideDTO){
        System.out.println("Save Guide :" + guideDTO);

    }

    @PutMapping
    public void update(@RequestBody GuideDTO guideDTO){
        System.out.println("Update Guide" +guideDTO);

    }

    @DeleteMapping(value= "/{id:\\d+}")
    public void delete(@PathVariable int id){
        System.out.println("Delete Guide");

    }
}
