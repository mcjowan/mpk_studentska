package edu.mpk.config;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.EnumSet;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
public class WebConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

    @Inject
    private Environment env;

//    @Autowired(required = false)
//    private MetricRegistry metricRegistry;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));
        }
        EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);
//        initMetrics(servletContext, disps);
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_PRODUCTION)) {
            initCachingHttpHeadersFilter(servletContext, disps);
        }
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_DEVELOPMENT)) {
            initH2Console(servletContext);
        }
        log.info("Web application fully configured");
    }

    /**
     * Customize the Servlet engine: Mime types, the document root, the cache.
     */
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        // IE issue
        mappings.add("html", "text/html;charset=utf-8");
        // CloudFoundry issue, see https://github.com/cloudfoundry/gorouter/issues/64
        mappings.add("json", "text/html;charset=utf-8");
        container.setMimeMappings(mappings);
        // When running in an IDE or with ./mvnw spring-boot:run, set location of the static web assets.
        setLocationForStaticAssets(container);
    }

    private void setLocationForStaticAssets(ConfigurableEmbeddedServletContainer container) {
        File root;
        String prefixPath = resolvePathPrefix();
        if (env.acceptsProfiles(Constants.SPRING_PROFILE_PRODUCTION)) {
            root = new File(prefixPath + "target/www/");
        } else {
            root = new File(prefixPath + "src/main/resources/");
        }
        if (root.exists() && root.isDirectory()) {
            container.setDocumentRoot(root);
        }
    }

    /**
     *  Resolve path prefix to static resources.
     */
    private String resolvePathPrefix() {
        String fullExecutablePath = this.getClass().getResource("").getPath();
        String rootPath = Paths.get(".").toUri().normalize().getPath();
        String extractedPath = fullExecutablePath.replace(rootPath, "");
        int extractionEndIndex = extractedPath.indexOf("target/");
        if(extractionEndIndex <= 0) {
            return "";
        }
        return extractedPath.substring(0, extractionEndIndex);
    }

    /**
     * Initializes the caching HTTP Headers Filter.
     */
    private void initCachingHttpHeadersFilter(ServletContext servletContext,
                                              EnumSet<DispatcherType> disps) {
        log.debug("Registering Caching HTTP Headers Filter");
//        FilterRegistration.Dynamic cachingHttpHeadersFilter =
//            servletContext.addFilter("cachingHttpHeadersFilter",
//                new CachingHttpHeadersFilter(jHipsterProperties));
//
//        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/content/*");
//        cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/app/*");
//        cachingHttpHeadersFilter.setAsyncSupported(true);
    }

    /**
     * Initializes Metrics.
     */
//    private void initMetrics(ServletContext servletContext, EnumSet<DispatcherType> disps) {
//        log.debug("Initializing Metrics registries");
//        servletContext.setAttribute(InstrumentedFilter.REGISTRY_ATTRIBUTE,
//            metricRegistry);
//        servletContext.setAttribute(MetricsServlet.METRICS_REGISTRY,
//            metricRegistry);
//
//        log.debug("Registering Metrics Filter");
//        FilterRegistration.Dynamic metricsFilter = servletContext.addFilter("webappMetricsFilter",
//            new InstrumentedFilter());
//
//        metricsFilter.addMappingForUrlPatterns(disps, true, "/*");
//        metricsFilter.setAsyncSupported(true);
//
//        log.debug("Registering Metrics Servlet");
//        ServletRegistration.Dynamic metricsAdminServlet =
//            servletContext.addServlet("metricsServlet", new MetricsServlet());
//
//        metricsAdminServlet.addMapping("/management/mpk/metrics/*");
//        metricsAdminServlet.setAsyncSupported(true);
//        metricsAdminServlet.setLoadOnStartup(2);
//    }


    /**
     * Initializes H2 console.
     */
    private void initH2Console(ServletContext servletContext) {
        log.debug("Initialize H2 console");
        ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
        h2ConsoleServlet.addMapping("/h2-console/*");
        h2ConsoleServlet.setInitParameter("-properties", "src/main/resources/");
        h2ConsoleServlet.setLoadOnStartup(1);
    }
}