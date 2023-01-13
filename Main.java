import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le nom du projet:");
        String nom_projet = sc.nextLine();
        System.out.println("Veuillez saisir le numéro de projet:");
        int id_projet = sc.nextInt();

        String user = "root";
        String pwd = "root";
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/binomotron";
        List<String> list_apprenant = new ArrayList<>();

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, pwd);
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM apprenants");

            while (rs.next()) {
                list_apprenant.add(rs.getString("nom") + " " + (rs.getString("prenom")));

            }
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
        } finally {
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {

            }
        }
        List<String> groupe = new ArrayList<>();//creation liste vierge des binome

        System.out.println(list_apprenant + "\n");//afficher liste de base \n pour sauter une ligne

        Collections.shuffle(list_apprenant);//melanger la liste

        System.out.println(list_apprenant + "\n");//afficher liste melangé

        System.out.println(list_apprenant.size() + "\n");//afficher taille de ma liste

        int i;
        int id_groupe = 1;

        if (list_apprenant.size() % 2 == 0) {
            //cas paire

            for (i = 0; i < list_apprenant.size(); i = i + 2) {
                System.out.println("binome" + id_groupe + " : " + list_apprenant.get(i) + " et " + list_apprenant.get(i + 1)+ "\n");
                groupe.add("binome" + id_groupe + " : " + list_apprenant.get(i) + " et " + list_apprenant.get(i + 1));//ajout des binomes à la liste des groupes
                id_groupe++;
            }

        } else {
            //cas impaire

            for (i = 0; i < list_apprenant.size() - 1; i = i + 2) { //taillelist-1 pour rendre ma liste impaire paire
                System.out.println("binome" + id_groupe + " : " + list_apprenant.get(i) + " et " + list_apprenant.get(i + 1)+ "\n");
                groupe.add("binome" + id_groupe + " : " + list_apprenant.get(i) + " et " + list_apprenant.get(i + 1));
                id_groupe++;
            }
            System.out.println("et un éleve en solo, le groupe " + id_groupe + " : " + list_apprenant.get(i) + "\n");
            groupe.add("solo" + id_groupe + " : " + list_apprenant.get(i));
        }

        System.out.println(groupe + "\n");
    }
}
