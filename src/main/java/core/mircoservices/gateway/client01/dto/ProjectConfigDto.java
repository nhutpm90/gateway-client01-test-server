package core.mircoservices.gateway.client01.dto;

import core.mircoservices.gateway.client01.ProjectConfig;

public class ProjectConfigDto extends ProjectConfig {

	public ProjectConfigDto() { }

	public ProjectConfigDto(ProjectConfig projectConfig) {
		this.setTitle(projectConfig.getTitle());
		this.setLastUpdated(projectConfig.getLastUpdated());
		this.setContacts(projectConfig.getContacts());
		this.setPhones(projectConfig.getPhones());
	}
}
