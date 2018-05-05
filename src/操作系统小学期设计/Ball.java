package 操作系统小学期设计;

import java.awt.Color;
import java.util.Random;

public class Ball extends Thread{
	private Color color;
	private int x;
	private int y;
	private int w;
	private int h;
	private int r;
	private start bf; 
	private boolean suspended=false;
	private String control = "";
	public Ball(Color color,int x,int y,int w,int h,int r,start bf,boolean suspended){
		this.color=color;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.r=r;
		this.bf=bf;
		this.suspended=suspended;
	}

	public void run(){
		super.run();
		while(true){
			synchronized (this) {
                if (suspended) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
			if(w+x>=750){
				x=-x;
			}
			if(w-x<=0){
				x=5;
			}
			if(h+y>=550){
				y=-y;
			}
			if(h+y<=0){
				y=5;
			}
			w+=x;
			h+=y;
			try{
				Thread.sleep(10);
			}catch (InterruptedException e) {  
                e.printStackTrace();  
            }
			bf.repaint();
		}
	}
	public void setSuspend(boolean suspend) { 
        if (!suspend) {  
            synchronized (this) {  
            	this.notifyAll();  
            }  
        }  
        this.suspended = suspend;  
    }  
	public int getW() {  
        return w;  
    }  
  
    public void setW(int w) {  
        this.w = w;  
    }  
  
    public int getH() {  
        return h;  
    }  
  
    public void setH(int h) {  
        this.h = h;  
    }
    public Color getColor(){
    	return color;
    }
    public void setColor(){
    	this.color=color;
    }
    public int getX() {  
        return x;  
    }  
  
    public void setX(int x) {  
        this.x = x;  
    }  
  
    public int getY() {  
        return y;  
    }  
  
    public void setY(int y) {  
        this.y = y;  
    }
    public int getR(){
    	return r;
    }
    public void setR(int r){
    	this.r=r;
    }
    public boolean getsuspend(){
    	return suspended;
    }
    public void issuspend(boolean suspended){
    	this.suspended=suspended;
    }
}
