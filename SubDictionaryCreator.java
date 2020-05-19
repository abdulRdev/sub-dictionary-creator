	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.io.PrintWriter;
	import java.io.FileOutputStream;
	import java.util.Scanner;
	
public class SubDictionaryCreator {

		public static void main(String[] args) throws FileNotFoundException {
			// TODO Auto-generated method stub

			Scanner keyboard = new Scanner (System.in);
			Scanner reader;
			
			System.out.print("Hi, please enter the file path: ");
			
			String file = keyboard.next();			
			
			reader = new Scanner(new FileInputStream(file)); //input stream to read from file and output stream to write to file
			int i =0;
			int j=0;
			
			ArrayList <String> text = new ArrayList <String>(100); //copy file to this list word by word 

			ArrayList <String> words = new ArrayList <String>(100); //take the words in alphabetical order
			
			String a= "";
			
			for (int k= 0; reader.hasNext(); k++)
			{		 
				a= reader.next().toUpperCase();
				
				if (a.contains("0") ||a.contains("1") ||a.contains("2") ||
						a.contains("3") ||a.contains("4") ||a.contains("5") ||
					a.contains("6") ||a.contains("7") ||a.contains("8") ||
						a.contains("9"))
						
					continue;
				
				if (a.equalsIgnoreCase("="))
					continue;
				
				if (a.length()==1)
				{
					if ( a.equalsIgnoreCase("a") || a.equalsIgnoreCase("i"));
					
					else
						continue;
				}
		
				
				//getting rid of any words with commas or periods 
				if (a.contains(".")) 
					a=a.substring(0,a.indexOf("."));
				
				if (a.contains(","))
					a=a.substring(0,a.indexOf(","));
				
				if (a.contains("?"))
					a=a.substring(0,a.indexOf("?"));
				
				if (a.contains("!"))
					a=a.substring(0,a.indexOf("!"));
				
				if (a.contains(":"))
					a=a.substring(0,a.indexOf(":"));
				
				if (a.contains(";"))
					a=a.substring(0,a.indexOf(";"));

				if (a.contains("’"))
					a=a.substring(0,a.indexOf("’"));	
				
				text.add(a);
				
				
				//ensuring no words contain exponents with except for one
				for(int p = 0; p < a.length(); p++)
				{
					char r = a.charAt(p);
					if((int)Character.getType(r)== 11 && !a.equalsIgnoreCase("MC²"))
					{
						text.remove(text.size()-1);
					}
				}
			
				
			}
			
			
			words.add(0, text.get(0)); //making both list have same word at index 0
				
			 
				for( j=0,i=1;i<text.size();i++) //i is for copy list, i does not change until for loop makes it
				{
		
				 if (text.get(i).compareToIgnoreCase(words.get(j))>0)// greater than 0 means first string comes after second
				 { 
					 words.add(j+1,text.get(i));
					 j++;
				 }
			 
				 
				 else  if (text.get(i).compareToIgnoreCase(words.get(j))<0)// less than 0 means first string comes before second
				 {		
					 int q = j;
					 	
					 while (text.get(i).compareToIgnoreCase(words.get(q))<0)
					 { 
						 q--;
					 
					 
					 	if (q<0)
					 	{
						 words.add(0,text.get(i));
						 j++;
						 break;
					 	}
					 
					 }
				words.add(q+1,text.get(i));
				j++;
				 }	
			 }
				//removing duplicates from ordered list
				
				for (int x=0;x<words.size()-1;x++)
				{	while(words.get(x).equalsIgnoreCase(words.get(x+1)))
						words.remove(x+1);
				}
				
				PrintWriter writer;
				
				writer = new PrintWriter(new FileOutputStream("SubDictionaryTest.txt"));
				
				writer.println("The document produced this sub-dictionary, which includes "+words.size()+" entries.");
				
				
			String letter; //first letter of every word that starts with a different letter from previous word 
			int p =0;
			
			for (int x=0;;x++)
			{
				if (p==words.size())
				{
					break;
				}
				letter = words.get(p).substring(0,1);
				
			writer.println("\n"+letter+"\n==");
			
		while (letter.equalsIgnoreCase(words.get(p).substring(0,1)))
			{	writer.println(words.get(p));
				p++;
				
				if (p==words.size())
				{
					break;
				}
				
			}
		
		}
			
			writer.close();		 
				
		System.out.println("Done! A sub-dictionary has been created.");	
			
		}

	}
