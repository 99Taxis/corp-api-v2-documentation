## 99 CORP - Documentação API

Documentação para uso dos endpoints da API para clientes corporativos da 99.

Endereço de acesso: https://api.corp.99taxis.com/v2

## Swagger

É possível realizar testes online utilizando a interface do Swagger. Para isso, acesse o endereço abaixo:

https://api.corp.99taxis.com/v2/docs/swagger-ui.html

## Autenticação

Todas as requisições precisam informar o token de acesso para o processo de autenticação. Informe a chave com nome `x-api-key` juntamente com o valor do token de acesso no cabeçalho HTTP de cada requisição. 

Considerando o cenário de exemplo onde a chave de acesso possui o valor `key-abc-123`, segue chamada CURL para a listagem de colaboradores:

```
curl -X GET PUT URL -H 'x-api-key: key-abc-123'
```

## Seções

- [Centro de Custo](#centro-de-custo)
- [Colaboradores](#colaboradores)
- [Colaboradores com Identificador Externo](#colaboradores-com-identificador-externo)

## Centro de Custo

#### Busca de centros de custo

* **URL**
  
  `/costcenters`

* **Método**

  `GET`
  
*  **Parâmetros via query**

      | Atributo   | Tipo do dado   | Descrição                                  | Obrigatório | Valor padrão | Exemplo     |
      |------------|----------------|------------------------------------------- |-------------|--------------|-------------|
      | search     | alfanumérico   | Busca pelo nome do centro de custo         | não         | -            | Atendimento |
      | limit      | numérico       | Quantidade de registros por página         | não         | 50           | 20          |
      | offset     | numérico       | Índice da página no sistema de paginação   | não         | 1            | 0           |
  
* **Retorno**
    
    **Status Code:** 200
    
    ```json
    [
      {
        "id": 1,
        "name": "Atendimento",
        "enabled": true,
        "company": {
          "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
          "name": "99"
        }
      },
      {
        "id": 2,
        "name": "Financeiro",
        "enabled": true,
        "company": {
          "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
          "name": "99"
        }
      }
    ]
    ```
-----

#### Cadastro de centro de custo

* **URL**
  
  `/costcenters`

* **Método**

  `POST`
  
*  **Parâmetros via body**

    | Atributo   | Tipo do dado   | Descrição                                  | Obrigatório | Valor padrão | Exemplo     |
    |------------|----------------|------------------------------------------- |-------------|--------------|-------------|
    | name       | alfanumérico   | Nome do centro de custo                    | sim         | -            | Atendimento |
  
* **Exemplo de envio**

    ```json
    {
      "name": "Atendimento"
    }
    ```

* **Retorno**
    
    **Status Code:** 201
    
    ```json
    {
      "id": 1,
      "name": "Atendimento",
      "enabled": true,
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      }
    }
    ```
-----

#### Busca de centros de custo por identificador

* **URL**
  
  `/costcenters/{id}`

* **Método**

  `GET`
  
*  **Parâmetros via url**

      | Atributo   | Tipo do dado   | Descrição                         | Obrigatório | Valor padrão | Exemplo     |
      |------------|----------------|-----------------------------------|-------------|--------------|-------------|
      | id         | numérico       | Identificador do centro de custo	| sim         | -            | 20          |
  
* **Retorno**
    
    **Status Code:** 200
    
    ```json
    {
      "id": 1,
      "name": "Atendimento",
      "enabled": true,
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      }
    }
    ```
-----

#### Desativar um centro de custo

* **URL**
  
  `/costcenters/{id}`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**

      | Atributo   | Tipo do dado   | Descrição                         | Obrigatório | Valor padrão | Exemplo     |
      |------------|----------------|-----------------------------------|-------------|--------------|-------------|
      | id         | numérico       | Identificador do centro de custo	| sim         | -            | 20          |
  
* **Retorno**
    
    **Status Code:** 204

-----

#### Busca de responsável de centro de custo por identificador

* **URL**
  
  `/costcenters/{id}/owners`

* **Método**

  `GET`
  
*  **Parâmetros via url**

      | Atributo   | Tipo do dado   | Descrição                         | Obrigatório | Valor padrão | Exemplo     |
      |------------|----------------|-----------------------------------|-------------|--------------|-------------|
      | id         | numérico       | Identificador do centro de custo	| sim         | -            | 20          |
  

* **Retorno**
    
    **Status Code:** 200
    
    ```json
    [{
      "name": "José Santos",
      "email": "jose.santos@empresa.com.br"
    }]
    ```
-----

#### Alteração de responsável de centro de custo por identificador

* **URL**
  
  `/costcenters/{id}/owners`

* **Método**

  `PUT`
  
*  **Parâmetros via url**

      | Atributo   | Tipo do dado   | Descrição                         | Obrigatório | Valor padrão | Exemplo     |
      |------------|----------------|-----------------------------------|-------------|--------------|-------------|
      | id         | numérico       | Identificador do centro de custo	| sim         | -            | 20          |
  
*  **Parâmetros via body**

    Lista de responsáveis:

    | Atributo          | Tipo do dado     | Descrição             | Obrigatório | Valor padrão | Exemplo                      |
    |-------------------|------------------|-----------------------|-------------|--------------|------------------------------|
    | name              | alfanumérico     | Nome do responsável   | sim         | -            | José Santos                  |
    | email             | alfanumérico     | E-mail do responsável | sim         | -            | jose.santos@empresa.com.br   |

*  **Regras**
  
    Se o responsável já tem uma conta, o `name` será desconsiderado.
  
    Se o responsável já tem uma conta e o perfil for tipo `EmployeeUser`, o perfil será mudado para `OwnerUser`

    Se o responsável não tem uma conta, a mesma será criada com o `name` e o `email`


* **Exemplo de envio**

    ```json
    [{
      "name": "José Santos",
      "email": "jose.santos@empresa.com.br"
    }]
    ```

* **Retorno**
    
    **Status Code:** 200
    
    ```json
    [{
      "name": "José Santos",
      "email": "jose.santos@empresa.com.br"
    }]
    ```

-----

## Colaboradores

#### Busca de colaboradores

* **URL**

  `/employees`

* **Método**

  `GET`
  
*  **Parâmetros via query**

    | Atributo   | Tipo do dado   | Descrição                                  | Obrigatório | Valor padrão | Exemplo     |
    |------------|----------------|------------------------------------------- |-------------|--------------|-------------|
    | search     | alfanumérico   | Busca pelo nome do colaborador             | não         | -            | José Santos |
    | offset     | numérico       | Índice da página no sistema de paginação   | não         | 1            | 0           |
    | limit      | numérico       | Quantidade de registros por página         | não         | 50           | 20          |
    | page       | numérico       | Depreciado                                 | não         | -            | -           |
    | nationalId | alfanumérico   | Documento do colaborador (somente números) | não         | -            | 98765432100 |

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "name": "José Santos",
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      },
      "email": "jose.santos@empresa.com.br",
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      },
      "nationalId": "98765432100",
      "supervisorId": 167,
      "pin": "053",
      "enabled": true,
      "externalId": 0,
      "categories": [
        "regular-taxi",
        "top99",
        "turbo-taxi",
        "pop99"
      ],
      "id": 125
    }
    ``` 
-----

#### Cadastro de colaborador

* **URL**

  `/employees`

* **Método**

  `POST`
  
*  **Parâmetros via body**

    | Atributo               | Tipo do dado              | Descrição                                                                                                | Obrigatório | Valor padrão | Exemplo                    |
    |------------------------|---------------------------|----------------------------------------------------------------------------------------------------------|-------------|--------------|-------------               |
    | employee.name          | alfanumérico              | Nome do colaborador                                                                                      | sim         | -            | José Santos                |
    | employee.phone.number  | alfanumérico              | Número do telefone do colaborador (somente números)                                                      | sim         | -            |  11999999999               |
    | employee.phone.country | alfanumérico              | Código do país atrelado ao número                                                                        | não         | BRA          | BRA                        |
    | employee.email         | alfanumérico              | E-mail do colaborador                                                                                    | sim         | -            | jose.santos@empresa.com.br |
    | employee.nationalId    | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                         | não         | -            | 98765432100                |
    | employee.pin           | alfanumérico              | Código de confirmação de corrida (deve conter 3 dígitos)                                                 | não         | -            | 934                        |
    | employee.externalId    | numérico                  | Identificador externo do colaborador. É possível relacionar o identificador de um sistema externo.       | não         | -            | 456                        |
    | employee.supervisorId  | numérico                  | Id do supervisor (employeeId)do colaborador.                                                             | não         | -            | 256                        |
    | employee.categories    | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99   | sim         | -            | regular-taxi, turbo-taxi   |
    | sendWelcomeEmail       | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                  | não         | false        | false                      |

    
*  **Regras**

    Ao cadastrar um colaborador é necessário informar o nationalId ou o pin. Ambos os atributos podem ser cadastrados, mas ao menos um deles é obrigatório.
    
    Quando o colaborador realizar uma corrida da categoria "regular-taxi", ao término da mesma será necessário entrar com um código de confirmação do pagamento. Esse código será os 3 primeiros dígitos do CPF ou o código pin cadastrado (também de 3 dígitos).

*   **Exemplo de envio**

    ```json
    {
      "employee": {
        "email": "jose.santos@empresa.com.br",
        "name": "José Santos",
        "nationalId": "98765432100",
        "externalId": 55091,
        "pin": "053",
        "usePin": true,
        "categories": [
          "regular-taxi",
          "turbo-taxi",
          "pop99"
        ],
        "phone": {
          "number": "11999999999",
          "country": "BRA"
        }
      },
      "sendWelcomeEmail": false
    }
    ```


* **Retorno**
  
  **Status Code:** 201
  
    ```json
    {
      "employee": {
        "email": "jose.santos@empresa.com.br",
        "name": "José Santos",
        "nationalId": "98765432100",
        "externalId": 55091,
        "pin": "053",
        "usePin": true,
        "categories": [
          "regular-taxi",
          "turbo-taxi",
          "pop99"
        ],
        "phone": {
          "number": "11999999999",
          "country": "BRA"
        }
      },
      "sendWelcomeEmail": false
    }
    ```
    
-----

#### Busca de centro de custo do colaborador

* **URL**

  `/employees/{employeeId}/costcenter`

* **Método**

  `GET`
  
*  **Parâmetros via query**

    | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo    |
    |--------------|------------------|--------------------------------|-------------|--------------|------------|
    | id           | numérico         | Identificador do colaborador   | sim         | -            | 20         |
    


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      {
        "id": 77045,
        "name": "IntegrationAPI"
      }
    ]
    ``` 
-----

#### Atualizar centro de custos do colaborador

* **URL**

  `/employees/{employeeId}/costcenter`

* **Método**

  `PATCH`
  
*  **Parâmetros via url**

   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | employeeId   | numérico         | Identificador do colaborador                 | sim             | -                | 125            |
   
  
*  **Parâmetros via body**

    | Atributo                   | Tipo do dado              | Descrição                             | Obrigatório | Valor padrão | Exemplo   |
    |----------------------------|---------------------------|---------------------------------------|-------------|--------------|-----------|
    | costCenterIDs              | conjunto de numéricos     | Identificadores de centros de custo   | sim         | -            | 100, 200  |

*   **Exemplo de envio**

    ```json
    {
      "costCenterIDs": [
        100,
        200
      ]
    }
    ``` 


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      100,
      200
    ]
    ```
    
-----

#### Desassociar um centro de custo de um colaborador

* **URL**

  `/employees/{employeeId}/costcenter/{costCenterId}`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | employeeId   | numérico         | Identificador do colaborador                 | sim             | -                | 10             |
   | costCenterId | numérico         | Identificador do centro de custo             | sim             | -                | 20             |
  
* **Retorno**
  
  **Status Code:** 204
  
-----

#### Busca de política de uso do colaborador

* **URL**

  `/employees/{employeeId}/policy`

* **Método**

  `GET`
  
*  **Parâmetros via query**

    | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo    |
    |--------------|------------------|--------------------------------|-------------|--------------|------------|
    | employeeId   | numérico         | Identificador do colaborador   | sim         | -            | 20         |
    


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "budget": {
        "enabled": true,
        "limit": 100.00,
        "period": "date",
        "validUntil": "2019-04-22"
      },
      "rideCredit": {
        "credit": 10,
        "enabled": true
      }
    }
    ``` 
-----

#### Atualizar política de uso do colaborador

* **URL**

  `/employees/{employeeId}/policy`

* **Método**

  `PUT`
  
*  **Parâmetros via query**

    | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo    |
    |--------------|------------------|--------------------------------|-------------|--------------|------------|
    | employeeId   | numérico         | Identificador do colaborador   | sim         | -            | 20         |
    
*  **Parâmetros via body**

    | Atributo                 | Tipo do dado              | Descrição                                                                                         | Obrigatório | Valor padrão | Exemplo                    |
    |--------------------------|---------------------------|---------------------------------------------------------------------------------------------------|-------------|--------------|----------------------------|
    | budget.enabled           | verdadeiro/falso          | Se verdadeiro, política por orçamento estará ativa para o colaborador                                           | sim         | false        | false                      |
    | budget.limit             | numérico                  | Limite de crédito disponível para corridas do colaborador                                         | não         | -            | 100.00                     |
    | budget.period            | alfanumérico              | Periodo de atividade da política por orçamento (valores: "monthly" ou "date")                                   | não         | -            | monthly                    |
    | budget.validUntil        | alfanumérico              | Data de validade da política por orçamento                                                                     | não         | -            | 2019-04-22                 |
    | rideCredit.enabled       | verdadeiro/falso          | Se verdadeiro, a política por quantidade de corridas estará ativo para o colaborador                               | sim         | false        | false                      |
    | rideCredit.credit        | numérico                  | Quantidade de corridas autorizadas para o colaborador                                             | não         | -            | 10                         |
    
> Os atributos `budget.limit` e `budget.period` serão obrigatórios caso `budget.enabled` seja `true`  
  O atributo `budget.validUntil` será obrigatório caso `budget.period` seja `date`  
  O atributo `rideCredit.credit` será obrigatório caso `rideCredit.enabled` seja `true`


*   **Exemplo de envio**

    ```json
      {
        "budget": {
          "enabled": true,
          "limit": 100.00,
          "period": "date",
          "validUntil": "2019-04-22"
        },
        "rideCredit": {
          "credit": 10,
          "enabled": true
        }
      }
    ``` 

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "budget": {
        "enabled": true,
        "limit": 100.00,
        "period": "date",
        "validUntil": "2019-04-22"
      },
      "rideCredit": {
        "credit": 10,
        "enabled": true
      }
    }
    ``` 
-----

#### Remover politica de uso do colaborador

* **URL**

  `/employees/{employeeId}/policy`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo        |
   |----------    |--------------    |--------------------------------|-------------|--------------|------------    |
   | employeeId   | numérico         | Identificador do colaborador   | sim         | -            | 20             |

* **Retorno**
  
  **Status Code:** 204
    
-----

#### Busca de colaborador por identificador

* **URL**

  `/employees/{id}`

* **Método**

  `GET`
  
*  **Parâmetros via url**

   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do colaborador                 | sim             | -                | 125            |
   

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "name": "José Santos",
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      },
      "email": "jose.santos@empresa.com.br",
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      },
      "nationalId": "98765432100",
      "supervisorId": 167,
      "pin": "053",
      "enabled": true,
      "externalId": 0,
      "categories": [
        "regular-taxi",
        "turbo-taxi",
        "pop99"
      ],
      "id": 125
    }
    ```
    
-----

#### Atualizar os dados de colaborador

* **URL**

  `/employees/{id}`

* **Método**

  `PUT`
  
*  **Parâmetros via url**

   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do colaborador                 | sim             | -                | 125            |
   
  
*  **Parâmetros via body**

    | Atributo                   | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
    |----------------------------|---------------------------|--------------------------------------------------------------------------------------------------------|-------------|--------------|----------------------------|
    | employee.name              | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
    | employee.phone.number      | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
    | employee.phone.country     | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
    | employee.email             | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
    | employee.nationalId        | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
    | employee.pin               | alfanumérico              | Código de confirmação de corrida (deve conter 3 dígitos)                                               | não         | -            | 934                        |
    | employee.externalId        | numérico                  | Identificador externo do colaborador. É possível relacionar o identificador de um sistema externo.     | não         | -            | 456                        |
    | employee.supervisorId      | numérico                  | Id do supervisor (employeeId) do colaborador.                                                          | não         | -            | 256                        |
    | employee.categories        | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
    | sendWelcomeEmail           | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

*   **Exemplo de envio**

    ```json
    {
      "employee": {
        "name": "José Santos",
        "phone": {
          "number": "11999999999",
          "country": "BRA"
        },
        "email": "jose.santos@empresa.com.br",
        "nationalId": "98765432100",
        "supervisorId": 256,
        "pin": "123",
        "externalId": 55666,
        "categories": [
          "pop99"
        ],
        "id": 125
      },
      "sendWelcomeEmail": false
    }
    ``` 


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "name": "José Santos",
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      },
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      },
      "email": "jose.santos@empresa.com.br",
      "nationalId": "98765432100",
      "supervisorId": 256,
      "pin": "123",
      "externalId": 55666,
      "categories": [
        "pop99"
      ],
      "id": 125
    }
    ```
    
-----

#### Desativar um colaborador

* **URL**

  `/employees/{id}`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo        |
   |----------    |--------------    |--------------------------------|-------------|--------------|------------    |
   | id           | numérico         | Identificador do colaborador   | sim         | -            | 20             |

* **Retorno**
  
  **Status Code:** 204
    
-----

#### Remover surpervisor do colaborador

* **URL**

  `/employees/{id}/supervisor`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo        |
   |----------    |--------------    |--------------------------------|-------------|--------------|------------    |
   | id           | numérico         | Identificador do colaborador   | sim         | -            | 20             |

* **Retorno**
  
  **Status Code:** 204
    
-----

## Colaboradores com Identificador Externo

#### Cadastrar um colaborador por identificador externo

* **URL**

  `/employees/external-id`

* **Método**

  `POST`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado | Descrição                     | Obrigatório | Valor padrão | Exemplo        |
   |--------------|--------------|-------------------------------|-------------|--------------|------------    |
   | externalId   | numérico     | Identificador do colaborador  | sim         | -            | 125            |
   
  
*  **Parâmetros via body**

    | Atributo                      | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
    |-------------------------------|---------------------------|--------------------------------------------------------------------------------------------------------|-------------|--------------|----------------------------|
    | employee.name                 | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
    | employee.phone.number         | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
    | employee.phone.country        | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
    | employee.email                | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
    | employee.nationalId           | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
    | employee.supervisorExternalId | numérico                  | Id do supervisor (employeeExternalId) do colaborador.                                                  | não         | -            | 256                        |
    | employee.pin                  | alfanumérico              | Código de confirmação de corrida (deve conter 3 dígitos)                                               | não         | -            | 934                        |
    | employee.categories           | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
    | sendWelcomeEmail              | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

*   **Exemplo de envio**

    ```json
    {
      "employee": {
        "name": "José Santos",
        "phone": {
          "number": "11999999999",
          "country": "BRA"
        },
        "email": "jose.santos@empresa.com.br",
        "nationalId": "98765432100",
        "supervisorExternalId": 256,
        "pin": "123",
        "categories": [
          "pop99"
        ]
      },
      "sendWelcomeEmail": false
    }
    ``` 
  > Se não for informar o `supervisorExternalId`, o comportamento do método será o mesmo que `POST /employees/`

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "name": "José Santos",
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      },
      "email": "jose@empresa.com",
      "nationalId": "98765432100",
      "enabled": true,
      "supervisorId": 256,
      "externalId": 123749,
      "pin": "123",
      "categories": [
        "pop99"
      ],
      "id": 999999
    }
    ```

  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.
    
-----

#### Busca de colaboradores por identificador externo
* **URL**

  `/employees/external-id/{externalId}`

* **Método**

  `GET`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo  |
   |--------------|--------------|------------------------------|-------------|--------------|----------|
   | externalId   | numérico     | Identificador do colaborador | sim         | -            | 10       |
   

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "name": "José Santos",
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      },
      "email": "jose.santos@empresa.com.br",
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      },
      "nationalId": "98765432100",
      "supervisorId": 167,
      "pin": "053",
      "enabled": true,
      "externalId": 10,
      "categories": [
        "regular-taxi",
        "turbo-taxi",
        "pop99"
      ],
      "id": 125
    }
    ```
  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.
-----

#### Atualizar os dados de colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}`

* **Método**

  `PUT`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado | Descrição                     | Obrigatório | Valor padrão | Exemplo        |
   |--------------|--------------|-------------------------------|-------------|--------------|------------    |
   | externalId   | numérico     | Identificador do colaborador  | sim         | -            | 125            |
   
  
*  **Parâmetros via body**

    | Atributo                      | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
    |-------------------------------|---------------------------|--------------------------------------------------------------------------------------------------------|-------------|--------------|----------------------------|
    | employee.name                 | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
    | employee.phone.number         | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
    | employee.phone.country        | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
    | employee.email                | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
    | employee.nationalId           | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
    | employee.supervisorExternalId | numérico                  | Id do supervisor (employeeExternalId) do colaborador.                                                  | não         | -            | 256                        |
    | employee.pin                  | alfanumérico              | Código de confirmação de corrida (deve conter 3 dígitos)                                               | não         | -            | 934                        |
    | employee.categories           | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
    | sendWelcomeEmail              | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

*   **Exemplo de envio**

    ```json
    {
      "employee": {
        "name": "José Santos",
        "phone": {
          "number": "11999999999",
          "country": "BRA"
        },
        "email": "jose.santos@empresa.com.br",
        "nationalId": "98765432100",
        "supervisorExternalId": 256,
        "pin": "123",
        "categories": [
          "pop99"
        ]
      },
      "sendWelcomeEmail": false
    }
    ``` 


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "name": "José Santos",
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      },
      "email": "jose@empresa.com",
      "nationalId": "98765432100",
      "enabled": true,
      "supervisorId": 256,
      "externalId": 123749,
      "pin": "123",
      "categories": [
        "pop99"
      ],
      "id": 999999
    }
    ```

  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.
    
-----

#### Desativar um colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | externalId   | numérico         | Identificador do colaborador                 | sim             | -                | 10             |
  
* **Retorno**
  
  **Status Code:** 204

-----

#### Busca de centros de custos do colaborador por identificador externo
* **URL**

  `/employees/external-id/{externalId}/costcenter`

* **Método**

  `GET`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo  |
   |--------------|--------------|------------------------------|-------------|--------------|----------|
   | externalId   | numérico     | Identificador do colaborador | sim         | -            | 10       |
   

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      {
        "id": 77045,
        "name": "IntegrationAPI"
      }
    ]
    ```
    
-----

#### Atualizar os dados de centro de custos do colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}/costcenter`

* **Método**

  `PATCH`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado | Descrição                     | Obrigatório | Valor padrão | Exemplo        |
   |--------------|--------------|-------------------------------|-------------|--------------|------------    |
   | externalId   | numérico     | Identificador do colaborador  | sim         | -            | 125            |
   
  
*  **Parâmetros via body**

    | Atributo                   | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
    |----------------------------|---------------------------|--------------------------------------------------------------------------------------------------------|-------------|--------------|----------------------------|
    | costCenterIDs              | conjunto de numéricos     | Ids dos centros de custos                                                                              | sim         | -            | 12, 14, 30                 |

*   **Exemplo de envio**

    ```json
    {
      "costCenterIDs": [
        12, 14, 30
      ]
    }
    ``` 


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      12, 14, 30
    ]
    ```
    
-----

#### Remover centro de custo de um colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}/costcenter/{costCenterId}`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | externalId   | numérico         | Identificador do colaborador                 | sim             | -                | 10             |
   | costCenterId | numérico         | Identificador do centro de custo             | sim             | -                | 20             |
  
* **Retorno**
  
  **Status Code:** 204
  
-----

#### Busca de política de uso do colaborador por identificador externo 

* **URL**

  `/employees/external-id/{externalId}/policy`

* **Método**

  `GET`
  
*  **Parâmetros via query**

    | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo    |
    |--------------|------------------|--------------------------------|-------------|--------------|------------|
    | externalId   | numérico         | Identificador do colaborador   | sim         | -            | 20         |
    

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "budget": {
        "enabled": true,
        "limit": 100.00,
        "period": "date",
        "validUntil": "2019-04-22"
      },
      "rideCredit": {
        "credit": 10,
        "enabled": true
      }
    }
    ``` 
-----

#### Atualizar política de uso do colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}/policy`

* **Método**

  `PUT`
  
*  **Parâmetros via query**

    | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo    |
    |--------------|------------------|--------------------------------|-------------|--------------|------------|
    | externalId   | numérico         | Identificador do colaborador   | sim         | -            | 20         |
    
*  **Parâmetros via body**

    | Atributo                 | Tipo do dado              | Descrição                                                                                         | Obrigatório | Valor padrão | Exemplo                    |
    |--------------------------|---------------------------|---------------------------------------------------------------------------------------------------|-------------|--------------|----------------------------|
    | budget.enabled           | verdadeiro/falso          | Se verdadeiro, política por orçamento estará ativa para o colaborador                                           | sim         | false        | false                      |
    | budget.limit             | numérico                  | Limite de crédito disponível para corridas do colaborador                                         | não         | -            | 100.00                     |
    | budget.period            | alfanumérico              | Periodo de atividade da política por orçamento (valores: "monthly" ou "date")                                   | não         | -            | monthly                    |
    | budget.validUntil        | alfanumérico              | Data de validade da política por orçamento                                                                     | não         | -            | 2019-04-22                 |
    | rideCredit.enabled       | verdadeiro/falso          | Se verdadeiro, a política por quantidade de corridas estará ativo para o colaborador                               | sim         | false        | false                      |
    | rideCredit.credit        | numérico                  | Quantidade de corridas autorizadas para o colaborador                                             | não         | -            | 10                         |
    
> Os atributos `budget.limit` e `budget.period` serão obrigatórios caso `budget.enabled` seja `true`  
  O atributo `budget.validUntil` será obrigatório caso `budget.period` seja `date`  
  O atributo `rideCredit.credit` será obrigatório caso `rideCredit.enabled` seja `true`


*   **Exemplo de envio**

    ```json
      {
        "budget": {
          "enabled": true,
          "limit": 100.00,
          "period": "date",
          "validUntil": "2019-04-22"
        },
        "rideCredit": {
          "credit": 10,
          "enabled": true
        }
      }
    ``` 

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "budget": {
        "enabled": true,
        "limit": 100.00,
        "period": "date",
        "validUntil": "2019-04-22"
      },
      "rideCredit": {
        "credit": 10,
        "enabled": true
      }
    }
    ``` 
-----

#### Remover politica de uso do colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}/policy`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                      | Obrigatório | Valor padrão | Exemplo        |
   |----------    |--------------    |--------------------------------|-------------|--------------|------------    |
   | externalId   | numérico         | Identificador do colaborador   | sim         | -            | 20             |

* **Retorno**
  
  **Status Code:** 204

-----

#### Remover supervisor de um colaborador por identificador externo

* **URL**

  `/employees/external-id/{externalId}/supervisor`

* **Método**

  `DELETE`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | externalId   | numérico         | Identificador do colaborador                 | sim             | -                | 10             |
  
* **Retorno**
  
  **Status Code:** 204
  
-----

## Suporte da API

Caso tenha mais dúvidas ou esteja com problemas na integração, mande um e-mail para *help-corp-api@99taxis.com*
