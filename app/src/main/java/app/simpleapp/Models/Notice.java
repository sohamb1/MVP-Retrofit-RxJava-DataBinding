package app.simpleapp.Models;

import com.google.gson.annotations.SerializedName;

public class Notice {

    @SerializedName("id")
    private String id;
    @SerializedName("employee_name")
    private String title;
    @SerializedName("employee_salary")
    private String brief;
    @SerializedName("employee_age")
    private String fileSource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getFileSource() {
        return fileSource;
    }

    public void setFileSource(String fileSource) {
        this.fileSource = fileSource;
    }

    public Notice(String id, String title, String brief, String fileSource) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.fileSource = fileSource;
    }

    public String titleName;
}
