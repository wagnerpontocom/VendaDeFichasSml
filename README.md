# FichaFest

Sistema desktop em Java para venda e emissao de fichas em eventos, como festas juninas, quermesses, caminhadas, confraternizacoes e cantinas temporarias.

O sistema permite cadastrar produtos como cerveja, refrigerante, comida, brincadeiras e outros itens, realizar vendas rapidamente por botoes, registrar formas de pagamento, imprimir fichas/comprovantes e gerar fechamentos de caixa e relatorios de vendas.

## Principais recursos

- Cadastro da empresa do evento.
- Cadastro de clientes/pessoas.
- Cadastro de grupos de produtos.
- Cadastro de produtos e servicos com preco de venda.
- Tela de venda rapida por botoes de produto.
- Venda com multiplicador de quantidade.
- Formas de pagamento: dinheiro, PIX, credito, debito, crediario e brinde.
- Calculo de total e troco.
- Impressao de fichas em impressora configuravel.
- Reimpressao de venda.
- Fechamento por periodo de vendas.
- Relatorio por forma de pagamento.
- Relatorio por produto vendido.
- Controle de crediario/fiado.
- Backup dos dados.
- Relatorios JasperReports incluidos em `src/main/resources/reports`.

## Tecnologias

- Java 8
- Swing
- Maven
- JasperReports
- Gson
- Lombok
- JTattoo Look and Feel
- ZXing
- Conectores MySQL e PostgreSQL disponiveis no projeto

Por padrao, a aplicacao usa persistencia em arquivos locais, atraves dos DAOs em `src/main/java/br/com/qualix/*/dao`.

## Estrutura do projeto

```text
.
|-- lib/                         # Bibliotecas locais usadas pelo Maven
|-- src/main/java/br/com/qualix  # Codigo-fonte da aplicacao
|   |-- aaaMain                  # Inicializacao e tela principal
|   |-- aaaConfig                # Configuracoes gerais
|   |-- pessoa                   # Cadastro de pessoas/clientes/empresa
|   |-- proserv                  # Produtos, grupos, pedidos e servicos
|   |-- pequenoprincipe          # Telas de venda e fechamento
|   |-- financeiro               # Pagamentos, contas e recibos
|   |-- print                    # Impressao de fichas e relatorios
|   |-- estoque                  # Controle/consulta de estoque
|   `-- seguranca                # Backup
|-- src/main/resources/assets    # Imagens, icones e fundos da interface
|-- src/main/resources/reports   # Modelos de relatorios Jasper
|-- pom.xml                      # Configuracao Maven
`-- nbactions.xml                # Acoes do NetBeans
```

## Requisitos

- JDK 8 ou superior compativel com Java 8.
- Maven instalado.
- Sistema operacional com ambiente grafico, pois a aplicacao e desktop/Swing.
- Impressora instalada no sistema operacional para emissao das fichas.

Observacao: o projeto possui dependencias locais em `lib/`, usadas para componentes como campos numericos e notificacoes Swing. Mantenha essa pasta no repositorio ou no ambiente de build.

## Como executar em desenvolvimento

Pelo Maven:

```bash
mvn process-classes org.codehaus.mojo:exec-maven-plugin:3.1.0:exec -Dexec.mainClass=br.com.qualix.aaaMain.AInicioSistema
```

Tambem e possivel abrir o projeto no NetBeans e executar a acao `run`, ja configurada em `nbactions.xml`.

## Como gerar o JAR

```bash
mvn clean package
```

O `pom.xml` configura a classe principal:

```text
br.com.qualix.aaaMain.AInicioSistema
```

E tambem configura o empacotamento com dependencias pelo `maven-assembly-plugin`.

Depois do build, execute o JAR gerado em `target/`:

```bash
java -jar target/SisteFacil-QSX-jar-with-dependencies.jar
```

Dependendo da configuracao/local do build, o nome final do arquivo pode variar. Verifique os arquivos gerados dentro da pasta `target/`.

## Dados da aplicacao

Por padrao, os dados sao gravados em arquivos locais no caminho:

```text
C:\Qualix\sistema\small\
```

Esse caminho esta definido em:

```text
src/main/java/br/com/qualix/aaaConfig/Configuracoes.java
```

Arquivos como produtos, pedidos, pessoas, contas e configuracoes sao criados usando os nomes/versionamentos definidos nessa mesma classe.

## Fluxo basico de uso

1. Abra o sistema.
2. Cadastre a empresa do evento.
3. Cadastre os grupos de produtos, se necessario.
4. Cadastre os produtos que serao vendidos, por exemplo:
   - Cerveja
   - Refrigerante
   - Pastel
   - Cachorro-quente
   - Brincadeira
5. Acesse a tela de venda.
6. Selecione os produtos pelos botoes.
7. Informe a forma de pagamento.
8. Finalize a venda para gravar e imprimir a ficha.
9. Ao final do periodo, use o fechamento para conferir pagamentos, produtos vendidos e crediario.

## Impressao

A impressora e selecionada pelo menu:

```text
Configuracoes > Selecionar Impressora
```

O sistema usa os servicos de impressao do Java (`javax.print`) e permite configurar o tamanho da bobina, como 58mm, 80mm ou 90mm.

## Relatorios e fechamento

O sistema possui telas e modelos para:

- Relatorio de vendas.
- Relatorio de clientes por cidade.
- Fechamento de caixa por intervalo de vendas.
- Totalizacao por produto vendido.
- Controle de crediario.
- Impressao de fechamento para assinatura do responsavel.

Os arquivos `.jrxml` ficam em:

```text
src/main/resources/reports
```

## Backup

O menu `Backup` possui a opcao para gerar copia dos dados locais. Como a persistencia padrao e baseada em arquivos, recomenda-se manter uma rotina de backup da pasta de dados antes e depois de cada evento.

## Observacoes para manutencao

- A classe inicial do sistema e `br.com.qualix.aaaMain.AInicioSistema`.
- A tela principal usada pela versao atual e `br.com.qualix.aaaMain.FBackground5`.
- A tela de venda principal e `br.com.qualix.pequenoprincipe.visao.FVenda`.
- Existe uma variante especifica para festa junina em `br.com.qualix.pequenoprincipe.visao.FVendaJunino`.
- A configuracao de persistencia atual aponta para DAOs baseados em arquivos em `DaoFactory`.
- O projeto contem formularios `.form` do NetBeans junto das telas Swing.

## Status do build

Foi feita uma tentativa de build local com:

```bash
mvn -DskipTests package
```

Neste ambiente, a execucao foi interrompida porque o Maven precisou baixar plugins do Maven Central e o acesso de rede estava bloqueado. Em um ambiente com internet ou cache Maven ja populado, use os comandos acima para compilar e gerar o JAR.
