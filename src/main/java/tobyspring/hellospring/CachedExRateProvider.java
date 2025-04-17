package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

public class CachedExRateProvider implements ExRateProvider{
	private final ExRateProvider target;

	private BigDecimal cachedExRate;
	private LocalDateTime cacheExpiredTime;
	public CachedExRateProvider(ExRateProvider target) {
		this.target = target;
	}

	@Override
	public BigDecimal getExRate(String currency) throws IOException {
		if (cachedExRate == null || cacheExpiredTime.isBefore(LocalDateTime.now())) {
			cachedExRate = target.getExRate(currency);
			cacheExpiredTime = LocalDateTime.now().plusSeconds(3);
			System.out.println("Cache Updated");
		}
		return cachedExRate;
	}
}
