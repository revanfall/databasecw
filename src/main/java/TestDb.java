import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestDb {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        try {
            db.getDbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Worker> list = db.getWorkers();

        JTable table;
        Object column[] = {"Айди", "Имя", "Фамилия", "Телефон", "Звание"};
//        for(int i=0;i<list.size();i++){
//            for(int j=0;j<5;j++){
//                obj[i][j] = list.get(i);
//            }
        System.out.println(list.get(0));
        for (BonusesTable s : db.getSalaryWithBonuses()){
            System.out.println(s.toString());
        }
    }
    }



