public class CurrentUser {
    private static CurrentUser instance;
    public Worker worker;

    private CurrentUser(Worker worker) {
        this.worker=worker;
    }
    public static CurrentUser getInstance(Worker worker){
        if(instance==null){
            instance= new CurrentUser(worker);
        }
        return instance;

    }
}
