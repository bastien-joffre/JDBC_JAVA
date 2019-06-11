import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DatabaseQuery database = new DatabaseQuery();
        System.out.println("Bienvenue dans le système !");

        String menuChoice = "";
        boolean appRuning;
        do {
            database.displayQuery("SELECT * FROM Hero");
            appRuning = true;
            System.out.println();
            System.out.println("================== BDD ==================");
            System.out.println("1 - Créer un personnage");
            System.out.println("2 - Modifier un personnage");
            System.out.println("3 - Supprimer un personnage");

            menuChoice = sc.nextLine();
            if(menuChoice.equals("1")) {
                database.createHero(sc);
            } else if (menuChoice.equals("2")) {
                database.updateHero(sc);
            } else if (menuChoice.equals("3")) {
                database.deleteHero(sc);
            }
        }while(appRuning);
        sc.close();
        System.out.println("À bientôt");
    }
}
