package com.raccoonberus.chatbot.strategy;

//import jdk.nashorn.internal.runtime.ParserException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeFilter;
import javax.swing.text.html.parser.Parser;

public class WantGoToCinema {

    private static final String keywords = "what about cinema?";

    /*public String run(String address) {

        try {
            Parser parser = new Parser();

            NodeList nodeList = parser.parse();

            for (int i = 0; i < nodeList.size(); i++) {

                Node node = nodeList.elementAt(i);
                System.out.println(node.toHtml());
            }

        } catch (ParserException e) {
            e.printStackTrace();
        }
        return ;
    }*/
}
