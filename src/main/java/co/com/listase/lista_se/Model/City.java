package co.com.listase.lista_se.Model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class City {
    @NotEmpty
    private String code;
    @NotEmpty
    private String city;
}
