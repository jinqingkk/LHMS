package edu.xhu.lhms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:application.properties")
public class ResourceConfigBean {
	@Value("${resource.path}")
	private String resourcePath;
	@Value("${resource.path.pattern}")
	private String resourcePathPattern;
	@Value("${resource.location.window}")
	private String resourceLocationWindow;
	@Value("${resource.location.linux}")
	private String resourceLocationLinux;

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getResourcePathPattern() {
		return resourcePathPattern;
	}

	public void setResourcePathPattern(String resourcePathPattern) {
		this.resourcePathPattern = resourcePathPattern;
	}

	public String getResourceLocationWindow() {
		return resourceLocationWindow;
	}

	public void setResourceLocationWindow(String resourceLocationWindow) {
		this.resourceLocationWindow = resourceLocationWindow;
	}

	public String getResourceLocationLinux() {
		return resourceLocationLinux;
	}

	public void setResourceLocationLinux(String resourceLocationLinux) {
		this.resourceLocationLinux = resourceLocationLinux;
	}
}
