package springboot.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	static final Counter requests = Counter.build().name("helloworld_requests_total").help("HelloWorld Total requests.").register();

	@RequestMapping("/helloworld")
	String home() {
		requests.inc();
		return "Hello World!";
	}
}
