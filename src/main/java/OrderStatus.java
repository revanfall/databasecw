import lombok.Data;

@Data
public class OrderStatus {
    int idOrder;
    String status;

    public OrderStatus(int idOrder, String status) {
        this.idOrder = idOrder;
        this.status = status;
    }
}
