package com.pattern.facade;

import com.pattern.facade.pagemaker.PageMaker;

public class Main {
    public static void main(String[] args) {

        PageMaker.makeWelcomePage("youngjin@youngjun.com", "welcome.html");
        PageMaker.makeLinkPage("linkPage.html");
    }
}
