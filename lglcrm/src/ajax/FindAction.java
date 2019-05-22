package ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import author.pojo.Right;
import author.pojo.User;


public class FindAction extends ActionSupport{
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<Right> rights = (List<Right>) session.get("rights");
		StringBuffer str=new StringBuffer();
//		创建一个新的数组，用来起标识作用
		boolean [] data=new boolean[18];
		for(Right r:rights){
			data[r.getRightId()-1]=true;
		}
		for(int i=0;i<data.length-1;i++){
			str.append(data[i]+"-");
		}
		str.append(data[data.length-1]);
		ServletActionContext.getResponse().getWriter().print(str);
		System.out.println(123);
		return null;
	}
}
