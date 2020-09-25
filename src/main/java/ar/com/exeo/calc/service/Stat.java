package ar.com.exeo.calc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntUnaryOperator;

/**
 * DOCUMENT .
 * @author tonioc
 *
 */
public class Stat {

    private static List<Stat> statList = new ArrayList<>();

    private String className;

    public int created;
    public int common = -1;
    public int inner = -1;
    public int repo = -1;
    public int stat = -1;
    public int other = -1;

    /**
     * Constructor.
     * @param className
     */
    public Stat(final String className) {
        this.className = className;
        statList.add(this);
    }

    private IntUnaryOperator rv = i ->  (i <= -1 ? i : 0);

    public void reset() {
        this.created = this.rv.applyAsInt(this.created);
        this.common = this.rv.applyAsInt(this.common);
        this.inner = this.rv.applyAsInt(this.inner);
        this.other = this.rv.applyAsInt(this.other);
        this.repo = this.rv.applyAsInt(this.repo);
        this.stat = this.rv.applyAsInt(this.stat);
    }

    public static void resetAll() {
        statList.forEach(Stat::reset);
    }

    public static String printAll() {

        StringBuilder bld = new StringBuilder();
        statList.forEach(st -> bld.append(st.toString()).append(System.lineSeparator()));
        return bld.toString();
    }


    @Override
    public String toString() {

        boolean ok = status(this.created, this.common, this.inner, this.repo, this.stat, this.other);

        return String.format(
                "Stat(%s)[className=%s, created=%s, common=%s, inner=%s, repo=%s, stat=%s, other=%s]"
                , (ok ? "OK" : "ERROR")
                , this.className
                , count(this.created)
                , count(this.common)
                , count(this.inner)
                , count(this.repo)
                , count(this.stat)
                , count(this.other)
                );
    }

    private String count(final int count) {
        return (count <= -1 ? "*" : Integer.toString(count));
    }


    private boolean status(final int ref, final int... values) {

        for (int i : values) {
            if (i >= 0  &&  i != ref) {
                return false;
            }
        }

        return true;
    }


}
