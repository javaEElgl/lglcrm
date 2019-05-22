package utils.inter;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import author.pojo.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * @author lgl
 *自定义登录拦截器，判断用户是否登录
 */
public class loginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		Map<String,Object> Session = ServletActionContext.getContext().getSession();
		User ur = (User) Session.get("user");
		if(ur!= null){
		   return ai.invoke();
		}
		return "unlogin";
	}

}
