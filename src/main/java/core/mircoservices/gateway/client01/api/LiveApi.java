package core.mircoservices.gateway.client01.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import core.mircoservices.gateway.client01.ProjectConfig;
import core.mircoservices.gateway.client01.dto.ProjectConfigDto;
import core.mircoservices.gateway.client01.service.ApiGatewayClient02FeignClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LiveApi {

	@Autowired
	private Environment environment;

	@Autowired
	private ProjectConfig projectConfig;
	
	@GetMapping(value = {"/", "/live-check"})
	public Map<String, Object> liveCheck(@RequestParam(required = false) List<String> envs) {
		log.info("liveCheck:: envs:: " + envs);
		if (envs == null) {
			envs = new ArrayList<>();
		}
		envs.addAll(Arrays.asList("spring.application.name", "server.port"));
		Map<String, Object> ret = this.getEnvironmentConfig(new HashSet<>(envs));
		ret.put("projectConfig", new ProjectConfigDto(projectConfig));

		return ret;
	}

	public Map<String, Object> getEnvironmentConfig(Set<String> envs) {
		Map<String, Object> ret = envs.stream()
				.collect(Collectors.toMap(
						k -> k, 
						v -> environment.getProperty(v)));
		return ret;
	}
	
	@Autowired
	private ApiGatewayClient02FeignClient apiGatewayClient02FeignClient;

	@GetMapping("/client-02/live-check")
	public Map<String, Object> gatewayClient02LiveCheck(
			@RequestHeader(required = false, name = "my-app-correlation-id") String correlationId) {
		log.info("gatewayClient02LiveCheck:: correlationId:: " + correlationId);
		return this.apiGatewayClient02FeignClient.liveCheck(correlationId);
	}
}
