package com.lukas8219.pollbe.config;

import com.lukas8219.pollbe.data.enumeration.EnvironmentEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpServerStartup implements CommandLineRunner {

    private final ApplicationEnvironment applicationEnvironment;
    @Value("${http-server-exec}")
    private String command;

    @Override
    public void run(String... args) throws Exception {
        if (applicationEnvironment.getEnvironment() == EnvironmentEnum.DEVELOPMENT) {
            log.info("Serving a Http-Server at localhost:8081 for Development");
            var processCode = Runtime.getRuntime()
                    .exec(command).waitFor();

            if (processCode != 0) {
                var error = new Error("Unable to serve a Http-Server with node http-server package. Please, confirm that you have this package installed");
                log.error(error.getMessage(), error);
                throw error;
            }
        }
    }

}
