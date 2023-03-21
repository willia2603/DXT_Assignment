package Services;

import Models.Purchase;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PurchaseFileParser implements IFileParser<Purchase> {
    private ArrayList<String> fileLines;

    public PurchaseFileParser(String path) {
        readFile(path);

    }

    private void readFile(String fileName) {

        try {
            InputStream is = getClass().getResourceAsStream("/Data/" + fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            fileLines = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                fileLines.add(line);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Purchase> processFile() {
        List<Purchase> purchases = new ArrayList<>();
        String custId;
        Integer year;
        Integer month;
        String itemId;
        Purchase purchase = new Purchase();

        try {
            for (String line : this.fileLines) {
                if (line.contains("CUST")) {
                    custId = line.substring(4);
                    if (custId.length() > 6) {
                        throw new InvalidFileFormat("invalid file format: customer number is longer than 6 characters");
                    }
                    if (purchase.getCustomerId() == null) {
                        purchase.setCustomerId(custId);
                    } else {
                        purchase.setId();
                        purchases.add(purchase);
                        purchase = new Purchase();
                        purchase.setCustomerId(custId);
                    }
                } else if (line.contains("DATE")) {
                    if (purchase.getMonth() == null) {
                        year = Integer.valueOf(line.substring(8, 12));
                        month = Integer.valueOf(line.substring(6, 8));
                        purchase.setYear(year);
                        purchase.setMonth(month);
                    } else {
                        throw new InvalidFileFormat("Invalid file format: a purchase cannot have two dates");
                    }
                } else if (line.contains("ITEM")) {
                    if (purchase.getMonth() != null) {
                        itemId = line.substring(4);
                        if (itemId.length() > 6) {
                            throw new InvalidFileFormat(
                                    "invalid file format: customer number is longer than 6 characters");
                        }
                        purchase.addPurchase(itemId);
                    } else {
                        throw new InvalidFileFormat("invalid file format: item purchase has no associated date");
                    }
                }
            }
        } catch (InvalidFileFormat e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            return purchases;
        }
    }

}
