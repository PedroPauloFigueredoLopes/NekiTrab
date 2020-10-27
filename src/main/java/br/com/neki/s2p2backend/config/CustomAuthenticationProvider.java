package br.com.neki.s2p2backend.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	public CustomAuthenticationProvider() {
		super();
	}

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		 RestTemplate template = new RestTemplate();
	        UriComponents uri = UriComponentsBuilder.newInstance()
	                .scheme("http")
	                .host("170.80.70.50:8180")
	                .path("/sflm/s2p2/contributor/initworkday")

	                .build();
	        String login = authentication.getName();
	        String password= authentication.getAuthorities().toString();
	        
	        Map<String, String> map = new HashMap<>();
	         map.put("login", login);
	         // TODO implementar a passagem do macAdress   map.put("macAdress", "f8:ff:c2:2b:91:0a");
	         map.put("password", password);
	        // send POST request
	         
	        ResponseEntity<Map> response = template.postForEntity(uri.toUriString(), map, Map.class);
	        if (response.getStatusCode() == HttpStatus.OK) {
	        List<GrantedAuthority> grantedAuths = new ArrayList<>();
			grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			UserDetails principal = new User(login, password, grantedAuths);
			Authentication auth = new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);

			return auth;
	        } else {
	            System.out.println("Request Failed");

	        }
	        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
