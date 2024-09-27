package Modelo.Currency;

import java.util.Map;

public class Currency {
    private final String code;

    private final Map<String, Float> conversionRates;

    public Currency(CurrencyOMDB currencyOMDB) {
        this.code = currencyOMDB.code();
        this.conversionRates = currencyOMDB.currencyConversion();
    }

    public String getCode() {
        return code;
    }

    public Map<String, Float> getConversionRates() {
        return conversionRates;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "code='" + code + '\'' +
                ", conversionRates=" + conversionRates +
                '}';
    }
}

