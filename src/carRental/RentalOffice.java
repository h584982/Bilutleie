	package carRental;

	import java.util.Arrays;
	import java.util.List;
	import java.util.Scanner;
	import java.util.stream.Collectors;

public class RentalOffice {

	
		public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			
			List<Car> biler = Arrays.asList(
				 new Car("EV99999", "Tesla", "Model S" , "Rød" , 'B', true),
				 new Car("EV99998", "Tesla", "Model X" , "Hvit" , 'A', true)
				 );
			
			//public void leieutSetup ()
			String regex = "^[A-ZÆØÅa-zæøå ]+$";
			System.out.println("Velkommen til bilutleie. Skriv inn hvilket bilmerke du vil leie:");
			
			//sjekke alle bilmerker og skriver de ut
			System.out.println(biler.stream()  
					.map(Car::getMerke)
					.distinct()
					.collect(Collectors.joining("\n")));
			
			String bilmerke=scanner.nextLine();
			
			
	//		while(!biler.stream()  
	//				.map(Bil::getMerke)
	//				.distinct()
	//				.collect(Collectors.joining("\n")).contains(bilmerke)){
	//			System.out.println("Skriv inn et bilmerke som er tilgjengelig.");
	//			 bilmerke=scanner.nextLine();
	//			}
			
			System.out.println("Skriv inn hvilken bilmodell du vil leie:");
			
			//sjekke alle modeller til bilmerket og skrive de ut
			System.out.println(biler.stream()
					.filter(a->a.getMerke().equals(bilmerke))   
					.map(Car::getModell)
					.collect(Collectors.joining("\n")));
			String bilmodell=scanner.nextLine();
			
		
			System.out.println("Skriv inn hvilken farge på bilen du vil leie:");
			
			//sjekke alle farger til valgt modell
			System.out.println(biler.stream()
					.filter(a->a.getMerke().equals(bilmerke) && a.getModell().equals(bilmodell))   
					.map(Car::getFarge)
					.collect(Collectors.joining("\n")));
			String bilfarge=scanner.nextLine();
			
			
			System.out.println("Leier ut bil ");
			

		}

	}
	
