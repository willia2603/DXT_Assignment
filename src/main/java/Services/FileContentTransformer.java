package Services;

import Models.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileContentTransformer {

    public Map<String, Double> priceListToHasMap(List<Item> prices) {
        Map<String, Double> pricesMap = new HashMap<>();
        for (Item item : prices) {
            pricesMap.put(item.getItemId(), item.getItemPrice());
        }
        return pricesMap;
    }

}
