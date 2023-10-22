package co.com.listase.lista_se.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Kid {
    @NotEmpty
    private String id;
    @NotEmpty
    private String name;
    @Max(25)
    private byte age;
    @NotEmpty
    private String gender;
    @Valid
    private City cityname;


}
