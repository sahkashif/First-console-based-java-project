import java.util.Scanner;
import java.util.Vector;

public class Team 
{
	Scanner input=new Scanner(System.in);
	
	public String name;
	public static int totalPlayers;
	public boolean tossResult; 
	public Player playerArray[]=new Player[totalPlayers];
	public int teamRun=0;
	public int givenExtra=0;
	public int wicketGone=0;
	public String startingStatus;
	public int playedBall=0;
	Vector<PartnerShip> classList =new Vector<PartnerShip>();
	Vector<Over> overRecords =new Vector<Over>();
	
	Team(){};
	Team(int n)
	{
		this.totalPlayers=n;
	}
	
	public void nameSetter()
	{
		name=input.next();
	}
	public void playerCreate()
	{
		for(int i=0;i<Team.totalPlayers;i++)
		{
			Player p=new Player();
			playerArray[i]=p;
		}
	}
	public void classArraySetup(Player  array[])
	{
		playerArray=array;
		
	}
	
}
