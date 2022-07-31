package core.mircoservices.gateway.client01.dto;

import core.mircoservices.gateway.client01.ProjectConfig;

public class ProjectConfigDto extends ProjectConfig {

	private Integer port;

	public ProjectConfigDto() {
	}

	public ProjectConfigDto(ProjectConfig projectConfig) {
		this.setTitle(projectConfig.getTitle());
		this.setLastUpdated(projectConfig.getLastUpdated());
		this.setContacts(projectConfig.getContacts());
		this.setPhones(projectConfig.getPhones());
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
}
