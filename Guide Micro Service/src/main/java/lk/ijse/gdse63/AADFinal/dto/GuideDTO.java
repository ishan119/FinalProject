package lk.ijse.gdse63.AADFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuideDTO {

    private int id;
    private String hotelname;
    private String address;
    private LocalDate birthday;
    private String contact;
    private String mdv;
    private String experiance;
    private String remarks;
}
