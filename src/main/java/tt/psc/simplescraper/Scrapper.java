package tt.psc.simplescraper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Scrapper {
    private final String baseUrl;

    public Scrapper(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void scrapeWholePage() {
        WebClient client = createClient();
        try {
            HtmlPage page = client.getPage(baseUrl);
            System.out.println(page.asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scrapeChosenElements() {
        try (WebClient client = createClient()) {
            HtmlPage page = client.getPage(baseUrl);
            List<HtmlElement> elements = page.getByXPath("//tr[@class='athing']");

            for (HtmlElement element : elements) {
                Integer id = Integer.valueOf(element.getId());
                String position = ((HtmlElement) element.getFirstByXPath("./td[@class='title']/span[@class='rank']")).asText().replaceAll("\\.", "");
                String title = ((HtmlElement) element.getFirstByXPath("./td[@class='title']/a[@class='storylink']")).asText();
                String url = ((HtmlAnchor) element.getFirstByXPath("./td[@class='title']/a")).getHrefAttribute();
                String author = ((HtmlElement) element.getFirstByXPath("./following-sibling::tr/td/a[@class='hnuser']")).asText();
                String points = ((HtmlElement) element.getFirstByXPath("./following-sibling::tr/td/span[@class='score']")).asText().replaceAll("\\D+", "");

                String newsJson = mapToJson(new News(id, Integer.valueOf(position), title, new URL(url), author, Integer.valueOf(points)));

                System.out.println(newsJson);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    private WebClient createClient() {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        client.setAjaxController(new NicelyResynchronizingAjaxController()); // TODO: 24.04.2020 check how this option works
        return client;
    }

}
