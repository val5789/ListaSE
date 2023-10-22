package co.com.listase.lista_se.Service;

import co.com.listase.lista_se.Controller.DTO.DataStructureDTO;
import co.com.listase.lista_se.Exception.KidsException;
import co.com.listase.lista_se.Model.City;
import co.com.listase.lista_se.Model.Kid;
import co.com.listase.lista_se.Model.ListaSE;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class ListaSEService {
    private ListaSE kids;

    public ListaSEService() {
        //Simular que leyo un archivo o una base de datos
        kids = new ListaSE();
        kids.addKidToEnd(new Kid("1006", "Valeria Osorio", (byte) 20,"Female",new City("17001","Manizales")));
        kids.addKidToEnd(new Kid("1007", "Jhair Torres", (byte)18 ,"Male",new City("05001","Medellin")));
        kids.addKidToEnd(new Kid("1003", "John Jaime", (byte) 18,"Male",new City("05091","Betania")));
        kids.addKidToEnd(new Kid("1004", "Sergio Nuñez", (byte) 19,"Male",new City("11001","Bogota")));
        kids.addKidToEnd(new Kid("1008", "Sebastian Rugeles", (byte) 19,"Male",new City("17001","Manizales")));

    }

    public String invert(){
        kids.invert();
        return "Invertido";
    }
    public String changeExt(){
        kids.changeExt();
        return "Invertidos los Extremos";
    }
    public String sortbyGender(){
        try {
            kids.sortbyGender();
            return "Intercalados";
        }catch (KidsException e){
            return e.getMessage();
        }
    }
    public String addPos(int posicion, Kid kid){
        kids.addPos(posicion,kid);
        return "Agregado";
    }
    public String updateInPos(byte pos, Kid kid){
        kids.updateInPos(pos,kid);
        return "Actualizado";
    }
    public String deletePos(int pos){
        try {
            kids.deletePos(pos);
            return "Eliminado";
        } catch (KidsException e) {
            return e.getMessage();
        }
    }

    public String deleteId(String id){
        try {
            kids.deleteId(id);
            return "Eliminado";
        }catch (KidsException e){
            return e.getMessage();
        }
    }

    public List<DataStructureDTO> cityReport() throws KidsException {
        try {
            return kids.cityReport();
        } catch (KidsException e) {
            throw new KidsException(e.getMessage());
        }
    }

    public List<String> getCities(){
        return kids.getCities();
    }



}
