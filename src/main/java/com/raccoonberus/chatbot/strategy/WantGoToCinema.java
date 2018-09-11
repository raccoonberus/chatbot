package com.raccoonberus.chatbot.strategy;

import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.NodeList;

public class WantGoToCinema {

    private static final String keywords = "what about cinema?";

    public String run(String address) {

        String nameAttribute = "itemprop";
        String nameAttributeValue = "name";
        String timeAttribute = "class";
        String timeAttributeValue = "or";

        try {
            Parser parser = new Parser(address);
            HasAttributeFilter filter = new HasAttributeFilter (nameAttribute, nameAttributeValue);
            NodeList nodeList = parser.parse(filter);

            Parser timeParser = new Parser(address);
            HasAttributeFilter timeFilter = new HasAttributeFilter (timeAttribute, timeAttributeValue);
            NodeList timeNodeList = timeParser.parse(timeFilter);

            for (int i = 0; i < timeNodeList.size(); i++) {

                String s = timeNodeList.elementAt(i).getText();
                s = "https://www.kinopoisk.ru/" + s.substring(20, s.length() - 1);
                System.out.println(s);
            }

            for (int i = 0; i < nodeList.size(); i++) {
                String s = nodeList.elementAt(i).getText().replaceAll("&nbsp;", " ");
                if (!s.contains("кинотеатрах")) {
                    s = s.substring(30, s.length() - 3);
                    System.out.println(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "nothing";
    }

    private String parseTime(String address) {

        String attribute = "data-template";
        String attributeValue = "schedule-item__template";

        String s = null;
        try {
            Parser parser = new Parser(address);
            HasAttributeFilter filter = new HasAttributeFilter (attribute, attributeValue);
            NodeList nodeList = parser.parse(filter);

            for (int i = 0; i < nodeList.size(); i++) {

                s = nodeList.elementAt(i).getText();
//                System.out.println(s.length());
//                s = s.substring(200, s.length() - 7);
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    public static void main (String args[]) {

//        new WantGoToCinema().run("https://www.kinopoisk.ru/afisha/new/city/452/");
        new WantGoToCinema().parseTime("https://www.kinopoisk.ru/film/991097/afisha/city/452/");
    }
}
