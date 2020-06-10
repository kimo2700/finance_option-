
public class BermudanOption extends Derivative {
	
	
	
	BermudanOption(MarketData m,double strikePrice ,double T,double  window_begin,double window_end, boolean put,int n){
	this.window_begin=	window_begin;
	this.window_end= window_end;
	if (T==0)throw new IllegalArgumentException("T is 0");
	this.T=T;
	this.strikePrice=strikePrice;
	this.mt=m;	
	this.put=put;
	this.n=n;
	this.deltaT=(T-mt.t0)/n;
	this.pv=Math.exp(-mt.r*this.deltaT);
	this.u=Math.exp(mt.sigma*Math.sqrt(deltaT));
	this.d=1/u;
	this.p=(Math.exp(mt.r*this.deltaT)-d)/(u-d);
	this.q= 1-p;

	
	
	
	
	this.root= new Node(0,mt.S);
	insert(root,root.lowerChild,n,u,d);
	}
	public double window_begin;
	public double window_end;

	@Override
	public void terminalCondition(Node nd) {
		// TODO Auto-generated method stub
		if(put) {// put option
			if (nd.isLeaf(n)) {
				if(nd.assetPrice>strikePrice)
					nd.payOff=0;
				
				else 
				nd.payOff=strikePrice-nd.assetPrice;
				nd.fugitValue=T;
				//System.out.println(nd.payOff);
				return;
			  }
			terminalCondition(nd.upperChild);
			
			terminalCondition(nd.lowerChild);
			nd.fugitValue= (nd.upperChild.fugitValue*p)+(nd.lowerChild.fugitValue*q);
			nd.payOff=pv*((nd.upperChild.payOff*p) + (nd.lowerChild.payOff*q));
		
				valuationTest(nd);
				
			
			//System.out.println(nd.payOff);
	    }
		else{// call option*/
				if (nd.isLeaf(n)) {
					if(nd.assetPrice<strikePrice)
						nd.payOff=0;
					else 
					nd.payOff=nd.assetPrice-strikePrice;;
					nd.fugitValue=T;
				//System.out.println(nd.payOff);
					return;
				  }
				
			
				terminalCondition(nd.upperChild);
				
				terminalCondition(nd.lowerChild);
				nd.fugitValue= (nd.upperChild.fugitValue*p)+(nd.lowerChild.fugitValue*q);
				nd.payOff=pv*((nd.upperChild.payOff*p) + (nd.lowerChild.payOff*q));
		
					valuationTest(nd);
					
				}
		
	}

	@Override
	public void valuationTest(Node nd) {
		// TODO Auto-generated method stub
		if(nd.isLeaf(n)) {
			nd.intrValue=nd.payOff;
			nd.fugitValue=T;
		   
		}
		else {
			if(put)
			nd.intrValue=strikePrice-nd.assetPrice;
			else nd.intrValue=nd.assetPrice-strikePrice;
			if(nd.intrValue>nd.payOff && window_begin<=deltaT*nd.level && deltaT*nd.level<=window_end ) {
				nd.EEX=true;
				nd.payOff=nd.intrValue;
				nd.fugitValue=deltaT*nd.level;
			}
		
	}
	

}
}
