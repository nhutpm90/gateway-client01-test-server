package core.mircoservices.gateway.client01.api;

import java.util.List;
import java.util.Map;
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
	
	@Autowired
	private ApiGatewayClient02FeignClient apiGatewayClient02FeignClient;
	
	@GetMapping("/live-check")
    public String liveCheck(@RequestHeader(required = false, name="my-app-correlation-id") String correlationId)  {
        log.info("liveCheck:: correlationId:: " + correlationId);
		Integer port = Integer.parseInt(environment.getProperty("server.port"));
		return String.format("Api Gateway Client 01 Server:: %s", port);
	}

	@GetMapping("/env-check")
	public Map<String, String> testParam(@RequestParam("envs") List<String> envs) {
		Map<String, String> ret = envs.stream()
				.collect(Collectors.toMap(
						k -> k, 
						v -> environment.getProperty(v)));
		return ret;
	}
	
	@GetMapping("/configuration")
	public ProjectConfigDto configuration() throws Exception {
		return new ProjectConfigDto(projectConfig);
	}
	
	@GetMapping("/gateway-client-02/live-check")
	public String gatewayClient02LiveCheck(@RequestHeader(required = false, name="my-app-correlation-id") String correlationId)  {
		log.info("gatewayClient02LiveCheck:: correlationId:: " + correlationId);
		return this.apiGatewayClient02FeignClient.liveCheck(correlationId);
	}
}
