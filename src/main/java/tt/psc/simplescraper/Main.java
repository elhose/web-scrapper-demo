package tt.psc.simplescraper;

import tt.psc.ExternalProperties;

public class Main {

    private static final String url = ExternalProperties.getProperty("simple.scraper.url");

    public static void main(String[] args) {

        Scrapper scrapper = new Scrapper(url);
//        scrapper.scrapeWholePage();
        scrapper.scrapeChosenElements();
    }

}
