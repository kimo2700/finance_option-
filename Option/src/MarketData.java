
public  class MarketData {
	
MarketData (double Price, double S, double r,double sigma, double t0){
	this.Price=Price;
	this.S=S;
	this.r=r;
	this.sigma=sigma;
	this.t0=t0;

}
MarketData(){
	Price=0.0;
	S=0.0;
	r=0.0;
    sigma=0.0;
	t0=0.0;
			
}
public double Price;// market price of security
public double S;//stock price 
public double r;//interest rate 
public double sigma;//volatility
public double t0; //current time 
}
