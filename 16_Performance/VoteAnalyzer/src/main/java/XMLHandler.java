import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class XMLHandler extends DefaultHandler {

    private Voter voter;
    private final static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    public XMLHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        try {
            String birthDay = attributes.getValue("birthDay");
            if (qName.equals("voter")) {
                voter = new Voter(attributes.getValue("name"), birthDayFormat.parse(birthDay));
            } else if (qName.equals("visit") && voter != null) {
                DBConnection.countVoter(voter);
            }
        } catch (ParseException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}