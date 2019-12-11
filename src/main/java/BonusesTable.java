import lombok.Data;

import java.nio.file.StandardWatchEventKinds;

@Data
public class BonusesTable {
    String wname;
    String wsurname;
    String titlename;
    int idOrder;
    double bonuses;

    public BonusesTable(String wname, String wsurname, String titlename, int idOrder, double bonuses) {
        this.wname = wname;
        this.wsurname = wsurname;
        this.titlename = titlename;
        this.idOrder = idOrder;
        this.bonuses = bonuses;
    }
}
