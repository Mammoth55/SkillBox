import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

// Default RUN with DOM Parser - OUT of Memory Exception (with configuration key -Xmx50M)
// Default RUN with DOM Parser - 308 Mb used (with configuration key -Xmx500M)
// RUN with SAX Parser - 144 Mb used
// RUN with SAX Parser (after refactoring classes) - 33 Mb used
// RUN with SAX Parser (with epsilon GC) - 2061 Mb used
// RUN with SAX Parser (after deleting doubled "new TimePeriod(visitTime, visitTime)") - 1174 Mb used
// RUN with SAX Parser (after refactoring method compareTo only) - 550 Mb used
// RUN with SAX Parser (after refactoring whole class "TimePeriod") - 264 Mb used
// 579s of loading 1.5Gb xml file into DB

public class Loader {

    static final String FILE_NAME = "src/main/resources/data-1572M.xml";

    public static void main(String[] args) throws Exception {
        long usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();

        System.out.println("Program started...");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(FILE_NAME), handler);
        DBConnection.executeMultiInsert();

        usage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory() - usage;
        System.out.println(usage / 1024 / 1024 + " Mb used.");
        System.out.println(System.currentTimeMillis() - start + " ms.");
//        DBConnection.printVoterCounts();
        String name="Деревягин Иларион";
        System.out.println(name + " => " + DBConnection.countVoterByName(name) + " times.");
    }
}