package lk.ijse.gdse63.AADFinal.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int hotelId;

    @ManyToOne(cascade = CascadeType.ALL)
    private UserTravelPackage userTravelPackage;


}
