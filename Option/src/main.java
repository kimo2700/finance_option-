
public class main {
	public static void main(String args[]) {
		
		final double Price = 10;
        final double S = 100;
        final double r = .08;
        final double sigma =0.4180;
        final double t0 = 0;
        final double T = .8;
        final double strike = 95;
        final int n = 4;
        final int max_iter = 100;
        final double tol = .005;
        final boolean put=true;
        final boolean american=true;
        final boolean european=false;
        final boolean call =false;
        final double window_begin=.1;
        final double window_end=.4;
        
        		
		MarketData mk= new MarketData(Price,S,r,sigma,t0);
		//MarketData mk1= new MarketData(Price,S,r,.5,t0);
		VanillaOption vn= new VanillaOption(mk,strike,T,american,call,n);
		
		vn.terminalCondition(vn.root);
		System.out.print("the vanilla option fair value is: ");
		System.out.println(vn.root.payOff);
		System.out.print("the vanilla option fair fugit is: ");
		System.out.println(vn.root.fugitValue);
		BermudanOption br= new BermudanOption(mk,strike,T,window_begin,window_end,put,n);
		br.terminalCondition(br.root);
		System.out.print("the Bermudan option fair value is: ");
		System.out.println(br.root.payOff);
		System.out.print("the Bermudan option fugit value is: ");
		System.out.println(br.root.fugitValue);
		Output out= Library.binom(vn,mk,n);
		System.out.print("binomial fair value for derivative vanilla option is ");
		System.out.println(out.FV);
		System.out.print("binomial fugit value for derivative vanilla option is ");
		System.out.println(out.fugit);
		 Output ou=new Output(20);
		Library.impvol(vn,mk, n, max_iter, tol, ou);
		System.out.println(Library.impvol(vn,mk, n, max_iter, tol, ou));
		System.out.print("imply votality of derivative vanilla option for security price 10  is: ");
	     System.out.print(ou.impvol);  
		     
	
		
		
		
		
		
}
}
