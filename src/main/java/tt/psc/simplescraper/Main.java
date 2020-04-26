package tt.psc.simplescraper;

public class Main {
    public static void main(String[] args) {
        String url = "https://news.ycombinator.com/";

        Scrapper scrapper = new Scrapper(url);
//        scrapper.scrapeWholePage();
        scrapper.scrapeChosenElements();
    }


}
