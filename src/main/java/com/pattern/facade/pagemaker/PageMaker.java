package com.pattern.facade.pagemaker;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class PageMaker {

    private PageMaker() {
    }

    public static void makeWelcomePage(String mailAddr, String filename) {
        try {
            Properties mailProp = Database.getProperties("maildata");
            String username = mailProp.getProperty(mailAddr);
            HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
            writer.title("Welcome to " + username + " `s page !");
            writer.paragraph(username + "의 페이지에 오신 걸 환영합니다.");
            writer.paragraph("메일을 기다리고 있습니다.");
            writer.mailto(mailAddr, username);
            writer.close();
            System.out.println(filename + " is created foe " + mailAddr + " (" + username + ") ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void makeLinkPage(String filename) {
        List<String> list = fileToList();
        try {
            HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
            writer.title("Link page");
            list.forEach(i -> {
                try {
                    String[] chars = i.split("=");
                    writer.mailto(chars[0], chars[1]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
            System.out.println(filename + " is created.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> fileToList() {

        List<String> list = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get("./src/main/resources/maildata.txt"))) {
            stream.forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
