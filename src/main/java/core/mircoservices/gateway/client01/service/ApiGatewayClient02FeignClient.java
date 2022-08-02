package core.mircoservices.gateway.client01.service;

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("api-gateway-client02")
public interface ApiGatewayClient02FeignClient {

	@GetMapping(value = "live-check", consumes = "application/json", produces = "application/json")
	Map<String, Object> liveCheck(@RequestHeader("my-app-correlation-id") String correlationId);
	
}

