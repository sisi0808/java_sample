import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;

class Paint4 extends Frame implements MouseListener,MouseMotionListener,ActionListener,KeyListener{
	int x,y;
	int btn;
	int reset;
	int Areset;

	ArrayList<Figure>objList;
	CheckboxGroup cbg,cbg2;
	Checkbox c1,c2,c3,c4,c5,c6,c7,c8;
	Checkbox pink,red,yellow,orange,green,cyan,magenta,blue,gray,white,black;
	Button Bwhite,Bgray,Bblack,Bblue,remove,Aremove,save,load,end;
	int mode=0;
	static int width;
	Figure obj;

	public static void main(String[] args){
		Paint4 f=new Paint4();
		f.setSize(1280,960);
		f.setTitle("Paint Sample");
		f.addWindowListener(new WindowAdapter(){
			@Override public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
		f.setVisible(true);

		if(args.length==1) f.load(args[0]);
	}
	Paint4(){
		objList=new ArrayList<Figure>();
		cbg=new CheckboxGroup();
		c1=new Checkbox("丸",cbg,true);
		c2=new Checkbox("円A",cbg,false);
		c3=new Checkbox("円B",cbg,false);
		c4=new Checkbox("四角A",cbg,false);
		c5=new Checkbox("四角B",cbg,false);
		c6=new Checkbox("線",cbg,false);
		c7=new Checkbox("楕円A",cbg,false);
		c8=new Checkbox("楕円B",cbg,false);
		remove=new Button("消去");
		Aremove=new Button("全消去");
		save=new Button("保存");
		load=new Button("読み込み");
		end=new Button("終了");

		cbg2=new CheckboxGroup();
		pink=new Checkbox("ピンク",cbg2,false);
		red=new Checkbox("赤",cbg2,false);
		yellow=new Checkbox("黄",cbg2,false);
		orange=new Checkbox("橙",cbg2,false);
		green=new Checkbox("緑",cbg2,false);
		cyan=new Checkbox("シアン",cbg2,false);
		magenta=new Checkbox("紫",cbg2,false);
		blue=new Checkbox("青",cbg2,false);
		gray=new Checkbox("灰",cbg2,false);
		white=new Checkbox("白",cbg2,false);
		black=new Checkbox("黒",cbg2,true);
		Bwhite=new Button("背景：白");
		Bgray=new Button("背景：灰色");
		Bblack=new Button("背景：黒");
		Bblue=new Button("背景：青");

		c1.setBounds(1020,50,60,30);
		c2.setBounds(1020,100,60,30);
		c3.setBounds(1020,150,60,30);
		c4.setBounds(1020,200,60,30);
		c5.setBounds(1020,250,60,30);
		c6.setBounds(1020,300,60,30);
		c7.setBounds(1020,350,60,30);
		c8.setBounds(1020,400,60,30);

		remove.setBounds(1020,500,60,30);
		Aremove.setBounds(1020,550,60,30);

		save.setBounds(1020,600,60,30);
		load.setBounds(1020,650,60,30);
		end.setBounds(1020,700,60,30);

		pink.setBounds(50,50,60,30);
		red.setBounds(50,100,60,30);
		yellow.setBounds(50,150,60,30);
		orange.setBounds(50,200,60,30);
		green.setBounds(50,250,60,30);
		cyan.setBounds(50,300,60,30);
		magenta.setBounds(50,350,60,30);
		blue.setBounds(50,400,60,30);
		gray.setBounds(50,450,60,30);
		white.setBounds(50,500,60,30);
		black.setBounds(50,550,60,30);
		Bwhite.setBounds(50,600,60,30);
		Bgray.setBounds(50,650,60,30);
		Bblack.setBounds(50,700,60,30);
		Bblue.setBounds(50,750,60,30);


		setLayout(null);
		add(c1);
		add(c2);
		add(c3);
		add(c4);
		add(c5);
		add(c6);
		add(c7);
		add(c8);
		add(remove);
		add(Aremove);
		add(save);
		add(load);
		add(end);

		add(pink);
		add(red);
		add(yellow);
		add(orange);
		add(green);
		add(cyan);
		add(magenta);
		add(blue);
		add(gray);
		add(white);
		add(black);
		add(Bwhite);
		add(Bgray);
		add(Bblack);
		add(Bblue);

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		Bwhite.addActionListener(this);
		Bgray.addActionListener(this);
		Bblack.addActionListener(this);
		Bblue.addActionListener(this);
		remove.addActionListener(this);
	  Aremove.addActionListener(this);
		save.addActionListener(this);
		load.addActionListener(this);
		end.addActionListener(this);
	}

	public void save(String fname){
	  try{
	    FileOutputStream fos = new FileOutputStream(fname);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(objList);
	    oos.close();
	    fos.close();
	    }
	    catch(IOException e){}
	}
	@SuppressWarnings("unchecked")
	public void load(String fname){
	    try{
	      FileInputStream fis = new FileInputStream(fname);
	      ObjectInputStream ois = new ObjectInputStream(fis);
	      objList=(ArrayList<Figure>)ois.readObject();
	      ois.close();
	      fis.close();
	    }
	    catch(IOException e){}
	    catch(ClassNotFoundException e){}
	    repaint();
	}

	@Override public void paint(Graphics g){
		Figure f;
		Dimension S=getSize();
		width=S.width;
		requestFocusInWindow();

		for(int i=0;i<objList.size();i++){
			f=objList.get(i);
			f.paint(g);
		}
		if(mode>=1) obj.paint(g);
	}

	@Override public void actionPerformed(ActionEvent e){
		if(e.getActionCommand()=="背景：白"){
			setBackground(Color.white);
		}
		if(e.getActionCommand()=="背景：灰色"){
			setBackground(Color.gray);
		}
		if(e.getActionCommand()=="背景：黒"){
			setBackground(Color.black);
		}
		if(e.getActionCommand()=="背景：青"){
			setBackground(Color.blue);
		}
		if(e.getActionCommand()=="消去"){
			if(objList.size()>0) objList.remove(objList.size()-1);
			repaint();
		}
		if(e.getActionCommand()=="全消去"){
				objList.clear();
				repaint();
		}
		if(e.getActionCommand()=="保存"){
		  FileDialog fileDialog=new FileDialog(this);
		  fileDialog.setVisible(true);
		  save(fileDialog.getFile());
		}
		if(e.getActionCommand()=="読み込み"){
		  FileDialog fileDialog=new FileDialog(this);
		  fileDialog.setVisible(true);
		  load(fileDialog.getFile());
		}
		if(e.getActionCommand()=="終了") System.exit(0);
	}

	@Override public void mousePressed(MouseEvent e){
		Checkbox c,d;
		x=e.getX();
		y=e.getY();
		btn = e.getButton();
		c=cbg.getSelectedCheckbox();
		d=cbg2.getSelectedCheckbox();
		obj=null;

		if(d==pink) Figure.i=0;
		else if(d==red) Figure.i=1;
		else if(d==yellow) Figure.i=2;
		else if(d==orange) Figure.i=3;
		else if(d==green) Figure.i=4;
		else if(d==cyan) Figure.i=5;
		else if(d==magenta) Figure.i=6;
		else if(d==blue) Figure.i=7;
		else if(d==gray) Figure.i=8;
		else if(d==white) Figure.i=9;
		else if(d==black) Figure.i=10;

		if(c==c1){
			mode=1;
			obj=new Dot();
		}
		else if(c==c2){
			mode=2;
			obj=new ACircle();
		}
		else if(c==c3){
			mode=2;
			obj=new BCircle();
		}
		else if(c==c4){
			mode=2;
			obj=new ARect();
		}
		else if(c==c5){
			mode=2;
			obj=new BRect();
		}
		else if(c==c6){
			mode=2;
			obj=new Line();
		}
		else if(c==c7){
			mode=2;
			obj=new AEllipse();
		}
		else if(c==c8){
			mode=2;
			obj=new BEllipse();
		}
		if(obj!=null){
			obj.moveto(x,y);
			repaint();
		}
	}

	@Override public void mouseReleased(MouseEvent e){
		x=e.getX();
		y=e.getY();
		btn = e.getButton();
		if(mode==1) obj.moveto(x,y);
		else if(mode==2) obj.setWH(x-obj.x,y-obj.y);
		if(mode>=1){
			objList.add(obj);
			obj=null;
		}
		mode=0;
		repaint();
	}
	@Override public void mouseClicked(MouseEvent e){}
	@Override public void mouseEntered(MouseEvent e){}
	@Override public void mouseExited(MouseEvent e){}
	@Override public void mouseDragged(MouseEvent e){
		x=e.getX();
		y=e.getY();
		if(mode==1) obj.moveto(x,y);
		else if(mode==2) obj.setWH(x-obj.x,y-obj.y);
		repaint();
	}
	@Override public void mouseMoved(MouseEvent e){}
	@Override public void keyPressed(KeyEvent e){
		if(e.getKeyText(e.getKeyCode())=="Up"){    //↑が押されたとき
			if(mode==1) obj.moveto(x,y+10);
			else if(mode==2) obj.setWH(x-obj.x,y-obj.y+10);
    System.out.println();
		  repaint();
    }
	}
	@Override public void keyReleased(KeyEvent e){
	}
	@Override public void keyTyped(KeyEvent e){
		char key = e.getKeyChar();
  if (key ==InputEvent.SHIFT_DOWN_MASK){
    System.out.println("aのキーが押された");
  }
	}
}


class Coord implements Serializable{
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
	int w,h;
	Color[] col={Color.pink,Color.red,Color.yellow,Color.orange,Color.green,Color.cyan,Color.magenta,Color.blue,
		 Color.gray, Color.white,Color.black};
	static int i;

	Color color = col[i];

	Figure(){
	  w=h=0;
	}

	public void paint(Graphics g){}
	public void setWH(int w,int h){
		this.w=w;
		this.h=h;
	}
}
class Dot extends Figure{
	Dot(){
		size=10;
	}
	@Override public void paint(Graphics g){
		g.setColor(color);
		g.fillOval(x-size/2,y-size/2,size,size);
	}
}

class ACircle extends Figure{

	ACircle(){}

	@Override public void paint(Graphics g){
	  int r=(int)Math.sqrt((double)(w*w+h*h));
	  g.setColor(color);
	  g.drawOval(x-r,y-r,r*2,r*2);
	  }
}

class BCircle extends Figure{

	BCircle(){}

	@Override public void paint(Graphics g){
	  int r=(int)Math.sqrt((double)(w*w+h*h));
	  g.setColor(color);
	  g.fillOval(x-r,y-r,r*2,r*2);
	}
}

class ARect extends Figure{

	ARect(){}

	@Override public void paint(Graphics g){
	    g.setColor(color);
	    if(w>0 && h>0) g.drawRect(x,y,w,h);
	    else if(w>0 && h<0) g.drawRect(x,y+h,w,-h);
	    else if(w<0 && h>0) g.drawRect(x+w,y,-w,h);
	    else if(w<0 && h<0) g.drawRect(x+w,y+h,-w,-h);
	}
}

class BRect extends Figure{

	BRect(){}

	@Override public void paint(Graphics g){
	   g.setColor(color);
	   if(w>0 && h>0) g.fillRect(x,y,w,h);
	   else if(w>0 && h<0) g.fillRect(x,y+h,w,-h);
	   else if(w<0 && h>0) g.fillRect(x+w,y,-w,h);
	   else if(w<0 && h<0) g.fillRect(x+w,y+h,-w,-h);
	}
}

class Line extends Figure{
	Line(){}

	@Override public void paint (Graphics g){
		g.setColor(color);
		g.drawLine(x,y,x+w,y+h);
	}
}

class AEllipse extends Figure{

	AEllipse(){}

	@Override public void paint(Graphics g){
	  g.setColor(color);
			if(w>0 && h>0) g.drawOval(x-(int)(w*1.41421356),y-(int)(h*1.41421356),(int)(w*2*1.41421356),(int)(h*2*1.41421356));
			else if(w>0 && h<0) g.drawOval(x-(int)(w*1.41421356),y-(int)(-h*1.41421356),(int)(w*2*1.41421356),(int)(-h*2*1.41421356));
			else if(w<0 && h>0) g.drawOval(x-(int)(-w*1.41421356),y-(int)(h*1.41421356),(int)(-w*2*1.41421356),(int)(h*2*1.41421356));
			else if(w<0 && h<0) g.drawOval(x-(int)(-w*1.41421356),y-(int)(-h*1.41421356),(int)(-w*2*1.41421356),(int)(-h*2*1.41421356));
		  }
}
class BEllipse extends Figure{

	BEllipse(){}

	@Override public void paint(Graphics g){
	  g.setColor(color);
		if(w>0 && h>0) g.fillOval(x-(int)(w*1.41421356),y-(int)(h*1.41421356),(int)(w*2*1.41421356),(int)(h*2*1.41421356));
		else if(w>0 && h<0) g.fillOval(x-(int)(w*1.41421356),y-(int)(-h*1.41421356),(int)(w*2*1.41421356),(int)(-h*2*1.41421356));
		else if(w<0 && h>0) g.fillOval(x-(int)(-w*1.41421356),y-(int)(h*1.41421356),(int)(-w*2*1.41421356),(int)(h*2*1.41421356));
		else if(w<0 && h<0) g.fillOval(x-(int)(-w*1.41421356),y-(int)(-h*1.41421356),(int)(-w*2*1.41421356),(int)(-h*2*1.41421356));
		}
}
