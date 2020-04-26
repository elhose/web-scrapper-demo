package tt.psc.submitformandscrape;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import tt.psc.submitformandscrape.pojo.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubmitForm {

    private String url;

    public SubmitForm(String url) {
        this.url = url;
    }

    public List<Product> searchForProducts(Integer minPrice, Integer maxPrice) {
        try (WebClient client = createClient(false, false)) {

            HtmlPage page = client.getPage(url);

            HtmlForm form = page.getFormByName("");

            HtmlInput inputMinPrice = form.getInputByName("min_price");
            HtmlInput inputMaxPrice = form.getInputByName("max_price");

            inputMinPrice.setValueAttribute(String.valueOf(minPrice));
            inputMaxPrice.setValueAttribute(String.valueOf(maxPrice));

            page = client.getPage(form.getWebRequest(null));
            HtmlElement searchResultTable = page.getPage().getFirstByXPath("//table[@class='ui celled table']");

            List<HtmlElement> rows = extractValuesFromTable(searchResultTable);

            return mapElementToPojo(rows);

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private List<HtmlElement> extractValuesFromTable(HtmlElement table) {
        return table.getByXPath("./tbody/tr");
    }

    private List<Product> mapElementToPojo(List<HtmlElement> elements) throws MalformedURLException {
        List<Product> products = new ArrayList<>();

        for (HtmlElement element : elements) {
            List<HtmlElement> rowValues = element.getByXPath("./td");

            String productName = rowValues.get(0).asText();
            String productUrl = rowValues.get(1).asText();
            String productPrice = rowValues.get(2).asText().replaceAll("[^0-9.]+", "");

            products.add(new Product(productName, new URL(productUrl), new BigDecimal(productPrice)));
        }

        return products;
    }

    private WebClient createClient(boolean javaScriptEnabled, boolean useInsecureSsl) {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(javaScriptEnabled);
        client.getOptions().setUseInsecureSSL(useInsecureSsl);
        client.setAjaxController(new NicelyResynchronizingAjaxController()); // TODO: 24.04.2020 check how this option works
        return client;
    }
}
