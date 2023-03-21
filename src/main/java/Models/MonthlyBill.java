package Models;

public class MonthlyBill {
    private String customer;
    private String year;
    private String month;
    private Double amountDue;
    private Double amountPayed;
    private Double balance;

    public MonthlyBill(String customer, String year, String month, Double amountDue, Double amountPayed, Double balance) {
        this.customer = customer;
        this.year = year;
        this.month = month;
        this.amountDue = amountDue;
        this.amountPayed = amountPayed;
        this.balance = balance;
    }

    public String getCustomer() {
        return this.customer;
    }

    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public Double getAmountPayed() {
        return amountPayed;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "MonthlyBill{" +
                "customer='" + customer + '\'' +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", amountDue=" + amountDue +
                ", amountPayed=" + amountPayed +
                ", balance=" + balance +
                '}';
    }
}
