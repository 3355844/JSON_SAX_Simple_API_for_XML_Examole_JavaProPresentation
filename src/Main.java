import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by 33558 on 18.01.2017.
 */
public class Main extends DefaultHandler {
    private boolean bAuthor;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Document started...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Document ends...");
    }

    @Override
    public void startElement(String namespaceURL, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Element: " + localName);
        bAuthor = localName.equals("author");
    }

    @Override
    public void characters(char[] chars, int start, int lenght) throws SAXException {
        if (bAuthor){
            System.out.println("Author value: " + new String(chars, start, lenght));
            bAuthor = false;
        }
    }

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();
            xmlReader.setContentHandler(new Main());
            xmlReader.parse("1.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
