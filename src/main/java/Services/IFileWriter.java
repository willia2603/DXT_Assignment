package Services;

import Models.MonthlyBill;

import java.util.List;

public interface IFileWriter{
    void writeFile(List<MonthlyBill> bills, String fileName);
}
