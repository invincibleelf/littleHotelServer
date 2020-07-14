package com.littlehotel.littleHotelServer.constants;

import java.util.Arrays;
import java.util.List;

public enum EnumCountry {

	AUS(EnumStates.ACT, EnumStates.NSW, EnumStates.NT, EnumStates.QLD, EnumStates.SA, EnumStates.TAS, EnumStates.VIC,
			EnumStates.WA),
	NEP(EnumStates.P1, EnumStates.P2);

	private final List<EnumStates> states;

	EnumCountry(EnumStates... enumStates) {
		this.states = Arrays.asList(enumStates);
	}

	public List<EnumStates> getStates() {
		return states;
	}

}
