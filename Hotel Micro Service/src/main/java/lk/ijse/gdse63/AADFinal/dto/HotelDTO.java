package lk.ijse.gdse63.AADFinal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private int id;
    private String hotelname;
    private String address;
    private String email;
    private String category;
    private String contact;
    private String maplink;
    private String pets;
}
