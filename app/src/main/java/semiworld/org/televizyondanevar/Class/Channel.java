package semiworld.org.televizyondanevar.Class;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 14.10.2016 - 20:20
 */

public class Channel {
    private String name;
    private String logo_url;
    private String link;

    public Channel() {
    }

    public Channel(String name, String logo_url, String link) {
        this.name = name;
        this.logo_url = logo_url;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
