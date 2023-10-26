package co.com.listase.lista_se.Controller;

import co.com.listase.lista_se.Controller.DTO.ResponseDTO;
import co.com.listase.lista_se.Exception.KidsException;
import co.com.listase.lista_se.Model.Kid;
import co.com.listase.lista_se.Model.ListaDE;
import co.com.listase.lista_se.Model.Node;
import co.com.listase.lista_se.Service.ListaDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path= "/listde")
public class ListaDEController {
    @Autowired
    private ListaDEService listaDEService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    listaDEService.getAllKidsl(),null),HttpStatus.OK);
        } catch (KidsException e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @PostMapping(path = "/add")
    public ResponseEntity<ResponseDTO> addToFinal(@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDEService.addKidToEnd(kid),null),HttpStatus.OK);
    }
    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDEService.addKidToStart(kid),null),HttpStatus.OK);
    }
    @PostMapping(path="/insertinpos/{pos}")
    public ResponseEntity<ResponseDTO> insertInPos(@PathVariable int pos, @RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDEService.insertInPos(pos,kid),null),HttpStatus.OK);
    }
    @GetMapping(path = "/invertlist")
    public ResponseEntity<ResponseDTO> invertList(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDEService.invertList(),null),HttpStatus.OK);
    }
    @GetMapping(path = "/invertedges")
    public ResponseEntity<ResponseDTO> invertEdges(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                listaDEService.invertEdges(),null),HttpStatus.OK);
    }
    @GetMapping(path="/intercalatebygender")
    public ResponseEntity<ResponseDTO> intercalateByGender(){
        String output = listaDEService.intercalateByGender();
        if(output.equals("Lista vacia")||output.equals("Insuficientes elementos")){
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
    }

    @DeleteMapping (path="/deletebyid/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id){
        String output = listaDEService.deleteById(id);
        if(output.equals("Eliminado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @DeleteMapping(path="/deletebypos/{pos}")
    public ResponseEntity<ResponseDTO> deleteByPos(@PathVariable int pos){
        String output = listaDEService.deleteInPos(pos);
        if(output.equals("Eliminado")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else{
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }

    }
    @DeleteMapping(path="/deletekamikaze/{pos}")
    public ResponseEntity<ResponseDTO> deleteKamikaze(@PathVariable int pos){
        String output = listaDEService.deleteKamikaze(pos);
        if(output.equals("Kamikazeeeee")){
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    output,null),HttpStatus.OK);
        }
        else {
            List<String> errors = new ArrayList<>();
            errors.add(output);
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
}
