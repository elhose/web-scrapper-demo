package tt.psc.loggingandscrapingdemo;

public class Main {

    private static final String login = "test@test.com";
    private static final String password = "test";
    private static final String baseUrl = "https://www.javawebscrapingsandbox.com/account/login";

    public static void main(String[] args) {
        LogToSite logToSite = new LogToSite(login, password, baseUrl);
        logToSite.loginToSite();
    }
}
