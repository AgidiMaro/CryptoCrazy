package maro.com.cryptocrazy;

/**
 * Created by Emmanuel on 10/10/2017.
 */

public class Currency {
    private String currency; // name of world currency
    private String value; // value of the crytpoCurrency in the world currency
    private String symbol;
    private int imageId;

    public String getSymbol() {
        return symbol;
    }

    public int getImageId() {
        return imageId;
    }

    public Currency(String currency, String symbol, String value, int imageId) {
        this.currency = currency;
        this.value = value;
        this.imageId=imageId;
        this.symbol=symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public String getValue() {
        return value;
    }
}
