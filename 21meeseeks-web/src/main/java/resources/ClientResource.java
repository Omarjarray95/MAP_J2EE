package resources;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.ClientServiceLocal;

import javax.ejb.EJB;
import javax.ejb.Remove;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.Application;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import entities.Client;
import entities.ClientCategory;

@Path("client")
@RequestScoped
public class ClientResource {

	@EJB(beanName="ClientService")
	ClientServiceLocal CSL;
	private Map<String, String> criterias;
	
	
	
	
	
	/*  searching for client using multiple criterias
	 * used a map to store criterias passed in path param
	 * to use them later on in the function.
	 */
	  @GET
	  @Produces( MediaType.APPLICATION_JSON )
	  public Response findClientsByName(
			    @QueryParam("idUser") String idUser,
			    @QueryParam("clientName") String clientName,
				@QueryParam("email") String email,
				@QueryParam("address") String address,
				@QueryParam("clientType") String clientType,
				@QueryParam("clientCategory") String clientCategory,
				@QueryParam("phoneNumber") String phoneNumber
			  ) {
		  enums.ClientType ct=null;
		  criterias=new HashMap<String, String>();
		  if(idUser!=null){criterias.put("idUser",idUser);}
		  if(clientName!=null){criterias.put("clientName",clientName+"%");}
		  if(email!=null){criterias.put("email", email+"%");}
		  if(address!=null){criterias.put("address", address+"%");}
		  if(clientType!=null){criterias.put("clientType", ct.valueOf(clientType).toString());}
		  if(clientCategory!=null){criterias.put("clientCategory", clientCategory);}
		  if(phoneNumber!=null){criterias.put("phoneNumber", phoneNumber);}
		List<Client> c=CSL.getClientsByCriterias(criterias);
		if(c.size()!=0)
		{
			return  Response.status(Status.FOUND).entity(CSL.clientlisttoJson(c)).build();
		}
		else
		{
			return  Response.status(Status.NOT_FOUND).build();
		}
	  }

	  
	  /*Add client received through json and return 
	   * the client in json format.
	   */
	  
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces( MediaType.APPLICATION_JSON)
	public Response addClient(Client c)
	{
		
		c.setIdUser(CSL.addClient(c));
		return Response.status(Status.CREATED).entity(CSL.clienttoJson(c)).build();
	}

	 @PUT
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response edit(Client c) {
			Client cl=CSL.editClient(c);

			return Response.status(Status.CREATED).entity(CSL.clienttoJson(cl)).build();

	 }
	
	  //
	  //customize category (add or delete)
	  //
	  
	
	
	
	@POST
	@Path("category")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces( MediaType.APPLICATION_JSON)
	public Response addClientCategory(ClientCategory cc)
	{	
		 
		cc.setIdCategory(CSL.addClientCategory(cc));
		if(cc.getIdCategory()==0)
		{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		{
			return Response.status(Status.CREATED).entity(cc).build();	
		}
	}
	@PUT
	@Path("category")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces( MediaType.APPLICATION_JSON)
	public Response editClientCategory(ClientCategory cc)
	{	
		 ClientCategory cat=CSL.editClientCategory(cc);
		if(cat==null)
		{
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
		{
			return Response.status(Status.OK).entity(cat).build();	
		}
	}
	/*
	 * delete a Category by name
	 */
	
	  @DELETE
	  @Path("category/{name}")
	  public Response deleteClientCategory(@PathParam("name") String name) {
	  if(CSL.deleteClientCategory(name))
	  {
		  return Response.status(Status.FOUND).entity(name+": category deleted").build();	
				  }
	  return Response.status(Status.NOT_FOUND).entity(name+": category not found").build();	

	  }
	  @GET
	  @Path("category")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response getallclientcategory() {
		  	List<ClientCategory> list = CSL.listClientCategories();
		  	if(list.size()!=0)
		  	{
	  return Response.status(Status.FOUND).entity(list).build();	
		  	}
		  	else
		  	{
		  	  return Response.status(Status.NO_CONTENT).build();	

		  	}

	  }

	 
	 
	  
}
