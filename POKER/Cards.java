//package hello;
public class Cards {
 private int cards[];
 private int num;
 private String name;

 public Cards(int n, String name) {
	 cards = new int[n];
	 num = 0;
	 this.name = name;
 }

 public void addCard(int card) {
	 cards[num] = card;
	 num++;
 }

 public int getCard(int index) {
	 return cards[index];
 }
 public void changeCard(int index,int x) {
	 cards[index] = x;
 }

 public void printCards(){
	 System.out.print("printCards():");
	 for(int index=0;index<this.num;index++) {
		 System.out.print(getCard(index)+" ");
	 }
 }

 public String getNumber(int index){
	 int a=getCard(index)%13;
	 if(a==10) return "J";
	 if(a==11) return "Q";
	 if(a==12) return "K";
	 else return String.valueOf(a);
 }

 public String getSuitNumber(int index){
	int a=getCard(index)%13;
	int b=(int)getCard(index)/13;
	String Suit="";

	if(b==0) Suit="S";
	else if(b==1) Suit="H";
	else if(b==2) Suit="D";
	else if(b>3) Suit="C";

	if(getCard(index)==0) return "0";
	else if(a==1) return Suit+"A";
	else if(a==11) return Suit+"J";
	else if(a==12) return Suit+"Q";
	else if(a==0) return Suit+"K";
	else return Suit+String.valueOf(a);
 }


 public void printNumbers(){
	 System.out.print("printNumbers():");
	 for(int index=0;index<this.num;index++) {
		 System.out.print(getNumber(index)+" ");
	 }
	 System.out.println();
 }
 public void printSuitsNumbers(){
	 System.out.print("printSuitNumbers():");
	 for(int index=0;index<this.num;index++) {
		 System.out.print(getSuitNumber(index)+" ");
	 }
	 System.out.println();
 }

 public int maxNumber() {
	 int max=0;
	 for(int index=0;index<this.num;index++) {
		 if(getCard(index)%13>max) max=getCard(index);
		 else if(getCard(index)%13==0) max=13;

	 }
	 return max;
 }
public void reverse() {
	int i=0;
	for(int index=0;index<this.num/2;index++) {
		i=getCard(index);
		changeCard(index,this.getCard(num-1-index));
		changeCard(this.num-1-index,i);
	}
}
public int removeFirst() {
	int re=getCard(0);
	for(int index=1;index<this.num;index++) {
		changeCard(index-1,getCard(index));
		if(getCard(index)==0) changeCard(index-1,0);
		if(index==this.num-1) changeCard(index,0);
	 }
	return re;

}
 public static void main(String args[]) {
	 Cards cards = new Cards(5, "手札");
	 cards.addCard(1);
	 cards.addCard(15);
	 cards.addCard(31);
	 cards.addCard(49);
	 cards.addCard(52);

	 cards.printSuitsNumbers();
	 System.out.println("Max: "+cards.maxNumber()+" ");
	 cards.reverse();
	 cards.printSuitsNumbers();
	 System.out.println("Re: "+cards.removeFirst());
	 cards.printSuitsNumbers();
	 System.out.println("Re: "+cards.removeFirst());
	 cards.printSuitsNumbers();

 }
}
