package com.qa.portal.smoke;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.portal.application.service.page.mapper.ProjectPageMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalAppServicePageMapperSmokeTest {

	@Autowired
	public ProjectPageMapper projectPageMapper;

	@Test
	public void projectPageMapperSmokeTest() {
		assertThat(this.projectPageMapper).isNotNull();
	}

}
