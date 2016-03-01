# [BuscaCEP](https://github.com/natanoliveiracruz/buscaCEP)
App desenvolovido com a finalidade de adquirir conhecimentos sob a plataforma android.

# Descrição

#### Requisicao do cep na api viacep.
#### Implementação de lista.
#### Implementação de banco de dados SQLite
#### Crud
#### Visualizção dos detalhes de um item  dentro da lista.

![Tela Inicial](https://github.com/natanoliveiracruz/buscaCEP/blob/master/screenshot/device-2015-11-23-083016.png "Tela Inicial")
![Tela de Busca](https://github.com/natanoliveiracruz/buscaCEP/blob/master/screenshot/device-2015-11-23-082922.png "Tela de Busca")
![Tela de Listagem](https://github.com/natanoliveiracruz/buscaCEP/blob/master/screenshot/device-2015-11-23-083034.png "Tela de Listagem")
![Tela de Detalhes](https://github.com/natanoliveiracruz/buscaCEP/blob/master/screenshot/device-2015-11-23-083539.png "Tela de Detalhes")

## Créditos.
App android de busca de CEP utilizando a api json do site https://viacep.com.br

# Testes
Para executar os testes funcionais serão necessários: 

- Ruby na versão 2.2.1 ou superior (para instalar será necessário rbenv ou rvm);
- Bundler (default):

```ruby
gem install bundler
```
- Executar o seguinte comando na pasta: buscaCEP/app/src/test/functional_tests 

```ruby
bundle install
```
Esse comando vai instalar todas as gems necessárias para execução dos testes que estão no arquivo Gemfile. Importante ressaltar que a gem cucumber deverá ser a de versão 1.3.17.

- Variáveis de ambiente ANDROID_HOME, ANDROID_SDK, JAVA_HOME (jdk 1.7.0 ou superior), ANDROID_TOOLS e ANDROID_PLATFORM_TOOLS devem estar com os apontamentos corretos;
- Pacotes adb instalados corretamente via SDK Manager.
- Versão do Android executadas para esse teste: 
Tools => SDK Platafform Tools = 23.0.1
Tools => Build- Tools = 22.0.1

Android suportados: 4.1.2 até 5.1.1.

Para executar os testes deverá navegar até a pasta buscaCEP/app/src/test/functional_tests e digitar o seguinte comando: 
```ruby
calabash-android run BuscaCep.apk --require features features/specifications/feature_a_ser_executada.feature
```
ou caso queria rodar todos os testes, executar: 

calabash-android run BuscaCep.apk
