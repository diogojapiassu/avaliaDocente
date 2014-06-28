avaliaDocente
=============

Sistema para avaliação de docentes da UFG. Trabalho prático das disciplinas de "Desenvolvimento de Software Concorrente" e "Desenvolvimento de Software para Persistência", ministradas pelo professor Marcelo Ricardo Quinta no curso de Engenharia de Software, Instituto de Informática, UFG.

#### Integrantes do grupo:
* Danilo Guimarães
* Diogo Japiassu
* Douglas Japiassu
* Jhonatan Santos
* Jober Rebouças
* Luã Silvério
* Rafael Cunha

***

#### Overview
Construir uma biblioteca de software que permita a avaliação em lote de dados de docentes da UFG segundo a [Resolução 32/2013](http://www.adufg.org.br/dados/editor3/file/Resolucao_CONSUNI_2013_0032.pdf "Resolução 32/2013").

#### Motivação
A cada ano, é sempre um problema para a Universidade Federal de Goiás avaliar docentes e departamentos. Existem diversas resoluções (atualizadas periodicamente) para gerar informações de avaliação e os sistemas não estão completos para realizar tal atividade. Portanto, precisamos de um sistema que permita o cadastro de docentes,atividades e avaliações. O cadastro de docente depende das informações do Departamento Pessoal. Atividades são definidas nas resoluções, como a 32/2013, que descreve áreas, fórmulas e pontuações de professores, inclusive para estágio probatório. Lembre-se: caso a resolução mude, o sistema deve se adequar não via código, mas de acordo com as informações cadastradas.

#### Objetivo
O projeto consiste em desenvolver um sistema capaz de receptar, processar e exibir as informações relacionadas a avaliação de desempenho dos docentes da UFG, conforme descrito na [Resolução 32/2013](http://www.adufg.org.br/dados/editor3/file/Resolucao_CONSUNI_2013_0032.pdf "Resolução 32/2013").

O sistema deve permitir o upload dos arquivos através de uma interface Web*, processá-los e exibir o resultado a medida que forem processados.
Estima-se uma massa de dados contendo 10.000 arquivos, sendo cada arquivo a representação de uma ficha com os dados da avaliação de um docente.

```
* Checar documentação de requisitos.
```

Serão priorizados nesse projeto um desempenho aceitável no processamento (em paralelo) da massa de dados e uma boa experiência de interação homem-computador.



***

### Arquitetura

#### Tecnologias empregadas
Serão empregadas as seguintes tecnologias durante o desenvolvimento do projeto:

* [Java](http://java.com), como plataforma geral de desenvolvimento;
* [Apache Maven](http://maven.apache.org), como ferramenta de integração e life-cycle management;
* [Spring Framework](http://docs.spring.io/spring-framework/docs/3.2.4.RELEASE/spring-framework-reference/html/overview.html#overview-dependency-injection), como injeção de dependencia e inversão de controle;
* [Spring Data JPA](http://docs.spring.io/spring-data/jpa/docs/1.4.2.RELEASE/reference/html/), como solução de persistência;
* [JSF](https://javaserverfaces-spec-public.java.net/), como especificação dos componentes web;
* [Primefaces](http://primefaces.org), como biblioteca de componentes JSF;
* [Jackson](http://jackson.codehaus.org/), como JSON processor;
* [C3P0](http://sourceforge.net/projects/c3p0/), como pool de conexões JDBC;

Os demais aspectos do projeto ainda estão em aberto para discussão.

***

