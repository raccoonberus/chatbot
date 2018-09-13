package com.raccoonberus.chatbot.strategy;

import org.htmlparser.*;
import org.htmlparser.filters.*;
import org.htmlparser.util.NodeList;

public class WantGoToCinema {

    private static final String keywords = "what about cinema?";

    public String run(String address) {

        String attributeMovieName = "itemprop";
        String attributeMovieNameValue = "name";
        String attributeSchedule = "class";
        String attributeScheduleValue = "or";

        try {
            Parser nameParser = new Parser(address);
            HasAttributeFilter nameFilter = new HasAttributeFilter (attributeMovieName, attributeMovieNameValue);
            NodeList nodeList = nameParser.parse(nameFilter);

            Parser scheduleParser = new Parser(address);
            HasAttributeFilter scheduleFilter = new HasAttributeFilter (attributeSchedule, attributeScheduleValue);
            NodeList timeNodeList = scheduleParser.parse(scheduleFilter);

            String scheduleURL;
            String movieName;

            for (int i = 0, j = 0; i < nodeList.size(); i++) {
                movieName = nodeList.elementAt(i).getText().replaceAll("&nbsp;", " ");

                if (!movieName.contains("кинотеатрах")) {

                    movieName = movieName.substring(30, movieName.length() - 3);
                    System.out.println(movieName + "\n");

                    scheduleURL = timeNodeList.elementAt(j).getText();
                    scheduleURL = "https://www.kinopoisk.ru/" + scheduleURL.substring(20, scheduleURL.length() - 1);
//                    System.out.println(scheduleURL);
                    parseSchedule(scheduleURL);
                    j++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "nothing";
    }

    public String parseSchedule(String address) {

        String attribute = "class";
        String attributeValue = "schedule-item";
        String attributePlaceValue = "schedule-cinema__name";
        String attributeTime = "data-template";

        try {
            Parser parser = new Parser(address);
            HasAttributeFilter filter = new HasAttributeFilter(attribute, attributeValue);
            NodeList nodeList = parser.parse(filter);
            String node;

            Parser placeParser = new Parser();
            HasAttributeFilter placeFilter = new HasAttributeFilter (attribute, attributePlaceValue);
            String place;

            Parser timeParser = new Parser();
            HasAttributeFilter timeFilter = new HasAttributeFilter (attributeTime);
            String time;

            for(int i = 0; i < nodeList.size(); i++) {

                node = nodeList.elementAt(i).toHtml();

                timeParser.setInputHTML(node);
                NodeList timeList = timeParser.parse(timeFilter);
                time = timeList.elementAt(0).toHtml(); // it's not fine case it's random nearest (2D/3D/IMAX - whatever)
                time = time.substring(time.length() - 12, time.length() - 7);
                System.out.print(time + "  ");

                placeParser.setInputHTML(node);
                NodeList placeList = placeParser.parse(placeFilter);
                place = placeList.elementAt(0).toHtml();
                place = place.substring(101, place.length() - 4);
                System.out.println(place);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "idk";
    }

    public static void main (String args[]) {

        new WantGoToCinema().run("https://www.kinopoisk.ru/afisha/new/city/452/");
//        new WantGoToCinema().parseSchedule("");
    }
}
