import br.com.sistema.DAO.GuestDAO;
import br.com.sistema.model.Guest;
import br.com.sistema.util.AgeCalculator;
import br.com.sistema.util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            }


            }

        }

    public static int showMenu() {
        System.out.println("""
                
                1 - Cadastrar hóspede.
                2 - Cadastrar quarto.
                3 - Listar clientes cadastrados
                4 - Listar quartos disponíveis.
                5 - Listar quartos ocupados.
                6 - Fazer reserva.
                0 - Sair.
                """);

        System.out.print("Digite a ação que deseja: ");

        return scanner.nextInt();
    }
    public static void newGuest() {

        scanner.nextLine();
        System.out.print("Nome do hóspede: ");
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


}


