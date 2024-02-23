package ma.enset.presentation;

import ma.enset.dao.IDao;
import ma.enset.metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class presentation2 {
    public static void main(String[] args) throws Exception{

        Scanner scan = new Scanner(new File("config.txt"));

        String daoClassName = scan.nextLine(); // récupérer le nom de la classe
        Class cDao = Class.forName(daoClassName); // déclarer la classe
        IDao dao = (IDao) cDao.newInstance(); // crée une instance

        String metierClassName = scan.nextLine(); // récupérer le nom de la classe
        Class cMetier = Class.forName(metierClassName); // déclarer la classe
        IMetier metier = (IMetier) cMetier.newInstance();

        Method method = cMetier.getMethod("setDao", IDao.class);
        method.invoke(metier,dao);

        System.out.println(metier.calcul());
    }
}
