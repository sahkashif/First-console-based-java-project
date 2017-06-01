
public class PartnerShip 
{
	public String name1,name2;
	public int partnershipRun;
	
	PartnerShip(String x,String y,int n)
	{
		name1=x;name2=y;partnershipRun=n;
	}
	
	public String toString()
	{
		return name1+" & "+name2+" both scores "+partnershipRun+" runs";
	}
	public String show()
	{
		return "\nPartnership between "+name1+" & "+name2+" > "+partnershipRun;
	}
}
