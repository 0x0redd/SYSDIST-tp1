package pres;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Pres2 {

    public static void main(String[] args) throws Exception {
        InputStream in = openConfig();
        try (Scanner scanner = new Scanner(in)) {
            String daoClassName = scanner.nextLine().trim();
            Class<?> cDao = Class.forName(daoClassName);
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

            String metierClassName = scanner.nextLine().trim();
            Class<?> cMetier = Class.forName(metierClassName);
            IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

            Method m = cMetier.getMethod("setDao", IDao.class);
            m.invoke(metier, dao);

            System.out.println(metier.calcul());
        }
    }

    /** Fichier à la racine du projet (vidéo) ou sur le classpath (Maven / JAR). */
    private static InputStream openConfig() throws Exception {
        File root = new File("config.txt");
        if (root.isFile()) {
            return new FileInputStream(root);
        }
        InputStream cp = Pres2.class.getClassLoader().getResourceAsStream("config.txt");
        if (cp != null) {
            return cp;
        }
        throw new IllegalStateException(
                "config.txt introuvable : placez-le à la racine du projet ou dans resources/");
    }
}
