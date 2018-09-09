package program.collection1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws Exception {
		FileInputStream fis=new FileInputStream("D:\\javase\\miniDVD_collection.txt");
		ObjectInputStream ois=new ObjectInputStream(fis);
		ArrayList<DVDSet> list =(ArrayList<DVDSet>)ois.readObject();
		ArrayList<DVDSet> list1 =new ArrayList<DVDSet>();
		for(DVDSet x:list){
			list1.add(x);
		}
//		if(obj instanceof ArrayList){
//			list=(ArrayList<DVDSet>) obj;
//		}
		for(DVDSet x:list1){
		System.out.println(x);
		}
		ois.close();
	}

}
