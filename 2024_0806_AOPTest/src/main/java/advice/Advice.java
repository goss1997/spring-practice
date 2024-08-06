package advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class Advice {

	@Autowired
	HttpServletRequest request;


	public void before(JoinPoint jp){
		Signature s =  jp.getSignature();

		long start = System.currentTimeMillis();

		request.setAttribute("start", start);

		System.out.println("----before:" + s.toString());
	}
	
	public void after(JoinPoint jp){
		Signature s =  jp.getSignature();

		long end = System.currentTimeMillis();

		System.out.println("----after:" + s.toString());
		System.out.printf("수행시간 : %d(ms)\n", (end - Long.parseLong(request.getAttribute("start").toString()) ));
	}
}
