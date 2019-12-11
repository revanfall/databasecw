import lombok.Data;

@Data
public class Titles {
    int idTitle;
    String titleName;

    public Titles(String titleName) {
        this.titleName = titleName;
    }

    public Titles(int idTitle, String titleName) {
        this.idTitle = idTitle;
        this.titleName = titleName;
    }
}
