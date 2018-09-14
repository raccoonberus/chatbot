package com.raccoonberus.chatbot.strategy;

import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.NodeList;

public class WantGoToCinema {

    private static final String keywords = "what about cinema?";

    public String run(String address) {

        String attribute = "data-day";
        String attributeValue = "today";
        String attributeMovieName = "target";
//        String attributeFormat2DValue = "format format-2D";
//        String attributeFormat3DValue = "format format-3D";
//        String attributeTime = "kh:widget";
//        String attributeTimeValue = "seance";

        try {
            Parser parser = new Parser(address);
            HasAttributeFilter filter = new HasAttributeFilter (attribute, attributeValue);
            NodeList nodeList = parser.parse(filter);
            address = nodeList.elementAt(1).toHtml();

            Parser nameParser = new Parser();
            nameParser.setInputHTML(address);
            HasAttributeFilter nameFilter = new HasAttributeFilter (attributeMovieName);
            NodeList nameList = nameParser.parse(nameFilter);
            String name;

//            Parser timeParser = new Parser();
//            timeParser.setInputHTML(address);
//            HasAttributeFilter timeFilter = new HasAttributeFilter (attributeTime, attributeTimeValue);
//            NodeList timeList = timeParser.parse(timeFilter);
//            String time;

            for(int i = 0; i < nameList.size(); i++) {

                name = nameList.elementAt(i).toHtml();
                name = name.substring(name.indexOf('>') + 1, name.length() - 4);
                System.out.println(name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "nothing";
    }

    public static void main (String args[]) {

        new WantGoToCinema().run("https://mori-cinema.ru/cinema_detail/76_mori_sinema_krasnogorsk/schedule.php");
    }
}
