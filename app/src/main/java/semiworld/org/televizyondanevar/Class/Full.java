package semiworld.org.televizyondanevar.Class;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 14.10.2016 - 22:00
 */

public class Full {
    private String name, time, link, photo_url, logo_url;

    public Full() {
    }

    public Full(String name, String time, String link, String photo_url, String logo_url) {
        this.name = name;
        this.time = time;
        this.link = link;
        this.photo_url = photo_url;
        this.logo_url = logo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
}
