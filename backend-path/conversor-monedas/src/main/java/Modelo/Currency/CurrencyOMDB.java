package Modelo.Currency;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public record CurrencyOMDB(
        @SerializedName("base_code") String code,
        @SerializedName("conversion_rates") Map<String, Float> currencyConversion
) {

}
