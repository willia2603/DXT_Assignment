package Services;

import Models.Payment;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PaymentFileParser implements IFileParser<Payment> {
    private List<Payment> payments;

    public PaymentFileParser(String path) {
        readFile(path);
    }

    private void readFile(String fileName) {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        InputStream is = getClass().getResourceAsStream("/Data/" + fileName);
        Reader reader = new InputStreamReader(is);
        Type paymentListType = new TypeToken<ArrayList<Payment>>() {
        }.getType();
        this.payments = gson.fromJson(reader, paymentListType);


        try {
            for (Payment payment : this.payments) {
                if (payment.hasNullEntry()) {
                    throw new InvalidFileFormat("At least one of the following keys is missing in the json file: customer, year, month or amount");
                }
            }
        } catch (InvalidFileFormat e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> processFile() {
        for (Payment payment : payments) {
            payment.setId();
        }
        return this.payments;
    }


}
