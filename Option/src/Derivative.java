import java.lang.Math;
public abstract class Derivative {
	public double T ;
	public double strikePrice;
	MarketData mt=new MarketData();
	public double pv;
	
	public int  n;// number of steps 
    public   double deltaT;// interval for a step
	
	public double u ;// up step size 
	public  double d;// down step size
	public double p;// probability of  up move
	public  double q;// probability of down move 
	
	public boolean american =false; // European  by default
	public boolean put= false; // call by default
	public Node root;

	
	//public double T =3;
	public static void insert(Node nd,Node ndLower,int steps, double p, double d) {// Building the binomial tree
		
		if(steps>=0) {
			if(ndLower==null) {
				nd.lowerChild= new Node();
				nd.lowerChild.level=nd.level+1;
				nd.lowerChild.assetPrice=nd.assetPrice*d;
				nd.upperChild=new Node();
				nd.upperChild.level=nd.level+1;
				nd.upperChild.assetPrice=nd.assetPrice*p;
				insert(nd.lowerChild,nd.lowerChild.lowerChild,steps-1,p,d);
				insert(nd.upperChild,nd.lowerChild.upperChild,steps-1,p,d);
				
			}
			else {
				nd.lowerChild=ndLower;
				nd.upperChild=new Node();
				nd.upperChild.level=nd.level+1;
				nd.upperChild.assetPrice=nd.assetPrice*p;
				insert(nd.upperChild,nd.lowerChild.upperChild,steps-1,p,d);
				
				
			}
		}
	}
	
	public abstract void terminalCondition(Node n);
	public abstract void valuationTest(Node n);
	
	

}
