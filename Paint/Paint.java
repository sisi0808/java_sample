import java.awt.*;
import java.awt.event.*;

class Paint extends Frame implements MouseListener, MouseMotionListener{
	int x, y;
//	Circle c;
//	Box c;
	Coord c;
	
	public static void main(String[] args){
		Paint f = new Paint();
		f.setSize(640,480);
		f.setTitle("Paint Sample");
		f.addWindowListener(new WindowAdapter(){
			@Override public void windowClosing(WindowEvent e){
				System.exit(0);
			}});
		f.setVisible(true);
	}
	Paint(){
		c = null;
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	@Override public void paint(Graphics g){
		if(c != null) c.paint(g);
	}
	@Override public void mousePressed(MouseEvent e){
		if(e.getButton()==MouseEvent.BUTTON1){ //左ボタン
			c = new point();
		}
		else if(e.getButton()==MouseEvent.BUTTON3){//右ボタン
			c = new Circle();
		}
		c.moveto(e.getX(),e.getY());
		x = e.getX();
		y = e.getY();
	}
	@Override public void mouseReleased(MouseEvent e){}
	@Override public void mouseClicked(MouseEvent e){}
	@Override public void mouseEntered(MouseEvent e){}
	@Override public void mouseExited(MouseEvent e){}
	@Override public void mouseDragged(MouseEvent e){
		if(c != null){
			c.move(e.getX() - x,e.getY() - y);
			repaint();
		}
		x = e.getX();
		y = e.getY();
	}
	@Override public void mouseMoved(MouseEvent e){}
}

class Coord {
	int x, y;
	Coord(){
		x = y = 0;
	}
	public void move(int dx, int dy){
		x += dx;
		y += dy;
	}
	public void moveto(int x,int y){
		this.x = x;
		this.y = y;
	}
	public void paint(Graphics g){}
}

class Circle extends Coord{
	int color, size;
	Circle(){
		color = 0;
		size = 50;
	}
	@Override public void paint(Graphics g){
		g.drawOval(x - size/2, y - size/2, size, size);
	}
	
	@Override public void move(int dx, int dy){
		x -= dx;
		y -= dy;
	}
}

class point extends Coord{
	int color, size;
	point(){
		color = 0;
		size = 5;
	}
	@Override public void paint(Graphics g){
		g.fillOval(x - size/2, y - size/2, size, size);
	}
}

