import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class MatchPlay
{
	public static void instructionShow()
	{
		System.out.println("\t\t* Select over, player numbers & team names.");
		System.out.println("\t\t* Enter 0,1,2,3,4,6 from keyboard when result off ball is these runs");
		System.out.println("\t\t* Enter 'W' when wicket falls");
		System.out.println("\t\t* Enter 'w' for wide & 'n' for no Ball");
		System.out.println("\t\t* Enter 'e' as extra like by runs");
	}
	public static void headertitle()
	{
	    System.out.println("\t\t\t****************************************************");
	    System.out.println("\t\t\t**************** Cricket Score Board ***************");
	    System.out.println("\t\t\t****************************************************");
	}
	public static int overs;
	public static void check(char c)throws MyException
	{
		if((c!='1')&&(c!='2')&&(c!='3')&&(c!='4')&&(c!='6')&&(c!='w')&&(c!='n')&&(c!='W')&&(c!='e')&&(c!='0'))
		{
			throw new MyException(c);
		}
	}
	
	public static void printing(Team obj1,Team obj2) throws IOException
	{
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("f.txt")));
		pw.write("                             Cricket Score Board                   ");
		pw.println();pw.println();
		pw.println("\t\t"+winnerSelection(obj1,obj2));
		if(obj1.tossResult==true){
		pw.println("\n\n\t\tTeam "+obj1.name+" won toss");}
		else
			pw.println("\t\tTeam "+obj2.name+" wins toss");
		
		pw.println("\t\tTotal Run of Team "+obj1.name+" is "+obj1.teamRun+" & Run of Team "+obj2.name+" is "+obj2.teamRun);
		pw.println("\t\tGiven Extra by Team "+obj1.name+" is "+obj1.givenExtra+" runs"+" & Team "+obj2.name+" is "+obj2.givenExtra);
		pw.println();
		for(int i=0;i<obj1.classList.size();i++)
		{
			pw.println("\t\t"+obj1.classList.elementAt(i).show());
		}
		pw.println("\n\n\t\tBatting innings of two teams:");
		for(int i=0;i<Team.totalPlayers;i++)
		{
			pw.println("\t\t"+(i+1)+". "+obj1.playerArray[i].name+": "+obj1.playerArray[i].run+"\t\t"+(i+1)+". "+obj2.playerArray[i].name+": "+obj2.playerArray[i].run);
		}
		pw.println();
		pw.println("\t\tBowling innings of two teams:");
		for(int i=0;i<Team.totalPlayers;i++)
		{
			pw.println("\t\t"+(i+1)+". "+obj1.playerArray[i].name+": "+obj1.playerArray[i].givenRun+"/"+obj1.playerArray[i].takenWicket+" from "+(obj1.playerArray[i].bowledOver/6)+"."+(obj1.playerArray[i].bowledOver%6)+" overs  |\t\t"+(i+1)+". "+obj2.playerArray[i].name+": "+obj2.playerArray[i].givenRun+"/"+obj2.playerArray[i].takenWicket+" from "+(obj2.playerArray[i].bowledOver/6)+"."+(obj2.playerArray[i].bowledOver%6)+" overs");
		}
		pw.close();
		
	}
	static void toss(Team Variable,Team variable)
	{
		
		Random tossGenerator=new Random();
		
		int tossSelection=tossGenerator.nextInt(2)+1;
		if(tossSelection==1)
			{Variable.tossResult=true;variable.tossResult=false;System.out.println("\n\t\t"+Variable.name+" wins the toss!");}
		else if(tossSelection==2)
			{Variable.tossResult=false;variable.tossResult=true;System.out.println("\n\t\t"+variable.name+" wins the toss!");}
	}
	
	public static String winnerSelection(Team x,Team y)
	{
		if(x.startingStatus=="bat")
		{
			if(x.teamRun>y.teamRun)
			{
				return x.name+" wins by "+(x.teamRun-y.teamRun)+" runs";
			}
			else if(x.teamRun<y.teamRun)
			{
				if(x.wicketGone>=y.wicketGone)
					return y.name+" wins by "+(x.wicketGone-y.wicketGone)+" wickets";
				else
					return x.name+" wins by "+(((overs*6)-x.playedBall)/6)+"."+(((overs*6)-x.playedBall)%6)+" overs";
			}
			else
				return "Match Draw!";
		}
		else if(y.startingStatus=="bat")
		{
			if(y.teamRun>x.teamRun)
			{
				return y.name+" wins by "+(y.teamRun-x.teamRun)+" runs";
			}
			else if(y.teamRun<x.teamRun)
			{
				if(y.wicketGone>=x.wicketGone)
					return x.name+" wins by "+(y.wicketGone-x.wicketGone)+" wickets";
				else
					return x.name+" wins by "+(((overs*6)-x.playedBall)/6)+"."+(((overs*6)-x.playedBall)%6)+" overs";
			}
			else
				return "Match Draw!";
		}
		else
			return "Shit happened!";
	}
	
	public static void matchCalculation(Team t1,Team t2)
	{
		Scanner input=new Scanner(System.in);
		int selection;
		int wicketGone=0,pshipRun=0;
		Team battingTeam=new Team();
		Team bowlingTeam=new Team();
		Player batsmanOnStrike1=new Player();
		Player batsmanOnStrike2=new Player();
		Vector <PartnerShip> tempPshipList=new Vector<PartnerShip>();
		Vector <PartnerShip> innings1pship=new Vector<PartnerShip>();
		Vector <PartnerShip> innings2pship=new Vector<PartnerShip>();
		
		System.out.println("\n\t\tSelect batting or bowling:\n\t\t\t1.Bat\n\t\t\t2.Ball");
		System.out.print("\t\t\t");
		selection=input.nextInt();
		if (selection==1)
		{
			if(t1.tossResult==true)
					{battingTeam=t1;t1.startingStatus="bat";bowlingTeam=t2;System.out.println("\t\tTeam "+t1.name+" selected to bat!\n");}
			else if(t2.tossResult==true)
					{battingTeam=t2;t2.startingStatus="bat";bowlingTeam=t1;System.out.println("\t\tTeam "+t2.name+" selected to bat!\n");}
				
		}
		else 
		{
			if(t1.tossResult==true)
					{battingTeam=t2;bowlingTeam=t1;}
			else if(t2.tossResult==true)
					{battingTeam=t1;bowlingTeam=t2;}
		}
		
		for(int innings=1;innings<=2;innings++)
		{
			
			int tempSelection;
	
			System.out.println("\t\tPut two numbers among below players list to select two batsman to start innings:");
			
			for(int p=0;p<Team.totalPlayers;p++)
			{
				System.out.println("\t\t\t"+(p+1)+". "+battingTeam.playerArray[p].name);
			}
			
			System.out.print("\t\t\t");
			tempSelection=input.nextInt();
			batsmanOnStrike1=battingTeam.playerArray[tempSelection-1];
			System.out.print("\t\t\t");
			tempSelection=input.nextInt();
			batsmanOnStrike2=battingTeam.playerArray[tempSelection-1];
			
			
			for(int over=0;over<overs;over++)
			{
				Over o=new Over();
				System.out.println("\t\tPut one number among below player list to select bowler: ");
				for(int p=0;p<Team.totalPlayers;p++)
				{
					System.out.println("\t\t\t"+(p+1)+". "+bowlingTeam.playerArray[p].name);
				}
				System.out.print("\t\t\t");
				tempSelection=input.nextInt();
				Player bowler=bowlingTeam.playerArray[tempSelection-1];
				o.bowlerName=bowler.name;
				for(int ball=0;ball<6;)
				{
					System.out.print("\t\tOver "+over+"."+(ball+1)+": ");
					char c=input.next().charAt(0);
					try{
						check(c);
						if(c=='0')
						{
							batsmanOnStrike1.playedBall++;
							ball++;
							bowler.bowledOver++;
							battingTeam.playedBall++;
						}
						else if(c==49 || c==51)
						{
							battingTeam.playedBall++;
							batsmanOnStrike1.playedBall++;
							ball++;
							o.oversRun+=(int)c-48;
							bowler.givenRun+=(int)c-48;
							bowler.bowledOver++;
							for(int p=0;p<Team.totalPlayers;p++)
							{
								if(batsmanOnStrike1.name.equals(battingTeam.playerArray[p].name))
										{
											battingTeam.playerArray[p].run+=(int)c-48;
										}
										
							}
										
							
							battingTeam.teamRun+=(int)c-48;
							pshipRun+=(int)c-48;
							
							Player temp=batsmanOnStrike1;              //           
							batsmanOnStrike1=batsmanOnStrike2;         //     <<<<   Strike swap
							batsmanOnStrike2=temp;                     //           
							if(innings==2){
								if(battingTeam.teamRun>bowlingTeam.teamRun)
									break;
								else
									continue;}
							else
								continue;
						}
		
						else if(c==50 || c==52 || c==54){
							battingTeam.playedBall++;
							batsmanOnStrike1.playedBall++;
							ball++;
							o.oversRun+=(int)c-48;
							bowler.givenRun+=(int)c-48;
							bowler.bowledOver++;
							for(int p=0;p<Team.totalPlayers;p++)
							{
								if(batsmanOnStrike1.name.equals(battingTeam.playerArray[p].name))
										{
											battingTeam.playerArray[p].run+=(int)c-48;
										}
										
							}
							pshipRun+=(int)c-48;
							battingTeam.teamRun+=(int)c-48;
							if(innings==2){
								if(battingTeam.teamRun>bowlingTeam.teamRun)
									break;
								else
									continue;}
							else
								continue;
						}
						else if(c=='w' || c=='n')
						{
							bowler.givenExtra++;
							o.extras++;
							bowler.givenExtra++;
							battingTeam.teamRun++;
							bowlingTeam.givenExtra++;
							if(innings==2){
								if(battingTeam.teamRun>bowlingTeam.teamRun)
									break;
								else
									continue;}
							else
								continue;
						}
						else if(c=='W')
						{
							battingTeam.playedBall++;
							bowler.takenWicket++;
							ball++;
							wicketGone++;
							PartnerShip p1=new PartnerShip(batsmanOnStrike1.name,batsmanOnStrike2.name,pshipRun);
							tempPshipList.add(p1);
							pshipRun=0;
							batsmanOnStrike1.playedBall++;
							if(wicketGone==Team.totalPlayers-1)
								break;
							else
							{
								System.out.println("\t\tSelect new batsman: ");
								int index=1;
								for(int p=1;p<=Team.totalPlayers;p++)
								{
									if(batsmanOnStrike1.name==battingTeam.playerArray[p-1].name||batsmanOnStrike2.name==battingTeam.playerArray[p-1].name)
										continue;
									else
									System.out.println("\t\t\t"+p+". "+battingTeam.playerArray[p-1].name);index++;
								}
								System.out.print("\t\t\t");
								tempSelection=input.nextInt();
								batsmanOnStrike1=battingTeam.playerArray[tempSelection-1];
							}
						}
						else if(c=='e')
						{
							bowlingTeam.givenExtra++;
							battingTeam.teamRun++;
							
						}
					}
					catch(MyException E)
					{
						System.out.print(E.toString()+"\nRe Enter previous ball result\n");
						continue;
						
					}
					
					if (ball==6)
					{
						System.out.println("\t\t********************************************************************");
						System.out.println("\n\t\tAfter Over: "+(over+1)+" Score is:"+battingTeam.teamRun+"/"+wicketGone);
						System.out.println("\t\t\t"+batsmanOnStrike1.name+" : "+batsmanOnStrike1.run+"\n\t\t\t"+batsmanOnStrike2.name+"*: "+batsmanOnStrike2.run);
						System.out.println("\t\t********************************************************************");
						Player temp=batsmanOnStrike1;
						batsmanOnStrike1=batsmanOnStrike2;
						batsmanOnStrike2=temp;
						battingTeam.overRecords.add(o);
						break;
					}
					else
						{continue;}
				}
				if(wicketGone==Team.totalPlayers-1)
					break;
				if(innings==2){
					if(battingTeam.teamRun>bowlingTeam.teamRun)
						break;
					else
						continue;}
				else
					continue;
			}
			for(int i=0;i<wicketGone;i++)
			{
				System.out.println("\t\t"+tempPshipList.elementAt(i).toString());
			}
			
			
			
			
			if(innings==1){
				battingTeam.wicketGone=wicketGone;
				System.out.println("\t\tTeam "+bowlingTeam.name+" requires "+(battingTeam.teamRun+1)+" runs to win"+" at run rate "+(double)battingTeam.teamRun/overs);
				innings1pship=tempPshipList;
				battingTeam.classList=innings1pship;
				Team tempTeam=battingTeam;              //
				battingTeam=bowlingTeam;                // Team swap after 1st innings        
				bowlingTeam=tempTeam;wicketGone=0;                  //
				System.out.print("\n\n\n\n\n\n\t\tNew Innings Started\n\n\n\n\n");}
			else if(innings==2){
				innings2pship=tempPshipList;battingTeam.classList=innings2pship;
			}
		}
	}
	
	public static void main(String args[]) throws IOException
	{
		Scanner input=new Scanner (System.in);
		MatchPlay.headertitle();
		for(;;)
		{
			System.out.println("\n\n\t\tEnter Choice:");
			System.out.println("\t\t\t1. Start Match\n\t\t\t2. Instructions\n\t\t\t3. Exit");
			
			System.out.print("\t\t\t");
			int choice=input.nextInt();
			if(choice==1)
			{
				
				System.out.println("\t\tHow many overs will be played?");
				System.out.print("\t\t");
				overs=input.nextInt();
				System.out.println("\t\tHow many players will play in each team?");
				System.out.print("\t\t");
				Team.totalPlayers=input.nextInt();
				
				Team A=new Team();
				Team B=new Team();
				
				A.playerCreate();
				B.playerCreate();
				
				System.out.println("\t\tEnter Teams names: ");
				System.out.print("\t\t\t");
				A.nameSetter();
				System.out.print("\t\t\t");
				B.nameSetter();
				
				System.out.println("\t\tEnter team "+A.name+"'s player names:");
				for(int l=1;l<=Team.totalPlayers;l++)
				{
					System.out.print("\t\t\t"+l+". ");
					A.playerArray[l-1].name=input.next();
				}
				System.out.println("\n\t\tEnter team "+B.name+"'s player names:");
				for(int l=1;l<=Team.totalPlayers;l++)
				{
					System.out.print("\t\t\t"+l+". ");
					B.playerArray[l-1].name=input.next();
				}
				
				toss(A,B);
				matchCalculation(A,B);
				System.out.println("\t\t********** "+winnerSelection(A,B)+" **************");
				printing(A,B);
			}
			else if(choice ==2)
			{
				for(int i=0;i<10;i++)
				{
					System.out.println();
				}
				MatchPlay.instructionShow();
				System.out.println("\n\n");
				MatchPlay.headertitle();
			}
			
			else if(choice==3)
			{
				System.exit(0);
			}
		}
		
		
	}
}