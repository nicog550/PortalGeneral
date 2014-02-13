/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalsax;

import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;

public class XMLLector {
    
    public void comprovarCreacioReserva(String s, StringBuilder sb) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            InputSource in = new InputSource(new StringReader(s));
            MeuHandler handler = new MeuHandler(sb);
            saxParser.parse(in, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
