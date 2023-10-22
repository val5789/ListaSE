package co.com.listase.lista_se.Controller;

import co.com.listase.lista_se.Model.ListaDE;
import co.com.listase.lista_se.Model.Node;
import co.com.listase.lista_se.Service.ListaDEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path= "/listDE")
public class ListaDEController {
    @Autowired
    private ListaDEService listaDEService;

    @GetMapping
    public ResponseEntity<Node> getAll(){
        return new ResponseEntity<Node>(
                listaDEService.getKids().getHead(), HttpStatus.OK
        );
    }
}
