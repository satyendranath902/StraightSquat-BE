package srt.mahakash.config;

import srt.mahakash.model.ApodModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

	private static final Logger log = LoggerFactory.getLogger(AppConfig.class);

	@Value("${nasa.key}")
	private String apiKey;

	@Value("${api.nasa.apod}")
	private String apodApiUrl;
	
	@Value("${api.nasa.epic}")
	private String epicApiUrl;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean()
	public ApodModel getApodApi(RestTemplate restTemplate) throws Exception {
		/*
		 * ApodModel apodModel = restTemplate.getForObject(apodApiUrl + Constant.API_KEY
		 * + apiKey, ApodModel.class); log.info(apodModel.toString());
		 * System.out.println(apodModel.getUrl()); return apodModel;
		 */
		return restTemplate.getForObject(apodApiUrl + "api_key=" + apiKey, ApodModel.class);
	}
	
//	@Bean()
//	public EpicModel getEpicApi(RestTemplate restTemplate) throws Exception {
//		return restTemplate.getForObject(epicApiUrl + "api_key=" + apiKey, EpicModel.class);
//	}

}
