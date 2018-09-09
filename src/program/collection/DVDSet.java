package program.collection;

import java.io.Serializable;

public class DVDSet implements Serializable{
	private static final long serialVersionUID = 1L;

	public String name="";// DVD名称
	public int state=1;// DVD借出状态：0已借出/1可借
	public String date="";// DVD借出日期
	public int num=0;// 借出次数，用于排行榜

	public DVDSet() {
		
	}

	public DVDSet(String name) {
		this.name=name;
	}

	public DVDSet(String name, int state) {
		this.name=name;
		this.state=state;
	}

	public DVDSet(String name, int state, String date) {
		this.name=name;
		this.state=state;
		this.date=date;
	}
	
	public DVDSet(String name, int state, String date,int num) {
		this.name=name;
		this.state=state;
		this.date=date;
		this.num=num;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name+this.date+this.num+this.state;
	}
}
