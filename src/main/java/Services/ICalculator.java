package Services;

import Models.MonthlyBill;
import Models.Payment;
import Models.Purchase;

import java.util.List;
import java.util.Map;

public interface ICalculator{
    List<MonthlyBill> computeBill(List<Purchase> purchases, Map<String, Double> prices,
                                         List<Payment> payments);

}
