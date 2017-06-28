package tw.com.eeit94.textile.system.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Spring Websocket Java 組態設定檔。
 * 
 * @author 賴
 * @version 2017/06/26
 */
@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages = { "tw.com.eeit94.textile.controller" })
public class SpringWebSocketJavaConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.setApplicationDestinationPrefixes("/message");
		registry.enableSimpleBroker("/passage");
		super.configureMessageBroker(registry);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/endpoint.do");
	}
}