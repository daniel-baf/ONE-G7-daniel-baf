package http;

import Modelo.Currency.Currency;
import Modelo.Currency.CurrencyOMDB;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.HashMap;

public class CurrencyHttpRequestManager {

    private HttpClient clientHttp;
    final String API_URL;
    private final Gson gson;

    public CurrencyHttpRequestManager() {
        this.clientHttp = HttpClient.newHttpClient();
        this.API_URL = "https://v6.exchangerate-api.com/v6/1eb12716e89f7e20bdb11c0b";
        this.gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    /**
     * Fetch all available currencies and codes
     * 
     * @param currencyCode  the currency code to fetch
     * @param currencyCode2 the currency code to compare
     * @throws IOException          error
     * @throws InterruptedException error
     */
    public float fetchPairConversion(String currencyCode, String currencyCode2)
            throws IOException, InterruptedException {
        HttpRequest requset = this.createRequestGet(
                String.format("%1$s/pair/%2$s/%3$s", this.API_URL.trim(), currencyCode, currencyCode2));
        HttpResponse<String> response = this.clientHttp.send(requset, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = this.gson.fromJson(response.body(), JsonObject.class);
        return jsonObject.get("conversion_rate").getAsFloat();
    }

    public Map<String, String> fetchCodes() throws IOException, InterruptedException, Exception {
        // HTTP request final
        HttpRequest request = this.createRequestGet(String.format("%1$s/codes", this.API_URL.trim()));
        HttpResponse<String> response = this.clientHttp.send(request, HttpResponse.BodyHandlers.ofString());
        // Merge current JSON into object
        JsonObject jsonObject = this.gson.fromJson(response.body(), JsonObject.class);
        JsonArray supportedCodesArray = jsonObject.getAsJsonArray("supported_codes");

        Map<String, String> currencyCodesMap = new HashMap<>();

        for (int i = 0; i < supportedCodesArray.size(); i++) {
            JsonArray codeEntry = supportedCodesArray.get(i).getAsJsonArray();
            String code = codeEntry.get(0).getAsString();
            String name = codeEntry.get(1).getAsString();
            currencyCodesMap.put(code, name);
        }

        return currencyCodesMap;
    }

    /**
     * Get all available currencies conversions from a currency
     * 
     * @param currencyCode the currency code to fetch
     * @return Currency object with all available conversions
     * @throws IOException
     * @throws InterruptedException
     */
    public Currency fetchLatest(String currencyCode) throws IOException, InterruptedException {
        return this.fetchGet(String.format("latest/%1$s", currencyCode));
    }

    /**
     * Consume el API y devuelve el valor
     * 
     * @param endpoint the endpint to call
     * @return String JSON with current fetched data
     * @throws IOException          no valid
     * @throws InterruptedException internet connection error
     */
    private Currency fetchGet(String endpoint) throws IOException, InterruptedException {
        // HTTP request final
        HttpRequest request = this.createRequestGet(String.format("%1$s/%2$s", this.API_URL.trim(), endpoint.trim()));
        HttpResponse<String> response = this.clientHttp.send(request, HttpResponse.BodyHandlers.ofString());
        // Merge current JSON into object
        CurrencyOMDB fetchedCurrencyOMDB = this.gson.fromJson(response.body(), CurrencyOMDB.class);
        return new Currency(fetchedCurrencyOMDB);
    }

    /**
     * Create a GET request used to make function resuable
     * 
     * @param finalUrl
     * @return
     */
    private HttpRequest createRequestGet(String finalUrl) {
        return HttpRequest.newBuilder()
                .uri(URI.create(finalUrl))
                .GET()
                .build();
    }

}
