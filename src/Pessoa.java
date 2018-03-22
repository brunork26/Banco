import java.util.Random;

public class Pessoa {
    private int id;
    private int idade;
    private int tempo = 0;

    Pessoa(int id){
        this.id = id;
        this.idade = new Random().nextInt(80);
        while(idade < 18) {
            this.idade = new Random().nextInt(80);
        }
        tempo =  new Random().nextInt(60);
        while(tempo == 0){
            this.tempo =  new Random().nextInt(60);
        }
    }

    public int getId() {
        return this.id;
    }

    public int getIdade() {
        return this.idade;
    }

    public int getTempo() {
        return this.tempo;
    }
}
