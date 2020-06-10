
public final class Node {
	
	/*Node(double assetPrice,double step,double p,double d){
		this.assetPrice=assetPrice;
		this.step=step;
		
		//System.out.println(assetPrice);
		if(step>0) {
		this.upperChild=new Node (assetPrice*p,step-1,p,d);
		this.lowerChild=new Node(assetPrice*d,step-1,p,d);
		}
		else this.leaf=true;}/*/
		
	
	
	
public double assetPrice ;
public double payOff ;
public double intrValue;
public Node upperChild;
public Node lowerChild;
public double fugitValue;
public int level;
public boolean EEX=false;

Node (){
	this.level=0;
	this.assetPrice=0;
	this.payOff=0;
	this.upperChild=null;
	this.lowerChild=null;
}

Node(int n ,double p){
	this.level=n;
	this.assetPrice=p;
	this.payOff=0;
	this.upperChild=null;
	this.lowerChild=null;
	
	
}
public boolean isLeaf(int n){
	if(level==n)
	return	true;
	else 
	return	false;
}

}
