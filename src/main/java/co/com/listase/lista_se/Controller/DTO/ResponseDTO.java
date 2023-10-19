package co.com.listase.lista_se.Controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private int code;
    Object data;
    List<String> error;
}
