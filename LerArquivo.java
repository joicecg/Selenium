package WebDriver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LerArquivo {
	public static void main(String[] args){
		ArrayList<Resolucao> a = new ArrayList<>();		
		String line;
		
		try{
			FileReader fr = new FileReader("C:/Users/Joice/Desktop/plano1.csv");
			BufferedReader in = new BufferedReader(fr);
			
			in.readLine();
			while((line = in.readLine()) != null){
				String[] l = line.split(",");
				Resolucao b = new Resolucao(l[0], l[1], l[2], l[3], l[4],l[5]);
				System.out.println("\n" + b.toString());
				
				a.add(b);	
				
		
				
			}
			
			in.close();
			
			
		}catch(IOException e){
			System.out.println(e.toString());
						
		}	
	}

}
