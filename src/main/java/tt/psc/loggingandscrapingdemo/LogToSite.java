package tt.psc.loggingandscrapingdemo;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class LogToSite {

    private final String login;
    private final String password;
    private final String url;

    public LogToSite(String login, String password, String url) {
        this.login = login;
        this.password = password;
        this.url = url;
    }

    public boolean loginToSite() {
        try (WebClient client = createClient(true, true)) {

            HtmlPage page = client.getPage(url);

            HtmlInput inputLogin = page.getFirstByXPath("//form//input[@name='email']");
            HtmlInput inputPassword = page.getFirstByXPath("//form//input[@name='password']");

            inputLogin.setValueAttribute(login);
            inputPassword.setValueAttribute(password);

            HtmlForm loginForm = inputPassword.getEnclosingForm();
            page = client.getPage(loginForm.getWebRequest(null));

//            System.out.println(page.asText());

            if (page.asText().contains("You are now logged in.")) {
                System.out.println("Log In Successful!");
                return true;
            }else {
                System.out.println("Error while logging in!");
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private WebClient createClient(Boolean javaScriptEnabled, Boolean useInsecureSsl) {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(javaScriptEnabled);
        client.getOptions().setUseInsecureSSL(useInsecureSsl);
        client.setAjaxController(new NicelyResynchronizingAjaxController()); // TODO: 24.04.2020 check how this option works
        return client;
    }

}
