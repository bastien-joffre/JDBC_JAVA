import java.sql.*;
import java.util.Scanner;

public class DatabaseQuery {

    private Connection conn;

    private String url;
    private String user;
    private String passwd;

    public DatabaseQuery() {
        this.url = "jdbc:mysql://localhost/jdbc_warriors?useLegacyDatetimeCode=false&serverTimezone=UTC";
        this.user = "root";
        this.passwd = "root";
    }

    public Statement getDatabaseStatement() throws SQLException {
        this.conn = DriverManager.getConnection(this.url, this.user, this.passwd);
        //Création d'un objet Statement
        return this.conn.createStatement();
    }


    public void displayQuery(String query) {
        try {
            Statement state = this.getDatabaseStatement();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = state.executeQuery(query);
            ResultSetMetaData resultMeta = result.getMetaData();

            this.displayResultTable(resultMeta, result);

            result.close();
            state.close();

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }

    private void displayResultTable(ResultSetMetaData resultMeta, ResultSet result) throws SQLException {
        System.out.println("\n**********************************************************************************************************");
        //On affiche le nom des colonnes
        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
            System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

        System.out.println("\n**********************************************************************************************************");

        while(result.next()){
            for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                System.out.print("\t" + result.getObject(i).toString() + "\t |");

            System.out.println("\n----------------------------------------------------------------------------------------------------------");
        }
    }

    public void createHero(Scanner scanner) {
        try {
            Statement state = this.getDatabaseStatement();

            String query = "INSERT INTO Hero (Type, Nom, Image, NiveauVie, Puissance, Arme_Sort, Bouclier) Values(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement prepare = this.conn.prepareStatement(query);

            System.out.println("Type :");
            prepare.setString(1, scanner.nextLine());
            System.out.println("Nom :");
            prepare.setString(2, scanner.nextLine());
            System.out.println("Image :");
            prepare.setString(3, scanner.nextLine());
            System.out.println("Vie :");
            prepare.setInt(4, Integer.parseInt(scanner.nextLine()));
            System.out.println("Power :");
            prepare.setInt(5, Integer.parseInt(scanner.nextLine()));
            System.out.println("Equipment :");
            prepare.setString(6, scanner.nextLine());
            System.out.println("Bouclier :");
            prepare.setString(7, scanner.nextLine());

            prepare.execute();
            state.close();

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }

    public void deleteHero(Scanner scanner) {
        try {
            Statement state = this.getDatabaseStatement();

            String query = "DELETE FROM Hero WHERE id = ?";
            PreparedStatement prepare = this.conn.prepareStatement(query);

            System.out.println("ID du personnage à supprimer : :");
            prepare.setInt(1, Integer.parseInt(scanner.nextLine()));

            prepare.execute();
            state.close();

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }

    public void updateHero(Scanner scanner) {
        try {
            Statement state = this.getDatabaseStatement();

            String query = "UPDATE Hero SET nom = ? WHERE id = ?";
            PreparedStatement prepare = this.conn.prepareStatement(query);

            System.out.println("ID du personnage à modifier :");
            prepare.setInt(2, Integer.parseInt(scanner.nextLine()));

            System.out.println("Nom :");
            prepare.setString(1, scanner.nextLine());

            prepare.execute();
            state.close();

        } catch (SQLException e) {
            // handle any errors
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());

        }
    }
}
