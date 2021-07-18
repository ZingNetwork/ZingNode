package zing.node;

import zing.node.util.Language;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Scanner;
import java.util.TimeZone;

public class Node {

    private static final Logger logger = Logger.getLogger(Node.class);

    private static Server server;

    public static void main(String[] args) {
        init();
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(timeZone);
        logger.info(Language.get("begin"));
        logger.info(Language.get("set.lang"));
        logger.info(Language.get("license"));
        server = new Server();
    }

    private static void init() {
        String lang = Config.get("language");
        if (!lang.equals("")) {
            if (!Language.load(lang)) Language.load("eng");
        } else {
            System.out.println("Welcome! Please choose a language first!\n");
            System.out.println("chs => 中文(简体)");
            System.out.println("eng => English");
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.print("\nYour choice:");
                if (scan.hasNext()) {
                    String str = scan.next();
                    Config.set("language", str);
                    if (Language.load(str)) break;
                }
            }
        }
        if (!Config.get("verification").equals("")) {
            //TODO
        } else {
            //TODO
        }
        Config.save();
        File articles = new File("articles");
        if (!articles.exists()) articles.mkdir();
    }

}
