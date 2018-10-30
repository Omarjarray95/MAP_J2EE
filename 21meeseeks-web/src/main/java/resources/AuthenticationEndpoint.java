package resources;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/authentication")
@Transactional
public class AuthenticationEndpoint {

	@PersistenceContext
	private EntityManager em;
	
	@Context
	private UriInfo uriInfo;
	
	@Context
	SecurityContext securityContext;


	
	
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username, 
                                     @FormParam("password") String password) {

        try {

            // Authenticate the user using the credentials provided
            authenticate(username, password);

            // Issue a token for the user
            String token = issueToken(username);

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }      
    }

    private void authenticate(String username, String password) throws Exception {
        // Authenticate against a database, LDAP, file or whatever
        // Throw an Exception if the credentials are invalid
    		
    	
    	
/*		 System.out.println("Authenticated user: " + securityContext.getUserPrincipal());

		 String authenticatedUser = sctx.getUserPrincipal().getName();
*/
	/*	 if (!authenticatedUser.equals(null))
			 return true;
*/
    	
    	User u =  (User) em.createQuery("SELECT u FROM User u WHERE  email=:username")
    			.setParameter("username", username).getSingleResult();
    	
    	
    	
    	if (u==null ) 
    		throw new Exception("invalid credentials");
    	//LAHNE EL CRYBTAGE
    	if (!u.getPassword().equals(password))
    		throw new Exception("password GHALET");
    	/*
		 * System.out.println(this.sctx.isSecure());
		 * System.out.println(this.sctx.getUserPrincipal());
		 * 
		 * if (this.sctx.isUserInRole("group4")) return true; throw new
		 * SecurityException("User is unauthorized.");
		 */
		System.out.println("Authenticating user...");
	

    
    }

    private String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
   	
		// Issue a token (can be a random String persisted to a database or a JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("the key is : " + key);
		String jwtToken = Jwts.builder().setSubject(username).
				setIssuer(uriInfo.getAbsolutePath().toString())
				.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();

		System.out.println("the returned token is : " + jwtToken);
		return jwtToken;

		/*
		 * Random random = new SecureRandom(); String token = new
		 * BigInteger(130,random).toString(32); return token;
		 */

		/*
		 * Date creationDate = new Date();
		 * 
		 * String key = UUID.randomUUID().toString().toUpperCase() + "|" + username +
		 * "|" + format.format(creationDate);
		 * 
		 * // this is the authentication token user will send in order to use the web //
		 * service String authenticationToken = jasypt.encrypt(key);
		 * 
		 * return authenticationToken;
		 */

    	
//    	return "";
    }
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}