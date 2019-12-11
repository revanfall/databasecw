import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection extends DbConfigs {
    Connection dbConnection;
    Statement st;

    public Connection getDbConnection() throws SQLException {

        String connectionString = "jdbc:mysql://"
                + dbHost + ":" + dbPort + "/" + dbName + "?allowMultiQueries=true&useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false" +
                "&serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        st = dbConnection.createStatement();
        return dbConnection;
    }

    @Override
    public String toString() {
        return "DatabaseConnection{" +
                "dbConnection=" + dbConnection +
                ", st=" + st +
                '}';
    }

    public Worker loginUser(LoginWorker login){
        ResultSet resultSet=null;
        String select="SELECT "+ConstTables.WORKERS_ID+","+ConstTables.WORKERS_NAME+","
                +ConstTables.WORKERS_SURNAME+","+ConstTables.WORKERS_PHONE+","+ConstTables.WORKERS_IDTITLE+
                " FROM "+ConstTables.ADVERT_WORKERS +" INNER JOIN "+ConstTables.LOGIN_TABLE+" ON "+ConstTables.LOGIN_IDUSER
                +" = "+ConstTables.WORKERS_ID +" WHERE "+ConstTables.LOGIN_LOGIN+" =?"+" AND "+ConstTables.LOGIN_PASSWORDS+" =?";

        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(select);
            prSt.setString(1,login.getLogin());
            prSt.setString(2,login.getPassword());

            resultSet=prSt.executeQuery();

            if(resultSet.next()){
                Worker worker=new Worker(resultSet.getInt(ConstTables.WORKERS_ID),resultSet.getString(ConstTables.WORKERS_NAME),
                        resultSet.getString(ConstTables.WORKERS_SURNAME),resultSet.getString(ConstTables.WORKERS_PHONE),
                        resultSet.getInt(ConstTables.WORKERS_IDTITLE));
                return worker;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Worker();
    }
    public boolean registerWorker(Worker worker){
        String insert="INSERT INTO "+ConstTables.ADVERT_WORKERS+" ( "+ConstTables.WORKERS_NAME+","+ConstTables.WORKERS_SURNAME+","
                +ConstTables.WORKERS_PHONE+","+ConstTables.WORKERS_IDTITLE+" ) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,worker.getName());
            preparedStatement.setString(2,worker.getSurname());
            preparedStatement.setString(3,worker.getPhone());
            preparedStatement.setInt(4,worker.getTitle());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean setAuthorisation(LoginWorker login){
        String insert="Insert into "+ConstTables.LOGIN_TABLE+" ("+ConstTables.LOGIN_LOGIN+","+ConstTables.LOGIN_PASSWORDS+","+
                ConstTables.LOGIN_IDUSER+") VALUES(?,?,?)";
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1,login.getLogin());
            preparedStatement.setString(2,login.getPassword());
            preparedStatement.setInt(3,login.getIduser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean changePassword(LoginWorker login,String newPassword){
       String update="Update "+ ConstTables.LOGIN_TABLE+" set "+ConstTables.LOGIN_PASSWORDS +"=? where "+ConstTables.LOGIN_LOGIN
               +"=? and "+ ConstTables.LOGIN_PASSWORDS+"=?";
        try {
            PreparedStatement preparedStatement=getDbConnection().prepareStatement(update);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,login.getLogin());
            preparedStatement.setString(3,login.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public ArrayList<Worker> getWorkers(){
        ArrayList<Worker> result=new ArrayList<>();
        String select="select "+ConstTables.WORKERS_ID +","+ConstTables.WORKERS_NAME+","+ConstTables.WORKERS_SURNAME+","+ConstTables.WORKERS_PHONE +","+ConstTables
                .TITLE_NAME+" from "+ ConstTables.ADVERT_WORKERS+ " inner join "+ConstTables.TITLE_TABLE+" on "+ConstTables.TITLE_ID+"="+
                ConstTables.WORKERS_IDTITLE+";";
        ResultSet resultSet;
        try {
            resultSet=st.executeQuery(select);
            while (resultSet.next()) {
                result.add(new Worker(resultSet.getInt(ConstTables.WORKERS_ID),
                        resultSet.getString(ConstTables.WORKERS_NAME),
                        resultSet.getString(ConstTables.WORKERS_SURNAME),
                        resultSet.getString(ConstTables.WORKERS_PHONE),
                        resultSet.getString(ConstTables.TITLE_NAME)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean deleteWotker(Worker worker){
        boolean flag=false;
        String delete="Delete from "+ConstTables.ADVERT_WORKERS+" where "+ConstTables.WORKERS_NAME+"=? and "+ConstTables.WORKERS_SURNAME+
                "=?";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(delete);
            prSt.setString(1,worker.getName());
            prSt.setString(2,worker.getSurname());
            prSt.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
    public void addOrder(Order order){
        String insert="insert into "+ConstTables.ORDER_TABLE+"("+ConstTables.ORDER_APDATE+","+ConstTables.ORDER_ORDERTYPE+
                ","+ConstTables.ORDER_IDWORKER+","+ConstTables.ORDER_ORDERPRICE+","+
                ConstTables.ORDER_ORDERQUANTITY+" ) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(insert);
            prSt.setDate(1, order.getAppointmentDate());
            prSt.setString(2,order.getOrderType());
            prSt.setInt(3,order.getIdEmployee());
            prSt.setDouble(4,order.getOrderPrice());
            prSt.setInt(5,order.getOrderQuant());

            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Order> getAllOrders(){
        ArrayList<Order> orders=new ArrayList<>();
        String select="select "+ConstTables.ORDER_APDATE+","+ConstTables.ORDER_ORDERTYPE+","+
                ConstTables.ORDER_IDWORKER+","+ConstTables.ORDER_ORDERPRICE+","+
                ConstTables.ORDER_ORDERQUANTITY+" from "+ConstTables.ORDER_TABLE;
        ResultSet resSet;
        try {
            resSet=st.executeQuery(select);
            while(resSet.next()){orders.add(new Order(resSet.getDate(ConstTables.ORDER_APDATE),
                    resSet.getString(ConstTables.ORDER_ORDERTYPE),
                    resSet.getInt(ConstTables.ORDER_IDWORKER),
                    resSet.getDouble(ConstTables.ORDER_ORDERPRICE),
                    resSet.getInt(ConstTables.ORDER_ORDERQUANTITY)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<Order> getCurrentWorkerOrders(Worker worker){
        ArrayList<Order> orders=new ArrayList<>();
        String select="select "+ConstTables.ORDER_APDATE+","+ConstTables.ORDER_ORDERTYPE+","+
                ConstTables.ORDER_IDWORKER+","+ConstTables.ORDER_ORDERPRICE+","+
                ConstTables.ORDER_ORDERQUANTITY+" from "+
                ConstTables.ORDER_TABLE+" where "+ConstTables.ORDER_IDWORKER+"="+worker.getIdWorker();
        ResultSet resSet;
        try {
            resSet=st.executeQuery(select);
            while (resSet.next()){
                orders.add(new Order(resSet.getDate(ConstTables.ORDER_APDATE),
                        resSet.getString(ConstTables.ORDER_ORDERTYPE),
                        resSet.getInt(ConstTables.ORDER_IDWORKER),
                        resSet.getDouble(ConstTables.ORDER_ORDERPRICE),
                        resSet.getInt(ConstTables.ORDER_ORDERQUANTITY)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public void setPayment(OrderPayment orPay){
        String insert="insert into "+ConstTables.ORDERPAYMENT_TABLE+" ("
                +ConstTables.ORDERPAYMENT_IDORDER+","
                +ConstTables.ORDERPAYMENT_PAYMENT+" ) VALUES(?,?)";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(insert);
            prSt.setInt(1,orPay.getOrderId());
            prSt.setDouble(2,orPay.getOrderPay());
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<OrderPayment> getAllPayments(){
        ArrayList<OrderPayment> result=new ArrayList<>();
        String select="select *"
                +" from "+ConstTables.ORDERPAYMENT_TABLE;
        try {
            ResultSet resSet=st.executeQuery(select);
            while (resSet.next()){
                result.add(new OrderPayment(resSet.getInt("idordes"),resSet.getInt(ConstTables.ORDERPAYMENT_IDORDER),
                        resSet.getDouble(ConstTables.ORDERPAYMENT_TABLE)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<OrderPayment> summaryAllPayments(){
        ArrayList<OrderPayment> result=new ArrayList<>();
        String select="Select "+ConstTables.ORDERPAYMENT_IDORDER+", sum("+ConstTables.ORDERPAYMENT_PAYMENT+") from "
                +ConstTables.ORDERPAYMENT_TABLE+" group by "+ConstTables.ORDERPAYMENT_IDORDER;
        try {
            ResultSet resSet=st.executeQuery(select);
            while(resSet.next()){
                result.add(new OrderPayment(resSet.getInt(ConstTables.ORDERPAYMENT_IDORDER),
                        resSet.getDouble("sum("+ConstTables.ORDERPAYMENT_TABLE+")")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<SalariesTable> getAllSalaries(){
        ArrayList<SalariesTable> result=new ArrayList<>();
        String select="select "+ConstTables.WORKERS_NAME+","+ConstTables.WORKERS_SURNAME+","+ConstTables.TITLE_NAME+
                ","+ConstTables.SALARIES_AMOUNT+
                " from (titles w join worker on idtitle=title join salaries on idtitles=title)";

        try {
            ResultSet resSet=st.executeQuery(select);
            while (resSet.next()){
                result.add(new SalariesTable(resSet.getString(ConstTables.WORKERS_NAME),
                resSet.getString(ConstTables.WORKERS_SURNAME),resSet.getString(ConstTables.TITLE_NAME),
                        resSet.getDouble(ConstTables.SALARIES_AMOUNT)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<BonusesTable> getAllBonuses(){
        ArrayList<BonusesTable> result=new ArrayList<>();
        String select="select wname, surname, titlename,idorder,sum(orderpaid)/10 from" +
                " (titles w join worker on idtitle=title " +
                "join salaries on idtitles=title " +
                "join orderpaid" +
                " on idorder=idorder) group by idorder;";
        try {
            ResultSet resSet=st.executeQuery(select);
            while(resSet.next()){
                result.add(new BonusesTable(resSet.getString("wname"),
                        resSet.getString("surname"),
                        resSet.getString("titlename"),
                        resSet.getInt("idorder"),
                        resSet.getDouble("sum(orderpaid)/10")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public ArrayList<BonusesTable> getSalaryWithBonuses(){
        ArrayList<BonusesTable> result=new ArrayList<>();
        String select="select wname, surname, titlename,idorder,(sum(orderpaid)/10)+salariescol " +
                "from (titles w join worker on idtitle=title join salaries on idtitles=title " +
                "join orderpaid on idorder=idorder)" +
                "group by idorder,idtitles;";
        try {
            ResultSet resSet=st.executeQuery(select);
            while (resSet.next()){result.add(new BonusesTable(resSet.getString("wname"),
                    resSet.getString("surname"),
                    resSet.getString("titlename"),
                    resSet.getInt("idorder"),
                    resSet.getDouble("(sum(orderpaid)/10)+salariescol")
            ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Titles> getTitlesList(){
        ArrayList<Titles> result=new ArrayList<>();
        String select="select * from titles";

        try {
            ResultSet resSet=st.executeQuery(select);
            while(resSet.next()){result.add(new Titles(resSet.getInt("idtitle"),
                    resSet.getString("titlename")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean updateSalariesInfo(int id,double sal){
        String update="update salaries set salariescol=? where idtitles=?";
        try {
            PreparedStatement prSt=getDbConnection().prepareStatement(update);
            prSt.setDouble(1,sal);
            prSt.setInt(2,id);
            prSt.executeUpdate();
        } catch (SQLException e) {
    e.printStackTrace();        }
        return true;
    }
    public int getWorkerId(String surname){
        int id=0;

        String select="SELECT idworker FROM worker where surname="+"'"+ surname+ "'";
        try {
            ResultSet resultSet=st.executeQuery(select);
            while(resultSet.next()){
                id=resultSet.getInt("idworker");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    public ArrayList<OrderStatus> getOrderStatus(){
        ArrayList<OrderStatus> result=new ArrayList<>();
        String select="SELECT idorder,status FROM orderstatus";
        try {
            ResultSet resultSet=st.executeQuery(select);
            while (resultSet.next()){result.add(new OrderStatus(resultSet.getInt("idorder"),
                    resultSet.getString("status")));}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
