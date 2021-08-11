package Projeto;

import java.io.Serializable;

/**
 * A classe Investigador representa todos os investigadores do CISUC.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
abstract public class Investigador implements Serializable {
    protected String nome;
    protected String email;
    protected Grupo grupo;

    /**
     * A classe construtora principal do Investigador. Esta classe é abstrata, sendo apenas utilizada nas subclasses com
     * recurso ao método super().
     *
     * @param a Corresponde ao nome do Investigador.
     * @param b Corresponde ao email do Investigador.
     * @param c Corresponde ao grupo do Investigador.
     */
    public Investigador(String a, String b, Grupo c){
        this.nome = a;
        this.email = b;
        this.grupo = c;
    }

    /**
     * A classe construtora do Investigador sem o recurso ao grupo. Ao longo do programa e necessario criar objetos sem
     * lhes atribuir imediatamente o grupo, recorrendo assim a este construtor.
     *
     * @param a Corresponde ao nome do Investigador.
     * @param b Corresponde ao email do Investigador.
     */
    public Investigador(String a, String b){
        this.nome = a;
        this.email = b;
    }

    /**
     * Funcao setGrupo atribui a um investigador o seu respetivo grupo.
     * @param a Corresponde a um objeto do tipo Grupo
     */
    public void setGrupo(Grupo a){
        this.grupo = a;
    }

    /**
     * Metodo abstrato para imprimir detalhes sobre um investigador.
     * @return Devolve uma String com dados relativos a um investigador.
     */
    abstract public String Imprimir();

    /**
     * Metodo abstrato para verificar se um investigador e um membro efetivo ou um estudante.
     * @return 0 ou 1, dependendo do resultado.
     */
    abstract public int VerMembroEfetivo();
}

/**
 * A classe MembroEfetivo e uma subclasse da classe Investigador e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class MembroEfetivo extends Investigador{
    protected String numgabinete;
    protected int numtelefone;

    /**
     * A classe construtor para os objetos do tipo Membro Efetivo. Recorre ao construtor da superclasse atraves do
     * metodo super().
     *
     * @param a Corresponde ao nome do Investigador.
     * @param b Corresponde ao email do Investigador.
     * @param c Corresponde ao grupo do Investigador.
     * @param d Corresponde ao numero de gabinete.
     * @param e Corresponde ao numero de telefone.
     */
    public MembroEfetivo(String a, String b, Grupo c, String d, int e) {
        super(a, b, c);
        this.numgabinete = d;
        this.numtelefone = e;
    }

    /**
     * A classe construtora do Membro Efetivo sem o recurso ao grupo. Ao longo do programa e necessario criar objetos
     * sem lhes atribuir imediatamente o grupo, recorrendo assim a este construtor. Tambem recorre ao metodo super()
     * para invocar o construtor da superclasse.
     *
     * @param a Corresponde ao nome do Investigador.
     * @param b Corresponde ao email do Investigador.
     * @param d Corresponde ao numero de gabinete.
     * @param e Corresponde ao numero de telefone.
     */
    public MembroEfetivo(String a, String b, String d, int e) {
        super(a, b);
        this.numgabinete = d;
        this.numtelefone = e;
    }

    /**
     * Metodo abstrato para imprimir detalhes sobre um Membro Efetivo.
     * @return Devolve uma String com dados relativos a um Membro Efetivo.
     */
    public String Imprimir(){
        String[] s;
        s = nome.split(" ");

        return String.format("Nome: %s | Efetivo | Nome de publicação: Professor %s %s", nome, s[0], s[s.length-1]);
    }

    /**
     * Metodo para verificar se um investigador e um membro efetivo ou um estudante.
     * @return Devolve o valor inteiro 1.
     */
    public int VerMembroEfetivo(){
        return 1;
    }
}

/**
 * A classe Estudante e uma subclasse da classe Investigador e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class Estudante extends Investigador{
    protected String titulotese;
    protected Data dataprevista;
    protected MembroEfetivo InvestigadorOrientador;

    /**
     * A classe construtor para os objetos do tipo Estudante. Recorre ao construtor da superclasse atraves do metodo
     * super().
     *
     * @param a Corresponde ao nome do Investigador.
     * @param b Corresponde ao email do Investigador.
     * @param c Corresponde ao grupo do Investigador.
     * @param d Corresponde ao titulo da tese.
     * @param e Corresponde a data prevista para a conclusao do mestrado.
     * @param f Corresponde ao Investigador Orientador do estudante
     */
    public Estudante(String a, String b, Grupo c, String d, Data e, MembroEfetivo f) {
        super(a, b, c);
        this.titulotese = d;
        this.dataprevista = e;
        this.InvestigadorOrientador = f;
    }

    /**
     * A classe construtora do Estudante sem o recurso ao grupo. Ao longo do programa e necessario criar objetos
     * sem lhes atribuir imediatamente o grupo, recorrendo assim a este construtor. Tambem recorre ao metodo super()
     * para invocar o construtor da superclasse.
     *
     * @param a Corresponde ao nome do Investigador.
     * @param b Corresponde ao email do Investigador.
     * @param d Corresponde ao titulo da tese.
     * @param e Corresponde a data prevista para a conclusao do mestrado.
     * @param f Corresponde ao Investigador Orientador do estudante
     */
    public Estudante(String a, String b, String d, Data e, MembroEfetivo f) {
        super(a, b);
        this.titulotese = d;
        this.dataprevista = e;
        this.InvestigadorOrientador = f;
    }

    /**
     * Metodo abstrato para imprimir detalhes sobre um Estudante.
     * @return Devolve uma String com dados relativos a um Estudante.
     */
    public String Imprimir(){
        String[] s, s1;
        s = nome.split(" ");
        s1 = s[0].split("");

        return String.format("Nome: %s | Estudante | Nome de publicação: %s. %s", nome, s1[0], s[s.length-1]);
    }

    /**
     * Metodo para verificar se um investigador e um membro efetivo ou um estudante.
     * @return Devolve o valor inteiro 0.
     */
    public int VerMembroEfetivo(){
        return 0;
    }

}
