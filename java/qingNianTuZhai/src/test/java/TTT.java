import java.util.Random;

/**
 * Created by highness on 2017/11/29 0029.
 */
public class TTT {



    public static void main(String[] args) {

        System.out.println(Math.log(100));
        System.out.println(Math.E);

        System.out.println(Math.pow(Math.E, 2));
        for (int i = 1; i < 100; i++) {
            System.out.println(Math.pow(Math.E, i));
        }

        Random random = new Random();
        double d = random.nextDouble();
        double m = random.nextDouble();
        double a = 2;

        double y = 100;

        for (int i = 0; i < 10000; i++) {
            double fenmu = Math.pow(a, m);

            double v = d / fenmu;

            y -= v;

            if (y == 0){
                System.out.println(i);
                break;
            }

        }
    }
}
