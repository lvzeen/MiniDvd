package program.collection;

import java.util.Comparator;

public class DVDSetComparator implements Comparator<DVDSet>{

	@Override
	public int compare(DVDSet o1, DVDSet o2) {
		// TODO Auto-generated method stub
		if (o1.num > o2.num) {
			return -1;
		} else if (o1.num== o2.num) {
			return 0;
		} else {
			return 1;
		}
	}

}
