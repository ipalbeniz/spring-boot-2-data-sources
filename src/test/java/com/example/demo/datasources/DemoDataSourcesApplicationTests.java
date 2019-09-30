package com.example.demo.datasources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoDataSourcesApplicationTests {

	private static final String SELECT_FROM_DUAL = "SELECT 1 FROM DUAL";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("customJdbcTemplate")
	private JdbcTemplate customJdbcTemplate;

	@Test
	public void the_app_should_configure_2_different_jdbc_templates() {

		assertNotEquals(jdbcTemplate, customJdbcTemplate);
	}

	@Test
	public void the_2_jdbc_templates_should_be_able_to_select_from_dual() {

		final int jdbcTemplateSelectResult = jdbcTemplate.queryForObject(SELECT_FROM_DUAL, Integer.class);
		final int customJdbcTemplateSelectResult = customJdbcTemplate.queryForObject(SELECT_FROM_DUAL, Integer.class);

		assertEquals(1, jdbcTemplateSelectResult);
		assertEquals(1, customJdbcTemplateSelectResult);
	}

}
