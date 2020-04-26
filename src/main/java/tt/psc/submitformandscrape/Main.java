package tt.psc.submitformandscrape;

import tt.psc.ExternalProperties;

public class Main {

    private static final String baseUrl = ExternalProperties.getProperty("submit.form.and.scrape.url");

    public static void main(String[] args) {
        SubmitForm submitForm = new SubmitForm(baseUrl);
        System.out.println(submitForm.searchForProducts(20, 50));
    }
}
