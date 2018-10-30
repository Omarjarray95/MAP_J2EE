package resources;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("Message")
@RequestScoped
public class MessageResource 
{
	@GET
	public String SayHello()
	{
		String S = "Hello";
		return S;
	}
	
}
