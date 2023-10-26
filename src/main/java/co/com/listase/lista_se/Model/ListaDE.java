package co.com.listase.lista_se.Model;

import co.com.listase.lista_se.Controller.DTO.*;
import co.com.listase.lista_se.Exception.KidsException;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ListaDE {
    private NodeDE head;
    private int size;

    public void addKidToEnd(Kid kid) {
        NodeDE newNode = new NodeDE(kid);
        if (this.head == null) {
            this.head = newNode;
        } else {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setPrevious(temp);

        }
        this.size++;
    }




    public List<Kid> getAllKids() throws KidsException {
        if(this.head==null){
            throw new KidsException("Lista vacia");
        }
        else{
            List<Kid> kids = new ArrayList<>();
            NodeDE temp = this.head;
            while(temp!=null){
                kids.add(temp.getData());
                temp = temp.getNext();
            }
            return kids;
        }
    }

    public void addToStart(Kid kid){
        if(this.head==null){
            this.head = new NodeDE(kid);
        }
        else{
            NodeDE newHead = new NodeDE(kid);
            newHead.setNext(this.head);
            newHead.setPrevious(null);
            this.head.setPrevious(newHead);
            this.head=newHead;

        }
        this.size++;
    }

    public void insertInPos(int pos, Kid kid) {
        if (pos == 1) {
            this.addKidToEnd(kid);

        } else if (pos > this.size) {
            this.addKidToEnd(kid);

        } else if (pos <= this.size) {
            NodeDE temp = this.head;
            int posAct = 1;
            while (posAct < pos - 1) {
                temp = temp.getNext();
                posAct++;
            }
            NodeDE newNode = new NodeDE(kid);
            temp.getNext().setPrevious(newNode);
            newNode.setNext(temp.getNext());
            newNode.setPrevious(temp);
            temp.setNext(newNode);
            this.size++;
        }
    }
    public void invertList() {
        if (this.head != null) {
            ListaDE listCopy = new ListaDE();
            NodeDE temp = this.head;
            while (temp != null) {
                listCopy.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCopy.getHead();
        }
    }
    public void invertEdges() {
        if (this.head != null) {
            NodeDE temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            Kid lastKid = temp.getData();
            temp.setData(this.head.getData());
            this.head.setData(lastKid);
        }
    }
    public void intercalateByGender() throws KidsException{
        if(this.head == null){
            throw new KidsException("Lista vacia");
        } else if (this.head.getNext()==null) {
            throw new KidsException("Insuficientes elementos");
        }
        else{
            ListaDE listCopy = new ListaDE();
            NodeDE temp = this.head;
            int posMale = 1;
            int posFemale = 2;
            while(temp!= null){
                if(temp.getData().getGender().equals("Male")){
                    listCopy.insertInPos(posMale,temp.getData());
                    posMale = posMale+2;
                } else if (temp.getData().getGender().equals("Female")) {
                    listCopy.insertInPos(posFemale,temp.getData());
                    posFemale=posFemale+2;
                }
                temp = temp.getNext();
            }
            this.head= listCopy.getHead();
        }
    }

   // public void deletebyid (String id) throws KidsException{
     //   if (this.head == null) {
       //     throw new KidsException("Lista Vacia");
        //}else if (this.head.getData().getId().equals(id)) {
          //  this.head = this.head.getNext();
            //this.size--;
        //}else{

        //}

    //}



    //
    //        }else{
    //            Node temp = this.head;
    //            while (temp != null) {
    //                if (temp.getNext().getData().getId().equals(id)) {
    //                    // Si encuentra un nodo cuya identificación coincide, actualiza las referencias
    //                    temp.setNext(temp.getNext().getNext());
    //                }
    //                temp = temp.getNext();
    //            }
    //            this.size--;
    //        }
    //    }

    public void deleteById(String id) throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacia");
        } else if (this.head.getData().getId().equals(id)) {
            //Nueva cabeza
            this.head = this.head.getNext();
            this.head.setPrevious(null);
            this.size--;
        }
        else{
            NodeDE temp = this.head;
            while(temp!=null) {
                if (temp.getData().getId().equals(id)) {
                    NodeDE previous = temp.getPrevious();
                    previous.setNext(temp.getNext());
                    if (temp.getNext()!=null){
                        temp.getNext().setPrevious(previous);
                    }


                    break;
                }
                temp = temp.getNext();
            }
            this.size--;
        }
    }
    public void deleteInPos(int pos) throws KidsException {
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 1) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else {
            NodeDE temp = this.head;
            int cont = 1;

            while (cont < pos - 1) {
                temp = temp.getNext();
                cont++;
            }


            temp.getNext().setPrevious(null);
            temp.setNext(temp.getNext().getNext());
        }

        this.size--;
    }
    public void deleteKamikaze(int pos) throws KidsException {
        if (pos <= 0 || pos > this.size) {
            throw new KidsException("Fuera de rango");
        }
        if (pos == 1) {
            this.head = this.head.getNext();
            this.head.setPrevious(null);
        } else {
            NodeDE temp = this.head;
            int cont = 1;

            while (cont!=pos) {
                temp = temp.getNext();
                cont++;
            }
            NodeDE previous = temp.getPrevious();
            temp.setPrevious(null);
            previous.setNext(temp.getNext());

            if (temp.getNext() != null){
                temp.getNext().setPrevious(previous);
            }


        }

        this.size--;
    }


    public List<String> getCities() {
        NodeDE temp = this.head;
        List<String> cities = new ArrayList<>();

        while (temp != null) {
            String city = temp.getData().getCityname().getCity();
            if (!cities.contains(city)) {
                cities.add(city);
            }
            temp = temp.getNext();
        }

        return cities;
    }



    public List<DataReportListaDEDTO> SchoolReport() throws KidsException{
        if(this.head==null){
            throw new KidsException("Lista vacía");
        } else {
            List<String>  cities = this.getCities();

            List<DataReportListaDEDTO> finalReport = new ArrayList<>();


            for(String city :cities){
                int total_city_count=0;//CUENTA EL TOTAL DE PERSONAS EN LA CIUDAD
                int schoolmale_count=0;//CUENTA LOS HOMBRES EN ESCUELAS
                int schoolfemale_count=0;//CUENTA LAS MUJERES EN ESCUELAS
                int malepublic_count=0;//CUENTA HOMBRES EN ESCUELAS PUBLICAS
                int maleprivate_count=0;//CUENTA HOMBRES EN ESCUELAS PRIVADAS
                int femalepublic_count=0;//CUENTA MUJERES EN PUBLICAS
                int femaleprivate_count=0;//CUENTA MUJERES EN PRIVADAS

                NodeDE temp = this.head;
                while(temp!=null){
                    if(temp.getData().getCityname().getCity().equals(city)){
                        System.out.println("------");
                        System.out.println(temp.getData());
                        System.out.println("----");
                        System.out.println(city);
                        if(temp.getData().getGender().equals("Male")&&
                                temp.getData().getSchool().equals("public")){
                            schoolmale_count++;
                            total_city_count++;

                            System.out.println("Male");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("----");
                            System.out.println("Public School");
                            malepublic_count++;
                            System.out.println("----");
                            System.out.println("value: "+malepublic_count);

                        }
                        if(temp.getData().getGender().equals("Male")&&
                                temp.getData().getSchool().equals("private")) {
                            schoolmale_count++;
                            total_city_count++;

                            System.out.println("Male");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("----");
                            System.out.println("Private School");
                            maleprivate_count++;
                            System.out.println("----");
                            System.out.println("value: " + maleprivate_count);
                        }
                        if(temp.getData().getGender().equals("Male")&&
                                temp.getData().getSchool().equals("No school")) {

                            total_city_count++;

                            System.out.println("Male");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("----");
                            System.out.println("No School");

                        }
                        if(temp.getData().getGender().equals("Female")&&
                                temp.getData().getSchool().equals("public")){
                            schoolfemale_count++;
                            total_city_count++;

                            System.out.println("Female");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("----");
                            System.out.println("Public School");
                            femalepublic_count++;
                            System.out.println("----");
                            System.out.println("value: "+femalepublic_count);

                        }
                        if(temp.getData().getGender().equals("Female")&&
                                temp.getData().getSchool().equals("private")) {
                            schoolfemale_count++;
                            total_city_count++;

                            System.out.println("Female");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("----");
                            System.out.println("Private School");
                            femaleprivate_count++;
                            System.out.println("----");
                            System.out.println("value: " + femaleprivate_count);
                        }
                        if(temp.getData().getGender().equals("Female")&&
                                temp.getData().getSchool().equals("No school")) {

                            total_city_count++;

                            System.out.println("Female");
                            System.out.println("---");
                            System.out.println(temp.getData());
                            System.out.println("----");
                            System.out.println("No School");

                        }



                        //System.out.println("New total count in"+city+"-----"+total_city_count);
                    }
                    //System.out.println("Female in shcool"+schoolfemale_count);
                    //System.out.println("Male in shcool"+schoolmale_count);
                    //System.out.println("New total count in"+city+"-----"+total_city_count);

                    temp = temp.getNext();
                }
                //set list for male

                List<SchoolStructureListDEDTO> SchoolMales = new ArrayList<>();
                SchoolMales.add(new SchoolStructureListDEDTO("Public",malepublic_count));
                SchoolMales.add(new SchoolStructureListDEDTO("Private",maleprivate_count));

                //set list for female

                List<SchoolStructureListDEDTO> SchoolFemales= new ArrayList<>();
                SchoolFemales.add(new SchoolStructureListDEDTO("Public",femalepublic_count));
                SchoolFemales.add(new SchoolStructureListDEDTO("Private",femaleprivate_count));

                //DIVIDE BY GENDER

                List<GenderStructrureListDEDTO> genders =new ArrayList<>();
                genders.add(new GenderStructrureListDEDTO("Male",SchoolMales,schoolmale_count));
                genders.add(new GenderStructrureListDEDTO("Female",SchoolFemales,schoolfemale_count));
                // Objeto para final report
                finalReport.add(new DataReportListaDEDTO(city,total_city_count,genders));


            }

            return finalReport;
        }
    }


}
