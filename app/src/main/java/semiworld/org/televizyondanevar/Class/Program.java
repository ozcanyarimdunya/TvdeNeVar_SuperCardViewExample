package semiworld.org.televizyondanevar.Class;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 12.10.2016 - 23:01
 */

public class Program {
    private String logo_url;
    private String program_name;
    private String program_category;
    private String program_start_time;
    private String channel_name;
    private String link;

    public Program() {
    }

    public Program(String logo_url, String program_name, String program_category, String program_start_time, String channel_name, String link) {
        this.logo_url = logo_url;
        this.program_name = program_name;
        this.program_category = program_category;
        this.program_start_time = program_start_time;
        this.channel_name = channel_name;
        this.link = link;
    }

    public String getLogo_url() {
        return logo_url;
    }

    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public String getProgram_category() {
        return program_category;
    }

    public void setProgram_category(String program_category) {
        this.program_category = program_category;
    }

    public String getProgram_start_time() {
        return program_start_time;
    }

    public void setProgram_start_time(String program_start_time) {
        this.program_start_time = program_start_time;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
