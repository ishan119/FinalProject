package lk.ijse.gdse63.AADFinal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPackageDTO {
    private int tpid;
    private String category;
    private Boolean petAllow;
    private int vehicleId;
    private String travelArea;
    private int numofAdults;
    private int numOfChildren;
    private double discount;
    private double price;
    private int customerid;

}
