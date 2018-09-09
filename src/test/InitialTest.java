package test;

import program.DVDMgr;
import program.DVDSet;

public class InitialTest {

	public static void main(String[] args) {
		DVDSet dvd=new DVDSet();
		DVDMgr.initial(dvd);
		System.out.println("name"+"\t"+"state"+"\t"+"date");
		System.out.print(dvd.name[0]+"\t"+dvd.state[0]+"\t"+dvd.date[0]+"\n");
		System.out.print(dvd.name[1]+"\t"+dvd.state[1]+"\t"+dvd.date[1]+"\n");
		System.out.print(dvd.name[2]+"\t"+dvd.state[2]+"\t"+dvd.date[2]+"\n");
	}

}
