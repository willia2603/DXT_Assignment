package Services;

import Models.MonthlyBill;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class CsvFileWriter implements IFileWriter {

    public void writeFile(List<MonthlyBill> bills, String fileName) {

        try {
            FileWriter fileWrt = new FileWriter(fileName + ".csv");
            BufferedWriter bufferWrt = new BufferedWriter(fileWrt);
            bufferWrt.write("customer,year,month,amountDue,amountPayed,balance\n");
            for (MonthlyBill bill : bills) {
                bufferWrt
                        .write(String.format(Locale.US, "%s,%s,%s,%.2f,%.2f,%.2f\n", bill.getCustomer(), bill.getYear(),
                                bill.getMonth(), bill.getAmountDue(), bill.getAmountPayed(), bill.getBalance()));
            }
            bufferWrt.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
