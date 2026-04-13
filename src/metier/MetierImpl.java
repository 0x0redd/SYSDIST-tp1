package metier;

import dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MetierImpl implements IMetier {
    private IDao dao;

    @Override
    public double calcul() {
        double d = dao.getDate();
        return d * 23;
    }

    /**
     * Injection d'une implémentation de {@link IDao}
     * (instanciation statique, dynamique, Spring XML ou annotations).
     */
    @Autowired
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
