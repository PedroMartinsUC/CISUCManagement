package Projeto;

import java.util.*;
import java.io.*;

/**
 * A classe projeto representa o programa e a maioria das funcoes que o fazem funcionar. Contem todas as funcoes
 * responsaveis por apresentar informacao ao utilizador.
 * @author Pedro Afonso Ferreira Lopes Martins.
 * @version 1.0
 */
public class Projeto {

    public static void main(String[] args){

        ArrayList<Grupo> grupos = null;
        ArrayList<Publicacao> publicacoes = new ArrayList<>();
        ArrayList<Investigador> investigadores = null;
        ArrayList<Publicacao> publicacoes5Anos = null;

        int num = 0;
        Scanner sc = new Scanner(System.in);
        System.out.print("\nBem vindo ao Gestor de Publicações do CISUC\n");
        while (num != 1 && num != 2) {
            System.out.print("\n\nEfetuou alterações aos ficheiros de texto? (Irá abrir os ficheiros de texto para carregar as novas alterações)");
            System.out.print("\n\n[1] Sim\n");
            System.out.print("[2] Não");
            System.out.print("\n\nEscolha uma opção: ");
            num = sc.nextInt();
            switch (num) {
                case (1) -> {
                    grupos = lerficheirotextogrupos();
                    investigadores = getInvestigadores(grupos);
                    publicacoes = lerficheirotextopublicacoes(investigadores);
                }
                case (2) -> {
                    grupos = lerficheirogrupos();
                    investigadores = getInvestigadores(grupos);
                    publicacoes = lerficheiropublicacoes(investigadores);
                }
                default -> System.out.print("\n\nEssa não é uma opção válida!");
            }
        }

        publicacoes5Anos = checkAno(publicacoes);
        menu(grupos, publicacoes, investigadores, publicacoes5Anos);

    }

    /**
     * A funcao menu e responsavel por apresentar ao utilizador o menu, a partir do qual e possivel selecionar o que
     * pretende visualizar ou sair do programa.
     * @param grupos Corresponde a um ArrayList com todos os grupos do CISUC.
     * @param publicacoes Corresponde a um ArrayList com todos os investigadores do CISUC.
     * @param investigadores Corresponde a um ArrayList com todos os investigadores do CISUC.
     * @param publicacoes5Anos Corresponde a um ArrayList com todas as publicacoes feitas nos ultimos 5 anos.
     */
    public static void menu(ArrayList<Grupo> grupos, ArrayList<Publicacao> publicacoes, ArrayList<Investigador> investigadores, ArrayList<Publicacao> publicacoes5Anos){
        int num = 0;

        while (num != 9) {
            System.out.print("\n\n[1] Indicadores Gerais do CISUC.\n");
            System.out.print("[2] Listar as publicações de um grupo.\n");
            System.out.print("[3] Listar os membros.\n");
            System.out.print("[4] Listar as publicações de um investigador.\n");
            System.out.print("[5] Listar todos os grupos de investigação.\n");
            System.out.print("[9] Sair do programa!\n");
            System.out.print("\nIntroduza um número para realizar uma ação: ");
            Scanner sc = new Scanner(System.in);
            num = sc.nextInt();
            switch (num) {
                case (1) -> indicadoresGerais(investigadores, publicacoes, publicacoes5Anos);
                case (2) -> listpubsGrupo(grupos, publicacoes5Anos);
                case (3) -> listarMembros(grupos);
                case (4) -> listpubsInv(publicacoes, investigadores);
                case (5) -> infoGrupo(grupos, publicacoes5Anos);
                case (9) -> {
                    escreverficheirogrupos(grupos);
                    escreverficheiropublicacoes(publicacoes);
                    System.out.print("\nA sair do programa!\n");
                }
                default -> System.out.print("\nValor introduzido não é válido!\n");
            }
        }
    }

    /**
     * A funcao indicadoresGerais obtem aspetos gerais do CISUC. Entre estes estao o total de membros, o numero de
     * membros efetivos e estudantes, o número de publicacoes e o numero de publicacoes de cada tipo.
     * @param investigadores Corresponde a um ArrayList com todos os investigadores do CISUC.
     * @param publicacoes Corresponde a um ArrayList com todas as publicacoes do CISUC.
     * @param publicacoes5Anos Corresponde a um ArrayList com todas as publicacoes feitas nos ultimos 5 anos.
     */
    public static void indicadoresGerais(ArrayList<Investigador> investigadores, ArrayList<Publicacao> publicacoes, ArrayList<Publicacao> publicacoes5Anos){
        int membrosefetivos = 0, estudantes = 0;
        int[] numpublicacoes = new int[5];

        System.out.printf("\n[*] Total de membros: %d\n\n", investigadores.size());
        for (Investigador investigador : investigadores){
            System.out.println("     [*] " + investigador.Imprimir());
            if (investigador.VerMembroEfetivo() == 1){
                membrosefetivos++;
            } else {
                estudantes++;
            }
        }
        System.out.printf("\n[*] Total de Membros Efetivos: %d | Total de Estudantes: %d", membrosefetivos, estudantes);
        System.out.printf("\n[*] Total de Publicações nos últimos 5 anos: %d\n\n", publicacoes5Anos.size());
        for(Publicacao publicacao : publicacoes5Anos){
            System.out.println("     [*] " + publicacao.toString());
        }
        for(Publicacao publicacao : publicacoes){
            switch (publicacao.tipo) {
                case ("Artigo de Conferencia") -> numpublicacoes[0]++;
                case ("Artigo de Revista") -> numpublicacoes[1]++;
                case ("Livro") -> numpublicacoes[2]++;
                case ("Capitulo de Livro") -> numpublicacoes[3]++;
                case ("Livro de Artigo de Conferencia") -> numpublicacoes[4]++;
            }
        }
        System.out.printf("\n[*] Artigos de Conferencia: %d | Artigos de Revista: %d | Livros: %d | Capitulos de Livro: %d | Livros de Artigos de Conferencia: %d"
        , numpublicacoes[0], numpublicacoes[1], numpublicacoes[2], numpublicacoes[3], numpublicacoes[4]);
    }

    /**
     * A funcao checkAno compara o ano de cada publicacao do CISUC com o ano de 2020 e devolve apenas as publicacoes que
     * tenham menos de 5 anos.
     * @param publicacoes Corresponde a um ArrayList com as publicacoes do CISUC.
     * @return Devolve um ArrayList de publicacoes com menos de 5 anos.
     */
    public static ArrayList<Publicacao> checkAno(ArrayList<Publicacao> publicacoes){
        ArrayList<Publicacao> pubs = new ArrayList<>();
        for (Publicacao publicacao : publicacoes){
            if ((2020 - publicacao.anodepublicacao) <= 5){
                pubs.add(publicacao);
            }
        }
        return pubs;
    }

    /**
     * A funcao infoGrupo e responsavel por apresentar a informacao relativa aos grupos do CISUC, recorrendo a funcao
     * infoDeUmGrupo.
     * @param grupos Corresponde a um ArrayList dos grupos do CISUC.
     * @param publicacoes Corresponde a um ArrayList das publicações do CISUC.
     */
    public static void infoGrupo(ArrayList<Grupo> grupos, ArrayList<Publicacao> publicacoes){

        for (Grupo grupo : grupos){
            infoDeUmGrupo(grupo, publicacoes);
        }
    }

    /**
     * A funcao infoDeUmGrupo e responsavel por apresentar na consola os dados de um grupo. Entre eles estao o total de
     * membros, o total de membros efetivos e estudantes, o numero de publicacoes nos ultimos 5 anos e o numero de
     * publicacoes dos ultimos 5 anos ordenadas por ano, tipo e fator de impacto.
     * @param grupo Corresponde a um grupo do CISUC
     * @param publicacoes Corresponde ao ArrayList das publicacoes do CISUC.
     */
    public static void infoDeUmGrupo(Grupo grupo, ArrayList<Publicacao> publicacoes){
        int membrosefetivos = 1, estudantes = 0, count = 0;
        ArrayList<Publicacao> grupopublicacoes = new ArrayList<>();

        System.out.printf("\n\nGrupo %s:\n", grupo.nome);
        System.out.printf("\n[*] Total de membros: %d", grupo.investigadores.size() + 1);
        System.out.println("\n\n     [*] " + grupo.InvestigadorResponsavel.Imprimir());
        for (Investigador investigador : grupo.investigadores){
            System.out.println("     [*] " + investigador.Imprimir());
        }
        for (Investigador investigador : grupo.investigadores){
            if (investigador.VerMembroEfetivo() == 1){
                membrosefetivos++;
            } else {
                estudantes++;
            }
        }
        System.out.printf("\n[*] Total de Membros Efetivos: %d | Total de Estudantes: %d", membrosefetivos, estudantes);
        if (grupo.investigadores.size() != 0){
            for (Investigador investigador : grupo.investigadores){
                for (Publicacao publicacao :publicacoes){
                    for (Investigador investigador1 : publicacao.autores){
                        if (investigador.nome.equals(investigador1.nome) || grupo.InvestigadorResponsavel.nome.equals(investigador1.nome)){
                            for (Publicacao publicacao1 : grupopublicacoes){
                                if (publicacao1.titulo.equals(publicacao.titulo)){
                                    count++;
                                    break;
                                }
                            }
                            if (count == 0){
                                grupopublicacoes.add(publicacao);
                            }
                            count = 0;
                            break;
                        }
                    }
                }
            }
        } else {
            for (Publicacao publicacao : publicacoes){
                for (Investigador investigador : publicacao.autores){
                    if (investigador.nome.equals(grupo.InvestigadorResponsavel.nome)){
                        grupopublicacoes.add(publicacao);
                    }
                }
            }
        }
        Collections.sort(grupopublicacoes, new SortPubs());
        System.out.printf("\n[*] Total de publicações dos últimos 5 anos: %d\n", grupopublicacoes.size());
        System.out.print("[*] Publicações dos últimos 5 anos:\n\n");
        for (Publicacao publicacao : grupopublicacoes){
            System.out.println("     [*] " + publicacao.toString());
        }
    }

    /**
     * A funcao listarMembros apresenta ao utilizador na consola uma escolha entre os 6 grupos existentes no CISUC e,
     * dependendo da escolha, chama a funcao listarMembrosCategoria para apresentar no ecra dados relativos aos membros
     * do grupo selecionado.
     * @param grupos Corresponde a um ArrayList com os grupos do CISUC
     */
    public static void listarMembros(ArrayList<Grupo> grupos){
        int num = 0;
        Scanner sc = new Scanner(System.in);

        while (num != 1 && num != 2 && num != 3 && num != 4 && num != 5 && num != 6) {
            System.out.print("\n[1] AC\n");
            System.out.print("[2] CMS\n");
            System.out.print("[3] ECOS\n");
            System.out.print("[4] IS\n");
            System.out.print("[5] LCT\n");
            System.out.print("[6] SSE\n");
            System.out.print("\nPor favor escolha o grupo: ");
            num = sc.nextInt();

            switch (num) {
                case (1) -> listarMembrosCategoria(0, grupos);
                case (2) -> listarMembrosCategoria(1, grupos);
                case (3) -> listarMembrosCategoria(2, grupos);
                case (4) -> listarMembrosCategoria(3, grupos);
                case (5) -> listarMembrosCategoria(4, grupos);
                case (6) -> listarMembrosCategoria(5, grupos);
                default -> System.out.print("\nEsse grupo é inválido");
            }
        }
    }

    /**
     * A funcao listarMembrosCategoria imprime no monitor os investigadores de um determinado grupo, apresentando-os
     * agrupados pelo seu cargo no CISUC (Membro efetivo ou estudante).
     * @param i Corresponde a um valor inteiro ao qual esta associado um grupo (A posicao de cada grupo no ArrayList de
     * grupos e conhecido, sendo apenas necessario recorrer a funcao .get() da classe ArrayList para obter o grupo em
     * questão.
     * @param grupos Corresponde ao ArrayList de grupos do CISUC.
     */
    public static void listarMembrosCategoria(int i, ArrayList<Grupo> grupos){
        System.out.print("\n\nMembros Efetivos:\n\n");
        for (Investigador investigador : grupos.get(i).investigadores) {
            if (investigador.VerMembroEfetivo() == 1) {
                System.out.println(investigador.Imprimir());
            }
        }
        System.out.println(grupos.get(i).InvestigadorResponsavel.Imprimir());
        System.out.print("\nEstudantes:\n\n");
        for (Investigador investigador : grupos.get(i).investigadores) {
            if (investigador.VerMembroEfetivo() == 0) {
                System.out.println(investigador.Imprimir());
            }
        }
    }

    /**
     * A funcao listpubsGrupo e responsavel por perguntar ao utilizador qual o grupo que pretende analisar, passando
     * essa informacao a funcao listaDeUmGrupo.
     * @param grupos Corresponde ao ArrayList de grupos do CISUC.
     * @param publicacoes Corresponde ao ArrayList de publicacoes do CISUC.
     */
    public static void listpubsGrupo(ArrayList<Grupo> grupos, ArrayList<Publicacao> publicacoes){
        int num = 0;
        Scanner sc = new Scanner(System.in);

        while (num != 1 && num != 2 && num != 3 && num != 4 && num != 5 && num != 6) {
            System.out.print("\n[1] AC\n");
            System.out.print("[2] CMS\n");
            System.out.print("[3] ECOS\n");
            System.out.print("[4] IS\n");
            System.out.print("[5] LCT\n");
            System.out.print("[6] SSE\n");
            System.out.print("\nPor favor escolha o grupo: ");
            num = sc.nextInt();

            switch (num) {
                case (1) -> listaDeUmGrupo(0, grupos, publicacoes);
                case (2) -> listaDeUmGrupo(1, grupos, publicacoes);
                case (3) -> listaDeUmGrupo(2, grupos, publicacoes);
                case (4) -> listaDeUmGrupo(3, grupos, publicacoes);
                case (5) -> listaDeUmGrupo(4, grupos, publicacoes);
                case (6) -> listaDeUmGrupo(5, grupos, publicacoes);
                default -> System.out.print("\nEsse grupo é inválido");
            }
        }
    }

    /**
     * A funcao listaDeUmGrupo e responsavel por apresentar ao utilizador uma lista ordenada de publicacoes que
     * correspondem a um grupo escolhido previamente.
     * @param i  Corresponde a um valor inteiro ao qual esta associado um grupo (A posicao de cada grupo no ArrayList de
     * grupos e conhecido, sendo apenas necessario recorrer a funcao .get() da classe ArrayList para obter o grupo em
     * questão.
     * @param grupos Corresponde ao ArrayList de grupos do CISUC.
     * @param publicacoes Corresponde ao ArrayList de grupos do CISUC.
     */
    public static void listaDeUmGrupo(int i, ArrayList<Grupo> grupos, ArrayList<Publicacao> publicacoes){
        ArrayList<Publicacao> publicacoesOrdenadas = new ArrayList<>();
        int count = 0;

        if (grupos.get(i).investigadores.size() != 0){
            for (Investigador investigador : grupos.get(i).investigadores) {
                for (Publicacao publicacao : publicacoes) {
                    for (Investigador investigador1 : publicacao.autores) {
                        if (investigador.nome.equals(investigador1.nome) || grupos.get(i).InvestigadorResponsavel.nome.equals(investigador1.nome)) {
                            for (Publicacao publicacao1 : publicacoesOrdenadas) {
                                if (publicacao1.titulo.equals(publicacao.titulo)) {
                                    count++;
                                    break;
                                }
                            }
                            if (count == 0) {
                                publicacoesOrdenadas.add(publicacao);
                            }
                            count = 0;
                            break;
                        }
                    }
                }
            }
        } else {
            for (Publicacao publicacao : publicacoes) {
                for (Investigador investigador1 : publicacao.autores) {
                    if (investigador1.nome.equals(grupos.get(i).InvestigadorResponsavel.nome)) {
                        publicacoesOrdenadas.add(publicacao);
                    }
                }
            }
        }

        Collections.sort(publicacoesOrdenadas, new SortPubs());
        System.out.print("\n");
        for (Publicacao publicacao : publicacoesOrdenadas){
            System.out.println("     [*] " + publicacao.toString());
        }
    }

    /**
     * A funcao listpubsInv apresenta ao utilizador todas as publicacoes ordenadas de um investigador. O nome do
     * investigador e pedido ao utilizador e, caso o nome seja encontrado no sistema, devolve a lista ordenada de
     * publicacoes.
     * @param publicacoes Corresponde ao ArrayList de publicacoes do CISUC.
     * @param investigadores Corresponde ao ArrayList de investigadores do CISUC.
     */
    public static void listpubsInv(ArrayList<Publicacao> publicacoes, ArrayList<Investigador> investigadores){
        ArrayList<Publicacao> publicacoesOrdenadas = new ArrayList<>();
        ArrayList<Publicacao> temporario = new ArrayList<>();
        int count = 0, count2 = 0;

        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntroduza o nome do investigador: ");
        String nome = sc.nextLine();
        System.out.print("\n");
        for (Investigador investigador : investigadores){
            if (nome.equals(investigador.nome)){
                count2++;
                for (Publicacao publicacao :publicacoes){
                    for (Investigador investigador1 : publicacao.autores){
                        if (investigador.nome.equals(investigador1.nome)){
                            for (Publicacao publicacao1 : publicacoesOrdenadas){
                                if (publicacao1.titulo.equals(publicacao.titulo)){
                                    count++;
                                    break;
                                }
                            }
                            if (count == 0){
                                publicacoesOrdenadas.add(publicacao);
                            }
                            count = 0;
                            break;
                        }
                    }
                }
            }
        }
        if (count2 == 0){
            System.out.print("\nO nome que introduziu não corresponde a nenhum investigador presente no sistema!\n");
        } else {
            Collections.sort(publicacoesOrdenadas, new SortPubs());
            for (Publicacao publicacao : publicacoesOrdenadas) {
                System.out.println("[*] " + publicacao.toString());
            }
        }
    }

    /**
     * A funcao lerficheirotextogrupos e responsavel por ler as linhas do ficheiro de texto de grupos e fornecer as
     * respetivas strings a uma funcao lergrupo.
     * @return Devolve um ArrayList contendo os grupos do CISUC.
     */
    public static ArrayList<Grupo> lerficheirotextogrupos(){
        String s, s1;
        Grupo grupo;
        ArrayList<Grupo> grupos = new ArrayList<>();

        try {
            File f = new File("grupos.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while((s = br.readLine()) != null){
                s1 = br.readLine();
                grupo = lergrupo(s, s1);
                grupos.add(grupo);
            }

        } catch (IOException e){
            System.out.println("Exceção " + e +".\n");
        }

        return grupos;
    }

    /**
     * A funcao lergrupo e responsavel por analisar duas strings que correspondem a duas linhas do ficheiro de grupos,
     * analisar o seu conteudo e criar um objeto da classe Grupo.
     * @param s1 Corresponde a primeira linha de texto (Dados relativos ao grupo e Investigador Orientador).
     * @param s2 Corresponde a segunda linha de texto (Dados relativos aos membros do grupo).
     * @return Devolve o grupo correspondente.
     */
    public static Grupo lergrupo(String s1, String s2){
        String nome, acronimo, nomepessoa, email, numgabinete, titulotese, nomeorientador, emailorientador, numgabineteorientador;
        String[] s3, s4, s5, s6, s7, s8;
        int numtelefone, numtelefoneorientador, count = 0;
        int[] datas = new int[3];
        ArrayList<Investigador> membros = new ArrayList<>();
        ArrayList<Estudante> estudantes = new ArrayList<>();
        MembroEfetivo principal;

        s3 = s1.split(", ");
        nome = s3[0];
        acronimo = s3[1];
        s4 = s3[2].split("#");
        nomepessoa = s4[0];
        email = s4[1];
        numgabinete = s4[2];
        numtelefone = Integer.parseInt(s4[3]);
        principal = new MembroEfetivo(nomepessoa, email, numgabinete, numtelefone);
        s5 = s2.split(", ");
        for (int i = 0; i < s5.length; i++){
            s6 = s5[i].split("#");
            nomepessoa = s6[0];
            email = s6[1];
            if (s6.length == 4){
                numgabinete = s6[2];
                numtelefone = Integer.parseInt(s6[3]);
                MembroEfetivo investigador2 = new MembroEfetivo(nomepessoa, email, numgabinete, numtelefone);
                membros.add(investigador2);
            } else if (s6.length == 5){
                titulotese = s6[2];
                s7 = s6[3].split("/");
                for (int j = 0; j < 3; j++){
                    datas[j] = Integer.parseInt(s7[j]);
                }
                Data data = new Data(datas[0], datas[1], datas[2]);
                data.checkData();
                s8 = s6[4].split("-");
                nomeorientador = s8[0];
                emailorientador = s8[1];
                numgabineteorientador = s8[2];
                numtelefoneorientador = Integer.parseInt(s8[3]);
                MembroEfetivo orientador = new MembroEfetivo(nomeorientador, emailorientador, numgabineteorientador, numtelefoneorientador);
                Estudante investigador2 = new Estudante(nomepessoa, email, titulotese, data, orientador);
                estudantes.add(investigador2);
                membros.add(investigador2);
            } else {
                System.out.printf("Pessoa %s possui atributos maiores que 6 ou menores que 5\n", nomepessoa);
                System.exit(-1);
            }
        }
        for (int j = 0; j < estudantes.size(); j++){
            if (estudantes.get(j).InvestigadorOrientador.nome.equals(principal.nome)){
                count++;
            }
            for (Investigador investigador : membros){
                if (investigador.VerMembroEfetivo() == 1){
                    if (investigador.nome.equals(estudantes.get(j).InvestigadorOrientador.nome)){
                        count++;
                        break;
                    }
                }
            }
            if (count == 0){
                System.out.printf("\nO Investigador Orientador do estudante %s não pertence ao grupo. A remover!\n", estudantes.get(j).nome);
                for (int k = 0; k < membros.size(); k++){
                    if (estudantes.get(j).nome.equals(membros.get(k).nome)){
                        membros.remove(k);
                    }
                }

            }
            count = 0;
        }
        Grupo grupo = new Grupo(nome, acronimo, principal, membros);
        for (Investigador investigador : membros){
            investigador.setGrupo(grupo);
        }

        return grupo;
    }

    /**
     * A funcao lerficheirogrupos e responsavel por obter os grupos do CISUC, ou atraves de um ficheiro de objetos ou,
     * caso este ficheiro ainda nao exista, atraves do ficheiro de texto.
     * @return Devolve um ArrayList de todos os grupos presentes no ficheiro de grupos.
     */
    public static ArrayList<Grupo> lerficheirogrupos(){

        ArrayList<Grupo> grupos;
        try {

            FileInputStream fi = new FileInputStream(new File("grupos.obj"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            grupos = (ArrayList<Grupo>) oi.readObject();
            return grupos;

        } catch (IOException e){

            System.out.println("Exceção :" + e + "\n");
            grupos = lerficheirotextogrupos();
            return grupos;

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return null;

        }
    }

    /**
     * A funcao getInvestigadores analisa o ArrayList de grupos e obtem todos os investigadores presentes nesse
     * ArrayList.
     * @param grupos Corresponde ao ArrayList de grupos do CISUC.
     * @return Devolve o ArrayList de todos os investigadores do CISUC.
     */
    public static ArrayList<Investigador> getInvestigadores(ArrayList<Grupo> grupos){
        ArrayList<Investigador> investigadores = new ArrayList<>();
        int count1 = 0;
        int count2 = 0;

        for (Grupo grupo: grupos){
            if (grupo.investigadores.size() != 0){
                for (Investigador investigador : grupo.investigadores) {
                    for (int i = 0; i < investigadores.size(); i++){
                        if (investigador.nome.equals(investigadores.get(i).nome)){
                            count1++;
                        }
                        if (grupo.InvestigadorResponsavel.nome.equals(investigadores.get(i).nome)){
                            count2++;
                        }

                    }
                    if (count1 == 0){
                        investigadores.add(investigador);
                    }
                    if (count2 == 0) {
                        investigadores.add(grupo.InvestigadorResponsavel);
                    }
                    count1 = 0;
                    count2 = 0;
                }
            } else {
                investigadores.add(grupo.InvestigadorResponsavel);
            }


        }

        return investigadores;
    }

    /**
     * A funcao lerficheirotextopublicacoes e responsavel por ler as linhas do ficheiro de texto de grupos e fornecer as
     * respetivas strings a uma funcao lerpublicacao.
     * @param investigadores Corresponde ao ArrayList de investigadores do CISUC.
     * @return Devolve um ArrayList que contem todas as publicacoes do CISUC.
     */
    public static ArrayList<Publicacao> lerficheirotextopublicacoes(ArrayList<Investigador> investigadores){
        String s;
        Publicacao publicacao;
        ArrayList<Publicacao> publicacoes = new ArrayList<>();

        try {

            File f = new File("publicacoes.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);

            while((s = br.readLine()) != null) {
                publicacao = lerpublicacao(s, investigadores);
                publicacoes.add(publicacao);
            }

        } catch (IOException e){
            System.out.println("Exceção " + e +".\n");
        }

        return publicacoes;
    }

    /**
     * A funcao ler publicacao e responsavel por ler linha a linha o ficheiro de texto de publicacoes, analisar o seu
     * conteudo e criar um objeto da classe Publicacao.
     * @param s Corresponde a uma linha de texto do ficheiro de publicacoes.
     * @param investigadores Corresponde ao ArrayList de investigadores do CISUC.
     * @return Devolve o ArrayList de todas as publicacoes do CISUC.
     */
    public static Publicacao lerpublicacao(String s, ArrayList<Investigador> investigadores){
        String titulo, tipo, resumo, nome, localizacao, editora, ISBN;
        int anodepublicacao, audiencia, numero, paginainicio, paginafim;
        Data data;
        String[] s1, s2, palavraschave, autores;
        int[] datas = new int[3];
        ArrayList<Investigador> autores2 = new ArrayList<>();

        s1 = s.split(", ");
        titulo = s1[0];
        palavraschave = s1[1].split("#");
        anodepublicacao = Integer.parseInt(s1[2]);
        audiencia = Integer.parseInt(s1[3]);
        tipo = s1[4];
        resumo = s1[5];
        autores = s1[6].split("#");

        for (String autor : autores){
            for (Investigador investigador : investigadores){
                if ((investigador.nome).equals(autor)){
                    autores2.add(investigador);
                    break;
                }
            }
        }

        if (tipo.equals("Artigo de Conferencia")){

            nome = s1[7];
            s2 = s1[8].split("/");
            for (int i = 0; i < 3; i++){
                datas[i] = Integer.parseInt(s2[i]);
            }
            data = new Data(datas[0], datas[1], datas[2]);
            data.checkData();
            localizacao = s1[9];
            return new ArtigoConferencia(titulo, palavraschave, anodepublicacao, audiencia, tipo, resumo, autores2, nome, data, localizacao);

        } else if (tipo.equals("Artigo de Revista")){

            nome = s1[7];
            s2 = s1[8].split("/");
            for (int i = 0; i < 3; i++){
                datas[i] = Integer.parseInt(s2[i]);
            }
            data = new Data(datas[0], datas[1], datas[2]);
            data.checkData();
            numero = Integer.parseInt(s1[9]);
            return new ArtigoRevista(titulo, palavraschave, anodepublicacao, audiencia, tipo, resumo, autores2, nome, data, numero);

        } else if (tipo.equals("Livro")){

            editora = s1[7];
            ISBN = s1[8];
            return new Livro(titulo, palavraschave, anodepublicacao, audiencia, tipo, resumo, autores2, editora, ISBN);

        } else if (tipo.equals("Capitulo de Livro")){

            editora = s1[7];
            ISBN = s1[8];
            nome = s1[9];
            paginainicio = Integer.parseInt(s1[10]);
            paginafim = Integer.parseInt(s1[11]);
            return new Capitulo(titulo, palavraschave, anodepublicacao, audiencia, tipo, resumo, autores2, editora, ISBN, nome, paginainicio, paginafim);


        } else if (tipo.equals("Livro de Artigo de Conferencia")){
            editora = s1[7];
            ISBN = s1[8];
            nome = s1[9];
            numero = Integer.parseInt(s1[10]);
            return new LivroArtigoConferencia(titulo, palavraschave, anodepublicacao, audiencia, tipo, resumo, autores2, editora, ISBN, nome, numero);

        }
        return null;
    }

    /**
     * A funcao lerficheiropublicacoes e responsavel por obter as publicacoes do CISUC, ou atraves de um ficheiro de
     * objetos ou, caso este ficheiro ainda nao exista, atraves do ficheiro de texto.
     * @param investigadores Corresponde ao ArrayList de investigadores do CISUC.
     * @return Devolve o ArrayList que contem todas as publicacoes do CISUC.
     */
    public static ArrayList<Publicacao> lerficheiropublicacoes(ArrayList<Investigador> investigadores){

        ArrayList<Publicacao> publicacoes;
        try {
            FileInputStream fi = new FileInputStream(new File("publicacoes.obj"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            publicacoes = (ArrayList<Publicacao>) oi.readObject();
            return publicacoes;

        } catch (IOException e){
            System.out.println("Exceção :" + e + "\n");
            publicacoes = lerficheirotextopublicacoes(investigadores);
            return publicacoes;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * A funcao escreverficheirogrupos e usada para criar ou atualizar o ficheiro de objetos contendo os dados de todos
     * os grupos do CISUC.
     * @param grupos Corresponde ao ArrayList de grupos do CISUC.
     */
    public static void escreverficheirogrupos(ArrayList<Grupo> grupos){

        try{
            FileOutputStream fo = new FileOutputStream(new File("grupos.obj"));
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            oo.writeObject(grupos);
        } catch (IOException e){
            System.out.println("Exceção :" + e + "\n");
        }
    }

    /**
     * A funcao escreverficheiropublicacoes e usada para criar ou atualizar o ficheiro de objetos contendo os dados de
     * todas as publicacoes do CISUC.
     * @param publicacoes Corresponde ao ArrayList de publicacoes do CISUC.
     */
    public static void escreverficheiropublicacoes(ArrayList<Publicacao> publicacoes){

        try{
            FileOutputStream fo = new FileOutputStream(new File("publicacoes.obj"));
            ObjectOutputStream oo = new ObjectOutputStream(fo);

            oo.writeObject(publicacoes);

        } catch (IOException e){
            System.out.println("Exceção :" + e + "\n");
        }
    }
}
