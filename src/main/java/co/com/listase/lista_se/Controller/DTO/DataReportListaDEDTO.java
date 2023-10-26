package co.com.listase.lista_se.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class DataReportListaDEDTO {
    private String city;
    private int total;
    private List<GenderStructrureListDEDTO> gender;

}
