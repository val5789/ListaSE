package co.com.listase.lista_se.Controller;

import co.com.listase.lista_se.Controller.DTO.ResponseDTO;
import co.com.listase.lista_se.Exception.KidsException;
import co.com.listase.lista_se.Model.Kid;
import co.com.listase.lista_se.Model.ListaSE;
import co.com.listase.lista_se.Model.Node;
import co.com.listase.lista_se.Service.ListaSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/list")
public class ListaSEController {
    @Autowired
    private ListaSEService listaSEService;

    @GetMapping
    public ResponseEntity<Node> getAll(){
        return new ResponseEntity<Node>(
                listaSEService.getKids().getHead(), HttpStatus.OK
        );
    }
    @PostMapping
    public ResponseEntity<String> addEnd(@RequestBody Kid kid){
        // irian las validaciones de la entrada
        listaSEService.getKids().addKidToEnd(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }

    @PostMapping(path = "/tostart")
    public ResponseEntity<String> addToStart(@RequestBody Kid kid){
        // irian las validaciones de la entrada
        listaSEService.getKids().addToStart(kid);
        return new ResponseEntity<String>(
                "Adicionado exitosamente", HttpStatus.OK);
    }
    @PostMapping(path="/addposition/{posicion}")
    public ResponseEntity<String> addPos( @RequestBody Kid kid, @PathVariable int posicion ){
        if (posicion<1){
            return new ResponseEntity<>("Posicion no valida",HttpStatus.BAD_REQUEST);
        }
        listaSEService.getKids().addPos(posicion, kid);
        return new ResponseEntity<>(
                "Adicionado exitosamente",HttpStatus.OK
        );
    }

    @GetMapping(path = "/invert")
    public ResponseEntity<ResponseDTO> invert(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaSEService.invert(),null),HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO> changeExt(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaSEService.changeExt(),null   ),HttpStatus.OK);

    }
    @GetMapping(path="/sortbygender")
    public ResponseEntity<ResponseDTO> sortbyGender(){
        String output= listaSEService.sortbyGender();
        if (output.equals("Lista vacia")||output.equals("Insuficiente elementos")){
            List<String> errors =new ArrayList<>();
            errors.add(output);
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                        null, errors ),HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deletepos/{pos}")
    public ResponseEntity<ResponseDTO> deleteInPos(@PathVariable int pos){
        String output = listaSEService.deletePos(pos);

        if (output.equals("La posición está fuera de rango.")){
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deleteid/{id}")
    public  ResponseEntity<ResponseDTO> deleteId(@PathVariable String id) {
        String output=listaSEService.deleteId(id);
        if (output.equals("Insertado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }else {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null,errors),HttpStatus.OK);
        }

    }
    @PutMapping(path="/updateinpos/{posicion}")
    public ResponseEntity<String> updateInPos(@PathVariable int posicion,@RequestBody Kid kid){
        listaSEService.getKids().updateInPos(posicion,kid);
        return new ResponseEntity<String>(
                "actualizado exitosamente", HttpStatus.OK);
    }

    @GetMapping(path = "/cityreport")
    public ResponseEntity<ResponseDTO> cityReport(){
        Object output = null;
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listaSEService.cityReport(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.NO_CONTENT.value(),
                    null,errors),HttpStatus.OK);

        }
    }

    @GetMapping(path="/test")
    public ResponseEntity<ResponseDTO> getCities(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaSEService.getCities(),null),HttpStatus.OK);
    }


}
