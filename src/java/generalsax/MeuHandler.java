/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generalsax;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author mascport
 */
public class MeuHandler extends DefaultHandler {

    private StringBuilder reserva;
    private final String resTag = "reserva";
    private boolean bIRes;

    public MeuHandler(StringBuilder res) {
        reserva = res;
        bIRes = false;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase(resTag)) bIRes = true;
    }

    @Override
    public void endElement(String uri, String localName,
            String qName) throws SAXException {
        if (qName.equalsIgnoreCase(resTag)) bIRes = false;
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (bIRes) {
            reserva.append(new String(ch, start, length));
            bIRes = false;
        }
    }
}
