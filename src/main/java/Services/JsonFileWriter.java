package Services;

import Models.MonthlyBill;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import java.util.List;

public class JsonFileWriter implements IFileWriter{

    public void writeFile(List<MonthlyBill> data, String fileName) {
        try (Writer writer = new FileWriter(fileName + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
