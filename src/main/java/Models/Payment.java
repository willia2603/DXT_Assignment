package Models;


public class Payment {
    private String id;
    private String customer;
    private String year;
    private String month;
    private Double amount;


    public Payment(String customer, String year, String month, Double amount) {
        this.customer = customer;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

    public Boolean hasNullEntry() {
        return customer == null || year == null || month == null || amount == null;
    }

    public void setId() {
        this.id = customer + year + month;
    }

    public Double getAmount() {
        return amount;
    }

    public String getId() {
        return id;
    }

    public String getCustomer() {
        return customer;
    }

    public String getYear() {
        return year;
    }


    public String getMonth() {
        return month;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "customerId='" + customer + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", amount=" + amount + '\'' +
                ", id=" + id +
                '}';
    }
}
