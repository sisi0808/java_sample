import java.util.*;
import java.awt.*;
import java.awt.event.*;

class Paint2 extends Frame implements MouseListener,MouseMotionListener{
	int x,y;
	int count=0; //円の数数え
	int flag=0;	//大きさの交互変化のflag
	int btn;		//押下したボタン
	static int limit; //表示上限の設定
	static int one;  //引数が1の場合に立つflag

	ArrayList<Figure>objList;
	Figure obj;
	public static void main(String[] args){
		Paint2 f=new Paint2();
		limit=0;
		if(args.length!=0) limit=Math.abs(Integer.parseInt(args[0])); //引数を格納
		if(limit==1 ) one=1;		//引数が１の場合にflagを立てる
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
		System.out.println(count);	//コンソール上に描画した図形数を表示
		count++;

		for(int i=0;i<objList.size();i++){
			f=objList.get(i);
			if(i>objList.size()-limit) f.paint(g);	//表示数を引数個までに制限
		}
		if(obj!=null) obj.paint(g);
	}
	public void mousePressed(MouseEvent e){
		x=e.getX();
		y=e.getY();
		btn = e.getButton();
		if(one==1) obj=new One(flag);	  //引数が１の場合の処理
		else {
			if (btn == MouseEvent.BUTTON1)  obj=new Circle(flag); //左ボタンが押されたときの処理
      else obj=new Square(flag);	//それ以外の時の処理
	  }
		obj.moveto(x,y);
		objList.add(obj);
		repaint();
		if(btn==MouseEvent.BUTTON2) dispose();  //中央ボタンが押された時の処理
	}

	public void mouseReleased(MouseEvent e){
		x=e.getX();
		y=e.getY();
		btn = e.getButton();
		if(one==1) obj=new  One(flag);  //引数が１の場合の処理
		else {
			if (btn == MouseEvent.BUTTON1)  obj=new Circle(flag); //左ボタンが押されたときの処理
			else obj=new Square(flag); //それ以外の時の処理
		}
		flag=(flag+1)%2;		//交互にサイズを変えるためのflag
		obj.moveto(x,y);
		objList.add(obj);
		repaint();
	}
	 public void mouseClicked(MouseEvent e){}
	 public void mouseEntered(MouseEvent e){}
	 public void mouseExited(MouseEvent e){}
	 public void mouseDragged(MouseEvent e){
		 x=e.getX();
		 y=e.getY();
		 if(one==1) obj=new One(flag);  //引数が１の場合の処理
		 else {
 			if (btn == MouseEvent.BUTTON1)  obj=new Circle(flag); //左ボタンが押されたときの処理
 			else  			obj=new Square(flag);  //それ以外の時の処理
		}
		 obj.moveto(x,y);
		 objList.add(obj);
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
	static int color;
	static int G;
	static int Gflag;
	static int R;
	static int Rflag;
	static int B;
	static int Bflag;

	Figure(){
			 color=(color+1)%100;

	     if(color%5==0){
				  if(R>=255 || R==0)  Rflag=(Rflag+1)%2;
				 	if(Rflag==1) R++;
					else R--;
			 }
	     else if(color%3==0){
				 	if(G>=255 || G==0)  Gflag=(Gflag+1)%2;
				 	if(Gflag==1) G++;
					else G--;
			 }
	     else if(color%2==0){
				 if(B>=255 || B==0) Bflag=(Bflag+1)%2;
				 if(Bflag==1) B++;
				 else B--;
			 }
	}
	public void paint(Graphics g){}
}
class Circle extends Figure{		//円描画処理

	Circle(){
	  size=10;
	}
	Circle(int flag){
	  if(flag==0) size=10;		//サイズ交互変換の処理
	  else size=50;
	}

	@Override public void paint(Graphics g){
	  g.setColor(new Color(R,G,B));
		g.fillOval(x-size/2,y-size/2,size,size);
	}
}
class Square extends Figure{  //四角形描画処理

	Square(){
	  size=10;
	}
	Square(int flag){
	  if(flag==0) size=10;  //サイズ交互変換の処理
	  else size=50;
	}

	@Override public void paint(Graphics g){
	  g.setColor(new Color(R,G,B));
		g.fillRect(x-size/2,y-size/2,size,size);
	}
}
class  One extends Figure{		//カーソル描画処理

	 One(){
	  size=10;
	}
	 One(int flag){
	  if(flag==0) size=10;		//サイズ交互変換の処理
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
