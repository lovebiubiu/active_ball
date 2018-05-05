package 操作系统小学期设计;

import java.applet.Applet;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.*;
import java.nio.DoubleBuffer;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class start extends JPanel{
	int ballnum;
	int h1,w1;
	int h2,w2;
	int x1,y1;
	int x2,y2;
	int size;
	JButton jb= new JButton();
	JButton stop = new JButton("暂停");
	JButton conti = new JButton("继续");
	private Random r = new Random(); 
	Ball ball[]= new Ball[6];
	start(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		frame.setSize(800, 600);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLayout(null);
		frame.setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.setLocationRelativeTo(null);
		frame.setTitle("球球大乱碰");
		
		jb.setText("添加一个小球");
		jb.setBounds(50, 50, 120, 60);
		this.add(jb);
		stop.setBounds(200,50,120,60);
		this.add(stop);
		conti.setBounds(350, 50, 120, 60);
		this.add(conti);
		
		ballnum=1;
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==jb){
					System.out.println("添加一个小球");
					if(ballnum<5)
					ballnum++;
					else{
						JOptionPane.showMessageDialog(null, "小球数量不能超过五个");  
					}
					addball(ballnum);
					repaint();
				}
			}
		});
		stop.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==stop){
					System.out.println("线程暂停");
					stop();
				}
			}
		});
		conti.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==conti){
					System.out.println("线程继续");
					conti();
				}
			}
		});
		for(int i=1;i<=ballnum;i++){
			ball[i] = new Ball(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),5,5,r.nextInt(750),r.nextInt(550),25,this,false);
		}
		for(int i=1;i<=ballnum;i++){
			ball[i].start();
		}
	}
	public void stop(){
		for(int i=1;i<=ballnum;i++)
			ball[i].issuspend(true);
	}
	public void conti(){
		for(int i=1;i<=ballnum;i++)
		ball[i].setSuspend(false);
			
		
	}
	public void addball(int ballnum){
		ball[ballnum]=new Ball(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)),5,5,r.nextInt(750),r.nextInt(550),25,this,false);
		ball[ballnum].start();
	}
	public void paint(Graphics g){
		super.paint(g);
		for(int i=1;i<=ballnum;i++){
			g.setColor(ball[i].getColor());
			g.fillOval(ball[i].getW(), ball[i].getH(), ball[i].getR()*2, ball[i].getR()*2);
		}
		impact();
	}
	public void impact(){
		int t;	
		double dis[][]=new double[6][6];
		double X1,X2,Y1,Y2;
		for(int i=1;i<=ballnum;i++){
			for(int j=1;j<=ballnum;j++){
				X1=ball[i].getW()+ball[i].getR();
				X2=ball[j].getW()+ball[j].getR();
				Y1=ball[i].getH()+ball[i].getR();
				Y2=ball[j].getH()+ball[j].getR();
				dis[i][j]=Math.sqrt((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1));
				}
			}
		for(int i=1;i<=ballnum;i++){
			for(int j=i+1;j<=ballnum;j++){
				if(dis[i][j]<=ball[i].getR()+ball[j].getR()){
					t=ball[i].getX();
					ball[i].setX(ball[j].getX());
					ball[j].setX(t);
					t=ball[i].getY();
					ball[i].setY(ball[j].getY());
					ball[j].setY(t);
					}
				}
			}
		}
		
	}
