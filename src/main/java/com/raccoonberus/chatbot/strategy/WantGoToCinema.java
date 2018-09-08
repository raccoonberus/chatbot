package com.raccoonberus.chatbot.strategy;

import org.htmlparser.*;
import org.htmlparser.util.NodeList;

public class WantGoToCinema {

    private static final String keywords = "what about cinema?";

    public String run() {

        String address = "https://www.google.com/search?ei=&q=что+в+кино&oq=что+в+кино";
        try {
            Parser parser = new Parser(address);
            NodeList nodeList = parser.parse(null);

            for (int i = 0; i < nodeList.size(); i++) {

                Node node = nodeList.elementAt(i);
                 System.out.println(node.toHtml());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "something";
    }

    public static void main (String args[]) {

        System.out.println(new WantGoToCinema().run());
    }
}
