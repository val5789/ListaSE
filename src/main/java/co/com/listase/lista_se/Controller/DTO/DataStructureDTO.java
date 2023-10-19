package co.com.listase.lista_se.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DataStructureDTO {
    private String City;
    private int quantity;
    private List<GenderStructureDTO> gender;


}
