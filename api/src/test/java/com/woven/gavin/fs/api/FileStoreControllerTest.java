package com.wov.gavin.fs.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FileStoreControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testListFiles() throws Exception {
		List<String> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/files", List.class);
		assertThat(true).isTrue();
	}

	@Test
	public void testDeleteFiles() throws Exception {
		this.restTemplate
				.delete("http://localhost:" + port + "/files/" + System.currentTimeMillis());
		assertThat(true).isTrue();
	}
}
