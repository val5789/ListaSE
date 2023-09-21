package co.com.listase.lista_se.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Node {
    private Node next;
    private Object data;


}
