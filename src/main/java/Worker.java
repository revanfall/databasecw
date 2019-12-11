import lombok.Data;

import java.io.Serializable;

@Data
public  class Worker implements Serializable {
    public Worker(String name, String surname, String phone, int title) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.title = title;
    }
    int idWorker;
    String name;
    String surname;
    String phone;
    int title;
    String titlename;

    public Worker(String name, String surname, String phone,String titlename) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.titlename=titlename;
    }

    public Worker(int idWorker, String name, String surname, String phone, String titlename) {
        this.idWorker = idWorker;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.titlename = titlename;
    }

    public Worker(int idWorker, String name, String surname, String phone, int title) {
        this.idWorker = idWorker;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.title = title;

    }

    public Worker() {

    }

    public Worker(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
