package ma.enset.dao;

import org.springframework.stereotype.Component;

//@Component("dao")
public class DaoImpl implements IDao{

    @Override
    public double getData() {
        System.out.println("version 1 BD");
        return 1;
    }
}
