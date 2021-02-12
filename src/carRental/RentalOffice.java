	package carRental;

	import java.util.Arrays;
	import java.util.List;
	import java.util.Scanner;
	import java.util.stream.Collectors;

public class RentalOffice {

		public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			
			List<Car> cars = Arrays.asList(
				 new Car("EV99999", "Tesla", "Model S" , "Rød" , 'B', true),
				 new Car("EV99998", "Tesla", "Model X" , "Hvit" , 'A', true)
				 );
			
			//public void leieutSetup ()
			String regex = "^[A-ZÆØÅa-zæøå ]+$";
			System.out.println("Velkommen til bilutleie. Skriv inn hvilket bilmerke du vil leie:");
			
			//sjekke alle bilmerker og skriver de ut
			System.out.println(cars.stream()  
					.map(Car::getBrand)
					.distinct()
					.collect(Collectors.joining("\n")));
			
			String carBrand=scanner.nextLine();
			
			
	//		while(!cars.stream()  
	//				.map(Car::getBrand)
	//				.distinct()
	//				.collect(Collectors.joining("\n")).contains(carBrand)){
	//			System.out.println("Skriv inn et bilmerke som er tilgjengelig.");
	//			 carBrand=scanner.nextLine();
	//			}
			
			System.out.println("Skriv inn hvilken bilmodell du vil leie:");
			
			//sjekke alle modeller til bilmerket og skrive de ut
			System.out.println(cars.stream()
					.filter(a->a.getBrand().equals(carBrand))   
					.map(Car::getModel)
					.collect(Collectors.joining("\n")));
			String carModel=scanner.nextLine();
			
		
			System.out.println("Skriv inn hvilken farge på bilen du vil leie:");
			
			//sjekke alle farger til valgt modell
			System.out.println(cars.stream()
					.filter(a->a.getBrand().equals(carBrand) && a.getModel().equals(carModel))   
					.map(Car::getColor)
					.collect(Collectors.joining("\n")));
			String carColor=scanner.nextLine();
			
			
			System.out.println("Leier ut bil ");
			

		}

	}
	
