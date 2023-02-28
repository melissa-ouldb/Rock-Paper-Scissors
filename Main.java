package rockpaperscissors;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import static java.util.Collections.rotate;

public class Main {
	static Scanner scanner= new Scanner(System.in) ;
	static String name,input="",read;
	static int score=0;

	static void game(String input){
		Random random= new Random();
		int r= random.nextInt(3);
		String computerIp;

		// assigning to the random values (computer's move)
		if(r==0) {
			computerIp= "rock";
		}
		else if(r==1) {
			computerIp="paper";
		}
		else {
			computerIp="scissors";
		}

		if(input.equals(computerIp)) {
			score=score+50;
			System.out.println("There is a draw ("+input+")");
		}
		else {
			switch (input) {
				case "rock" -> {
					if (computerIp.equals("scissors")) {
						score = score + 100;
						System.out.println("Well done. The computer chose " + computerIp + " and failed");
					} else {
						System.out.println("Sorry, but the computer chose " + computerIp);
					}
				}
				case "paper" -> {
					if (computerIp.equals("scissors")) {
						System.out.println("Sorry, but the computer chose " + computerIp);
					} else {
						score = score + 100;
						System.out.println("Well done. The computer chose " + computerIp + " and failed");
					}
				}
				case "scissors" -> {
					if (computerIp.equals("rock")) {
						System.out.println("Sorry, but the computer chose " + computerIp);
					} else {
						score = score + 100;
						System.out.println("Well done. The computer chose " + computerIp + " and failed");
					}
				}
			}
		}
	}

	static void readRating(String name) throws FileNotFoundException {
		File file= new File("rating.txt");
		Scanner reader= new Scanner(file);

		while(reader.hasNext()){
			read= reader.nextLine();
			if(read.contains(name)){

			}
		}
		reader.close();
	}

	static void importScore(String name) throws FileNotFoundException{
		File file= new File("rating.txt");
		Scanner reader= new Scanner(file);

		while(reader.hasNext()){
			read= reader.nextLine();
			if(read.contains(name)){
				String[]scoreImport=read.split(" ");
				score= Integer.parseInt(scoreImport[1]);
			}
		}
		reader.close();
	}
	public static void enhancedGame(String input, String user)  {

		String[]options= input.split(",");
		ArrayList<String> list= new ArrayList<>();

		for (int i=0;i< options.length;i++){
			list.add(options[i]);
		}

		Collections.reverse(list);
		int ind= list.indexOf(user);
		int position= options.length-ind;
		Collections.rotate(list,position);

		Random r= new Random();
		int compRandom= r.nextInt(options.length);
		String compEnh= list.get(compRandom);

			if (user.equals(compEnh)){
				score=score+50;
				System.out.println("There is a draw ("+user+")");
			} else if (compRandom <= ((options.length) / 2)) {
				score=score+100;
				System.out.println("Well done. The computer chose " + compEnh + " and failed");
			}
			else if (compRandom > ((options.length) / 2)){
				System.out.println("Sorry, but the computer chose " + compEnh);
			}

	}
	public static void main(String[] args) throws FileNotFoundException  {
		File file= new File("rating.txt");
		System.out.println("Enter your name:");
		name= scanner.nextLine();
		System.out.println("Hello, "+name);

		importScore(name);

		input = scanner.nextLine();

		if (input.isBlank()){
			System.out.println("Okay, let's start");
		  while(!input.equals("!exit")) {
			input = scanner.nextLine();
			if (input.equals("!rating")) {
				System.out.println("Your rating: "+score);
			}else if (!input.equals("rock") && !input.equals("paper") && !input.equals("scissors") && !input.equals("!exit")) {
				System.out.println("Invalid input");
			}
			else{
				game(input);
			}
		}}
		 else{
			 System.out.println("Okay, let's start");
			 String user = scanner.nextLine();;
			 while(!user.equals("!exit")) {
				 if (user.equals("!rating")) {
					 System.out.println("Your rating: "+score);
				 }
				 else if(!input.contains(user)){
					 System.out.println("Invalid input");
				 }
				 else {
					 enhancedGame(input,user);
				 }
				 user = scanner.nextLine();
			 }
		 }
		System.out.println("Bye!");
		file.delete();
		scanner.close();
	}
}

