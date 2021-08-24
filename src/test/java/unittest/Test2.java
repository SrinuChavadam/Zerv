package unittest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test2 {
	public static void main(String[] args) {
	    /*  
        String[] array = {"One", "Two", "Three", "Four", "e"};
        List aa=List.of("One", "Two", "Three", "Four", "e");
        List<String> titlingTitleUIList = new ArrayList<String>();
  
       
      
        //Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
        stream1.forEach(x -> System.out.println(x));

        //Stream.of
        Stream<String> stream2 = Stream.of(array);
        aa.forEach(x ->titlingTitleUIList.add("drop"+x));
      System.out.println(titlingTitleUIList);
        */
		String s1="Item title - English-Pf#1";
		
		if(s1.contains("PDF"))
		{
			System.out.println("PDF present");
		}
		else
		{
			System.out.println("PDF not present");
		}
    }



}

