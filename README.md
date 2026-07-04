# FichaFest Small

Sistema desktop simples, feito em Java/Swing, para emissao de fichas em eventos.

A proposta desta versao nao e ser um ERP completo. Ela existe para atender um fluxo direto: cadastrar a empresa, configurar a impressora, selecionar produtos em uma tela de venda rapida, registrar a forma de pagamento e emitir a ficha para entrega ao cliente.

## O que o sistema faz

- Cadastro basico da empresa do evento.
- Cadastro de produtos que aparecerao como botoes na tela de venda.
- Venda rapida por clique nos produtos.
- Multiplicador de quantidade para adicionar varias fichas do mesmo item.
- Identificacao opcional do cliente.
- Formas de pagamento: dinheiro, PIX, credito, brinde e crediario.
- Calculo de total e troco.
- Impressao da ficha em impressora configurada.
- Reimpressao de ficha pelo numero da venda.
- Fechamento simples por intervalo de vendas.
- Impressao de resumo por pagamentos, produtos vendidos e crediario.

## O que esta fora do escopo

Este projeto e intencionalmente enxuto. Ele nao deve ser tratado como sistema completo de gestao comercial, fiscal, estoque ou financeiro. Existem classes, telas e relatorios herdados de uma base maior, mas a versao atual esta voltada principalmente para a emissao de ficha em eventos.

## Tecnologias

- Java 8
- Swing
- Maven
- JasperReports
- Gson
- Lombok
- JTattoo Look and Feel
- ZXing
- Bibliotecas locais em `lib/`

Por padrao, a persistencia usada pela aplicacao e em arquivos locais.

## Estrutura principal

```text
.
|-- lib/                         # Bibliotecas locais usadas pelo Maven
|-- src/main/java/br/com/qualix  # Codigo-fonte da aplicacao
|   |-- aaaMain                  # Inicializacao e tela principal
|   |-- aaaConfig                # Configuracoes gerais
|   |-- pessoa                   # Cadastro da empresa e pessoas
|   |-- proserv                  # Produtos e pedidos
|   |-- pequenoprincipe          # Venda, ficha e fechamento simples
|   |-- print                    # Impressao
|   `-- database/dao/service     # Selecao dos DAOs usados
|-- src/main/resources/assets    # Imagens e icones da interface
|-- src/main/resources/reports   # Modelos JasperReports herdados
|-- pom.xml                      # Configuracao Maven
`-- nbactions.xml                # Acoes do NetBeans
```

## Fluxo basico de uso

1. Abrir o sistema.
2. Cadastrar a empresa do evento.
3. Configurar a impressora.
4. Cadastrar ou ajustar os produtos que serao vendidos.
5. Abrir a tela `Venda`.
6. Selecionar os produtos pelos botoes.
7. Informar a forma de pagamento.
8. Finalizar a venda para gravar e imprimir a ficha.
9. Ao final do periodo, usar o fechamento para conferir vendas e imprimir os resumos necessarios.

## Impressao

A impressora e selecionada em:

```text
Configuracoes > Selecionar Impressora
```

O sistema usa os servicos de impressao do Java (`javax.print`). O tamanho de bobina padrao fica nas configuracoes da aplicacao, com suporte a opcoes como 58mm, 80mm e 90mm.

## Dados da aplicacao

Os dados locais sao gravados por padrao em:

```text
C:\Qualix\sistema\small\
```

Esse caminho esta definido em:

```text
src/main/java/br/com/qualix/aaaConfig/Configuracoes.java
```

Os DAOs ativos sao baseados em arquivos e estao selecionados em:

```text
src/main/java/br/com/qualix/database/dao/service/DaoFactory.java
```

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

A classe principal configurada no `pom.xml` e:

```text
br.com.qualix.aaaMain.AInicioSistema
```

Depois do build, execute o JAR gerado em `target/`. O nome esperado pelo empacotamento atual e semelhante a:

```bash
java -jar target/SisteFacil-QSX-jar-with-dependencies.jar
```

Se o nome variar, confira os arquivos gerados na pasta `target/`.

## Pontos importantes para manutencao

- Entrada do sistema: `br.com.qualix.aaaMain.AInicioSistema`.
- Tela principal: `br.com.qualix.aaaMain.FBackground5`.
- Tela de venda/emissao de ficha: `br.com.qualix.pequenoprincipe.visao.FVenda`.
- Configuracoes gerais e caminho dos dados: `br.com.qualix.aaaConfig.Configuracoes`.
- Persistencia em arquivos: `br.com.qualix.database.dao.service.DaoFactory`.
- Impressao de fichas e textos: classes em `br.com.qualix.print`.

## Observacoes

- A pasta `lib/` deve ser mantida, pois contem dependencias locais usadas pelo Maven.
- O projeto possui arquivos `.form` do NetBeans junto das telas Swing.
- Ha conectores e relatorios herdados de versoes maiores, mas nem tudo faz parte do fluxo principal desta versao.
- Antes de usar em um evento, teste a impressora, a bobina e a emissao de algumas fichas de exemplo.
