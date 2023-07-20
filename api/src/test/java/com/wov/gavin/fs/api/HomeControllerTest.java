package com.wov.gavin.fs.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

	@LocalServerPort
	public int port;

	@Autowired
	public TestRestTemplate restTemplate;

	@Test
	public void greetingShouldReturnDefaultMessage() {
		String message = this.restTemplate
				.getForObject("http://localhost:" + port + "/", String.class);
		assertThat(message).contains("file-store");
	}
}
