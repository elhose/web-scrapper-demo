package tt.psc.loggingandscrapingdemo;

import tt.psc.ExternalProperties;

public class Main {

    private static final String login = ExternalProperties.getProperty("logging.and.scraping.demo.email");
    private static final String password = ExternalProperties.getProperty("logging.and.scraping.demo.password");
    private static final String url = ExternalProperties.getProperty("logging.and.scraping.demo.url");

    public static void main(String[] args) {
        LogToSite logToSite = new LogToSite(login, password, url);
        logToSite.loginToSite();
    }
}
