package co.com.listase.lista_se.Model;

import lombok.Data;

@Data
public class ListaDE {
    private Node head;
    private int size;

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
}
