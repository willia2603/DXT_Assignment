package Services;

import Models.Item;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PriceFileParser implements IFileParser<Item> {
    private Document xmlDocument;

    public PriceFileParser(String filepath) {
        readFile(filepath);
    }

    public void readFile(String fileName) {
        InputStream is = getClass().getResourceAsStream("/Data/" + fileName);
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(is);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> processFile() {
        NodeList itemsNodeList = xmlDocument.getElementsByTagName("ItemPrice");
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < itemsNodeList.getLength(); i++) {
            Element itemElement = (Element) itemsNodeList.item(i);
            NodeList itemIds = itemElement.getElementsByTagName("Item");
            NodeList itemPrices = itemElement.getElementsByTagName("Price");

            try {
                if (itemPrices.getLength() > 1) {
                    throw new InvalidFileFormat("found two prices for one item in the file");
                }
                if (itemIds.getLength() > 1) {
                    throw new InvalidFileFormat("found two ids for one item");
                }
            } catch (InvalidFileFormat e) {
                e.printStackTrace();
            }

            String itemId = itemIds.item(0).getTextContent();
            Double itemPrice = Double.valueOf(itemPrices.item(0).getTextContent());
            Item item = new Item(itemId, itemPrice);
            items.add(item);
        }

        return items;
    }

}
