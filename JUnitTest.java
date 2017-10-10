package WebDriver;
import Auxiliares.*;

//import static org.junit.Assert.*;
//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class JUnitTest {
	private JavascriptExecutor j;
	private WebDriver driver;
	
	private int teste = 10; //número de planos a serem testados
	
	@Before // Para acessar o nosso site antes da execucao de cada teste
	public void setUp() { 		
		//determina a url a ser acessada para teste
		String baseUrl = "http://pat2math.unisinos.br/pat2math/login#";
		
		//modifica a localizacao de onde o teste executara o firefox (gecko)
		System.setProperty("webdriver.firefox.driver", "c://geckodriver.exe");	
		driver = new FirefoxDriver(); //inicia o driver	
		driver.manage().window().maximize(); //Maiximizar a pagina
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
		
		j = (JavascriptExecutor)driver;
	}
	
	@Test //início dos testes 
	public void findElements()
	{
		String user = "@teste10";
		String password = "aluno";
		
		WebElement email = driver.findElement(By.name("j_username"));
		email.sendKeys(user);
		
		WebElement senha = driver.findElement(By.name("j_password"));
		senha.sendKeys(password);
		
		WebElement login = driver.findElement(By.cssSelector("input[type='submit'"));
		login.click();
		
		try {
			
			for(int i = 1; i <= teste; i++){ //passa por todos os planos
				
					Thread.sleep(1000);		
					j.executeScript("loadTasks("+i+")");
					
					for(int k = 0; k < 6; k++){									
						Thread.sleep(2000);
						j.executeScript("loadExercise("+i+"0"+k+")");
																  
						WebElement send = driver.findElement(By.id("button"));						
						WebElement input = driver.findElement(By.id("inputMobile"));					
						input.sendKeys();	
						send.click();
															
						Thread.sleep(3000);
			
				
						//Thread.sleep(4000);
						//j.executeScript("loadExercise("+i+"0"+k+")");					
						//j.executeScript("getResolutionEquation("+(k+1)+")");
						
						//Thread.sleep(5000);
						//System.out.println(verify(leArquivo("C:/Users/Joice/Desktop/plano1.csv"), k));
						//System.out.println(leArquivo("C:/Users/Joice/Desktop/plano1.csv"));
						//System.out.println();
					
				
			
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(NoSuchElementException e){
			e.printStackTrace();
		}catch(UnderflowException e){
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<StaticQueue<Resolucao>> readFile(String filename){
		String line;
		ArrayList<StaticQueue<Resolucao>> a = new ArrayList<>();
		StaticQueue<Resolucao> e0 = new StaticQueue<>(100);
		StaticQueue<Resolucao> e1 = new StaticQueue<>(100);
		StaticQueue<Resolucao> e2 = new StaticQueue<>(100);
		StaticQueue<Resolucao> e3 = new StaticQueue<>(100);
		StaticQueue<Resolucao> e4 = new StaticQueue<>(100);
		
		try{
			FileReader fr = new FileReader(filename);
			BufferedReader in = new BufferedReader(fr);
			
			in.readLine();
			while((line = in.readLine()) != null){
				String[] l = line.split(";");
				Resolucao b = new Resolucao(l[0], l[1], l[2], l[3], l[4],l[5]);
				String x = b.getIdEq();	
				int x1 = x.length()-1;
				
				switch(x.charAt(x1)){
					case '0':
						e0.enqueue(b);
					case '1':
						e1.enqueue(b);
					case '2':
						e2.enqueue(b);
					case '3':
						e3.enqueue(b);
					case '4':
						e4.enqueue(b);					
				}	
			}
			
			a.add(e0); a.add(e1); a.add(e2); a.add(e3); a.add(e4); 
			
			in.close();
			return a;
			
		}catch(IOException e){
			System.out.println(e.toString());
						
		}catch(OverflowException e){
			System.out.println(e.toString());
			
		}
		return a;
		
	}
	
	public String verify(ArrayList<StaticQueue<Resolucao>> x, int d){
		
		try {
			Thread.sleep(6000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement line = driver.findElement(By.id("line3"));		
		String[] line1 = line.getText().split("\n");
		String line2 = "";			
		for(int k = 0; k < line1.length; k++)
			line2 += line1[k];	
		
		//WebElement element = driver.findElement(By.id("currentEquation"));
		//String[] str = element.getText().split("\n");
		//String s = "";		
		//for(int i = 0; i < str.length; i++)
		//	s += str[i];		
	
		
			for(int q = 0; q < x.get(d).numElements(); q++){				
				while(!x.get(d).isEmpty()){
					int i = 1;
					String res = x.get(d).dequeue().getRes();
					if((res.equals(line2))){
						return "true: na " + i + " tentativa\n";
					}
					else 
						i++;
				}	
				
			}
		
		return "ready";
	
	}
	/*@After //apos todos testes terminarem
	public void quit(){
		driver.quit();
		}*/
	}