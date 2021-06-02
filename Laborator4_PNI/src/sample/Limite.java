package sample;

public class Limite {
    private int inf;
    private int infR;
    private int infB;
    private int infG;
    private int sup;
    private int supR;
    private int supB;
    private int supG;

    public Limite(int inf, int infR, int infB, int infG, int sup, int supR, int supB, int supG) {
        this.inf = inf;
        this.infR = infR;
        this.infB = infB;
        this.infG = infG;
        this.sup = sup;
        this.supR = supR;
        this.supB = supB;
        this.supG = supG;
    }

    public int getInf() {
        return inf;
    }

    public int getInfR() {
        return infR;
    }

    public int getInfB() {
        return infB;
    }

    public int getInfG() {
        return infG;
    }

    public int getSup() {
        return sup;
    }

    public int getSupR() {
        return supR;
    }

    public int getSupB() {
        return supB;
    }

    public int getSupG() {
        return supG;
    }
}
