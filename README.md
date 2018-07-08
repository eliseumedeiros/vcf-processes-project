# VCF-Processes Project
Projeto em Bioinformática para manipulação de dados e identificação de relevância em mutações sobre os arquivos de Variant Call Format (VCF). Para maiores informações sobre o funcionamento do projeto, ver arquivo (README-VCF-ProcessesProject.pdf)

## RESUMO
### PROPOSTA:
Na atualidade a demanda por chamada de variantes de um genoma vem crescendo principalmente devido aos avanços na biologia molecular e na medicina de precisão, que visa o tratamento mais adequado e otimizado de acordo com o contexto genômico do paciente, é inegável que os estudos e análises pontuais do material genético de determinado organismo possam direcionar para um tratamento medicamentoso mais adequado e efetivo. O processo de anotação de variantes genômicas é um processo laborioso que exige muito esforço técnico/computacional, muitas vezes requerendo conhecimentos avançados de programação e sistemas operacionais como o linux. Dado e exposto o objetivo desse projeto foi o desenvolvimento de software para o sistema operacional Windows® com uma interface friendly que oferece uma forma facilitada de anotação e análise de variantes genômicas.

### MÉTODOS:
O procedimento de anotação e identificação de relevância de mutações, deriva dos alelos variantes identificados após os processos de alinhamento de sequências e chamada de variantes, que são padronizados em um arquivo bruto, denominado Variant Call Format (VCF), que servirá de input para ferramenta proposta. Para a anotação dos alelos variantes a plataforma utiliza bancos de polimorfismos (dbSNP), preditores de efeito deletério (Sift, Polyphen, Proven) e anotadores de variantes como o SnpEff e SnpSift. Sobre os alelos variantes essas ferramentas fornecem diversas características que serão convertidas em features internamente em nosso sistema.

### RESULTADOS:
Desenvolvemos uma interface com objetivo de fornecer de forma facilitada a anotação de mutações, dando relevância quanto a sua patogenicidade. Além da anotação também foi construído um sistema de filtros de seleção de variantes sobre as colunas (features), atuando como filtro direto sobre as features para, por exemplo, fazer buscas por genes, posições genômicas, patogenicidades, etc. Remoção e seleção dinâmica de colunas de forma a facilitar o processo de análise em colunas de interesse de estudo, no arquivo. Por fim a ferramenta oferece a possibilidade de salvar os resultados obtidos pelas anotações e filtros utilizados, em arquivos no formato  EXCEL ou CSV.

### CONCLUSÃO:
Foi desenvolvido um software Windows® que facilita a análise de variantes genômicas, fornecendo a anotação gênica, informações de patogenicidade além de oferecer filtros para determinar a relevância mutacional no contexto gênico estudado.  

### Requisitos para execução
1. Sistema operacional Windows
2. Java 64 bits instalado (verificar atualização: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
3. Memória RAM disponível de 4GB

### Download Executável 
(todos os arquivos precisam estar no mesmo diretório)
https://drive.google.com/drive/folders/12GEZz13KasGg6P6TCgYDNGuJXpJLk71R?usp=sharing

 
