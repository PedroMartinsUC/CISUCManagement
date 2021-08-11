package Projeto;
import java.io.Serializable;
import java.util.*;

/**
 * A classe Grupo representa todos os grupos do CISUC.
 * @author Pedrp Afonso Ferreira Lopes Martins
 * @version 1.0
 */
public class Grupo implements Serializable {
    protected String nome;
    protected String acronimo;
    protected MembroEfetivo InvestigadorResponsavel;
    protected ArrayList<Investigador> investigadores;

    /**
     * A classe construtor da nossa publicacao. Responsavel por criar objetos do tipo Grupo.
     *
     * @param a Corresponde ao nome do Grupo.
     * @param b Corresponde ao acronimo do Grupo.
     * @param c Corresponde ao Investigador Responsavel do Grupo.
     * @param d Corresponde a um ArrayList de investigadores do grupo.
     */
    public Grupo(String a, String b, MembroEfetivo c, ArrayList<Investigador> d){
        this.nome = a;
        this.acronimo = b;
        this.InvestigadorResponsavel = c;
        this. investigadores = d;
    }

    /**
     * A funcao toString tem como objetivo devolver uma String com os dados do Grupo ordenados.
     * @return Devolve uma String com dados relativos ao grupo.
     */
    public String toString(){
        return String.format("Nome: %s\nAcrónimo: %s\nInvestigador Responsável: %s", nome, acronimo, InvestigadorResponsavel.nome);
    }
}
