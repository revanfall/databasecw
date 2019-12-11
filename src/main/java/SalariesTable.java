import lombok.Data;

@Data
public class SalariesTable {
    String wname;
    String wsurname;
    String title;
    int idOrder;
    double salary;

    public SalariesTable(String wname, String wsurname, String title, double salary) {
        this.wname = wname;
        this.wsurname = wsurname;
        this.title = title;
        this.salary = salary;
    }
}
