import java.util.*;
import java.awt.*;
import java.awt.event.*;

class Paint2 extends Frame implements MouseListener,MouseMotionListener{
	int x,y;
	int count=0;
	int flag=0;
	int btn;
	static int limit;
	static int one;

	ArrayList<Figure>objList;
	Figure obj;
	public static void main(String[] args){
		Paint2 f=new Paint2();
		limit=0;
		if(args.length!=0) limit=Math.abs(Integer.parseInt(args[0]));
		if(limit==1 || limit==0) one=1;
		f.setSize(640,480);
		f.setTitle("Paint Sample");
		f.addWindowListener(new WindowAdapter(){
			@Override public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
		f.setVisible(true);
	}
	Paint2(){
		objList=new ArrayList<Figure>();
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	@Override public void paint(Graphics g){
		Figure f;
		System.out.println(count);
		count++;

		for(int i=0;i<objList.size();i++){
			f=objList.get(i);
			if(i<limit) f.paint(g);
		}
		if(obj!=null) obj.paint(g);
	}
	public void mousePressed(MouseEvent e){
		x=e.getX();
		y=e.getY();
	  btn = e.getButton();
		if(one==1) obj=new One(flag);
		else {
			if (btn == MouseEvent.BUTTON1)  obj=new Circle(flag);
      else obj=new Square(flag);
	  }
		obj.moveto(x,y);
		obj.color();
		objList.add(0,obj);
		repaint();
		if(btn==MouseEvent.BUTTON2) dispose();
	}

	public void mouseReleased(MouseEvent e){
		x=e.getX();
		y=e.getY();
		btn = e.getButton();
		if(one==1) obj=new  One(flag);
		else {
			if (btn == MouseEvent.BUTTON1)  obj=new Circle(flag);
			else obj=new Square(flag);
		}
		flag=(flag+1)%2;
		obj.moveto(x,y);
		obj.color();
		objList.add(0,obj);
		repaint();
	}
	 public void mouseClicked(MouseEvent e){}
	 public void mouseEntered(MouseEvent e){}
	 public void mouseExited(MouseEvent e){}
	 public void mouseDragged(MouseEvent e){
		 x=e.getX();
		 y=e.getY();
		 if(one==1) obj=new One(flag);
		 else {
 			if (btn == MouseEvent.BUTTON1)  obj=new Circle(flag);
 			else  			obj=new Square(flag);
		}
		 obj.moveto(x,y);
		 obj.color();
		 objList.add(0,obj);
		 repaint();
	 }
	public void mouseMoved(MouseEvent e){}
}


class Coord{
	int x,y;
	Coord(){
		x=y=0;
	}
	public void move(int dx,int dy){
		x+=dx;
		y+=dy;
	}
	public void moveto(int x,int y){
		this.x=x;
		this.y=y;
	}
}
class Figure extends Coord{
	int size;
	static int R;
	static int Rflag;
  static int G;
  static int Gflag;
	static int B;
	static int Bflag;

	Figure(){}

	public void color(){
		if(R>=255 || R==0)  Rflag=(Rflag+1)%2;
		if(Rflag==1) R++;
		else R--;

		if(G>=252 || G==0)  Gflag=(Gflag+1)%2;
		if(Gflag==1) G=G+3;
		else G--;

		if(B>=254 || B==0) Bflag=(Bflag+1)%2;
		if(Bflag==1) B=B+2;
		else B--;
	}

	Color color = new Color(R,G,B);
	public void paint(Graphics g){}
}
class Circle extends Figure{

	Circle(){
	  size=10;
	}
	Circle(int flag){
	  if(flag==0) size=10;
	  else size=50;
	}

	@Override public void paint(Graphics g){
	  g.setColor(color);
		g.fillOval(x-size/2,y-size/2,size,size);
	}
}
class Square extends Figure{

	Square(){
	  size=10;
	}
	Square(int flag){
	  if(flag==0) size=10;
	  else size=50;
	}

	@Override public void paint(Graphics g){
	  g.setColor(color);
		g.fillRect(x-size/2,y-size/2,size,size);
	}
}
class  One extends Figure{

	 One(){
	  size=10;
	}
	 One(int flag){
	  if(flag==0) size=10;
	  else size=50;
	}

	@Override public void paint(Graphics g){
	 	g.setColor(Color.black);
		g.drawString("Click!!",x,y+10);
		int xPoints[] = {x,x+size/4,x+size/10,x+size/10,x-size/10,x-size/10,x-size/4};
    int yPoints[] = {y,y-size/2,y-size/2,y-size,y-size,y-size/2,y-size/2};
		g.drawPolygon(xPoints, yPoints, 7);
	}
}
