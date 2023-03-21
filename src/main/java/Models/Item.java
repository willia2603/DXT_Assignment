package Models;

public class Item {
    private String itemId;
    private Double itemPrice;

    public Item(String itemId, Double itemPrice) {
        this.itemId = itemId;
        this.itemPrice = itemPrice;
    }

    public String getItemId() {
        return itemId;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
