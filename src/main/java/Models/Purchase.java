package Models;

import java.util.ArrayList;

public class Purchase {

    private String id;
    private String customer;
    private Integer year;
    private Integer month;
    private ArrayList<String> itemIds;


    public Purchase() {
        this.itemIds = new ArrayList<>();
    }

    public void setCustomerId(String customerId) {
        this.customer = customerId;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public void setId() {
        this.id = customer + year + month;
    }

    public String getId() {
        return id;
    }

    public Integer getMonth() {
        return month;
    }

    public String getCustomerId() {
        return customer;
    }

    public void addPurchase(String item_id) {
        this.itemIds.add(item_id);
    }

    public ArrayList<String> getItemIds() {
        return itemIds;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id='" + id + '\'' +
                ", customer='" + customer + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", itemIds=" + itemIds +
                '}';
    }
}
