package tt.psc.submitformandscrape;

public class Main {

    private static final String baseUrl = "https://www.javawebscrapingsandbox.com/product/search";

    public static void main(String[] args) {
        SubmitForm submitForm = new SubmitForm(baseUrl);
        System.out.println(submitForm.searchForProducts(20, 50));
    }
}
