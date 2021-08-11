package Projeto;
import java.io.Serializable;
import java.util.*;

/**
 * A classe Publicacao representa as publicacoes do sistema CISUC.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
abstract public class Publicacao implements Serializable {
    protected String titulo;
    protected String tipo;
    protected String[] palavraschave;
    protected int anodepublicacao;
    protected int audiencia;
    protected String resumo;
    protected ArrayList<Investigador> autores;
    protected String fatordeimpacto;

    /**
     * A classe construtor da nossa publicacao. Uma vez que a classe e abstrata, apenas e utilizado atraves do metodo super()
     * de forma a criar objetos pertencentes a uma subclasse.
     *
     * @param a Corresponde ao titulo da publicacao.
     * @param b Corresponde a um array que contem as palavras-chave da publicacao.
     * @param c Corresponde ao ano de publicacao.
     * @param d Corresponde a audiencia da publicacao.
     * @param g Corresponde ao tipo da publicacao.
     * @param e Corresponde ao resumo da publicacao.
     * @param f Corresponde ao ArrayList que contem os autores de uma publicacao.
     */
    public Publicacao(String a, String[] b, int c, int d, String g, String e, ArrayList<Investigador> f){
        this.titulo = a;
        this.palavraschave = b;
        this.anodepublicacao = c;
        this.audiencia = d;
        this.resumo = e;
        this.autores = f;
        this.tipo = g;
        this.fatordeimpacto = calcfatordeimpacto(d);
    }

    /**
     * Metodo abstrato para calculo do fator de impacto de uma publicacao.
     * @param d Corresponde a audiencia de uma publicacao.
     * @return O valor do fator de impacto.
     */
    abstract public String calcfatordeimpacto(int d);

    /**
     * Metodo toString apresenta numa string os aspetos de uma publicacao.
     * @return Devolve os aspetos gerais de uma publicacao.
     */
    public String toString(){
        return String.format("Título: %s | Ano de Publicação: %d | Fator de Impacto: %s | ", titulo, anodepublicacao, fatordeimpacto);
    }

    /**
     * Usado para obter o tipo de um objeto da classe Publicacao.
     * @return O tipo de publicacao.
     */
    public String getTipo(){
        return tipo;
    }

    /**
     * Usado para obter o ano de publicacao de um objeto da classe Publicacao
     * @return O ano de publicacao.
     */
    public int getAnodepublicacao(){
        return anodepublicacao;
    }

    /**
     * Usado para obter o fator de impacto de um objeto da classe Publicacao
     * @return O fator de impacto.
     */
    public String getFatordeimpacto(){
        return fatordeimpacto;
    }

}

/**
 * A classe SortPubs e responsavel por ordenar as publicacoes por ano de publicacao, tipo e fator de impacto
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class SortPubs implements Comparator<Publicacao>{

    @Override
    public int compare(final Publicacao a, final Publicacao b){
        if(a.getAnodepublicacao() != b.getAnodepublicacao()) {
            return b.anodepublicacao - a.anodepublicacao;
        } else if (!a.getTipo().equals(b.getTipo())){
            return a.getTipo().compareTo(b.getTipo());
        } else {
            return a.getFatordeimpacto().compareTo(b.getFatordeimpacto());
        }

    }
}

/**
 * A classe ArtigoConferencia e uma subclasse da classe Publicacao e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class ArtigoConferencia extends Publicacao{
    protected String nome;
    protected Data data;
    protected String localizacao;

    /**
     * A classe construtor para os objetos do tipo Artigo de Conferencia. Recorre ao construtor da superclasse atraves
     * do metodo super().
     *
     * @param a Corresponde ao titulo da publicacao.
     * @param b Corresponde a um array que contem as palavras-chave da publicacao.
     * @param c Corresponde ao ano de publicacao.
     * @param d Corresponde a audiencia da publicacao.
     * @param j Corresponde ao tipo da publicacao.
     * @param e Corresponde ao resumo da publicacao.
     * @param f Corresponde ao ArrayList que contem os autores de uma publicacao.
     * @param g Corresponde ao nome do artigo.
     * @param h Corresponde a data do artigo
     * @param i Corresponde a localizacao do artigo
     */
    public ArtigoConferencia(String a, String[] b, int c, int d, String j, String e, ArrayList<Investigador> f, String g, Data h, String i) {
        super(a, b, c, d, j, e, f);
        this.nome = g;
        this.data = h;
        this.localizacao = i;
    }

    /**
     * Metodo para calculo do fator de impacto de uma publicacao do tipo Artigo de Conferencia.
     * @param d Corresponde a audiencia de uma publicacao.
     * @return O valor do fator de impacto.
     */
    public String calcfatordeimpacto(int d){
        if (d >= 500){
            return "A";
        } else if (d < 500 && d >= 200){
            return "B";
        } else if (d < 200){
            return "C";
        }
        return null;
    }

    /**
     * Metodo toString apresenta numa string os aspetos de uma publicacao do tipo Artigo de Conferencia.
     * @return Devolve os aspetos gerais de uma publicacao.
     */
    public String toString(){
        return super.toString() + "Artigo de Conferencia";
    }
}

/**
 * A classe ArtigoConferencia e uma subclasse da classe Publicacao e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class ArtigoRevista extends Publicacao{
    protected String nome;
    protected Data data;
    protected int numero;

    /**
     * A classe construtor para os objetos do tipo Artigo de Revista. Recorre ao construtor da superclasse atraves
     * do metodo super().
     *
     * @param a Corresponde ao titulo da publicacao.
     * @param b Corresponde a um array que contem as palavras-chave da publicacao.
     * @param c Corresponde ao ano de publicacao.
     * @param d Corresponde a audiencia da publicacao.
     * @param j Corresponde ao tipo da publicacao.
     * @param e Corresponde ao resumo da publicacao.
     * @param f Corresponde ao ArrayList que contem os autores de uma publicacao.
     * @param g Corresponde ao nome do artigo.
     * @param h Corresponde a data do artigo.
     * @param i Corresponde ao numero do artigo.
     */
    public ArtigoRevista(String a, String[] b, int c, int d, String j, String e, ArrayList<Investigador> f, String g, Data h, int i) {
        super(a, b, c, d, j, e, f);
        this.nome = g;
        this.data = h;
        this.numero = i;
    }

    /**
     * Metodo para calculo do fator de impacto de uma publicacao do tipo Artigo de Revista.
     * @param d Corresponde a audiencia de uma publicacao.
     * @return O valor do fator de impacto.
     */
    public String calcfatordeimpacto(int d){
        if (d >= 1000){
            return "A";
        } else if (d < 1000 && d >= 500){
            return "B";
        } else if (d < 500){
            return "C";
        }
        return null;
    }

    /**
     * Metodo toString apresenta numa string os aspetos de uma publicacao do tipo Artigo de Revista.
     * @return Devolve os aspetos gerais de uma publicacao.
     */
    public String toString(){
        return super.toString() + "Artigo de Revista";
    }

}

/**
 * A classe ArtigoConferencia e uma subclasse da classe Publicacao e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class Livro extends Publicacao{
    protected String editora;
    protected String ISBN;

    /**
     * A classe construtor para os objetos do tipo Livro. Recorre ao construtor da superclasse atraves
     * do metodo super().
     *
     * @param a Corresponde ao titulo da publicacao.
     * @param b Corresponde a um array que contem as palavras-chave da publicacao.
     * @param c Corresponde ao ano de publicacao.
     * @param d Corresponde a audiencia da publicacao.
     * @param i Corresponde ao tipo da publicacao.
     * @param e Corresponde ao resumo da publicacao.
     * @param f Corresponde ao ArrayList que contem os autores de uma publicacao.
     * @param g Corresponde a editora do livro.
     * @param h Corresponde ao ISBN do livro.
     */
    public Livro(String a, String[] b, int c, int d, String i, String e, ArrayList<Investigador> f, String g, String h){
        super(a, b, c, d, i, e, f);
        this.editora = g;
        this.ISBN = h;
    }

    /**
     * Metodo para calculo do fator de impacto de uma publicacao do tipo Livro.
     * @param d Corresponde a audiencia de uma publicacao.
     * @return O valor do fator de impacto.
     */
    public String calcfatordeimpacto(int d){
        if (d >= 10000){
            return "A";
        } else if (d < 10000 && d >= 5000){
            return "B";
        } else if (d < 5000){
            return "C";
        }
        return null;
    }

    /**
     * Metodo toString apresenta numa string os aspetos de uma publicacao do tipo Livro.
     * @return Devolve os aspetos gerais de uma publicacao.
     */
    public String toString(){
        return super.toString() + "Livro";
    }
}

/**
 * A classe ArtigoConferencia e uma subclasse da classe Publicacao e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class Capitulo extends Livro{
    protected String nomecapitulo;
    protected int paginainicio;
    protected int paginafim;

    /**
     * A classe construtor para os objetos do tipo Capitulo de Livro. Recorre ao construtor da superclasse atraves
     * do metodo super().
     *
     * @param a Corresponde ao titulo da publicacao.
     * @param b Corresponde a um array que contem as palavras-chave da publicacao.
     * @param c Corresponde ao ano de publicacao.
     * @param d Corresponde a audiencia da publicacao.
     * @param l Corresponde ao tipo da publicacao.
     * @param e Corresponde ao resumo da publicacao.
     * @param f Corresponde ao ArrayList que contem os autores de uma publicacao.
     * @param g Corresponde a editora do livro.
     * @param h Corresponde ao ISBN do livro.
     * @param i Corresponde ao nome do capitulo.
     * @param j Corresponde a pagina inicial do capitulo.
     * @param k Corresponde a pagina final do capitulo.
     */
    public Capitulo(String a, String[] b, int c, int d, String l, String e, ArrayList<Investigador> f, String g, String h, String i, int j, int k) {
        super(a, b, c, d, l, e, f, g, h);
        this.nomecapitulo = i;
        this.paginainicio = j;
        this.paginafim = k;
    }

    /**
     * Metodo para calculo do fator de impacto de uma publicacao do tipo Capitulo de Livro.
     * @param d Corresponde a audiencia de uma publicacao.
     * @return O valor do fator de impacto.
     */
    public String calcfatordeimpacto(int d){
        if (d >= 10000){
            return "A";
        } else if (d < 10000 && d >= 5000){
            return "B";
        } else if (d < 5000){
            return "C";
        }
        return null;
    }

    /**
     * Metodo toString apresenta numa string os aspetos de uma publicacao do tipo Capitulo de Livro.
     * @return Devolve os aspetos gerais de uma publicacao.
     */
    public String toString(){
        return super.toString() + " de Capítulo";
    }

}

/**
 * A classe ArtigoConferencia e uma subclasse da classe Publicacao e representa todos os objetos desse tipo.
 * @author Pedro Afonso Ferreira Lopes Martins
 * @version 1.0
 */
class LivroArtigoConferencia extends Livro{
    protected String nomeconferencia;
    protected int numeroartigos;

    /**
     * A classe construtor para os objetos do tipo Capitulo de Livro. Recorre ao construtor da superclasse atraves
     * do metodo super().
     *
     * @param a Corresponde ao titulo da publicacao.
     * @param b Corresponde a um array que contem as palavras-chave da publicacao.
     * @param c Corresponde ao ano de publicacao.
     * @param d Corresponde a audiencia da publicacao.
     * @param k Corresponde ao tipo da publicacao.
     * @param e Corresponde ao resumo da publicacao.
     * @param f Corresponde ao ArrayList que contem os autores de uma publicacao.
     * @param g Corresponde a editora do livro.
     * @param h Corresponde ao ISBN do livro.
     * @param i Corresponde ao nome da Conferencia.
     * @param j Corresponde ao numero de artigos.
     */
    public LivroArtigoConferencia(String a, String[] b, int c, int d, String k, String e, ArrayList<Investigador> f, String g, String h, String i, int j) {
        super(a, b, c, d, k, e, f, g, h);
        this.nomeconferencia = i;
        this.numeroartigos = j;
    }

    /**
     * Metodo para calculo do fator de impacto de uma publicacao do tipo Livro de Artigo de Conferencia.
     * @param d Corresponde a audiencia de uma publicacao.
     * @return O valor do fator de impacto.
     */
    public String calcfatordeimpacto(int d){
        if (d >= 7500){
            return "A";
        } else if (d < 7500 && d >= 2500){
            return "B";
        } else if (d < 2500){
            return "C";
        }
        return null;
    }

    /**
     * Metodo toString apresenta numa string os aspetos de uma publicacao do tipo Livro de Artigo de Conferencia.
     * @return Devolve os aspetos gerais de uma publicacao.
     */
    public String toString(){
        return super.toString() + " de Artigo de Conferencia";
    }

}