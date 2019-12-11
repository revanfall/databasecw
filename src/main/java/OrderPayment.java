import lombok.Data;

@Data
public class OrderPayment {
    int paymentId;
    int orderId;
    double orderPay;

    public OrderPayment(int orderI) {
        this.orderId = orderId;
        this.orderPay = orderPay;
    }

    public OrderPayment(int paymentId, int orderId, double orderPay) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.orderPay = orderPay;
    }

    public OrderPayment(int orderId, double orderPay) {
        this.orderId = orderId;
        this.orderPay = orderPay;
    }
}
