package org.gendigital.exoplanetcatalog.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.isNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.gendigital.exoplanetcatalog.dto.Exoplanet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

class ExoplanetClientTest {

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private ObjectMapper objectMapper;

  private ExoplanetClient exoplanetClient;

  private static String exoplanetDataUrl = "https://gist.githubusercontent.com";

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    exoplanetClient = new ExoplanetClient(restTemplate, objectMapper, exoplanetDataUrl);
  }

  @Test
  void testDownloadExoplanetData_SuccessfulResponse() throws JsonProcessingException {
    // Mock successful response
    ResponseEntity<String> responseEntity = new ResponseEntity<>(
        "[{\"PlanetIdentifier\":\"Exoplanet 1\",\"TypeFlag\":3,\"TadiusJpt\":1.5,\"DiscoveryYear\":2010,\"HostStarTempK\":1000}," +
            "{\"PlanetIdentifier\":\"Exoplanet 2\",\"TypeFlag\":1,\"RadiusJpt\":0.8,\"DiscoveryYear\":2012,\"HostStarTempK\":2000}]",
        HttpStatus.OK
    );

    // Mock ObjectMapper behavior
    List<Exoplanet> expectedExoplanets = new ArrayList<>();
    expectedExoplanets.add(new Exoplanet("Exoplanet 1", 3, 1.5, 2010, 1000));
    expectedExoplanets.add(new Exoplanet("Exoplanet 2", 1, 0.8, 2012, 2000));
    when(objectMapper.readValue(anyString(), any(TypeReference.class)))
        .thenReturn(expectedExoplanets);

    // Mock RestTemplate behavior
    when(restTemplate.exchange(
        eq(exoplanetDataUrl),
        eq(HttpMethod.GET),
        isNull(),
        eq(String.class)
    )).thenReturn(responseEntity);

    // Invoke the method under test
    List<Exoplanet> exoplanets = exoplanetClient.downloadExoplanetData();

    // Verify the interactions and assertions
    verify(restTemplate, times(1)).exchange(
        eq(exoplanetDataUrl),
        eq(HttpMethod.GET),
        isNull(),
        eq(String.class)
    );
    assertEquals(expectedExoplanets, exoplanets);
  }

  @Test
  void testDownloadExoplanetData_EmptyResponse() throws JsonProcessingException {
    // Mock empty response
    ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.OK);

    // Mock RestTemplate behavior
    when(restTemplate.exchange(
        eq(exoplanetDataUrl),
        eq(HttpMethod.GET),
        isNull(),
        eq(String.class)
    )).thenReturn(responseEntity);

    // Invoke the method under test
    List<Exoplanet> exoplanets = exoplanetClient.downloadExoplanetData();

    // Verify the interactions and assertions
    verify(restTemplate, times(1)).exchange(
        eq(exoplanetDataUrl),
        eq(HttpMethod.GET),
        isNull(),
        eq(String.class)
    );
    verify(objectMapper, never()).readValue(anyString(), any(TypeReference.class));
    assertEquals(Collections.emptyList(), exoplanets);
  }

  @Test
  void testDownloadExoplanetData_ErrorResponse() throws JsonProcessingException {
    // Mock error response
    ResponseEntity<String> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    // Mock RestTemplate behavior
    when(restTemplate.exchange(
        eq(exoplanetDataUrl),
        eq(HttpMethod.GET),
        isNull(),
        eq(String.class)
    )).thenReturn(responseEntity);

    // Invoke the method under test
    List<Exoplanet> exoplanets = exoplanetClient.downloadExoplanetData();

    // Verify the interactions and assertions
    verify(restTemplate, times(1)).exchange(
        eq(exoplanetDataUrl),
        eq(HttpMethod.GET),
        isNull(),
        eq(String.class)
    );
    verify(objectMapper, never()).readValue(anyString(), any(TypeReference.class));
    assertEquals(Collections.emptyList(), exoplanets);
  }
}

