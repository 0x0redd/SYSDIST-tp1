package dao;

import org.springframework.stereotype.Component;

@Component
public class DaoImpl implements IDao {

    @Override
    public double getDate() {
        System.out.println("Version base de données");
        return 80;
    }
}
