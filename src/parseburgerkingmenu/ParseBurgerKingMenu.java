/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseburgerkingmenu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ковалев Владислав
 */
public class ParseBurgerKingMenu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String URL = "https://burgerking.ru/menu";
        try {
            parseMenu(URL);
        } catch (IOException ex) {
            Logger.getLogger(ParseBurgerKingMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void parseMenu(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        System.out.println(html.title());
        Elements categories = html.select(".food-category a");
        for(Element category: categories){
            String title = category.text();
            String link = category.absUrl("href");
            System.out.println(title + " - " + link);
            parseCategory(link);
        }
    }  
    
    public static void parseCategory(String URL) throws IOException{
        Document html = Jsoup.connect(URL).get();
        Elements items = html.select(".food-item span");
        for(Element item: items){
            String title = item.text();
            System.out.println("\t" + title);
        }
    }  
}
