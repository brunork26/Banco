public class Caixa {
    private int numeroCaixa;
    private int timer = 0;
    private boolean ocupado = false;
    private Pessoa atendendo;

    // Cria caixa
    Caixa(int numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    // Verifica atendimento de cliente
    public void atender(Pessoa atendido){
        this.atendendo = atendido;
        this.ocupado = true;
        if (this.atendendo != null && timer == atendido.getTempo()) {
            System.out.println("O caixa " + numeroCaixa + " foi desocupado por: " + atendido.getId());
            this.ocupado = false;
            this.timer = 0;
        }
    }

    public int getNumeroCaixa(){
        return this.numeroCaixa;
    }

    public int getTimer(){
        return this.timer;
    }

    public boolean getOcupado(){
        return this.ocupado;
    }

    public void setTimer(){
        this.timer++;
        this.atender(this.atendendo);
    }

}
