package com.raccoonberus.chatbot.strategy;

import com.raccoonberus.chatbot.ReplyStrategy;
import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.NodeList;

public class WantGoToCinema implements ReplyStrategy {

    private static final String keywords = "what about cinema";

    @Override
    public boolean isSupport(String message) {
        return true;
    }

    @Override
    public boolean isSupport(String... keywords) {
        return false;
    }

    @Override
    public String run(String message) {
        String address = "https://mori-cinema.ru/cinema_detail/76_mori_sinema_krasnogorsk/schedule.php";

        try {
            Parser parser = new Parser(address);
            HasAttributeFilter filter = new HasAttributeFilter ("data-day", "today");
            NodeList nodeList = parser.parse(filter);
            address = nodeList.elementAt(1).toHtml();

            Parser blockParser = new Parser();
            blockParser.setInputHTML(address);
            TagNameFilter blockFilter = new TagNameFilter("tr");
            NodeList blockList = blockParser.parse(blockFilter);
            String block;

            Parser nameParser = new Parser();
            HasAttributeFilter nameFilter = new HasAttributeFilter ("target");
            NodeList nameList;
            String name;

            Parser timeParser = new Parser();
            HasAttributeFilter timeFilter = new HasAttributeFilter ("kh:widget", "seance");
            NodeList timeList;
            String time;

            System.out.println("MORI CINEMA nearest movie sessions for today:\n");

            for (int i = 0; i < blockList.size(); i++) {

                block = blockList.elementAt(i).toHtml();
                timeParser.setInputHTML(block);
                nameParser.setInputHTML(block);
                timeList = timeParser.parse(timeFilter);
                nameList = nameParser.parse(nameFilter);

                if (timeList.size() > 0) {
                    time = timeList.elementAt(0).toHtml();
                    time = time.replaceAll(" ", "");
                    time = time.substring(time.indexOf('>') + 2, time.indexOf('>') + 7);
                    System.out.print(time + "  ");
                } else if (nameList.size() > 0) {
                    System.out.print("--:--  ");
                }

                if (nameList.size() > 0) {
                    name = nameList.elementAt(0).toHtml();
                    name = name.substring(name.indexOf('>') + 1, name.length() - 4);
                    System.out.println(name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }
}
