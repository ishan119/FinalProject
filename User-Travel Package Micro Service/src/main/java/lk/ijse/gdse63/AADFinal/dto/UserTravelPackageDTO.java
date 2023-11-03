package lk.ijse.gdse63.AADFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTravelPackageDTO {
    private int id;
    private String packageId;
    private int customerId;
    private ArrayList<String> places;
    private ArrayList<Integer> hotels;
    private ArrayList<Integer> vehicles;
    private double payment;
}
