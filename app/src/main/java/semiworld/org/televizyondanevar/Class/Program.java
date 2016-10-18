package semiworld.org.televizyondanevar.Class;

/**
 * Author       : l50 - Özcan YARIMDUNYA (@ozcaan11)
 * Created at   : 12.10.2016 - 23:01
 */

public class Program {
    private String photo;
    private String program_name;
    private String program_category;
    private String program_start_time;
    private String link;
    private String program_time;
    private String program_summary;

    public Program() {
    }

    public Program(String photo, String program_name, String program_category, String program_start_time, String link, String program_time, String program_summary) {
        this.photo = photo;
        this.program_name = program_name;
        this.program_category = program_category;
        this.program_start_time = program_start_time;
        this.link = link;
        this.program_time = program_time;
        this.program_summary = program_summary;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProgram_time() {
        return program_time;
    }

    public void setProgram_time(String program_time) {
        this.program_time = program_time;
    }

    public String getProgram_summary() {
        return program_summary;
    }

    public void setProgram_summary(String program_summary) {
        this.program_summary = program_summary;
    }
}
