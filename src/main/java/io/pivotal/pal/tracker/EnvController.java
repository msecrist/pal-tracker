package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private String port;
    private String memory;
    private String cfIndex;
    private String cfAddress;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memory,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfIndex,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfAddress) {
       this.port = port;
       this.memory = memory;
       this.cfIndex = cfIndex;
       this.cfAddress = cfAddress;

    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> results = new HashMap<>();
        results.put("PORT", port);
        results.put("MEMORY_LIMIT", memory);
        results.put("CF_INSTANCE_INDEX", cfIndex);
        results.put("CF_INSTANCE_ADDR", cfAddress);

        return results;

    }
}
