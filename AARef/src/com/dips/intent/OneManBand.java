package com.dips.intent;

import java.util.List;

public class OneManBand implements Performer{
	private List<Instrument> list;
	@Override
	public void perform() throws Exception {
		// TODO Auto-generated method stub
		for(Instrument instrument:list){
			instrument.play();
		}
	}
	public List<Instrument> getList() {
		return list;
	}
	public void setList(List<Instrument> list) {
		this.list = list;
	}

}
