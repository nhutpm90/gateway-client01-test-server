package core.mircoservices.gateway.client01.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.mircoservices.gateway.client01.dto.ProjectConfigDto;

@FeignClient("api-gateway-client02")
public interface ApiGatewayClient02FeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "live-check", consumes = "application/json")
	String liveCheck(@RequestHeader("my-app-correlation-id") String correlationId);
	
	@RequestMapping(method = RequestMethod.GET, value = "configuration", consumes = "application/json")
	ProjectConfigDto getProjectConfig();
}

