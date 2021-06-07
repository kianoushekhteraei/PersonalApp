package andriod.bingnerdranch.kia.Model;

public class Tab_Orders_Mdl {


    private Integer id ;
    private String name_orders , before_price , 	after_price , 	off , description , link_img ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName_orders() {
        return name_orders;
    }

    public void setName_orders(String name_orders) {
        this.name_orders = name_orders;
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
