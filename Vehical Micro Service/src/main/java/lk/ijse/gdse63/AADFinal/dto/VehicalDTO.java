package lk.ijse.gdse63.AADFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicalDTO {
    private int id;
    private String vehicalname;
    private String transmission;
    private String category;
    private String vehicalType;
    private String seatCapasity;
    private String fuelType;
    private String feeForDay;
    private String fuelUsage;
    private String feeFor1km;
    private String hybrid;
    private String images;
    private int driverId;
    private String driverName;
    private String contactNo;
    private String license;


}
