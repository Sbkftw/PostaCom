package com.sbkftw.postacom.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    private Documentation documentation;

    @Data
    public static class Documentation {

        private boolean enabled = false;
    }

}
