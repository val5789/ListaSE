package co.com.listase.lista_se.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class GenderStructrureListDEDTO {
    private String gender;
    private List<SchoolStructureListDEDTO> school;
    private int quantity;

}
