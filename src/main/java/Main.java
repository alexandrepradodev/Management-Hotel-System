import br.com.sistema.DAO.BedroomDAO;
import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.DAO.ReservationDAO;
import br.com.sistema.DAO.TierDAO;
import br.com.sistema.model.Bedroom;
import br.com.sistema.model.Guest;
import br.com.sistema.model.Reservation;
import br.com.sistema.model.Tier;
import br.com.sistema.service.BedroomService;
import br.com.sistema.service.GuestService;
import br.com.sistema.service.ReservationService;
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
                    break;
                case 4:
                    showAllGuests();
                    break;
                case 5:
                    showAllBedrooms();
                    break;
                case 6:
                    showAllReservations();
                    break;
                case 7:
                    removeGuest();
                    break;
                case 8:
                    cancelReservation();
                    break;

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
                7 - Excluir cadastro de hóspede.
                8 - Cancelar reserva de hóspede.
                0 - Sair.
                """);

        System.out.print("Digite a ação que deseja: ");

        return scanner.nextInt();
    }
    public static void newGuest() {

        GuestService.newGuest();


    }
    public static void newBedroom() {

        BedroomService.newBedroom();
    }

    public static void makeReservation() {

        ReservationService reservationService = new ReservationService();
        reservationService.makeReservation();
    }

    public static void showAllGuests() {

        GuestService.showAllGuests();

    }
    public static void showAllBedrooms() {

        BedroomService.showAllBedrooms();
    }
    public static void showAllReservations() {

        ReservationService.showAllReservations();

    }
    public static void removeGuest() {
        scanner.nextLine();

        showAllGuests();

        System.out.print("Digite o Id do cliente que deseja remover: ");
        Long guestId = scanner.nextLong();

        EntityManager entityManager = JPAUtil.getEntityManager();
        GuestDAO guestDAO = new GuestDAO(entityManager);

        entityManager.getTransaction().begin();
        guestDAO.removeGuestPerId(guestId);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public static void cancelReservation() {

        EntityManager entityManager = JPAUtil.getEntityManager();
        ReservationDAO reservationDAO = new ReservationDAO(entityManager);

        List<Reservation> reservationList = reservationDAO.getAllReservation();

        for (Reservation reservation : reservationList) {
            System.out.print(reservation.stringBuilder2());
            System.out.println("\n");
        }

        System.out.print("Digite o id da reserva que deseja cancelar: ");
        Long reservationId = scanner.nextLong();



        entityManager.getTransaction().begin();
        reservationDAO.cancelReservation(reservationId);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}


