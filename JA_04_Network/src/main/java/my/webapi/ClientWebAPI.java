package my.webapi;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.Arrays;
import java.util.List;

public class ClientWebAPI {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        String url = "http://yand.dyndns.org/api/tickers.aspx";
        WebTarget target = client.target(url);
//        String text = target.request().get(String.class);
//        System.out.println(text);

//        StockData[] data = target.request().get(StockData[].class);
//        Arrays.stream(data).forEach(d -> {
//            System.out.println(d.getTicker() + " " + d.getValue());
//        } );

        List<StockData> data =
                target.request().get(new GenericType<List<StockData>>(){});
//        data.forEach(d -> {
//            System.out.println(d.getTicker() + " " + d.getValue());
//        });
        data.forEach(System.out::println);
    }
}

class StockData {
    private String ticker;
    private int value;

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return
                "ticker='" + ticker + '\'' +
                ", value=" + value;

    }
}
