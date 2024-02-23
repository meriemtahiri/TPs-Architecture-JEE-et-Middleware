package ma.enset.presentation;


import ma.enset.dao.DaoImpl;
import ma.enset.dao.DaoImpl2;
import ma.enset.metier.MetierImpl;

public class presentation1 {
    public static void main(String[] args) {

        //MetierImpl metier=new MetierImpl(dao);

        //version 1
        DaoImpl dao1 = new DaoImpl();
        MetierImpl metier1 = new MetierImpl(dao1);
        //metier.setDao(dao1);
        System.out.println(metier1.calcul());

        //version 2
        DaoImpl2 dao2 = new DaoImpl2();
        MetierImpl metier2 = new MetierImpl(dao2);
        //metier.setDao(dao2);
        System.out.println(metier2.calcul());
    }
}
