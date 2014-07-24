package com.dips.intent;

public class Juggler implements Performer{
	private int beanBags=3;
	static int i=0;
	public Juggler(){
		i++;
		System.out.println("              "+i);
	}
	public Juggler(int beanBags){
		i++;
		System.out.println("              "+i);
		this.beanBags=beanBags;
	}
	@Override
	public void perform() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("JUGGLING "+beanBags+" BEANBAGS");
	}

}
