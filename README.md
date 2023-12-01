
# Sistema de gerenciamento de hotéis com JPA e Hibernate

![Logo da StayFy](https://i.imgur.com/wd6Wq99.png)

O projeto é sobre um sistema criado por mim chamado 
de StayFy. O sistema permite que a administração dos 
hotéis possa fazer de forma rápida, 
eficiente e organizada o cadastro dos hóspedes, 
o cadastro dos quartos e o cadastro das reservas. É importante ressaltar que as 
informações salvas no sistema irão estar localizados em nuvem, pois dessa forma os
dados estarão mais seguros e disponíveis 
Além disso, é importante ressaltar que o projeto está 
em constante evolução e logo mais trarei novas 
funcionalidades para ele. A ideia é que o usuário do aplicativo possa escolher
entre vários planos de assinatura, ou seja, o serviço trabalha com escalabilidade. 
O usuário só paga pelo armazenamento que usa.

## Tecnologias utilizadas 

### Java 17
![Imagem Java](https://th.bing.com/th/id/R.fcb9bf4f7b1c2a15445450b9e20711c7?rik=t3DOw4O5XsNI4Q&pid=ImgRaw&r=0)

### JPA e Hibernate 5.6.15
![Imagem JPA e Hibernate](https://th.bing.com/th/id/R.abfb5be086b83901d8e911611f948cfe?rik=aiTxpwhDsA251Q&riu=http%3a%2f%2fwww.formadoresit.es%2fwp-content%2fuploads%2f2018%2f07%2fjpa_hibernate.png&ehk=1WBsnxS5vmkfuCITGc52x8kClJ6xv33ziA4hKDf4194%3d&risl=&pid=ImgRaw&r=0)

### Apache Maven 4.0.0
![Imagem Apache Maven](https://th.bing.com/th/id/OIP.xs-AOeTbtv0UukYu-Uu2mAHaB4?rs=1&pid=ImgDetMain)

### MySQL 8.0.33
![Imagem MySQL](https://th.bing.com/th/id/OIP.JVt34lGxmm0GAGNNL_mwBgHaHa?rs=1&pid=ImgDetMain)

### Estrutura dos diretórios
![Imagem da estrutura](https://i.imgur.com/LoHb8rK.png)

### Dependências do projeto no pom.xml
![Imagem do pom.xml](https://i.imgur.com/BtwKQ86.png)

### Modelagem do banco de dados (Modelo Físico)
![Modelo do banco de dados](https://i.imgur.com/d73OtNp.png)

## Telas do aplicativo 


### Tela de Login

![Tela de Login](https://i.imgur.com/dDxbA2S.jpeg)

A tela de login é 
bem simples e fácil de entender. 
Basta o usuário acessar utilizando o email 
cadastrado e a sua senha. Em caso de esquecimento de 
senha é possível recuperá-la através do email .

### Tela de opções

![Tela de opções](https://i.imgur.com/eTNALaY.jpg)

Na barra de busca é possível buscar a reserva pelo seu ID próprio, 
nome ou CPF do cliente. Além disso, é possível visualizar o número de hóspeedes,
bem como a quantidade dos quartos que estão ocupados.
No botão de nova reserva, é possível fazer a reserva.
Logo abaixo é possível mostrar todas as reservas,
bem como todos os quartos.

### Tela de check-in e check-out

![Tela de check_in](https://i.imgur.com/KFClBPF.jpeg)

Nessa tela, o usuário irá especificar
o número de adultos, o número de crianças, o número de quarto
e a data de check-in e checkout. Alguns hotéis
cobram valores diferentes para crianças e adultos, 
portanto, é necessário
que seja especificado o número de adultos e crianças.

### Tela de cadastro de clientes

![Tela de cadastro](https://i.imgur.com/c4Aky9v.jpeg)

Nessa tela, o usuário irá preencher os dados dos hóspedes do quarto, informando nome comleto,
rg, cpf, email, data de nascimento e telefone.
Sendo que apenas nome, rg, cpf e  data de nascimento são são obrigatórios.

