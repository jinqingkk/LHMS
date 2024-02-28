package edu.xhu.lhms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description WebMvcConfig
 * @Author JiangHu
 * @Date 2023/6/12 9:38
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ResourceConfigBean resourceConfigBean;

	/**
	 * 将本地文件夹映射给系统一个路径
	 * 硬编码的问题
	 * 系统的问题
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().startsWith("win")) {
			registry
					.addResourceHandler(resourceConfigBean.getResourcePathPattern())
					.addResourceLocations(ResourceUtils.FILE_URL_PREFIX
							+ resourceConfigBean.getResourceLocationWindow());
		} else {
			registry
					.addResourceHandler(resourceConfigBean.getResourcePathPattern())
					.addResourceLocations(ResourceUtils.FILE_URL_PREFIX
							+ resourceConfigBean.getResourceLocationLinux());
		}
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//允许跨越访问路径
				.allowedOrigins("*")//允许跨越访问源
				.allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")//允许请求方法
				.maxAge(3600)//预检间隔时间
				.allowedOriginPatterns("*");
	}
}
