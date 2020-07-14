package com.littlehotel.littleHotelServer.constants;

public enum EnumStates {
	ACT("Australian Capital Teritory"), NSW("New South Wales"), NT("Northern Teritory"), QLD("Queensland"),
	SA("South Australia"), VIC("Victoria"), TAS("Tasmania"), WA("Western Australia"), P1("Pradesh 1"), P2("Pradesh 2");

	private String name;

	EnumStates(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
