package co.com.listase.lista_se.Model;

import co.com.listase.lista_se.Controller.DTO.DataStructureDTO;
import co.com.listase.lista_se.Controller.DTO.GenderStructureDTO;
import co.com.listase.lista_se.Exception.KidsException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class ListaSE {
    private Node head;
    private int size;

    //ADD TO THE END

    public void addKidToEnd(Kid kid){

        //verificamos si hay datos en la cabeza

        if(this.head==null){
            this.head = new Node(kid) ;
        }else {
            //LLAMAR A AYUDANTE
            Node tempNode =this.head;
            while
            (tempNode.getNext()!=null){
                tempNode=tempNode.getNext();
            }
            //Node newnode = new Node(kid);
            //tempNode.setNext(newnode);
            tempNode.setNext(new Node(kid));

        }
        this.size++;
    }

    //ADD TO START
    public void addToStart(Kid kid){
        if(this.head==null){
            this.head = new Node(kid) ;
        }else {
            Node newNode = new  Node (kid);
            newNode.setNext(this.head);
            this.head=newNode;
        }
        this.size ++;
    }
    //ADD TO POSITION
    public void addPos(int posicion, Kid kid) {
        if (posicion==1){
            this.addToStart(kid);

        } else if (posicion>this.size) {
            this.addKidToEnd(kid);

        }else if (posicion <= this.size) {
            Node temp = this.head;
            int posAct = 1;
            while (posAct < posicion - 1) {
                temp = temp.getNext();
                posAct++;
            }
            Node newNode = new Node(kid);
            newNode.setNext(temp.getNext());
            temp.setNext(newNode);
            this.size++;
        }


    }
    //INVERTIR LA LISTA

    public void invert() {
        //Hay datos?
        if (this.head != null) {
            ///Creo una lista copia
            ListaSE listCopy = new ListaSE();
            //Lamar a mi ayudante
            Node temp = this.head;
            while (temp != null) {
                listCopy.addToStart(temp.getData());
                temp = temp.getNext();
            }
            // en la lista copia estan invertidos
            // cambio la cabeza
            this.head = listCopy.getHead();
        }
    }
    //CAMBIAR EXTREMOS
    public void changeExt(){
        if(this.head !=null){
            Node temp= this.head;
            while(temp.getNext()!=null){
                temp=temp.getNext();
            }
            Kid lastKid=temp.getData();
            temp.setData(this.head.getData());
            this.head.setData(lastKid);
        }
    }
    //SORTEAR POR GENERO
    public void sortbyGender() throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacia");
        }else if(this.head.getNext()==null){
            throw new KidsException("Insuficientes elementos");
        }
        else{
            ListaSE listcopy=new ListaSE();
            Node temp= this.head;
            int posMale=1;
            int posFemale=2;
            while(temp!=null){
                if (temp.getData().getGender().equals("Male")){
                    listcopy.addPos(posMale,temp.getData());
                    posMale=posMale+2;
                }else if (temp.getData().getGender().equals("Female")) {
                    listcopy.addPos(posFemale, temp.getData());
                    posFemale = posFemale + 2;
                }
                temp=temp.getNext();
            }
            this.head=listcopy.getHead();
        }
    }
//DELETE IN POSITION
    public void deletePos(int posicion) throws KidsException {
        if (posicion <= 0 || posicion > this.size) {
            // Verifica si la posición está fuera de rango
            throw new KidsException("La posición está fuera de rango.");
        }
        if (posicion == 1) {
            // Si la posición es 0, actualiza la cabeza para eliminar el primer nodo
            this.head = this.head.getNext();
        } else {
            Node temp = this.head;
            int contador = 1;
            // Encuentra el nodo anterior al que se va a eliminar
            while (contador < posicion - 1) {
                temp = temp.getNext();
                contador++;
            }
            // Actualiza las referencias para eliminar el nodo en la posición especificada
            temp.setNext(temp.getNext().getNext());
        }

        this.size--;
    }
//DELETE FOR ID
    public void deleteId(String id) throws KidsException {
        if (this.head == null) {
            throw new KidsException("Lista Vacia");
            // La lista está vacía, no se puede borrar nada
        } else if (this.head.getData().getId().equals(id)) {
            // Si la identificación coincide con la de la cabeza, elimina el primer nodo
            this.head = this.head.getNext();
            this.size--;

        }else{
            Node temp = this.head;
            while (temp != null) {
                if (temp.getNext().getData().getId().equals(id)) {
                    // Si encuentra un nodo cuya identificación coincide, actualiza las referencias
                    temp.setNext(temp.getNext().getNext());
                }
                temp = temp.getNext();
            }
            this.size--;
        }
    }

    public void updateInPos(int posicion, Kid kid) {
        if(this.head!= null) {
            Node temp = this.head;
            byte currentPos = 0;
            if (posicion > this.size) {
                this.addKidToEnd(kid);
            }
            while(temp.getNext()!=null){
                if(currentPos==posicion){
                    temp.setData(kid);
                }
                temp = temp.getNext();
                currentPos++;
            }
        }
    }

    public List<String> getCities() {
        Node temp = this.head;
        List<String> cities = new ArrayList<>();

        while (temp != null) {
            String city = temp.getData().getCity();
            if (!cities.contains(city)) {
                cities.add(city);
            }
            temp = temp.getNext();
        }

        return cities;
    }

    public List<DataStructureDTO> cityReport() throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacía");
        } else {
            List<String>  cities = this.getCities();

            List<DataStructureDTO> cityReport = new ArrayList<>();

            List<DataStructureDTO> cities_report = new ArrayList<>();

            for(String city :cities){
                int total_city_count=0;
                int male_count=0;
                int female_count =0;
                Node temp = this.head;
                while(temp!=null){
                    if(temp.getData().getCity().equals(city)){
                        System.out.println("------");
                        System.out.println(temp.getData());
                        System.out.println("----");
                        System.out.println(city);
                        if(temp.getData().getGender().equals("Male")){
                            System.out.println("Male");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("Previous value: "+male_count);
                            System.out.println("----");
                            male_count++;
                            System.out.println("New male value:  "+male_count);
                        }
                        if(temp.getData().getGender().equals("Female")){
                            System.out.println("Female");
                            System.out.println("-----");
                            System.out.println(temp.getData());
                            System.out.println("Previous value: "+female_count);
                            System.out.println("-----");
                            female_count++;
                            System.out.println("New Female value:  "+female_count);
                        }
                        total_city_count++;
                        System.out.println("New total count in"+city+"-----"+total_city_count);
                    }

                    temp = temp.getNext();
                }
                GenderStructureDTO city_females = new GenderStructureDTO("Female",female_count);
                GenderStructureDTO city_males = new GenderStructureDTO("Males",male_count);
                System.out.println(city+"Females in the city  "+ city_females);
                System.out.println(city+"Males in the city  "+city_males);

                List<GenderStructureDTO> genders = new ArrayList<>();
                genders.add(city_females);
                genders.add(city_males);

                DataStructureDTO finalCityReport = new DataStructureDTO(city,total_city_count,genders);
                System.out.println("----");
                System.out.println(finalCityReport);

                cities_report.add(finalCityReport);
            }

            System.out.println("---");
            System.out.println(cities_report);

            return cities_report;
        }
    }

}//fin del public
