package br.com.neki.s2p2backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.HashMap;
import java.util.Map;

public class RestTemplatePost {
	void cosumerAPIpost() {

		RestTemplate template = new RestTemplate();
		UriComponents uri = UriComponentsBuilder.newInstance().scheme("http").host("170.80.70.50:8180")
				.path("/sflm/s2p2/contributor/initworkday")
				.build();

		Map<String, String> map = new HashMap<>();
		map.put("login", "filipe.sa");
		map.put("macAdress", "f8:ff:c2:2b:91:0a");
		map.put("password", "BF5CDE2CC90265657B00E7CC9079DE6D42325EB71F4AE0A6FFFA0113C26A8C18");
		// send POST request
		ResponseEntity<Map> response = template.postForEntity(uri.toUriString(), map, Map.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println(response.getBody().get("name"));
		} else {
			System.out.println("Request Failed");

		}
	}
}
