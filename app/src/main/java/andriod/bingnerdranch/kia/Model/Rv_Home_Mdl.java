package andriod.bingnerdranch.kia.Model;

import com.google.gson.annotations.SerializedName;

public class Rv_Home_Mdl {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("before_price")
    private String before_price;
    @SerializedName("after_price")
    private String after_price;
    @SerializedName("off")
    private String off;
    @SerializedName("description")
    private String description;
    @SerializedName("link_img")
    private String link_img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBefore_price() {
        return before_price;
    }

    public void setBefore_price(String before_price) {
        this.before_price = before_price;
    }

    public String getAfter_price() {
        return after_price;
    }

    public void setAfter_price(String after_price) {
        this.after_price = after_price;
    }

    public String getOff() {
        return off;
    }

    public void setOff(String off) {
        this.off = off;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink_img() {
        return link_img;
    }

    public void setLink_img(String link_img) {
        this.link_img = link_img;
    }
}
