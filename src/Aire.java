public class Aire
{
    private double AireQt;
    private double AireQc;
    private double AireQb;
    private double AireQbt;

    public Aire()
    {
        this.AireQt = 0;
        this.AireQc = 0;
        this.AireQb = 0;
        this.AireQbt = 0;
    }

    public Aire(double AireQt, double AireQc, double AireQb, double AireQbt)
    {
        this.setAireQt(AireQt);
        this.setAireQc(AireQc);
        this.setAireQb(AireQb);
        this.setAireQbt(AireQbt);
    }

    public void MajAire(int dateSimu, int derniereDateSimu, int Qt, int Qc, int Bt, int Bc, int Qbt)
    {
        setAireQt(getAireQt() + ((dateSimu - derniereDateSimu) * Qt));
        setAireQc(getAireQc() + ((dateSimu - derniereDateSimu) * Qc));
        setAireQb(getAireQb() + ((dateSimu - derniereDateSimu) * (Bt + Bc)));
        setAireQbt(getAireQbt() + ((dateSimu - derniereDateSimu) * Qbt));
    }

    public void MajAireQt(int dateSimu, int derniereDateSimu, int Qt)
    {
        setAireQt(getAireQt() + ((dateSimu - derniereDateSimu) * Qt));
    }

    public void MajAireQc(int dateSimu, int derniereDateSimu, int Qc)
    {
        setAireQc(getAireQc() + ((dateSimu - derniereDateSimu) * Qc));
    }

    public void MajAireQb(int dateSimu, int derniereDateSimu, int Bt, int Bc)
    {
        setAireQb(getAireQb() + ((dateSimu - derniereDateSimu) * (Bt + Bc)));
    }

    public void MajAireQbt(int dateSimu, int derniereDateSimu, int Qbt)
    {
        setAireQbt(getAireQbt() + ((dateSimu - derniereDateSimu) * Qbt));
    }


    public double getAireQt() {
        return AireQt;
    }

    public void setAireQt(double aireQt) {
        AireQt = aireQt;
    }

    public double getAireQc() {
        return AireQc;
    }

    public void setAireQc(double aireQc) {
        AireQc = aireQc;
    }

    public double getAireQb() {
        return AireQb;
    }

    public void setAireQb(double aireQb) {
        AireQb = aireQb;
    }

    public double getAireQbt() {
        return AireQbt;
    }

    public void setAireQbt(double aireQbt) {
        AireQbt = aireQbt;
    }
}
