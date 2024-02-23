package ma.enset.dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImpl2 implements IDao{

    @Override
    public double getData() {
        System.out.println("version 2 web services");
        return 2;
    }
}
