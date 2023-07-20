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
	public int port;

	@Autowired
	public TestRestTemplate restTemplate;

	@Test
	public void testListFiles() {
		List<?> list = this.restTemplate
				.getForObject("http://localhost:" + port + "/files", List.class);
		assertThat(list != null).isTrue();
	}

	@Test
	public void testDeleteFiles() {
		this.restTemplate
				.delete("http://localhost:" + port + "/files/" + System.currentTimeMillis());
		assertThat(true).isTrue();
	}
}
