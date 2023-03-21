package Services;

import Models.MonthlyBill;
import Models.Payment;
import Models.Purchase;

import java.util.*;

public class MonthlyCalculator implements ICalculator{

    public List<MonthlyBill> computeBill(List<Purchase> purchases, Map<String, Double> prices,
            List<Payment> payments) {
        Map<String, Double> amounts = new HashMap<>();
        // compute monthly purchase amount for each client
        for (Purchase purchase : purchases) {
            ArrayList<String> PurchaseItemIds = purchase.getItemIds();
            for (String itemId : PurchaseItemIds) {
                Double itemAmount = prices.get(itemId);
                String id = purchase.getId();
                if (amounts.containsKey(id)) {
                    Double sum = itemAmount + amounts.get(id);
                    amounts.put(id, sum);
                } else {
                    amounts.put(id, itemAmount);
                }
            }
        }

        amounts.forEach((key, value) -> amounts.put(key, Double.parseDouble(String.format(Locale.US,"%.2f", value))));

        List<MonthlyBill> monthlyBills = new ArrayList<>();

        for (Payment payment : payments) {
            String id = payment.getId();
            if (amounts.containsKey(id)) {
                Double amountDue = amounts.get(id);
                Double amountPayed = payment.getAmount();
                String strBalance = String.format(Locale.US,"%.2f", (amountDue - amountPayed));
                // balance:
                // if negative -> store owes money;
                // if positive -> store is owned money
                Double balance = Double.parseDouble(strBalance);
                if (balance != 0) {
                    MonthlyBill monthlyBill = new MonthlyBill(payment.getCustomer(), payment.getYear(),
                            payment.getMonth(), amountDue, amountPayed, balance);
                    monthlyBills.add(monthlyBill);
                }
            } else {
                Double amountDue = 0.0;
                Double amountPayed = payment.getAmount();
                Double balance = -amountPayed;
                Models.MonthlyBill monthlyBill = new Models.MonthlyBill(payment.getCustomer(), payment.getYear(),
                        payment.getMonth(), amountDue, amountPayed, balance);
                monthlyBills.add(monthlyBill);
            }
        }

        Collections.sort(monthlyBills,
                (b1, b2) -> Double.compare(Math.abs(b2.getBalance()), Math.abs(b1.getBalance())));

        return monthlyBills;

    }

}
