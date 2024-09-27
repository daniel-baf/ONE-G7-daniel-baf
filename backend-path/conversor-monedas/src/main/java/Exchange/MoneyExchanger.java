package Exchange;

import Modelo.Currency.Currency;
import http.CurrencyHttpRequestManager;
import java.util.Map;

public class MoneyExchanger {

    CurrencyHttpRequestManager currencyHttpRM;

    public MoneyExchanger() {
        this.currencyHttpRM = new CurrencyHttpRequestManager();
    }

    /**
     * Get as a parameter the O.C. code and the I.C. code, and the value to convert
     * fetch data to get latest currency exchange + estimate the latest value
     * 
     * @param initialCurrencyCode   current coin, ej. GTQ
     * @param objectiveCurrencyCode the unknown value, ej. USD
     * @param value                 xx to convert
     * @return xx with value in objectiveCurrencyCode
     */
    public float exchange(String initialCurrencyCode, String objectiveCurrencyCode, float value) {
        try {
            float exchangeRate = this.currencyHttpRM.fetchPairConversion(initialCurrencyCode, objectiveCurrencyCode);
            return value * exchangeRate;
        } catch (Exception e) {
            System.out.println("Unable to fetch the value of coins, " + e.getMessage());
            return -1;
        }
    }

    /**
     * Get all available currencies conversions from a currency
     * 
     * @param currencyCode the currency code to fetch
     * @return Currency object with all available conversions
     */
    public Currency getAvailableExchanges(String currencyCode) {
        try {
            return this.currencyHttpRM.fetchLatest(currencyCode);
        } catch (Exception e) {
            System.out.println("Unable to fetch the value of coins, " + e.getMessage());
            return null;
        }
    }

    /**
     * Get all available currencies codes and names
     * 
     * @return map with all available currencies
     */
    public Map<String, String> fetchSupported() {
        try {
            return this.currencyHttpRM.fetchCodes();
        } catch (Exception e) {
            System.out.println("Unable to fetch the value of coins, " + e.getMessage());
            return null;
        }
    }
}
