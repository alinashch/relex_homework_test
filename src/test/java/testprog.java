import java.util.Random;

public class testprog {
    private static final char[] pool = {
            'a','b','c','d','e','f','g',
            'h','i','j','k','l','m','n',
            'o','p','q','r','s','t','u',
            'v','w','x','y','z'};

    private Random rnd;

    public testprog () { rnd = new Random(); }

    public char getChar() { return pool[rnd.nextInt(pool.length)]; }

    public String getStr(int sz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sz; i++)
            sb.append(getChar());
        return new String(sb);
    }


}