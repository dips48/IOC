package com.dips.intent;

public class Instrumentalist implements Performer{
	public Instrumentalist(){}
	private String song;
	@Override
	public void perform() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("playing  "+song+"  :");
		instrument.play();
	}
	public void setSong(String song){
		this.song=song;
	}
	public String  getSong(){
		return song;
	}

	public String screamSong(){
		return song;
	}
	
	private Instrument instrument;
	
	public void setInstrument(Instrument instrument){
		this.instrument=instrument;
	}
}
