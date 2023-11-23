import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.DAO.ReservationDAO;
import br.com.sistema.DAO.TierDAO;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Guest;
import br.com.sistema.model.Reservation;
import br.com.sistema.model.Tier;
import br.com.sistema.util.AgeCalculator;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        System.out.println("GOLDEN WIND HOTEL");

        int option = 100;

        while (option != 0) {



            option = showMenu();

            switch (option) {
                case 1:
                    newGuest();
                    break;
                case 2:
                    newBedroom();
                    break;
                case 3:
                    makeReservation();
                case 4:
                    showAllGuests();
                case 5:
                    showAllBedrooms();

            }

            }

        }

    public static int showMenu() {
        System.out.println("""
                
                1 - Cadastrar hóspede.
                2 - Cadastrar quarto.
                3 - Fazer reserva.
                4 - Listar hóspedes cadastrados.
                5 - Listar quartos cadastrados.
                6 - Listar reservas.
                0 - Sair.
                """);

        System.out.print("Digite a ação que deseja: ");

        return scanner.nextInt();
    }
    public static void newGuest() {

        scanner.nextLine();
        System.out.print("\nNome do hóspede: ");
        String name = scanner.nextLine();

        System.out.print("Data de nascimento: ");
        LocalDate birthday = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        AgeCalculator ageCalculator = new AgeCalculator();

        int age = ageCalculator.calculateAge(birthday, LocalDate.now());

        Guest guest = new Guest(name, birthday, age);
        EntityManager entityManager = JPAUtil.getEntityManager();
        GuestDAO guestDAO = new GuestDAO(entityManager);

        entityManager.getTransaction().begin();
        guestDAO.save(guest);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public static void newBedroom() {

        scanner.nextLine();
        System.out.print("\nCapacidade total do quarto: ");
        int capacity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Valor da diária: ");
        BigDecimal dailyRate = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.print("Nível do quarto: ");
        String tierName = scanner.nextLine();
        Tier tier = new Tier(tierName);

        EntityManager entityManager = JPAUtil.getEntityManager();
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        Bedroom bedroom = new Bedroom(capacity, dailyRate, tier);
        TierDAO tierDAO = new TierDAO(entityManager);

        entityManager.getTransaction().begin();
        tierDAO.save(tier);
        bedroomDAO.save(bedroom);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void makeReservation() {
        scanner.nextLine();

        System.out.print("Número do quarto que deseja fazer a reserva: ");
        Long bedroomNumber = scanner.nextLong();

        System.out.print("Digite o Id do cliente que deseja fazer a reserva: ");
        Long guestId = scanner.nextLong();

        System.out.print("Digite a quantidade de adultos: ");
        int adults = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a quantidade de crianças: ");
        int children = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Data do check-in (DD/MM/AAAA): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        System.out.print("Data do check-out (DD/MM/AAAA): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine(), dateTimeFormatter);

        EntityManager entityManager = JPAUtil.getEntityManager();
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);
        BedroomDAO bedroomDAO = new BedroomDAO(entityManager);
        GuestDAO guestDAO = new GuestDAO(entityManager);

        Reservation reservation1 = new Reservation();
        BigDecimal dailyRate = bedroomDAO.getDailyRatePerId(bedroomNumber);
        Bedroom bedroom = bedroomDAO.getBedroomPerId(bedroomNumber);
        Guest guest = guestDAO.getGuestPerId(guestId);
        int calculateDays = reservation1.calculateDays(checkIn, checkOut);
        BigDecimal stayValue = reservation1.calculateStay(dailyRate, calculateDays, children, adults);

        Reservation reservation = new Reservation(bedroom, guest, children, adults, checkIn, checkOut, stayValue);

        entityManager.getTransaction().begin();
        reservationDAO.save(reservation);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void showAllGuests() {


        GuestDAO guestDAO = new GuestDAO(JPAUtil.getEntityManager());
        List<Guest> guests = guestDAO.getAllGuests();

        for (Guest guest: guests) {
            System.out.println(guest.stringBuilder());
            System.out.println();

        }
    }
    public static void showAllBedrooms() {

        BedroomDAO bedroomDAO = new BedroomDAO(JPAUtil.getEntityManager());
        List<Bedroom> bedrooms = bedroomDAO.showAllBedrooms();

        for (Bedroom bedroom : bedrooms) {
            System.out.println(bedroom.stringBuilder());
            System.out.println();
        }



    }


}


