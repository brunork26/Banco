import java.util.ArrayList;

public class Banco {
    static private ArrayList<Caixa> caixas = new ArrayList<Caixa>();
    static private ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
    static private ArrayList<Pessoa> preferenciais = new ArrayList<Pessoa>();

    private static Pessoa procuraPessoa(int numeroCaixa){
        // Caso não haja pessoas na fila retorna null
        if(pessoas.size() == 0) return null;
        // Verifica se caixa é preferencial e se tem alguem para atender na fila de preferenciais
        if(numeroCaixa < 6 && preferenciais.size() != 0){
            Pessoa preferencial = preferenciais.get(0);
            preferenciais.remove(preferencial);
            pessoas.remove(preferencial);
            return preferencial;
        }
        // Atende pessoa e caso seja um caixa não preferencial atendendo um cliente preferencial
        // ele remove também da fila preferencial
        Pessoa pessoa = pessoas.get(0);
        pessoas.remove(pessoa);
        if(pessoa.getIdade() > 64) {
            preferenciais.remove(pessoa);
        }
        return pessoa;
    }

    public static void main(String[] args) {
        // Cria caixas
        for (int i = 0; i < 10; i++ ) {
            caixas.add(new Caixa(i + 1));
            System.out.println("Caixa criado: " + caixas.get(i).getNumeroCaixa());
        }
        int id = 1;
        int timer = 0;
        boolean funcionamento = true;
        while(funcionamento) {
            funcionamento = false;
            // Após entrar 50 pessoas ninguém mais é permitido
            if(timer <= 50){
                Pessoa entrou = new Pessoa(id);
                if(entrou.getIdade() > 64){
                    System.out.println("Entrou uma pessoa de: " + entrou.getIdade() + " anos na fila preferêncial.");
                    preferenciais.add(entrou);
                }
                System.out.println("Entrou uma pessoa de: " + entrou.getIdade() + " anos na fila.");
                pessoas.add(entrou);
                id++;
            }
            for (Caixa caixa: caixas) {
                // Verifica se caixa está ocupado
                if(!caixa.getOcupado()){
                    Pessoa atendendo = procuraPessoa(caixa.getNumeroCaixa());
                    if(atendendo == null){
                        // Caso não haja ninguem para atender quebra o loop
                        System.out.println("Ninguem para atender ainda");
                        break;
                    } else {
                        // Atender primeira pessoa na fila ou se for caixa preferencial(1-5) atende preferencial(64 anos >)
                        System.out.println("Estou(" + caixa.getNumeroCaixa() + ") atendendo o cliente de número: " + atendendo.getId() + " que tem: " + atendendo.getIdade() + " anos.");
                        caixa.atender(atendendo);
                        funcionamento = true;
                    }
                } else {
                    // Caso caixa ocupado Banco continua em funcionamento
                    funcionamento = true;
                    caixa.setTimer();
                }
            }
            timer++;
        }
        // Movimento das filas
        System.out.println(pessoas.toString());
        System.out.println(preferenciais.toString());
    }
}
