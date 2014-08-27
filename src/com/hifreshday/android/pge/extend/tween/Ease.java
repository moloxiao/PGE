package com.hifreshday.android.pge.extend.tween;

/**
 * 
 * Tween
 * Linear：无缓动效果；  
 * Quadratic：二次方的缓动（t^2）；[Quad] 
 * Cubic：三次方的缓动（t^3）； 
 * Quartic：四次方的缓动（t^4）； 
 * Quintic：五次方的缓动（t^5）；  
 * Sinusoidal：正弦曲线的缓动（sin(t)）； 
 * Exponential：指数曲线的缓动（2^t）；  
 * Circular：圆形曲线的缓动（sqrt(1-t^2)）； 
 * Elastic：指数衰减的正弦曲线缓动；  
 * Back：超过范围的三次方缓动（(s+1)*t^3 - s*t^2）； 
 * Bounce：指数衰减的反弹缓动。
 * 
 * Ease
 * easeIn：从0开始加速的缓动； 
 * easeOut：减速到0的缓动；  
 * easeInOut：前半段从0开始加速，后半段减速到0的缓动。
 * 
 * 每个方法的p的值是由当前已执行的时间除以总时间得到的.[0,1)
 * 
 * @author molo
 *
 */
public class Ease {

	public static double QuadIn (float p) {
		return Math.pow(p, 2);
	}
	
	public static double QuadOut (float p) {
		return -(Math.pow((p-1), 2) -1); 
	}
	
	public static double QuadInOut(float p){
		if ((p/=0.5) < 1) {
			return 0.5*Math.pow(p,2);
		}else {
			return -0.5 * ((p-=2)*p - 2);
		}
	}
	
	
	public static double CubicIn(float p){
		return Math.pow(p, 3);
	}
	
	public static double CubicOut (float p) {
		return (Math.pow((p-1), 3) +1);
	}
	
	public static double CubicInOut (float p) {
		if ((p/=0.5) < 1) return 0.5*Math.pow(p,3);
		return 0.5 * (Math.pow((p-2),3) + 2);
	}
	public static double QuartIn (float p) {
		return Math.pow(p, 4);
	}
	
	public static double QuartOut (float p) {
		return -(Math.pow((p-1), 4) -1);
	}
	
	public static double QuartInOut (float p) {
		if ((p/=0.5) < 1) return 0.5*Math.pow(p,4);
		return -0.5 * ((p-=2)*Math.pow(p,3) - 2);
	}
	
	public static double QuintIn (float p) {
		return Math.pow(p, 5);
	}
	
	public static double QuintOut (float p) {
		return (Math.pow((p-1), 5) +1);
	}
	
	public static double QuintInOut (float p) {
		if ((p/=0.5) < 1) return 0.5*Math.pow(p,5);
		return 0.5 * (Math.pow((p-2),5) + 2);
	}
	
	public static double SineIn (float p) {
		return -Math.cos(p * (Math.PI/2)) + 1;
	}
	
	public static double SineOut (float p) {
		return Math.sin(p * (Math.PI/2));
	}
	
	public static double SineInOut (float p) {
		return (-.5 * (Math.cos(Math.PI*p) -1));
	}

	public static double ExpoIn (float p) {
		return (p==0) ? 0 : Math.pow(2, 10 * (p - 1));
	}
	
	public static double ExpoOut (float p) {
		return (p==1) ? 1 : -Math.pow(2, -10 * p) + 1;
	}
	
	public static double ExpoInOut (float p) {
		if(p==0) return 0;
		if(p==1) return 1;
		if((p/=0.5) < 1) return 0.5 * Math.pow(2,10 * (p-1));
		return 0.5 * (-Math.pow(2, -10 * --p) + 2);
	}
	public static double CircIn (float p) {
		return -(Math.sqrt(1 - (p*p)) - 1);
	}
	
	public static double CircOut (float p) {
		return Math.sqrt(1 - Math.pow((p-1), 2));
	}
	
	public static double CircInOut (float p) {
		if((p/=0.5) < 1) return -0.5 * (Math.sqrt(1 - p*p) - 1);
		return 0.5 * (Math.sqrt(1 - (p-=2)*p) + 1);
	}
	
	public static double BackIn (float p)  {
		float s = 1.70158f;
		return p*p*((s+1)*p - s);
	}
	public static double BackOut (float p)  {
		float s = 1.70158f;
		return (p-=1)*p*((s+1)*p + s) + 1;
	}
	
	public static double BackInOut (float p)  {
		float s = 1.70158f;
		return ((p/=0.5) < 1) ? 0.5*(p*p*(((s*=(1.525))+1)*p - s)) : 0.5*((p-=2)*p*(((s*=(1.525))+1)*p + s) + 2);
	}	
	
	public static double BounceOut (float p) {
		if ((p) < (1/2.75)) {
			return (7.5625*p*p);
		} else if (p < (2/2.75)) {
			return (7.5625*(p-=(1.5/2.75))*p + .75);
		} else if (p < (2.5/2.75)) {
			return (7.5625*(p-=(2.25/2.75))*p + .9375);
		} else {
			return (7.5625*(p-=(2.625/2.75))*p + .984375);
		}
	}
	
	public static double BouncePast (float p)  {
		if (p < (1/2.75)) {
			return (7.5625*p*p);
		} else if (p < (2/2.75)) {
			return 2 - (7.5625*(p-=(1.5/2.75))*p + .75);
		} else if (p < (2.5/2.75)) {
			return 2 - (7.5625*(p-=(2.25/2.75))*p + .9375);
		} else {
			return 2 - (7.5625*(p-=(2.625/2.75))*p + .984375);
		}
	}
	
	public static double FromTo (float p)  {
		if ((p/=0.5) < 1) return 0.5*Math.pow(p,4);
		return -0.5 * ((p-=2)*Math.pow(p,3) - 2);
	}
	
	public static double From (float p)  {
		return Math.pow(p,4);
	}
	
	public static double To (float p)  {
		return Math.pow(p,0.25);
	}
	
	public static double  Linear (float p)  {
		return p;
	}
	
	public static double Sinusoidal (float p)  {
		return (-Math.cos(p*Math.PI)/2) + 0.5;
	}
	
	public static double Reverse (float p)  {
		return 1 - p;
	}
	
	public static double Wobble (float p)  {
		return (-Math.cos(p*Math.PI*(9*p))/2) + 0.5;
	}
	
	public static double Spring (float p)  {
		return 1 - (Math.cos(p * 4.5 * Math.PI) * Math.exp(-p * 6));
	}
	
	public static double Elastic (float p)  {
		return -1 * Math.pow(4,-8*p) * Math.sin((p*6-1)*(2*Math.PI)/2) + 1;
	}
	
	public static double None (float p) {
		return 0;
	}
	
	public static double Full (float p) {
		return 1;
	}
}
