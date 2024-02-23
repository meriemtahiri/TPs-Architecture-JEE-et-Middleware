package metier;

import ma.enset.metier.Calcule;
import org.junit.Assert;
import org.junit.Test;

public class TestCalcul {
    private Calcule calcule;

    @Test
    public void testSomme(){
        Calcule calcul1 = new Calcule();
        Assert.assertTrue(calcul1.somme(4,3)==7);
    }
}
