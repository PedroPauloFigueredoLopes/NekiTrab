package br.com.neki.s2p2backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.neki.s2p2backend.config.CustomAuthenticationProvider;
import br.com.neki.s2p2backend.config.JwtUtil;
import br.com.neki.s2p2backend.model.AuthenticationResponse;
import br.com.neki.s2p2backend.model.Request;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;

	@Autowired
	private JwtUtil jwtUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Request request) throws Exception {
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}

		Authentication authentication = authenticationProvider
				.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
		String token = jwtUtil.generateToken(authentication);
		return ResponseEntity.ok(new AuthenticationResponse(token));

	}
}
