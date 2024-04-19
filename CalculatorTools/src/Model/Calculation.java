package Model;



import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;



@Entity
@Stateless
public class Calculation {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private int number1;
	@NotNull
	private int number2;
	private double res;
	@NotNull
	private String operation;
	
	public void setid(Long id)
	{
		this.id=id;
	}
	public void setnumber1(int number1)
	{
		this.number1=number1;
	}
	public void setnumber2(int number2)
	{
		this.number2=number2;
	}
	public void setres(double res)
	{
		this.res=res;
	}
	public void setoperation(String operation)
	{
		this.operation=operation;
	}
	
	public Long getid()
	{
		return id;
	}

	public int getnumber1()
	{
		return number1;
	}
	public int getnumber2()
	{
		return number2;
	}
	public double getres()
	{
		return res;
	}
	public String getoperation()
	{
		return operation;
	}

	
	
	

	

}
