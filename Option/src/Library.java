
public final class Library {
	static Output binom(final Derivative deriv, final MarketData mkt, int n) {
		
		Output result = new Output();
		
		
		deriv.mt=mkt;
		
		deriv.n=n;
		
		deriv.deltaT=(deriv.T-deriv.mt.t0)/n;
		
		deriv.pv=Math.exp(-deriv.mt.r*deriv.deltaT);
		deriv.u=Math.exp(deriv.mt.sigma*Math.sqrt(deriv.deltaT));
	    deriv.d=1/deriv.u;
		deriv.p=(Math.exp(deriv.mt.r*deriv.deltaT)-deriv.d)/(deriv.u-deriv.d);
		deriv.q= 1-deriv.p;
		deriv.root= new Node(0,deriv.mt.S);
		
		deriv.insert(deriv.root,deriv.root.lowerChild,n,deriv.u,deriv.d);
		
		
		deriv.terminalCondition(deriv.root);
		result.FV=deriv.root.payOff;
		result.fugit= deriv.root.fugitValue;
		return result;
			}
	
	static int impvol(final Derivative deriv ,final MarketData mkt, int n, int max_iter, double tol,Output out  ) {
		
	 
		MarketData mkt_low=new MarketData(mkt.Price,mkt.S,mkt.r,0.01,mkt.t0) ;
		
		
		Output Output_low=binom (deriv,mkt_low,n);
		
		double low_fv= Output_low.FV;
		if (Math.abs(low_fv-out.FV)<=tol) {
			out.impvol=low_fv;
			return 0;
		}
		MarketData mkt_high=new MarketData(mkt.Price,mkt.S,mkt.r,2.0,mkt.t0) ;
		
		Output Output_high=binom (deriv,mkt_high,n);
		double high_fv= Output_high.FV;
		if (Math.abs(high_fv-out.FV)<=tol) {
	
			out.impvol=high_fv;
			return 0;
		}
		final MarketData mkt_=new MarketData(mkt.Price,mkt.S,mkt.r,0.0,mkt.t0);
		for (int num_iter = 1; num_iter < max_iter; ++num_iter) {
			
			mkt_.sigma=(mkt_low.sigma + mkt_high.sigma)/2;
			
			if(Math.abs(binom (deriv,mkt_,n).FV-out.FV)<=tol) {
				out.impvol=mkt_.sigma;
				return 0;}
			
			if((binom (deriv,mkt_,n).FV-out.FV)*(binom (deriv,mkt_low,n).FV-out.FV)>=0.0) 
				
				mkt_low.sigma=(mkt_low.sigma + mkt_high.sigma)/2;
				
			else {
				
				mkt_high.sigma=(mkt_low.sigma + mkt_high.sigma)/2;
			}
			
				
			
		}
		
		
		return 1;
				
	

}
}
