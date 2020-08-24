import java.util.*;
import java.math.*;
import static java.lang.Math.*;

public class AqaAsadiNames {
	
	public String getName(String Dad, String Mom, String FirstChild, String Gender) {
		
		if(!gender(FirstChild).equals(Gender)) {
			switch (Gender) {
				case "Boy" :
					return Dad.split(" ")[1] + " " + Dad.split(" ")[0];
				case "Girl" :
					return Mom.split(" ")[1] + " " + Mom.split(" ")[0];
			}
		} else {
			switch (Gender) {
				case "Boy" :
					return Dad.split(" ")[0] + " " + FirstChild.split(" ")[1];
				case "Girl" :
					return Mom.split(" ")[0] + " " + FirstChild.split(" ")[1];
			}
		}
		
		return null;
	}

	/**
	 * false - male
	 * true - female
	 * @param name
	 * @return
	 */
	String gender(String name) {
		Set<Character> vovels = new HashSet<>();
		vovels.add('A');
		vovels.add('E');
		vovels.add('I');
		vovels.add('O');
		vovels.add('U');
		vovels.add('Y');
		
		return  vovels.contains(name.charAt(0)) ? "Girl" : "Boy";
	}
}
