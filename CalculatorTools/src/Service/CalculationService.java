package Service;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Model.Calculation;

import java.util.List;


@Stateless
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculationService {
 
	@PersistenceContext(unitName="CalcPU")
	private EntityManager entityManager;
   
    public CalculationService()
    {
    	
    }
    
    @POST
    @Path("calc")
    public Response calculate(Calculation calculation) {
        int number1 = calculation.getnumber1();
        int number2 = calculation.getnumber2();
        String operation = calculation.getoperation();
        double res = 0.0;
        try {
        if (operation.equals("+")) {
            res = number1 + number2;
        } else if (operation.equals("-")) {
            res = number1 - number2;
        } else if (operation.equals("*")) {
            res = number1 * number2;
        } else if (operation.equals("/")) {
            if (number2 != 0) {
                res = (double)number1 / number2;
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("cannot divide by zero").build();
            }
        }
        else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception occurred: operator not found").build();

        }
        calculation.setres(res);
        entityManager.persist(calculation);
        return Response.status(Response.Status.OK).entity("result: " + res).build();
        } catch (ArithmeticException | IllegalArgumentException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error in input or operation: " + e.getMessage()).build();
        }catch(Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception occurred: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("calculations")
    public Response getAllCalculations()
    {
    	try {
    	List<Calculation> calculations=entityManager.createQuery("SELECT c FROM Calculation c",Calculation.class).getResultList();
        return Response.status(Response.Status.OK).entity(calculations).build();
    	}catch(Exception e)
    	{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Exception occurred: " + e.getMessage()).build();

    	}

    }
    
    
}
