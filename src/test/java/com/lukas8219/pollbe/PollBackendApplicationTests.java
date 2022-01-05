package com.lukas8219.pollbe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.lukas8219.pollbe.config.Profiles.DEVELOPMENT;
import static com.lukas8219.pollbe.config.Profiles.TEST;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class PollBackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
