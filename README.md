## Desafio Financeiro
``Esse projeto tem como objeto ser uma prova de conceito demostrativa de conhecimento.``
### ``Executando o projeto``
0. Para a execução do projeto, é necessário que se tenha docker configurado na máquina
1. Após configurado o docker execute via terminal o seguinte comando ``docker-compose docker-compose up``, ou se preferir, podemos utilizar a interface grafica atráves de um plugin do intellij ``Abra as configurações e busque por plugins, na barra de pesquisa digite docker e intale o plugin.``
2. Para este projeto utilizei como gradle JVM a Oracle OpenJdk 19.0.2
3. Certifique-se de que o gradle está sincronizado, para isto basta abrir na IDE o arquivo build.gradle.kts, quando sincronizado basta executar o comando de build através da IDE ou executando o comando via terminal ``./gradlew build`` dentro da pasta raiz do projeto.
4. Após todos os passos pode-se executar a aplicação através do comando bootRun no menu lateral do gradle dentro do intellij ou via terminal com ``./gradlew bootrun``
5. Para utilizar as apis da aplicação, temos a pasta collection, nela temos a collection no formato json que pode ser importada na ferramenta ``postman``.
