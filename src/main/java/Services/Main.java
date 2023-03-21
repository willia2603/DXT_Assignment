package Services;

import Models.Item;
import Models.MonthlyBill;
import Models.Payment;
import Models.Purchase;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // 1. read and process files
        IFileParser priceXmlParser = new PriceFileParser("Prices.xml");
        List<Item> prices = priceXmlParser.processFile();

        IFileParser paymentParser = new PaymentFileParser("Payments.json");
        List<Payment> payments = paymentParser.processFile();

        IFileParser purchaseParser = new PurchaseFileParser("Purchases.dat");
        List<Purchase> purchases = purchaseParser.processFile();

        FileContentTransformer transformer = new FileContentTransformer();
        Map<String, Double> pricesMap = transformer.priceListToHasMap(prices);

        // 2.compute differences
        ICalculator calc = new MonthlyCalculator();
        List<MonthlyBill> monthlyBills = calc.computeBill(purchases, pricesMap, payments);
        IFileWriter writer = new JsonFileWriter();

        for (String arg : args) {
            switch (arg) {
                case "csv":
                    writer = new CsvFileWriter();
                    break;
                default:
                    System.out.println("Invalid argument. Will use JSON as default");
            }
        }

        // 3. write to file
        writer.writeFile(monthlyBills, "PaymentsNotMatched");

    }
}
