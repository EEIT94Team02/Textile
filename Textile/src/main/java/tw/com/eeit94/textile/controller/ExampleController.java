package tw.com.eeit94.textile.controller;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.com.eeit94.textile.model.example.ExampleBean;


@Controller
@RequestMapping(path = { "/example.controller" }, produces = { "application/json; charset=UTF-8" })
public class ExampleController {

	@RequestMapping(method = { RequestMethod.GET })
	@ResponseBody
	public void process(ExampleBean bean, BindingResult bindingResult, Model model, Writer out) throws IOException {
		out.write("{ 'key' : 'value' }");
		// 要產生View元件則要return "Url Pattern"的相對或絕對路徑。
	}
}