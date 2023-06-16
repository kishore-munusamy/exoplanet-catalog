package org.gendigital.exoplanetcatalog.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.gendigital.exoplanetcatalog.dto.Exoplanet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * ExoplanetClient is responsible for downloading exoplanet data from an API.
 */
@Log4j2
@Component
public class ExoplanetClient {

  private final RestTemplate restTemplate;

  private final ObjectMapper objectMapper;

  private final String exoplanetDataUrl;

  @Autowired
  public ExoplanetClient(RestTemplate restTemplate, ObjectMapper objectMapper,
      @Value("${exoplanet.api.url}") String exoplanetDataUrl) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
    this.exoplanetDataUrl = exoplanetDataUrl;
  }

  /**
   * Downloads exoplanet data from the API.
   *
   * @return a list of Exoplanet objects representing the downloaded data
   */
  public List<Exoplanet> downloadExoplanetData() {
    ResponseEntity<String> responseEntity = restTemplate.exchange(
        exoplanetDataUrl,
        HttpMethod.GET,
        null,
        String.class
    );

    if (responseEntity.getStatusCode().is2xxSuccessful()) {
      String responseBody = responseEntity.getBody();
      if (responseBody != null) {
        try {
          return objectMapper.readValue(responseBody,
              new TypeReference<List<Exoplanet>>() {});
        } catch (JsonProcessingException e) {
          log.error("Something went wrong while parsing the response body", e);
        }
      }
    }
    return Collections.emptyList();
  }

}
