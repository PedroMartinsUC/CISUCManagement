package Projeto;
import java.io.Serializable;

/**
 * A classe Data representa todos os objetos que sejam uma data, fornecendo metodo toString e verificacao de dia e mes,
 * com a possibilidade de verificacao do ano, atraves do codigo comentado na funcao checkData().
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
public class Data implements Serializable {
    protected int dia;
    protected int mes;
    protected int ano;

    /**
     * A classe construtor da classe Data. Recebe dia, mes e ano e cria um objeto do tipo data.
     *
     * @param a Corresponde ao dia de uma data.
     * @param b Corresponde ao mes de uma data.
     * @param c Corresponde ao ano de uma data.
     */
    public Data(int a, int b, int c){
        this.dia = a;
        this.mes = b;
        this.ano = c;
    }

    /**
     * A funcao toString tem como objetivo apresentar ao utilizador uma data mais legivel, no formato dia/mes/ano
     * @return Devolve uma String com a data no formato dia/mes/ano.
     */
    public String toString(){
        return String.format("%d/%d/%d", dia, mes, ano);
    }

    /**
     * A funcao checkData verifica se a data fornecida tem como dia um valor entre 1 e 31 e se tem como mes um valor
     * entre 1 e 12, com a opcao de introduzir um limite anual, recorrendo ao codigo comentado dentro da funcao.
     */
    public void checkData(){
        if (dia < 1 || dia > 31){
            System.out.print("Erro a apresentar a data : O dia não se encontra entre 1 e 31!\n");
            System.exit(1);
        }

        if (mes < 1 || mes > 12){
            System.out.print("Erro a apresentar a data : O mes não se encontra entre 1 e 12!\n");
            System.exit(2);
        }

        /* Caso seja necessário introduzir um limite minimo e maximo para o ano, use o código comentado abaixo.
        if (data.ano < valormin || data.ano > valormax){
            return false;
        }
        */

    }
}
