package com.founder.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.velocity.VelocityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

// SpringBoot 应用标识
@SpringBootApplication(scanBasePackages = "com.founder", exclude = VelocityAutoConfiguration.class)
@EnableScheduling
@EnableFeignClients
public class WebApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

    /**
     * 启动嵌入式的Tomcat并初始化Spring环境.
     * <p>
     * 无 applicationContext.xml 和 web.xml, 靠下述方式进行配置：
     * <p>
     * 1. 扫描当前package下的class设置自动注入的Bean<br/>
     * 2. 也支持用@Bean标注的类配置Bean <br/>
     * 3. 根据classpath中的三方包Class及集中的application.properties条件配置三方包，如线程池 <br/>
     * 4. 也支持用@Configuration标注的类配置三方包.
     */
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//        tomcat.setUriEncoding(Charset.forName("UTF-8"));
//        tomcat.setDocumentRoot(new File("web/src/main/webapp"));
//        tomcat.addContextCustomizers(context -> ((StandardJarScanner)context.getJarScanner()).setScanManifest(false));
//        return tomcat;
//    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        File root = new File("web/src/main/webapp/");
        if (root.exists() && root.isDirectory()) {
            container.setDocumentRoot(root);
        }
    }
}
