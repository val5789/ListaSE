package co.com.listase.lista_se.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class NodeDE {
    private Kid data;
    private NodeDE next;
    private NodeDE previous;

    public NodeDE(Kid data) {
        this.data = data;
    }

}
