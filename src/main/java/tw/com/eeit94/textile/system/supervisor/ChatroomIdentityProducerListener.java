package tw.com.eeit94.textile.system.supervisor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import tw.com.eeit94.textile.model.chatroom.ChatroomService;
import tw.com.eeit94.textile.model.secure.SecureService;
import tw.com.eeit94.textile.system.common.ConstHelperKey;

@Component
public class ChatroomIdentityProducerListener implements ServletContextListener {
	private ServletContext servletContext;
	@Autowired
	private ChatroomService chatroomService;
	@Autowired
	private SecureService secureService;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.servletContext);
		this.servletContext = sce.getServletContext();
		produceChatroomIdentity();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

	private Map<Long, String> produceChatroomIdentity() {
		Map<Long, String> ciMap = new HashMap<>();
		this.servletContext.setAttribute(ConstHelperKey.CHATROOM_IDENTITIES.key(), ciMap);
		List<Long> primaryKeys = this.chatroomService.getAllChatroomPrimaryKeys();

		return null;
	}
}