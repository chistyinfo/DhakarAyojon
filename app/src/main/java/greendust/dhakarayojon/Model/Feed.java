package greendust.dhakarayojon.Model;

/**
 * Created by Joker on 7/24/16.
 */
public class Feed {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimed() {
        return timed;
    }

    public void setTimed(String timed) {
        this.timed = timed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setVenu(String name) {
        this.venu = venu;
    }
    public String getVenu() {
        return venu;
    }

    public void setCont(String name) {
        this.cont = cont;
    }
    public String getCont() {
        return cont;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int id;
    private String name,image,status,timed,venu,cont;
}
