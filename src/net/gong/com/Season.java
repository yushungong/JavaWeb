package net.gong.com;

public enum Season {
	AUTUMN("autumn","秋高气爽"),
	SPRING("winter","白雪皑皑");
	
	private final String seasonName;
	private final String seasonDesc;
	private Season(String seasonName,String seasonDesc){
		this.seasonName = seasonName;
		this.seasonDesc = seasonDesc;
	}
	public String getSeasonName(){
		return seasonName;
	}
	public String getSeasonDesc(){
		return seasonDesc;
	}
}
