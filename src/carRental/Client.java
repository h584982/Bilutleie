package carRental;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Client { //main method
    static Random random = new Random();
    final static int maxReservationLength = 10;


    public static void main(String[] args) {


        CarRental carRental = setupCarRental();
        System.out.println("Car rental finished");
        Boolean loggedIn = true;

        while(loggedIn){
            Scanner input = new Scanner(System.in);
            System.out.println("velg 1:\nReservasjon\n 2: \nPlukk opp bil\n 3:\nLever inn og betal \n");
            int input_choice = input.nextInt();
            switch (input_choice){
                case 1:

                    System.out.println("Er du registrert? false for nei, true for ja");
                    boolean registered = input.nextBoolean();
                    if (registered) {
                        boolean found = false; //test if written customerID is valid
                        while(!found) {
                            System.out.println("Skriv inn ditt kunde nummer (index nummer):");
                            int customerIndexNumber = input.nextInt();
                            Customer customer = carRental.getCustomers().get(customerIndexNumber);
                            if (!(customer == null))
                                found = true;
                        }
                    } else {
                        System.out.println("Skriv inn ditt fornavn:");
                        String firstname = input.next();
                        System.out.println("Skriv inn ditt etternavn:");
                        String lastname = input.next();

                        System.out.println("Skriv inn din gate:");
                        String street = input.next();
                        System.out.println("Skriv inn ditt gatenummer");
                        int zipcode = input.nextInt();
                        System.out.println("Skriv inn din by");
                        String city = input.next();
                        Address address = new Address(street, zipcode, city);

                        System.out.println("Skriv inn ditt telefonnummer:");
                        int phoneNumber = input.nextInt();


                        carRental.createCustomer(firstname, lastname, address, phoneNumber);
                    }





                    System.out.println("Hvilken dag henter du ut bilen?"); //how many days into the future
                    LocalDateTime pickUpDate = LocalDateTime.now().plusDays(input.nextInt());


                    System.out.println("Hvor lenge skal du låne bilen?"); //days from pickUpDate to deliveryDate
                    LocalDateTime deliveryDate = pickUpDate.plusDays(input.nextInt());


                    System.out.println("Velg en by"); //search for all offices in city
                    String location = input.next();
                    ArrayList<RentalOffice> offices = carRental.findOffices(location);

                    ArrayList<Car> availableCarsForOffice = new ArrayList<>();
                    System.out.println(offices.size());
                    offices.forEach(office -> System.out.println( office.getAddress().getCity()));
//                    for(int i = 0 ; i < offices.size() ; i++)
                    for(int index = 0 ; index < offices.size();index++ ){
                        RentalOffice officeObject = offices.get(index);
                        availableCarsForOffice = carRental.searchQuery(officeObject, pickUpDate, deliveryDate);

                        System.out.println("office cars:" + availableCarsForOffice.size());
                        for(int i = 0; i < availableCarsForOffice.size() ;i++){
                            Car car = availableCarsForOffice.get(i);
                            System.out.println(
                                    "Office: " + index + ". Car number: " + i
                                    + " Car info:" +  car.toString()
                                    + " Pris: " + officeObject.getPriceMap()
                            );
                            //TODO: legg til pris (pricemap)


                        }
                    }
                    System.out.println("Velg kontor som inneholder bilen du ønsker:");
                    int chosenOffice = input.nextInt();

                    System.out.println("Velg bil i det valgte kontoret:");
                    int chosenCar = input.nextInt();

                    Car car = offices.get(chosenOffice).getCarPark().get(chosenCar);


//                   carRental.makeReservation( carRental, car, customer, pickUpDate, deliveryDate );

                    //dersom null blei ikkje reservasjonen gjort, ellers returnerer reservasjonsID


                    break;
                case 2:
                        pickUpSession(carRental);

                    break;
                case 3:
                        dropOffSession(carRental);

                    break;
            }
            loggedIn = false;
            input.close();
        }



        // TODO: Menu system

        // - search via internet , with existing costumer

        // - rental office clerk , no customer

        // pickup car event - prompt user for location and reservation id

        // drop off event -

        // helpmethods
        // createAddress - customer

        LocalDateTime now = LocalDateTime.now().plusDays(random.nextInt(364)).plusHours(random.nextInt(24));
        System.out.println(now.toString());
        LocalDateTime future = now.plusDays(random.nextInt(maxReservationLength)).plusHours(random.nextInt(24));
        System.out.println(future);


    }


    private static void pickUpSession(CarRental carRental){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Enter ReservationID: ");
            int reservationID = input.nextInt();
            boolean hasCreditCard = carRental.pickUpCar(reservationID);
            if (hasCreditCard){
                System.out.println("Car pick up complete");

                break;
            }
            System.out.println("No creditcard information found");
            System.out.println("Please enter credit information, or 'c' for cancle");
            String creditcard = input.next();
            if (creditcard.equals("c")){
                System.out.println("pickup failed");
                break;
            }
           carRental.getReservationsMap().get(reservationID).getCustomer().setCardNumber(Long.parseLong(creditcard));
        }




    }

    public static void dropOffSession(CarRental carRental){

        while( true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter reservationID");

            int reservationID = input.nextInt();
            Integer officeID = null;
            LocalDateTime date = null;

            System.out.println("Is car delivered to new location y/n?:");

            String answer = input.next();
            if (answer.equals("y")){
                System.out.println("Enter location id");
                officeID = input.nextInt();
            }
            else if (!answer.equals("n")){
                System.out.println("invalid answer! Aborting");
                return;
            }


            System.out.println("Enter date");
            date = parseDateTime(input.next());
            Integer price = null;
            if (date==null && officeID==null){
                // same office same date
                price = carRental.dropOffCar(reservationID);

            }else  if(date==null){
                // new office same date
                price = carRental.dropOffCar(officeID, reservationID);

            } else if (officeID==null){
                // same office new date
                price = carRental.dropOffCar(reservationID, date);
            }   else{
                // new everything
                price = carRental.dropOffCar(officeID, reservationID, date);
            }
            System.out.println("Car drop off success. Amount due:"+ price);


        }

    }

    public static LocalDateTime parseDateTime(String input){

       return LocalDate.parse(input).atTime(14,30);
    }

    public static CarRental setupCarRental() {
        populateCarMap();
        CarRental carRental = new CarRental("Joe's Garage", 23502350, new ArrayList<Customer>(), new ArrayList<RentalOffice>(), randomAddress());

        // Populate customers

        int  customerAmount = random.nextInt(10000)+100;
        for(int i = 0; i < customerAmount; i++){
           carRental.getCustomers().add(randomCustomer());
       }

        // Populate Rental Offices
        for(int i = 0 ; i  < random.nextInt(60) + 10; i++){
            carRental.getOffices().add(randomOffice(i));
        }
        // Populate Reservations
        for(RentalOffice officeObject : carRental.getOffices()){
            int amountOfReservations = random.nextInt(40)+10;
            for(int i = 0 ; i < amountOfReservations ; i++) {

                Car car = null;
                Customer customer = null;
                LocalDateTime pickupDate;
                LocalDateTime deliveryDate = null;


                Reservation reservation = null;

                int reservationID = carRental.giveNextReservationID();
                do {
                    // Get random car from carPark
                    car = officeObject.getCarPark().get(random.nextInt(officeObject.getCarPark().size()));
                    // get random customer
                    customer = carRental.getCustomers().get(random.nextInt(carRental.getCustomers().size()));

                    // get random startDate
                    pickupDate = LocalDateTime.now().plusDays(random.nextInt(364) + 1);

/*
                    int year = random.nextInt(2) + 2021;
                    int month = random.nextInt(11)+ 1;
                    int date = random.nextInt(27) + 1;
                    int hour = random.nextInt(23);
                    int min = random.nextInt(59);
*/


                    // get random endDate
                    deliveryDate = pickupDate.plusDays(random.nextInt(maxReservationLength));

/*
                    int returnYear = random.nextInt(1) + year;

                    int returnMonth;
                    // if-statement if deliveryDate is equal to the same year as pickupDate
                    if (returnYear == year) {
                        returnMonth = random.nextInt(12 - month) + month;
                    } else {
                        returnMonth = random.nextInt(11)+ 1;
                    }

                    int returnDate;
                    // if-statement if deliveryDate is equal to the same year and month as pickupDate
                    if (returnYear == year && returnMonth == month) {
                        returnDate = random.nextInt(28 - date) + date;
                    } else {
                        returnDate = random.nextInt(27) + 1;
                    }

                    int returnHour;
                    // if-statement if deliveryDate is equal to the same year, month and day as pickupDate
                    if (returnYear == year && returnMonth == month && returnDate == date) {
                        returnHour = random.nextInt(23 - hour) + hour;
                    } else {
                        returnHour = random.nextInt(23);
                    }

                    int returnMin;
                    // if-statement if return date is equal to the same year, month, day and hour as pickupDate
                    // and probably all stars align
                    if (returnYear == year && returnMonth == month && returnDate == date && returnHour == hour) {
                        returnMin = random.nextInt(59 - min) + min;
                    } else {
                        returnMin = random.nextInt(59);
                    }
*/

                   reservation = officeObject.createReservation(reservationID, car, customer, pickupDate, deliveryDate);

                } while(reservation == null);
                carRental.getReservationsMap().put(reservation.getReservationId(), reservation);
            }


        }
        return carRental;
    }


    public static RentalOffice randomOffice(int officeID){

        Address address = randomAddress();

        int phone = random.nextInt(90000000)+10000000;


        ArrayList<Car> carPark = new ArrayList<>();
        int amountOfCars = random.nextInt(140)+10;
        for( int i = 0 ; i < amountOfCars ; i++) {
            carPark.add(randomCar());
        }


        ArrayList<Reservation> reservations = new ArrayList<Reservation>();
        ArrayList<Reservation> reservationArchive = new ArrayList<Reservation>();

        HashMap<Character, Integer> priceMap = new HashMap<Character,Integer>();
        priceMap.put('A',  random.nextInt(1000)+500);
        priceMap.put('B', random.nextInt(1000)+1000);
        priceMap.put('C', random.nextInt(1000)+1500);
        priceMap.put('D', random.nextInt(1000)+2000);

        return new RentalOffice(officeID, address, phone, carPark, reservations, reservationArchive, priceMap);
    }


    private static List<String> streets = new ArrayList<String>((
            List.of("Kvalvåglia ", "Nord-Villen ", "Meieriveien ", "Brandts gate ", "Rimfakses vei ", "Klokkersmauet ", "Gislebakken ", "Kongens gate ", "Carsten E. Rosenvinges veg ", "Herføllbakken ", "Furras gate ", "Skoglien ", "Hjortefaret ", " Munkegaten ", "Olav Kyrres gate  ")));

    private static List<String> cities = new ArrayList<String>((
            List.of("Bergen", "Arna", "Sotra", "Førde", "Ålesund", "Stord", "Oslo", "Stavanger", "Sundal", "Jondal", "MO I RANA", "HARSTAD", "KONGSVINGER", "MOSS", "RISØR", "ARENDAL", "TRONDHEIM", "BODØ", "TROMS", "KAUTOKEINO")));

    public static Address randomAddress(){
        String street = streets.get(random.nextInt(streets.size())) + (random.nextInt(150)+1);
        Integer zipCode = random.nextInt(10000);
        String city = cities.get(random.nextInt(cities.size()));

        return new Address(street, zipCode, city);
    }



    private static List<String> firstnames = new ArrayList<String>((
            List.of("Mia", "Jens", "Geir", "Henrik", "Ferdinand", "Storm", "Iver", "Karoline", "Mikkel", "Julie", "Eirik", "Louise", "Linnea", "Nora", "Lars", "Finn", "Are", "Andrea", "Karl", "Pernille")));
    private static List<String> surnames = new ArrayList<String>((
            List.of("Ludvigsen", "Isaksen", "Geir", "Teigen", "Leren", "Moen", "Midtbø", "Jensen", "Klokkerød", "Holberg", "Bratberg", "Kolstad", "Ulvestad", "Stenberg", "Alvestad", "Humme", "Nesbø", "Solberg", "Furuseth", "Granli")));


    public static Customer randomCustomer(){

        String name = firstnames.get(random.nextInt(firstnames.size()));
        String surname = surnames.get(random.nextInt(surnames.size()));
        Integer phone = random.nextInt(90000000)+10000000;
        Long creditCard = null; // Can be null
        Address address = randomAddress();
        if (random.nextBoolean()) {
            if (random.nextBoolean()) {
                creditCard = (random.nextLong() % 100000000000000L) + 5200000000000000L;
            }
            else{
                creditCard = (random.nextLong() % 100000000000000L) + 4100000000000000L;
            }
            return new Customer(name, surname,address, phone, creditCard);

        } else
        { return  new Customer(name, surname,address,phone);
        }




    }

    private static List<String> colors = new ArrayList<String>((
            List.of("Blue", "Cyan", "Teal", "Beige", "White", "Black", "Silver", "Gold", "Chrome", "Yellow", "Orange", "Grey", "Green", "Purple", "Indigo", "Ivory", "Ruby Red", "Bronze", "Brass", "Vantablack")));

    private static HashMap<String, ArrayList<String>> cars = new HashMap<String, ArrayList<String>>();
    private  static List<String> carKeyMap = null;

    public static void populateCarMap(){
      ArrayList<String> fords = new ArrayList<String>((
                List.of("Ranger", "Transit", "Everest", "Ranger Raptor", "Mustang", "Focus ST", "Fiesta ST", "EcoSport", "GT")));

        cars.put("Ford",fords);
        ArrayList<String> hondas = new ArrayList<String>((
                List.of("Brio", "Civic", "Accord", "Envix", "Legend", "Elysion", "Jade", "Shuttle", "Breeze")));

        cars.put("Honda",hondas);

        ArrayList<String> ladas = new ArrayList<String>((
                List.of("4x4 ", "Granta ", "Vesta", "Samara", "Classic", "1200 S", "Nova 1500 Estate", "Nova 1500 Brake", "Nova 1500 Family")));

        cars.put("Lada",ladas);

        ArrayList<String> teslas = new ArrayList<String>((
                List.of("Model S", "Model 3","Model X", "Model Y", "Cybertruck")));

        cars.put("Tesla",teslas);
        carKeyMap = new ArrayList<String>(cars.keySet());

    }



    public static Car randomCar(){
        String registration = random.ints(97, 123)
                .limit(2)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase() + String.format("%05d", random.nextInt(100000));
        String brand = carKeyMap.get(random.nextInt(carKeyMap.size()));
        String  model = cars.get(brand).get(random.nextInt(cars.get(brand).size()));

        String color = colors.get(random.nextInt(colors.size()));


        Character rentalGroup = "ABCD".charAt(random.nextInt(4));


        return new Car(registration, brand, model, color, rentalGroup, random.nextInt(500000));
    }
}
