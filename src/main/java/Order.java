import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
public class Order {
Date appointmentDate;
String orderType;
int idEmployee;
double orderPrice;
int orderQuant;

    public Date getAppointmentDateCur() {
        appointmentDate=new Date(0);
        long d=System.currentTimeMillis();
        appointmentDate.setTime(d);
        return appointmentDate;
    }

    public Order(String orderType, int idEmployee, double orderPrice, int orderQuant) {
        this.appointmentDate = getAppointmentDateCur();
        this.orderType = orderType;
        this.idEmployee = idEmployee;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
    }

    public Order(Date appointmentDate, String orderType, int idEmployee, double orderPrice, int orderQuant) {
        this.appointmentDate = appointmentDate;
        this.orderType = orderType;
        this.idEmployee = idEmployee;
        this.orderPrice = orderPrice;
        this.orderQuant = orderQuant;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }
}
