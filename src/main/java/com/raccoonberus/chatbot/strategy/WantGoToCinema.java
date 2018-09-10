package com.raccoonberus.chatbot.strategy;

import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.NodeList;

public class WantGoToCinema {

    private static final String keywords = "what about cinema?";

    public String run(String address) {

        String nameAttribute = "itemprop";
        String nameAttributeValue = "name";
//        String timeAttribute = "class";
//        String timeAttributeValue = "buyTicketLead";
        try {
            Parser parser = new Parser(address);
            HasAttributeFilter filter = new HasAttributeFilter (nameAttribute, nameAttributeValue);
            NodeList nodeList = parser.parse(filter);
            Node node;

            for (int i = 0; i < nodeList.size(); i++) {

                node = nodeList.elementAt(i);
                if (!node.getText().contains("кинотеатрах")) {
                    String s = node.getText().replaceAll("&nbsp;", " ");
                    int g = s.indexOf('"', 30);
                    System.out.println(s.substring(30, g));
                }
            }

/*            filter = new HasAttributeFilter (timeAttribute, timeAttributeValue);
            nodeList = parser.parse(filter);

            for (int i = 0; i < nodeList.size(); i++) {

                node = nodeList.elementAt(i);
                String s = node.getText().replaceAll("&nbsp;", " ");
                System.out.println(s);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "nothing";
    }

    public static void main (String args[]) {

        new WantGoToCinema().run("https://www.kinopoisk.ru/afisha/new/city/452/");
    }
}
