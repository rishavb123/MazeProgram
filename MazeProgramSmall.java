import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
public class MazeProgramSmall extends JPanel implements KeyListener
{
	JFrame frame;
	char[][] mazeBoard=new char[75][30];
	int x=10;
	int y=20;
	boolean go;
	int moves=0;
	boolean mV= false;
	boolean mH=true;
	boolean run= false;
	boolean redBox=false;
	boolean redBox2=false;
	boolean greenBox=false;
	boolean greenBox2=false;
	boolean blueBox=false;
	boolean blueBox2=false;
	int num=1;
	boolean gotKey = false;
	boolean gotRKey = false;
	boolean gotKey1=false;
	boolean gotKey2=false;

	Scanner input = new Scanner(System.in);
	public MazeProgramSmall()
	{
		while(!run)
		{
			run=true;
			setBoard();
			if(run)
			{
				frame = new JFrame();
				frame.add(this);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800,450);
				frame.setVisible(true);
				frame.addKeyListener(this);
			}
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(mH && ((redBox && !redBox2) || (!redBox && redBox2)))
			mazeBoard[x/10][y/10]=(char)97;
		if(mV && ((redBox && !redBox2) || (!redBox && redBox2)))
			mazeBoard[x/10][y/10]=(char)98;
		if(mH && ((!greenBox && greenBox2) || (greenBox && !greenBox2)))
			mazeBoard[x/10][y/10]=(char)99;
		if(mV &&((!greenBox && greenBox2) || (greenBox && !greenBox2)))
			mazeBoard[x/10][y/10]=(char)100;
		if(mV &&((!blueBox && blueBox2) || (blueBox && !blueBox2)))
			mazeBoard[x/10][y/10]=(char)101;
		if(mH &&((!blueBox && blueBox2) || (blueBox && !blueBox2)))
			mazeBoard[x/10][y/10]=(char)102;
		g.setColor(Color.BLACK);
		g.fillRect(0,0,800,450);
		for(int e=0;e<mazeBoard.length-1;e++)
			for(int w=0;w<mazeBoard[e].length;w++)
				if(mazeBoard[e][w]==97 && (redBox|| redBox2))
				{
					g.setColor(Color.RED);
					g.drawLine(e*10,w*10+5,(e)*10+5,w*10+5);
				}
				else if(mazeBoard[e][w]==98 && (redBox|| redBox2))
				{
					g.setColor(Color.RED);
					g.drawLine(e*10+5,w*10,(e)*10+5,(w)*10+5);
				}
				else if(mazeBoard[e][w]==101 && (blueBox|| blueBox2))
				{
					g.setColor(Color.CYAN);
					g.drawLine(e*10+5,w*10,(e)*10+5,(w)*10+5);
				}
				else if(mazeBoard[e][w]==102 && (blueBox|| blueBox2))
				{
					g.setColor(Color.CYAN);
					g.drawLine(e*10,w*10+5,(e)*10+5,w*10+5);
				}
				else if(mazeBoard[e][w]==99 && (greenBox || greenBox2))
				{
					g.setColor(Color.GREEN);
					g.drawLine(e*10,w*10+5,(e)*10+5,w*10+5);
				}
				else if(mazeBoard[e][w]==100 && (greenBox || greenBox2))
				{
					g.setColor(Color.GREEN);
					g.drawLine(e*10+5,w*10,(e)*10+5,(w)*10+5);
				}
				else if(mazeBoard[e][w]==35)
				{
					g.setColor(Color.GRAY);
					g.fillRect((e)*10,(w)*10,10,10);
					g.setColor(Color.BLACK);
					g.drawRect((e)*10,(w)*10,10,10);
				}
		if(num==3)
		{
			g.setColor(Color.GRAY);
			g.fillRect(560,270,10,10);
			g.setColor(Color.BLACK);
			g.drawRect(560,270,10,10);
			mazeBoard[56][27]=35;
			g.setColor(Color.YELLOW);
			g.drawOval(530,130,10,10);
			g.drawRect(540,133,10,3);
			g.drawRect(545,137,5,3);
			g.setColor(Color.RED);
			g.drawOval(120,250,10,10);
			g.drawRect(130,253,10,3);
			g.drawRect(135,257,5,3);
		}
		if(x==120 && y==250 && num==3 && !gotRKey)
		{
			gotRKey = true;
			System.out.print((char)7);
		}
		if(x==230 && y==100 && num==2 && !gotKey2)
		{
			gotKey2 = true;
			System.out.print((char)7);
		}
		if(x==310 && y==280 && num==1 && !gotKey1)
		{
			gotKey1 = true;
			System.out.print((char)7);
		}
		if(num==2 && gotKey2==false)
		{
			g.setColor(Color.GREEN);
			g.drawOval(230,100,10,10);
			g.drawRect(240,103,10,3);
			g.drawRect(245,107,5,3);
		}
		if(num==1 && gotKey1==false)
		{
			g.setColor(Color.GREEN);
			g.drawOval(310,280,10,10);
			g.drawRect(320,283,10,3);
			g.drawRect(325,287,5,3);
		}
		if(gotRKey)
		{
			gotKey=false;
			g.setColor(Color.BLACK);
			g.fillRect(120,250,20,10);
			g.drawRect(120,250,20,10);
			g.fillRect(670,280,10,10);
		}
		if(x==530 && y==130 && num==3 && !gotKey)
		{
			gotKey = true;
			System.out.print((char)7);
		}
		if(gotKey)
		{
			g.setColor(Color.BLACK);
			g.fillRect(530,130,20,10);
			g.drawRect(530,130,20,10);
			g.fillRect(560,270,10,10);
			mazeBoard[56][27]=32;
			gotRKey=false;
		}

		g.setColor(Color.RED);
		g.fillOval(x,y,10,10);

		g.setFont(new Font("Times New Roman",Font.BOLD,18));
		g.drawString("Moves: "+moves, 10,330);
		g.drawString("Click R to restart", 10,360);
		g.drawString("Whatever happens isn't a glitch ;)", 10,390);
		if(x==730 && y ==280 )
		{
			if(num==2 && gotKey2)
			{
				num++;
				setBoard();
				x=10;
				y=20;
			}
			else if(num==1 && gotKey1 && redBox && redBox2 && greenBox && greenBox2 && blueBox && blueBox2)
			{
				num++;
				setBoard();
				x=10;
				y=20;
			}
			else if(num==3)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0,0,2400,1100);
				g.setColor(Color.RED);
				g.drawString("YOU BEAT THE MAZE !!!!!!!!!! And it only took "+moves+" moves!",30,110);
			}
		}



		if(num==1)
		{
			g.setColor(Color.RED);
			g.drawRect(180,210,10,10);
			g.drawRect(580,100,10,10);
			g.setColor(Color.GREEN);
			g.drawRect(620,60,10,10);
			g.drawRect(670,250,10,10);
			g.setColor(Color.CYAN);
			g.drawRect(10,80,10,10);
			g.drawRect(640,30,10,10);
		}


		if(x==640 && y==30 && ((!redBox && !redBox2)||(redBox && redBox2))&&((!greenBox && !greenBox2)||(greenBox&&greenBox2)))
			blueBox2=true;
		if(blueBox2)
		{
			g.setColor(Color.BLACK);
			g.drawRect(640,30,10,10);
		}

		if(x==10 && y==80&& ((!redBox && !redBox2)||(redBox && redBox2))&&((!greenBox && !greenBox2)||(greenBox&&greenBox2)))
			blueBox=true;
		if(blueBox)
		{
			g.setColor(Color.BLACK);
			g.drawRect(10,80,10,10);
		}


		if(x==620 && y==60&& ((!redBox && !redBox2)||(redBox && redBox2))&&((!blueBox&&!blueBox2)||(blueBox&&blueBox2)))
			greenBox=true;
		if(greenBox)
		{
			g.setColor(Color.BLACK);
			g.drawRect(620,60,10,10);
		}

		if(x==670 && y==250&& ((!redBox && !redBox2)||(redBox && redBox2))&&((!blueBox&&!blueBox2)||(blueBox&&blueBox2)))
			greenBox2=true;
		if(greenBox2)
		{
			g.setColor(Color.BLACK);
			g.drawRect(670,250,10,10);
		}


		if(x==180 && y==210&&((!greenBox && !greenBox2)||(greenBox&&greenBox2))&&((!blueBox&&!blueBox2)||(blueBox&&blueBox2)))
			redBox=true;
		if(redBox)
		{
			g.setColor(Color.BLACK);
			g.drawRect(180,210,10,10);
		}


		if(x==580 && y==100&&((!greenBox && !greenBox2)||(greenBox&&greenBox2))&&((!blueBox&&!blueBox2)||(blueBox&&blueBox2)))
			redBox2=true;
		if(redBox2)
		{
			g.setColor(Color.BLACK);
			g.drawRect(580,100,10,10);
		}




		if(x==0 && y==20)
		{
			if(num==2)
			{
				num--;
				setBoard();
				x=720;
				y=280;
			}
			else if(num==3)
			{
				num--;
				setBoard();
				x=720;
				y=280;
			}
			repaint();
		}

	}
	public void setBoard()
	{
		//choose your maze design

		//pre-fill maze array here

		File name = new File("Maze"+num+".txt");
		int r=0;
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(name));
			StringBuffer buffer=new StringBuffer();
			String text;
			while((text=input.readLine())!=null)
			{
				//System.out.println(text);
				//fill maze array with actual maze stored in text file
				for(int x=0;x<text.length();x++)
					mazeBoard[x][r]=(char)text.charAt(x);
				r++;
			}
		}
		catch(IOException io)
		{
			System.err.println("File error-KaBOOM");
			run=false;
		}
	}
	public void keyPressed(KeyEvent e)
	{
		go=true;
		//System.out.println(e.getKeyCode());
		//determine the arrow keys and configure them to move though the array
		switch(e.getKeyCode())
		{
			case 38 :
				if(gotKey && !gotRKey)
					moveRight();
				else
					moveUp();
							break;
			case 40:
				if(gotKey && !gotRKey)
					moveLeft();
				else
					moveDown();
							break;
			case 39 :
				if(gotKey && !gotRKey)
					moveDown();
				else
					moveRight();
							break;
			case 37:
				if(gotKey && !gotRKey)
					moveUp();
				else
					moveLeft();
							break;
			case 82:
				moves=0;
				mV= false;
				mH=true;
				run= false;
				redBox=false;
				redBox2=false;
				greenBox=false;
				greenBox2=false;
				blueBox=false;
				blueBox2=false;
				num=1;
				x=10;
				y=20;
				gotKey = false;
				gotRKey = false;
				gotKey1=false;
				gotKey2=false;
				setBoard();
						break;
			case 87:
				y-=10;
				mV=true;
				mH=false;
					break;
			case 83:
				y+=10;
				mV=true;
				mH=false;
					break;
			case 65:
				x-=10;
				mH=true;
				mV=false;
					break;
			case 68:
				x+=10;
				mH=true;
				mV=false;
					break;
			case 27: frame.dispose();
							break;
			case 32: System.out.println(x+"\t"+y);
		}
		teleportation();
		repaint();
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}
	public void teleportation()
	{
		if(num==2)
		{
			tel(540,20,100,40);
			tel(690,280,100,40);
			tel(350,140,320,150);
			tel(320,140,680,280);
			tel(380,200,670,210);
			tel(470,200,670,210);
			tel(670,200,10,20);
			tel(570,200,400,150);
			tel(460,140,570,140);
			tel(460,160,570,160);
			tel(580,140,230,100);
			tel(580,160,60,70);
			tel(240,100,10,20);
			tel(240,170,230,100);
			tel(300,260,700,280);
			tel(30,140,10,20);
			tel(30,160,10,20);
			tel(30,190,10,20);
		}
		if(num==3)
		{
			tel(100,70,150,30);
			if(x==320 && y==30)
			{
				int bo=(int)(Math.random()*2);
				if(bo==0)
					tel(320,30,390,10);
				else
					tel(320,30,390,50);
			}
			tel(110,120,150,90);
			if(x==320 && y==90)
			{
				int bo=(int)(Math.random()*2);
				if(bo==0)
					tel(320,90,390,70);
				else
					tel(320,90,390,110);
			}
			tel(660,280,690,280);
			tel(680,280,650,280);
			tel(470,10,510,10);
			tel(610,110,690,130);
			tel(630,110,640,130);
			tel(570,110,590,130);
			tel(670,110,520,130);
			tel(470,50,30,70);
			tel(470,110,30,130);
			tel(470,70,30,130);
			tel(700,130,10,20);
			tel(650,130,10,20);
			tel(600,130,10,20);
			tel(540,130,150,140);
			tel(610,150,10,20);
			tel(260,220,120,220);
			tel(590,150,570,150);
			tel(130,220,150,140);
		}
	}
	public void tel(int startX,int startY,int endX,int endY)
	{
		if(x==startX && y ==startY)
		{
			x=endX;
			y=endY;
			System.out.print((char)7);
		}
	}
	public void moveUp()
	{
		if(mazeBoard[(x+7)/10][(y-3)/10]!=35)
		{
			if(((redBox&&!redBox2)||(redBox2&&!redBox))&&(mazeBoard[(x+7)/10][(y-3)/10]==99||mazeBoard[(x+7)/10][(y-3)/10]==100||mazeBoard[(x+7)/10][(y-3)/10]==101||mazeBoard[(x+7)/10][(y-3)/10]==102))
				go=false;
			if(((greenBox&&!greenBox2)||(greenBox2&&!greenBox))&&(mazeBoard[(x+7)/10][(y-3)/10]==97||mazeBoard[(x+7)/10][(y-3)/10]==98||mazeBoard[(x+7)/10][(y-3)/10]==101||mazeBoard[(x+7)/10][(y-3)/10]==102))
				go=false;
			if(((blueBox&&!blueBox2)||(blueBox2&&!blueBox))&&(mazeBoard[(x+7)/10][(y-3)/10]==97||mazeBoard[(x+7)/10][(y-3)/10]==98||mazeBoard[(x+7)/10][(y-3)/10]==100||mazeBoard[(x+7)/10][(y-3)/10]==99))
				go=false;
			if(go)
			{
				y-=10;
				moves++;
				mV=true;
				mH=false;
			}
		}
	}
	public void moveDown()
	{
		if(mazeBoard[(x+7)/10][y/10+1]!=35)
		{
			if(((redBox&&!redBox2)||(redBox2&&!redBox))&&(mazeBoard[(x+7)/10][y/10+1]==99||mazeBoard[(x+7)/10][y/10+1]==100||mazeBoard[(x+7)/10][y/10+1]==101||mazeBoard[(x+7)/10][y/10+1]==102))
				go=false;
			if(((greenBox&&!greenBox2)||(greenBox2&&!greenBox))&&(mazeBoard[(x+7)/10][y/10+1]==97||mazeBoard[(x+7)/10][y/10+1]==98||mazeBoard[(x+7)/10][y/10+1]==101||mazeBoard[(x+7)/10][y/10+1]==102))
				go=false;
			if(((blueBox&&!blueBox2)||(blueBox2&&!blueBox))&&(mazeBoard[(x+7)/10][y/10+1]==97||mazeBoard[(x+7)/10][y/10+1]==98||mazeBoard[(x+7)/10][y/10+1]==100||mazeBoard[(x+7)/10][y/10+1]==99))
				go=false;
			if(go)
			{
				y+=10;
				moves++;
				mV=true;
				mH=false;;
			}
		}
	}
	public void moveRight()
	{
		if(mazeBoard[x/10+1][(y+7)/10]!=35 && x<730)
		{
			if(((redBox&&!redBox2)||(redBox2&&!redBox))&&(mazeBoard[x/10+1][(y+7)/10]==99||mazeBoard[x/10+1][(y+7)/10]==100||mazeBoard[x/10+1][(y+7)/10]==101||mazeBoard[x/10+1][(y+7)/10]==102))
				go=false;
			if(((greenBox&&!greenBox2)||(greenBox2&&!greenBox))&&(mazeBoard[x/10+1][(y+7)/10]==97||mazeBoard[x/10+1][(y+7)/10]==98||mazeBoard[x/10+1][(y+7)/10]==101||mazeBoard[x/10+1][(y+7)/10]==102))
				go=false;
			if(((blueBox&&!blueBox2)||(blueBox2&&!blueBox))&&(mazeBoard[x/10+1][(y+7)/10]==97||mazeBoard[x/10+1][(y+7)/10]==98||mazeBoard[x/10+1][(y+7)/10]==100||mazeBoard[x/10+1][(y+7)/10]==99))
				go=false;
			if(go)
			{
				x+=10;
				moves++;
				mH=true;
				mV=false;
			}
		}
	}
	public void moveLeft()
	{
		if(mazeBoard[(x-3)/10][(y+7)/10]!=35 && x>0)
		{
			if(((redBox&&!redBox2)||(redBox2&&!redBox))&&(mazeBoard[(x-3)/10][(y+7)/10]==99||mazeBoard[(x-3)/10][(y+7)/10]==100||mazeBoard[(x-3)/10][(y+7)/10]==101||mazeBoard[(x-3)/10][(y+7)/10]==102))
				go=false;
			if(((greenBox&&!greenBox2)||(greenBox2&&!greenBox))&&(mazeBoard[(x-3)/10][(y+7)/10]==97||mazeBoard[(x-3)/10][(y+7)/10]==98||mazeBoard[(x-3)/10][(y+7)/10]==101||mazeBoard[(x-3)/10][(y+7)/10]==102))
				go=false;
			if(((blueBox&&!blueBox2)||(blueBox2&&!blueBox))&&(mazeBoard[(x-3)/10][(y+7)/10]==97||mazeBoard[(x-3)/10][(y+7)/10]==98||mazeBoard[(x-3)/10][(y+7)/10]==100||mazeBoard[(x-3)/10][(y+7)/10]==99))
				go=false;
			if(go)
			{
				x-=10;
				moves++;
				mH=true;
				mV=false;
			}
		}
	}
	public static void main(String args[])
	{
		MazeProgramSmall app = new MazeProgramSmall();
	}
}