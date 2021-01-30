import java.util.*;

public class Main {
    public static int max = 0;

    public static int[] stevila;

    public static void main(String[] args){

        vnos();
        //nakljucn_vnos();
        System.out.println("");
        izpis();
    }

    public static void vnos() {
        System.out.print("Vnsesi koliko vnosov zelis: ");
        try{
            max = new Scanner(System.in).nextInt();
        } catch (Exception e){
            System.out.println("Vnos ni stevilo!");
            System.exit(1);
        }
        stevila = new int[max];
        for (int i = 0; i < max; i++) {
            System.out.print("vnesi st: ");
            try {
                stevila[i] = new Scanner(System.in).nextInt();
            } catch (Exception e){
                System.out.println("Vnos ni stevilo!");
                System.exit(1);
            }
        }
    }

    public static void nakljucn_vnos() {
        max = nakljucno_stevilo(10,500);
        stevila = new int[max];
        for (int i = 0; i < max; i++) {
            stevila[i] = nakljucno_stevilo(0,10000);
        }
    }

    private static int nakljucno_stevilo(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("min mora biti manj kot max");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static int razlicna_stevila() {
        int out = 0;
        ArrayList<Integer> raz = new ArrayList<>();

        for (int i = 0; i < max; i++) {
            if (!raz.contains(stevila[i])) {
                raz.add(stevila[i]);
            }
        }
        if (raz.size() == 1) {
            out = 0;
        } else {
            out = raz.size();
        }
        return out;
    }

    public static HashMap<Integer, Integer> pojavitev_stevil() {

        HashMap<Integer, Integer> raz = new HashMap<>();

        for (int i = 0; i < max; i++) {
            if (!raz.containsKey(stevila[i])) {
                raz.put(stevila[i], 1);
            } else {
                raz.replace(stevila[i], raz.get(stevila[i]) + 1);
            }
        }
        return raz;
    }

    public static int soda_stevila() {
        long out = 0;
        for (int i = 0; i < max; i++) {
            if (stevila[i] % 2 == 0) {
                out += 1;
            }
        }
        return (int) out;
    }

    public static String pojavitev_v_procentih() {
        StringBuilder out = new StringBuilder();
        for (int st : pojavitev_stevil().keySet()) {
            out.append("        ").append(toString(st, ((float) pojavitev_stevil().get(st) / max * 100))).append("%").append('\n');
        }
        return out.toString();
    }

    public static int najveckrat_ponovljeno() {
        int out = (int) pojavitev_stevil().keySet().toArray()[0];
        for (int st : pojavitev_stevil().keySet()) {
            out = pojavitev_stevil().get(st) > pojavitev_stevil().get(out) ? st : out;
        }
        return out;
    }

    public static int najvecje_stevilo() {
        int out = (int) pojavitev_stevil().keySet().toArray()[0];
        for (int st : pojavitev_stevil().keySet()) {
            out = Math.max(st, out);
        }
        return out;
    }

    public static int druga_najmanjsa_vrednost() {
        LinkedHashMap<Integer, Integer> sortirano = new LinkedHashMap<>();
        pojavitev_stevil().entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> sortirano.put(x.getKey(), x.getValue()));
        if (sortirano.keySet().size() > 1 ) return (int) sortirano.keySet().toArray()[1];
        return (int) sortirano.keySet().toArray()[0];
    }

    public static float povprecje_vseh_steviil() {

        long sum = 0;
        for (int i : stevila) {
            sum += i;
        }
        return (float) sum / max;
    }

    public static double standardna_diviacija() {

        double out = 0;

        float povp = povprecje_vseh_steviil();

        long sumX = 0;

        for (int i = 0; i < stevila.length; i++) {
            int x = stevila[i];
            long first = (long) (x - povp);
            sumX += (long) (Math.pow(first, 2));
        }

        out =  (Math.sqrt((float) sumX / (double) (max - 1)));

        return  out;
    }

    public static int mediana() {
        return (max + 1) / 2;
    }

    public static long vsota_vseh_stevil() {
        long sum = 0;
        for (int i = 0; i < stevila.length;i++){
            sum += stevila[i];
        }
        return sum;
    }

    public static boolean jePalindrom(int n) {

        int orig = n;
        int ost = 0;
        int rn = 0;
        for (; n != 0;) {

            ost = n % 10;
            rn = rn * 10 + ost;
            n = n / 10;
        }

        if (orig == rn) {
            return true;
        }

        return false;
    }

    public static int st_palindromov() {
        long out = 0;
        for(int i = 0; i < stevila.length; i++) {
            if(jePalindrom(stevila[i])) {
                out += 1;
            }
        }

        return (int) out;
    }

    public static int najvecji_palindrom() {
        int np = 0;
        for (int i = 0; i < stevila.length; i++) {
            if(jePalindrom(stevila[i])){
                if(stevila[i] > np && stevila[i] < najvecje_stevilo()){
                    np = stevila[i];
                }
            }
        }

        return np;
    }

    public static String toString(int a, float b) {
        return String.format( "%5s ......... %-8s", a, b);
    }

    public static String print() {
        StringBuilder out = new StringBuilder();
        for (int i = stevila.length - 1; i >= 0;i--){
            out.append(stevila[i]).append(", ");
        }
        return out.toString();
    }

    public static void izpis() {
        System.out.println("St elementov: " + max);
        System.out.println("St razlicnih elementov: " + razlicna_stevila());
        System.out.println("St sodih elementov: " + soda_stevila());
        System.out.println("Stevilo lihih elm: " + (max - soda_stevila()));
        System.out.println("Frekvenca ponavljanja:");
        System.out.println(pojavitev_v_procentih());
        System.out.println("število, ki se največkrat ponovi: " + najveckrat_ponovljeno());
        System.out.println("najvecje stevilo: " + najvecje_stevilo());
        System.out.println("drugo najmanjse stevilo: " + druga_najmanjsa_vrednost());
        System.out.println("povprecje vseh stevil: " + povprecje_vseh_steviil());
        System.out.println("Standardna diviacija je priblizno: " + standardna_diviacija());
        System.out.println("Mediana je " + mediana());
        System.out.println("Vsota vseh stevil je " + vsota_vseh_stevil());
        System.out.println("Stevilo palindromov: " + st_palindromov());
        System.out.println("Najvecji palindorm je " + najvecji_palindrom());
        System.out.println(print());
    }

}
