package lk.ijse.gdse63.AADFinal.api;


import lk.ijse.gdse63.AADFinal.dto.UserTravelPackageDTO;
import lk.ijse.gdse63.AADFinal.exception.SaveFailException;
import lk.ijse.gdse63.AADFinal.service.UserTravelPackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-travel-package")
public class UserTravelPackageAPI {
    UserTravelPackageService service;
    public UserTravelPackageAPI(UserTravelPackageService service){
        this.service = service;
    }
    @GetMapping()
    public ResponseEntity get(){
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity post(@RequestBody UserTravelPackageDTO obj){
        try {
            int save = service.save(obj);
            return ResponseEntity.ok(save);
        } catch (SaveFailException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity savePaymentData(@RequestParam("value") double price,
                                          @RequestPart("paymentSlip") byte[] paymentSlip){
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity put(){
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paymentOfPackage/{id:\\d+}")
    public ResponseEntity getPaymentsOfPackage(@PathVariable int id){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unPayedPackagesOfUser/{userId:\\d+}")
    public ResponseEntity getUnPayedPackagesOfUser(@PathVariable int userId){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/historyOfUser/{userId:\\d+}")
    public ResponseEntity historyOfUser(@PathVariable int userId){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/needApprovalPayments")
    public ResponseEntity getNeedApprovalPayments(){
        return ResponseEntity.ok().build();
    }
    @PostMapping("/approvePayment/{paymentId:\\d+}")
    public ResponseEntity approvePayment(@PathVariable int paymentId){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/monthlyIncome")
    public ResponseEntity getMonthlyIncome(){
        return ResponseEntity.ok("Income");
    }


}
