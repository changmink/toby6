package tobyspring.hellospring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public class WebApiExRateProvider implements ExRateProvider {
	@Override
	public BigDecimal getExRate(String currency) throws IOException {
		URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String response = bufferedReader.lines().collect(Collectors.joining());
		bufferedReader.close();

		ObjectMapper mapper = new ObjectMapper();
		ExchangeRateData data = mapper.readValue(response, ExchangeRateData.class);

		System.out.println("API Rate: " + data.rates().get("KRW"));
		BigDecimal exRate = data.rates().get("KRW");
		return exRate;
	}
}
