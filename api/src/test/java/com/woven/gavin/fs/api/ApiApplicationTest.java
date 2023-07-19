package com.wov.gavin.fs.api;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.wov.gavin.fs.api.controller.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiApplicationTest {

	@Autowired
	private HomeController homeController;

	@Test
	public void contextLoads() {
		assertThat(homeController).isNotNull();
	}
}
