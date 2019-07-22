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

- [Empresas](#empresas)
- [Políticas de uso](#políticas-de-uso)
- [Centro de Custo](#centro-de-custo)
- [Colaboradores](#colaboradores)
- [Colaboradores com Identificador Externo](#colaboradores-com-identificador-externo)
- [Receitas](#receitas)
- [Corridas](#corridas)
- [Usuários](#usuários)

-----

## Empresas

#### Busca de empresas autenticadas

* **URL**

  `/companies`

* **Método**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      {
       "id": "8b8fe0bc-f417-4bfc-8c4c-528e46b776dd",
        "name": "Empresa principal"
      },
      {
        "id": "75492c7e-f33c-4b7f-87ce-abe3f933991b",
        "name": "Empresa secundária"
      }
    ]
    ``` 
    
-----

#### Busca de parâmetros de justificativa para corrida

* **URL**

  `/companies/justification`

* **Método**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
        "justificationRequired": true,
        "minLength": 4,
        "maxLength": 128
    }
    ``` 
    
-----

#### Alteração de parâmetros de justificativa para corrida

* **URL**

  `/companies/justification`

* **Método**

  `PUT`

- **Parâmetros via body**

  | Atributo                | Tipo do dado       | Descrição                                    | Obrigatório | Valor padrão | Exemplo   |
  | ----------------------- | ------------------ | -------------------------------------------- | ----------- | ------------ | --------- |
  | justificationRequired   | verdadeiro/falso   | Habilitar obrigatoriedade da justificativa   | sim         | -            | true      |
  | minLength               | numérico           | Tamanho mínimo da justificativa              | não         | -            | 4         |
  | maxLength               | numérico           | Tamanho máximo da justificativa              | não         | -            | 128       |

> Os atributos `minLength` e `maxLength` são obrigatórios quando o atributo `justificationRequired` é igual a `true` 
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
        "justificationRequired": true,
        "minLength": 4,
        "maxLength": 128
    }
    ``` 
    
-----

#### Busca de emails dos responsáveis da empresa 

* **URL**

  `/companies/emails`

* **Método**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      "colaborador@99app.com",
      "colaborador2@99app.com",
      "colaborador3@99app.com"
    ]
    ``` 
    
-----

#### Alteração de emails de responsáveis da empresa

* **URL**

  `/companies/emails`

* **Método**

  `PUT`

- **Parâmetros via body**

  | Atributo   | Tipo do dado   | Descrição             | Obrigatório | Valor padrão | Exemplo                                             |
  | -----------| -------------- | ----------------------| ----------- | ------------ | --------------------------------------------------- |
  | email      | Lista          | E-mail do responsável | sim         | -            | ["colaborador@99app.com", "colaborador2@99app.com"] |
  
* **Exemplo de envio**

    ```json
    [
      "colaborador@99app.com",
      "colaborador2@99app.com",
      "colaborador3@99app.com"
    ]
    ```

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      "colaborador@99app.com",
      "colaborador2@99app.com",
      "colaborador3@99app.com"
    ]
    ``` 
    
-----

## Políticas de uso

### Busca por políticas de uso

- **URL**

  `/timepolicies`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo | Tipo do dado | Descrição                                | Obrigatório | Valor padrão | Exemplo     |
  | -------- | ------------ | ---------------------------------------- | ----------- | ------------ | ----------- |
  | limit    | numérico     | Quantidade de registros por página       | não         | 20           | 50          |
  | page     | numérico     | Índice da página no sistema de paginação | não         | 1            | 3           |

- **Retorno**
  **Status Code:** 200
  ```json
  [
    {
      "id": 2035,
      "name": "Política para comerciais",
      "description": "Define o uso para os colaboradores do setor comercial",
      "intervals": [
        {
          "dayOfWeek": 1,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 2,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 3,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 4,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 5,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 6,
          "startTime": "09:00",
          "endTime": "17:29"
        },
      ]
    },
    {
      "id": 2037,
      "name": "Política para implantação",
      "description": "Define o uso para os colaboradores do setor de TI",
      "intervals": [
        {
          "dayOfWeek": 2,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 3,
          "startTime": "09:00",
          "endTime": "17:29"
        },
        {
          "dayOfWeek": 4,
          "startTime": "09:00",
          "endTime": "17:29"
        }      ]
    }
  ]
  ```
-----

### Cadastro de política de uso

- **URL**

  `/timepolicies`

- **Método**

  `POST`

- **Parâmetros via body**

  | Atributo            | Tipo do dado | Descrição                                              | Obrigatório | Valor padrão | Exemplo                                               |
  | ------------------- | ------------ | ------------------------------------------------------ | ----------- | ------------ | ----------------------------------------------------- |
  | name                | alfanumérico | Nome da política de uso                                | sim         | -            | Política para comerciais                              |
  | description         | alfanumérico | Descrição da política de uso                           | não         | -            | Define o uso para os colaboradores do setor comercial |
  | intervals           | lista        | Define os intervalos de uso                            | sim         | -            |                                                       |
  | intervals.dayOfWeek | numérico     | Identifica os dias da semanas                          | sim         | -            | 1                                                     |
  | intervals.startTime | hora         | Identifica a hora de inicio do intervalo               | sim         | -            | 10:00                                                 |
  | intervals.endTime   | hora         | Identifica a hora de fim do intervalo                  | sim         | -            | 17:29                                                 |

- **Exemplo de envio**

  ```json
  {
    "name": "Política para atendimentos emergenciais",
    "description": "Define o uso para atendimentos emergenciais nos finais de semana",
    "intervals": [
      {
        "dayOfWeek": 0,
        "startTime": "07:00",
        "endTime": "22:29"
      },
      {
        "dayOfWeek": 6,
        "startTime": "07:00",
        "endTime": "22:29"
      }
    ]
  }
  ```

- **Retorno**
  **Status Code:** 201
  ```json
  {
    "id": 2066,
    "name": "Política para atendimentos emergenciais",
    "description": "Define o uso para atendimentos emergenciais nos finais de semana",
    "intervals": [
      {
        "dayOfWeek": 0,
        "startTime": "7:00",
        "endTime": "22:29"
      },
      {
        "dayOfWeek": 6,
        "startTime": "7:00",
        "endTime": "22:29"
      }
    ]
  }
  ```
> O atributo `intervals.dayOfWeek` identifica os dias da semana, sendo `0` domingo e `6` o sábado. Qualquer número diferente desse rango de 0 à 6 não é aceito.
>  O atributo `intervals.startTime` indica a hora de inicio do intervalo em horas e minutos. Os minutos aceitos são `0` e `30`  
>  O atributo `intervals.endTime` indica a hora de finalização do intervalo em horas e minutos. Os minutos aceitos são `29` e `59`  

-----

#### Busca de política de uso por identificador

- **URL**

  `/timepolicies/{id}`

- **Método**

  `GET`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador da política de uso | sim         | -            | 2066    |

- **Retorno**
  **Status Code:** 200
  ```json
  {
    "id": 2066,
    "name": "Política para atendimentos emergenciais",
    "description": "Define o uso para atendimentos emergenciais nos finais de semana",
    "intervals": [
      {
        "dayOfWeek": 0,
        "startTime": "7:00",
        "endTime": "22:29"
      },
      {
        "dayOfWeek": 6,
        "startTime": "7:00",
        "endTime": "22:29"
      }
    ]
  }
  ```
-----

### Atualizar de política de uso

- **URL**

  `/timepolicies/{id}`

- **Método**

  `PUT`

- **Parâmetros via url**

  | Atributo   | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id         | numérico     | Identificador da política de uso | sim         | -            | 2066     |

- **Parâmetros via body**

  | Atributo            | Tipo do dado | Descrição                                              | Obrigatório | Valor padrão | Exemplo                                               |
  | ------------------- | ------------ | ------------------------------------------------------ | ----------- | ------------ | ----------------------------------------------------- |
  | name                | alfanumérico | Nome da política de uso                                | sim         | -            | Política para comerciais                              |
  | description         | alfanumérico | Descrição da política de uso                           | não         | -            | Define o uso para os colaboradores do setor comercial |
  | intervals           | lista        | Define os intervalos de uso                            | sim         | -            |                                                       |
  | intervals.dayOfWeek | numérico     | Identifica os dias da semanas                          | sim         | -            | 1                                                     |
  | intervals.startTime | hora         | Identifica a hora de inicio do intervalo               | sim         | -            | 10:00                                                 |
  | intervals.endTime   | hora         | Identifica a hora de fim do intervalo                  | sim         | -            | 17:29                                                 |

- **Exemplo de envio**

  ```json
  {
    "name": "Política para atendimentos emergenciais",
    "description": "Define o uso para atendimentos emergenciais nos finais de semana",
    "intervals": [
      {
        "dayOfWeek": 0,
        "startTime": "07:00",
        "endTime": "22:29"
      },
      {
        "dayOfWeek": 5,
        "startTime": "21:00",
        "endTime": "23:59"
      },
      {
        "dayOfWeek": 6,
        "startTime": "07:00",
        "endTime": "22:29"
      }
    ]
  }
  ```

- **Retorno**
  **Status Code:** 200
  ```json
  {
    "id": 2066,
    "name": "Política para atendimentos emergenciais",
    "description": "Define o uso para atendimentos emergenciais nos finais de semana",
    "intervals": [
      {
        "dayOfWeek": 0,
        "startTime": "7:00",
        "endTime": "22:29"
      },
      {
        "dayOfWeek": 5,
        "startTime": "21:00",
        "endTime": "23:59"
      },
      {
        "dayOfWeek": 6,
        "startTime": "7:00",
        "endTime": "22:29"
      }
    ]
  }
  ```
> O atributo `intervals` sobreescreve os intervalos existentes.
>  O atributo `intervals.dayOfWeek` identifica os dias da semana, sendo `0` domingo e `6` o sábado. Qualquer número diferente desse rango de 0 à 6 não é aceito.
>  O atributo `intervals.startTime` indica a hora de inicio do intervalo em horas e minutos. Os minutos aceitos são `0` e `30`  
>  O atributo `intervals.endTime` indica a hora de finalização do intervalo em horas e minutos. Os minutos aceitos são `29` e `59`  

-----

#### Remover uma política de uso

- **URL**

  `/timepolicies/{id}`

- **Método**

  `DELETE`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador da política de uso | sim         | -            | 2066    |

- **Retorno**
  **Status Code:** 204

-----
#### Busca de centros de custo da política de uso

- **URL**

  `/timepolicies/{id}/costcenter`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador da política de uso | sim         | -            | 20      |

* **Retorno**

  **Status Code:** 200

  ```json
  [
    {
      "id": 696575,
      "name": "CC comerciais setor norte"
    },
    {
      "id": 696576,
      "name": "CC comerciais setor este"
    }
  ]
  ```
---

#### Atualizar centro de custos da política de uso

- **URL**

  `/employees/{id}/costcenter`

- **Método**

  `PATCH`

- **Parâmetros via url**

  | Atributo   | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id         | numérico     | Identificador da política de uso | sim         | -            | 125     |

* **Parâmetros via body**

  | Atributo      | Tipo do dado          | Descrição                           | Obrigatório | Valor padrão | Exemplo  |
  | ------------- | --------------------- | ----------------------------------- | ----------- | ------------ | -------- |
  | lista de id   | conjunto de numéricos | Identificadores de centros de custo | sim         | -            | 100, 200 |

* **Exemplo de envio**

  ```json
  [696575, 696576]
  ```

- **Retorno**

  **Status Code:** 200

  ```json
  [696575, 696576]
  ```

---

#### Desassociar um centro de custo de uma política de uso

- **URL**

  `/timepolicies/{id}/costcenter/{costCenterId}`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo     | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
| ------------ | ------------ | -------------------------------- | ----------- | ------------ | ------- |
| id           | numérico     | Identificador da política de uso | sim         | -            | 10      |
| costCenterId | numérico     | Identificador do centro de custo | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

## Centro de Custo

#### Busca de centros de custo

- **URL**

  `/costcenters`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo | Tipo do dado | Descrição                                | Obrigatório | Valor padrão | Exemplo     |
  | -------- | ------------ | ---------------------------------------- | ----------- | ------------ | ----------- |
  | search   | alfanumérico | Busca pelo nome do centro de custo       | não         | -            | Atendimento |
  | limit    | numérico     | Quantidade de registros por página       | não         | 50           | 20          |
  | page     | numérico     | Índice da página no sistema de paginação | não         | 1            | 3           |

- **Retorno**
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

---

#### Cadastro de centro de custo

- **URL**

  `/costcenters`

- **Método**

  `POST`

- **Parâmetros via body**

  | Atributo | Tipo do dado | Descrição               | Obrigatório | Valor padrão | Exemplo     |
  | -------- | ------------ | ----------------------- | ----------- | ------------ | ----------- |
  | name     | alfanumérico | Nome do centro de custo | sim         | -            | Atendimento |

- **Exemplo de envio**

  ```json
  {
    "name": "Atendimento"
  }
  ```

- **Retorno**
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

---

#### Busca de centros de custo por identificador

- **URL**

  `/costcenters/{id}`

- **Método**

  `GET`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do centro de custo | sim         | -            | 20      |

- **Retorno**
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

---

#### Desativar um centro de custo

- **URL**

  `/costcenters/{id}`

- **Método**

  `DELETE`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do centro de custo | sim         | -            | 20      |

- **Retorno**
  **Status Code:** 204

---

#### Busca de responsável de centro de custo por identificador

- **URL**

  `/costcenters/{id}/owners`

- **Método**

  `GET`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do centro de custo | sim         | -            | 20      |

* **Retorno**
  **Status Code:** 200
  ```json
  [
    {
      "name": "José Santos",
      "email": "jose.santos@empresa.com.br"
    }
  ]
  ```

---

#### Alteração de responsável de centro de custo por identificador

- **URL**

  `/costcenters/{id}/owners`

- **Método**

  `PUT`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | -------------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do centro de custo | sim         | -            | 20      |

- **Parâmetros via body**

  Lista de responsáveis:

  | Atributo | Tipo do dado | Descrição             | Obrigatório | Valor padrão | Exemplo                    |
  | -------- | ------------ | --------------------- | ----------- | ------------ | -------------------------- |
  | name     | alfanumérico | Nome do responsável   | sim         | -            | José Santos                |
  | email    | alfanumérico | E-mail do responsável | sim         | -            | jose.santos@empresa.com.br |

- **Regras**

  Se o responsável já tem uma conta, o `name` será desconsiderado.

  Se o responsável já tem uma conta e o perfil for tipo `EmployeeUser`, o perfil será mudado para `OwnerUser`

  Se o responsável não tem uma conta, a mesma será criada com o `name` e o `email`

* **Exemplo de envio**

  ```json
  [
    {
      "name": "José Santos",
      "email": "jose.santos@empresa.com.br"
    }
  ]
  ```

* **Retorno**
  **Status Code:** 200
  ```json
  [
    {
      "name": "José Santos",
      "email": "jose.santos@empresa.com.br"
    }
  ]
  ```

---

## Colaboradores

#### Busca de colaboradores

- **URL**

  `/employees`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo   | Tipo do dado | Descrição                                  | Obrigatório | Valor padrão | Exemplo     |
  | ---------- | ------------ | ------------------------------------------ | ----------- | ------------ | ----------- |
  | search     | alfanumérico | Busca pelo nome do colaborador             | não         | -            | José Santos |
  | limit      | numérico     | Quantidade de registros por página         | não         | 50           | 20          |
  | page       | numérico     | Índice da página no sistema de paginação   | não         | 1            | 3           |
  | nationalId | alfanumérico | Documento do colaborador (somente números) | não         | -            | 98765432100 |

- **Retorno**

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
    "enabled": true,
    "externalId": 0,
    "categories": ["regular-taxi", "top99", "turbo-taxi", "pop99"],
    "id": 125
  }
  ```

---

#### Cadastro de colaborador

- **URL**

  `/employees`

- **Método**

  `POST`

- **Parâmetros via body**

  | Atributo               | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
  | ---------------------- | ------------------------- | ------------------------------------------------------------------------------------------------------ | ----------- | ------------ | -------------------------- |
  | employee.name          | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
  | employee.phone.number  | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
  | employee.phone.country | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
  | employee.email         | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
  | employee.nationalId    | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
  | employee.externalId    | numérico                  | Identificador externo do colaborador. É possível relacionar o identificador de um sistema externo.     | não         | -            | 456                        |
  | employee.supervisorId  | numérico                  | Id do supervisor (employeeId)do colaborador.                                                           | não         | -            | 256                        |
  | employee.categories    | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
  | sendWelcomeEmail       | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

- **Exemplo de envio**

  ```json
  {
    "employee": {
      "email": "jose.santos@empresa.com.br",
      "name": "José Santos",
      "nationalId": "98765432100",
      "externalId": 55091,
      "categories": ["regular-taxi", "turbo-taxi", "pop99"],
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
      "categories": ["regular-taxi", "turbo-taxi", "pop99"],
      "phone": {
        "number": "11999999999",
        "country": "BRA"
      }
    },
    "sendWelcomeEmail": false
  }
  ```

---

#### Busca de centro de custo do colaborador

- **URL**

  `/employees/{employeeId}/costcenter`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do colaborador | sim         | -            | 20      |

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

---

#### Atualizar centro de custos do colaborador

- **URL**

  `/employees/{employeeId}/costcenter`

- **Método**

  `PATCH`

- **Parâmetros via url**

  | Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | employeeId | numérico     | Identificador do colaborador | sim         | -            | 125     |

* **Parâmetros via body**

  | Atributo      | Tipo do dado          | Descrição                           | Obrigatório | Valor padrão | Exemplo  |
  | ------------- | --------------------- | ----------------------------------- | ----------- | ------------ | -------- |
  | costCenterIDs | conjunto de numéricos | Identificadores de centros de custo | sim         | -            | 100, 200 |

* **Exemplo de envio**

  ```json
  {
    "costCenterIDs": [100, 200]
  }
  ```

- **Retorno**

  **Status Code:** 200

  ```json
  [100, 200]
  ```

---

#### Desassociar um centro de custo de um colaborador

- **URL**

  `/employees/{employeeId}/costcenter/{costCenterId}`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo     | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
| ------------ | ------------ | -------------------------------- | ----------- | ------------ | ------- |
| employeeId   | numérico     | Identificador do colaborador     | sim         | -            | 10      |
| costCenterId | numérico     | Identificador do centro de custo | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

#### Busca de política de uso do colaborador

- **URL**

  `/employees/{employeeId}/policy`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | employeeId | numérico     | Identificador do colaborador | sim         | -            | 20      |

* **Retorno**

  **Status Code:** 200

  ```json
  {
    "budget": {
      "enabled": true,
      "limit": 100.0,
      "period": "date",
      "validUntil": "2019-04-22"
    },
    "rideCredit": {
      "credit": 10,
      "enabled": true
    }
  }
  ```

---

#### Atualizar política de uso do colaborador

- **URL**

  `/employees/{employeeId}/policy`

- **Método**

  `PUT`

- **Parâmetros via query**

  | Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | employeeId | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Parâmetros via body**

  | Atributo           | Tipo do dado     | Descrição                                                                            | Obrigatório | Valor padrão | Exemplo    |
  | ------------------ | ---------------- | ------------------------------------------------------------------------------------ | ----------- | ------------ | ---------- |
  | budget.enabled     | verdadeiro/falso | Se verdadeiro, política por orçamento estará ativa para o colaborador                | sim         | false        | false      |
  | budget.limit       | numérico         | Limite de crédito disponível para corridas do colaborador                            | não         | -            | 100.00     |
  | budget.period      | alfanumérico     | Periodo de atividade da política por orçamento (valores: "monthly" ou "date")        | não         | -            | monthly    |
  | budget.validUntil  | alfanumérico     | Data de validade da política por orçamento                                           | não         | -            | 2019-04-22 |
  | rideCredit.enabled | verdadeiro/falso | Se verdadeiro, a política por quantidade de corridas estará ativo para o colaborador | sim         | false        | false      |
  | rideCredit.credit  | numérico         | Quantidade de corridas autorizadas para o colaborador                                | não         | -            | 10         |

> Os atributos `budget.limit` e `budget.period` serão obrigatórios caso `budget.enabled` seja `true`  
>  O atributo `budget.validUntil` será obrigatório caso `budget.period` seja `date`  
>  O atributo `rideCredit.credit` será obrigatório caso `rideCredit.enabled` seja `true`

- **Exemplo de envio**

  ```json
  {
    "budget": {
      "enabled": true,
      "limit": 100.0,
      "period": "date",
      "validUntil": "2019-04-22"
    },
    "rideCredit": {
      "credit": 10,
      "enabled": true
    }
  }
  ```

- **Retorno**

  **Status Code:** 200

  ```json
  {
    "budget": {
      "enabled": true,
      "limit": 100.0,
      "period": "date",
      "validUntil": "2019-04-22"
    },
    "rideCredit": {
      "credit": 10,
      "enabled": true
    }
  }
  ```

---

#### Remover politica de uso do colaborador

- **URL**

  `/employees/{employeeId}/policy`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| employeeId | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

#### Busca de colaborador por identificador

- **URL**

  `/employees/{id}`

- **Método**

  `GET`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do colaborador | sim         | -            | 125     |

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
    "externalId": 0,
    "categories": ["regular-taxi", "turbo-taxi", "pop99"],
    "id": 125
  }
  ```

---

#### Atualizar os dados de colaborador

- **URL**

  `/employees/{id}`

- **Método**

  `PUT`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do colaborador | sim         | -            | 125     |

* **Parâmetros via body**

  | Atributo               | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
  | ---------------------- | ------------------------- | ------------------------------------------------------------------------------------------------------ | ----------- | ------------ | -------------------------- |
  | employee.name          | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
  | employee.phone.number  | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
  | employee.phone.country | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
  | employee.email         | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
  | employee.nationalId    | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
  | employee.externalId    | numérico                  | Identificador externo do colaborador. É possível relacionar o identificador de um sistema externo.     | não         | -            | 456                        |
  | employee.supervisorId  | numérico                  | Id do supervisor (employeeId) do colaborador.                                                          | não         | -            | 256                        |
  | employee.categories    | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
  | sendWelcomeEmail       | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

* **Exemplo de envio**

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
      "externalId": 55666,
      "categories": ["pop99"],
      "id": 125
    },
    "sendWelcomeEmail": false
  }
  ```

- **Retorno**

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
    "externalId": 55666,
    "categories": ["pop99"],
    "id": 125
  }
  ```

---

#### Desativar um colaborador

- **URL**

  `/employees/{id}`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| -------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| id       | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

#### Remover surpervisor do colaborador

- **URL**

  `/employees/{id}/supervisor`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| -------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| id       | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

## Colaboradores com Identificador Externo

#### Cadastrar um colaborador por identificador externo

- **URL**

  `/employees/external-id`

- **Método**

  `POST`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 125     |

- **Parâmetros via body**

  | Atributo                      | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
  | ----------------------------- | ------------------------- | ------------------------------------------------------------------------------------------------------ | ----------- | ------------ | -------------------------- |
  | employee.name                 | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
  | employee.phone.number         | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
  | employee.phone.country        | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
  | employee.email                | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
  | employee.nationalId           | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
  | employee.supervisorExternalId | numérico                  | Id do supervisor (employeeExternalId) do colaborador.                                                  | não         | -            | 256                        |
  | employee.categories           | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
  | sendWelcomeEmail              | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

- **Exemplo de envio**

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
      "categories": ["pop99"]
    },
    "sendWelcomeEmail": false
  }
  ```

  > Se não for informar o `supervisorExternalId`, o comportamento do método será o mesmo que `POST /employees/`

- **Retorno**

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
    "categories": ["pop99"],
    "id": 999999
  }
  ```

  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.

---

#### Busca de colaboradores por identificador externo

- **URL**

  `/employees/external-id/{externalId}`

- **Método**

  `GET`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 10      |

- **Retorno**

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
    "enabled": true,
    "externalId": 10,
    "categories": ["regular-taxi", "turbo-taxi", "pop99"],
    "id": 125
  }
  ```

  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.

---

#### Atualizar os dados de colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}`

- **Método**

  `PUT`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 125     |

- **Parâmetros via body**

  | Atributo                      | Tipo do dado              | Descrição                                                                                              | Obrigatório | Valor padrão | Exemplo                    |
  | ----------------------------- | ------------------------- | ------------------------------------------------------------------------------------------------------ | ----------- | ------------ | -------------------------- |
  | employee.name                 | alfanumérico              | Nome do colaborador                                                                                    | sim         | -            | José Santos                |
  | employee.phone.number         | alfanumérico              | Número do telefone do colaborador (somente números)                                                    | sim         | -            | 11999999999                |
  | employee.phone.country        | alfanumérico              | Código do país atrelado ao número                                                                      | não         | BRA          | BRA                        |
  | employee.email                | alfanumérico              | E-mail do colaborador                                                                                  | sim         | -            | jose.santos@empresa.com.br |
  | employee.nationalId           | alfanumérico              | Documento do colaborador (CPF) (Somente números)                                                       | não         | -            | 98765432100                |
  | employee.supervisorExternalId | numérico                  | Id do supervisor (employeeExternalId) do colaborador.                                                  | não         | -            | 256                        |
  | employee.categories           | conjunto de alfanuméricos | Categorias permitidas para uso do colaborador. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99 | sim         | -            | regular-taxi, turbo-taxi   |
  | sendWelcomeEmail              | verdadeiro/falso          | Se verdadeiro, colaborador cadastrado receberá um e-mail de boas vindas                                | não         | false        | false                      |

- **Exemplo de envio**

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
      "categories": ["pop99"]
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
    "categories": ["pop99"],
    "id": 999999
  }
  ```

  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.

---

#### Desativar um colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 10      |

- **Retorno**

  **Status Code:** 204

---

#### Busca de centros de custos do colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/costcenter`

- **Método**

  `GET`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 10      |

- **Retorno**

  **Status Code:** 200

  ```json
  [
    {
      "id": 77045,
      "name": "IntegrationAPI"
    }
  ]
  ```

---

#### Atualizar os dados de centro de custos do colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/costcenter`

- **Método**

  `PATCH`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 125     |

- **Parâmetros via body**

  | Atributo      | Tipo do dado          | Descrição                 | Obrigatório | Valor padrão | Exemplo    |
  | ------------- | --------------------- | ------------------------- | ----------- | ------------ | ---------- |
  | costCenterIDs | conjunto de numéricos | Ids dos centros de custos | sim         | -            | 12, 14, 30 |

- **Exemplo de envio**

  ```json
  {
    "costCenterIDs": [12, 14, 30]
  }
  ```

* **Retorno**

  **Status Code:** 200

  ```json
  [12, 14, 30]
  ```

---

#### Remover centro de custo de um colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/costcenter/{costCenterId}`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo     | Tipo do dado | Descrição                        | Obrigatório | Valor padrão | Exemplo |
| ------------ | ------------ | -------------------------------- | ----------- | ------------ | ------- |
| externalId   | numérico     | Identificador do colaborador     | sim         | -            | 10      |
| costCenterId | numérico     | Identificador do centro de custo | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

#### Busca de política de uso do colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/policy`

- **Método**

  `GET`

- **Parâmetros via query**

  | Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | externalId | numérico     | Identificador do colaborador | sim         | -            | 20      |

* **Retorno**

  **Status Code:** 200

  ```json
  {
    "budget": {
      "enabled": true,
      "limit": 100.0,
      "period": "date",
      "validUntil": "2019-04-22"
    },
    "rideCredit": {
      "credit": 10,
      "enabled": true
    }
  }
  ```

---

#### Atualizar política de uso do colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/policy`

- **Método**

  `PUT`

- **Parâmetros via query**

  | Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | externalId | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Parâmetros via body**

  | Atributo           | Tipo do dado     | Descrição                                                                            | Obrigatório | Valor padrão | Exemplo    |
  | ------------------ | ---------------- | ------------------------------------------------------------------------------------ | ----------- | ------------ | ---------- |
  | budget.enabled     | verdadeiro/falso | Se verdadeiro, política por orçamento estará ativa para o colaborador                | sim         | false        | false      |
  | budget.limit       | numérico         | Limite de crédito disponível para corridas do colaborador                            | não         | -            | 100.00     |
  | budget.period      | alfanumérico     | Periodo de atividade da política por orçamento (valores: "monthly" ou "date")        | não         | -            | monthly    |
  | budget.validUntil  | alfanumérico     | Data de validade da política por orçamento                                           | não         | -            | 2019-04-22 |
  | rideCredit.enabled | verdadeiro/falso | Se verdadeiro, a política por quantidade de corridas estará ativo para o colaborador | sim         | false        | false      |
  | rideCredit.credit  | numérico         | Quantidade de corridas autorizadas para o colaborador                                | não         | -            | 10         |

> Os atributos `budget.limit` e `budget.period` serão obrigatórios caso `budget.enabled` seja `true`  
>  O atributo `budget.validUntil` será obrigatório caso `budget.period` seja `date`  
>  O atributo `rideCredit.credit` será obrigatório caso `rideCredit.enabled` seja `true`

- **Exemplo de envio**

  ```json
  {
    "budget": {
      "enabled": true,
      "limit": 100.0,
      "period": "date",
      "validUntil": "2019-04-22"
    },
    "rideCredit": {
      "credit": 10,
      "enabled": true
    }
  }
  ```

- **Retorno**

  **Status Code:** 200

  ```json
  {
    "budget": {
      "enabled": true,
      "limit": 100.0,
      "period": "date",
      "validUntil": "2019-04-22"
    },
    "rideCredit": {
      "credit": 10,
      "enabled": true
    }
  }
  ```

---

#### Remover politica de uso do colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/policy`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Retorno**

  **Status Code:** 204

---

#### Remover supervisor de um colaborador por identificador externo

- **URL**

  `/employees/external-id/{externalId}/supervisor`

- **Método**

  `DELETE`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
| externalId | numérico     | Identificador do colaborador | sim         | -            | 10      |

- **Retorno**

  **Status Code:** 204

---

## Receitas

#### Busca de receitas

- **URL**

  `/receipts`

- **Método**

  `GET`

- **Parâmetros via url**

  | Atributo     | Tipo do dado | Descrição                                                                         | Obrigatório | Valor padrão | Exemplo     |
  | -----------  | ------------ | --------------------------------------------------------------------------------- | ----------- | ------------ | ----------- |
  | startDate    | data         | Indica data de início para filtro de busca por corridas finalizadas               | sim         | -            | 2017-06-01  |
  | endDate      | data         | Indica data de término para filtro de busca por corridas finalizadas              | sim         | -            | 2017-06-02  |
  | costCenterId | numérico     | Identificador do centro de custo                                                  | não         | -            | 20          |
  | projectId    | numérico     | Identificador do projeto                                                          | não         | -            | 15          |
  | taxiCategory | alfanumérico | Categoria da corrida. Valores possíveis: regular-taxi, turbo-taxi, top99 ou pop99 | não         | -            | pop99       |
  | limit        | numérico     | Quantidade de registros por página                                                | não         | 20           | 10          |
  | page         | numérico     | Índice da página no sistema de paginação                                          | não         | 1            | 3           |

* **Retorno**
  
  **Status Code:** 200
  
  **Estrutura de retorno**
   
   | Atributo                       | Descrição                                                                                          |
   |----------------------------    |------------------------------------------------------------------------------------------------    |
   | ride.receiptId                 | Identificador do recibo                                                                            |
   | ride.company.id                | Identificador da empresa                                                                           |
   | ride.company.name              | Nome da empresa                                                                                    |
   | ride.employee.id               | Identificador do colaborador                                                                       |
   | ride.employee.name             | Nome do colaborador                                                                                |
   | ride.employee.phone            | Telefone do colaborador                                                                            |
   | ride.employee.phoneCountry     | País do telefone do colaborador                                                                    |
   | ride.employee.email            | Email do colaborador                                                                               |
   | ride.employee.nationalId       | Documento do colaborador (somente números)                                                         |
   | ride.employee.externalId       | Identificador externo do colaborador. É possível relacionar o identificador de um sistema externo. |
   | ride.costCenter.id             | Identificador do centro de custo                                                                   |
   | ride.costCenter.name           | Nome do centro de custo                                                                            |
   | ride.project.id                | Identificador do projeto                                                                           |
   | ride.project.name              | Nome do projeto                                                                                    |
   | ride.fare                      | Valor total da corrida                                                                             |
   | ride.note                      | Justificativa                                                                                      |
   | ride.odometer                  | Distância total da corrida em metros                                                               |
   | ride.duration                  | Duração da corrida em minutos                                                                      |
   | ride.start.latitude            | Latitude do ponto de origem                                                                        |
   | ride.start.longitude           | Longitude do ponto de origem                                                                       |
   | ride.start.date                | Data de início da corrida                                                                          |
   | ride.start.address             | Endereço de origem                                                                                 |
   | ride.end.latitude              | Latitude do ponto de destino                                                                       |
   | ride.end.longitude             | Longitude do ponto de destino                                                                      |
   | ride.end.date                  | Data de término da corrida                                                                         |
   | ride.end.address               | Endereço de destino                                                                                |
   | ride.realStart.latitude        | Latitude do ponto de embarque                                                                      |
   | ride.realStart.longitude       | Longitude do ponto de embarque                                                                     |
   | ride.realStart.date            | Data de início da corrida                                                                          |
   | ride.realStart.address         | Endereço de embarque                                                                               |
   | ride.realEnd.latitude          | Latitude do ponto de desembarque                                                                   |
   | ride.realEnd.longitude         | Longitude do ponto de desembarque                                                                  |
   | ride.realEnd.date              | Data de início da corrida                                                                          |
   | ride.realEnd.address           | Endereço de desembarque                                                                            |
   | ride.driver.name               | Nome do motorista                                                                                  | 
   | ride.driver.phone              | Telefone do motorista                                                                              |
   | ride.driver.car                | Veículo do motorista                                                                               |
   | ride.driver.carYear            | Ano do veículo                                                                                     |
   | ride.driver.carPlate           | Placa do veículo                                                                                   |
   | ride.city                      | Cidade                                                                                             |
   | ride.callPlatform              | Plataforma de chamada                                                                              |
   | ride.requesterName             | Nome da pessoa que solicitou a corrida                                                             |
   | ride.taxiCategoryName          | Categoria da corrida                                                                               |
   | summary.quantity               | Quantidade de corridas                                                                             |
   | summary.totalFare              | Valor total das corridas retornadas                                                                |

  **Exemplo de retorno**
  
    ```json
    {
      "rides": [
        {
          "receiptId": "8382932932",
          "company": {
            "id": 9311,
            "name": "99 Tecnologia"
          },
          "employee": {
            "id": 434343,
            "name": "COLABORADOR",
            "phone": "11999999999",
            "phoneCountry": "BRA",
            "email": "colaborador@99taxis.com",
            "nationalId": "63313137709",
            "externalId": 329932
          },
          "costCenter": {
            "id": 8483843,
            "name": "99Integracao"
          },
          "project": {
            "id": 433902,
            "name": "99Projeto"
          },
          "fare": 25.4,
          "note": "justificativa",
          "odometer": 7593,
          "start": {
            "latitude": -30.021647,
            "longitude": -51.210702,
            "date": "2019-04-04 17:13",
            "address": "R. Gaspar Martins, 395 - Floresta, Porto Alegre - RS, 90220-160, Brasil"
          },
          "end": {
            "latitude": -30.029277,
            "longitude": -51.234226,
            "date": "2019-04-04 17:24",
            "address": "Rua General João Manoel, 50, Centro Histórico, Porto Alegre - RS, Brasil"
          },
          "realStart": {
            "latitude": -30.021714,
            "longitude": -51.210759,
            "date": "2019-04-04 17:13",
            "address": "Floresta - R. São Carlos, 545 - Floresta, Porto Alegre - RS, 90220-121, Brasil"
          },
          "realEnd": {
            "latitude": -30.029156,
            "longitude": -51.233957,
            "date": "2019-04-04 17:24",
            "address": "R. Gen. João Manoel, 127 - Centro Histórico, Porto Alegre - RS, 90010-030, Brasil"
          },
          "duration": 11,
          "driver": {
            "name": "Motorista",
            "phone": "11999999999",
            "car": "Fiat Siena",
            "carYear": 2015,
            "carPlate": "EJF-3931"
          },
          "city": "Porto Alegre",
          "callPlatform": "app",
          "requesterName": "COLABORADOR",
          "taxiCategoryName": "Taxi 30% OFF"
        }
      ],
      "summary": {
        "quantity": 1,
        "totalFare": 25.4
      }
    }
    ```
    
-----

### Busca do recibo pelo identificador da corrida

* **URL**

  `/receipts/{rideId}`

* **Método**

  `GET`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | rideId       | alfanumérico     | Identificador da corrida chamada pela api    | sim             | -                | 1              |

*  **Regras**

    Após o término da corrida, existe o tempo de sincronização e geração do recibo. Devido a isso, pode demorar até 10 minutos para que o recibo da corrida esteja disponível. O identificador da corrida é o mesmo usado para obter os dados do recibo.

    Enquanto o recibo não estiver disponível, o endpoint irá retornar o status code 404.

* **Retorno**
  
  **Status Code:** 200
  
  **Exemplo de retorno**
  
    ```json
    {
      "receiptId": "8382932932",
      "company": {
        "id": 9311,
        "name": "99 Tecnologia"
      },
      "employee": {
        "id": 434343,
        "name": "COLABORADOR",
        "phone": "11999999999",
        "phoneCountry": "BRA",
        "email": "colaborador@99taxis.com",
        "nationalId": "63313137709",
        "externalId": 329932
      },
      "costCenter": {
        "id": 8483843,
        "name": "99Integracao"
      },
      "project": {
        "id": 433902,
        "name": "99Projeto"
      },
      "fare": 25.4,
      "note": "justificativa",
      "odometer": 7593,
      "start": {
        "latitude": -30.021647,
        "longitude": -51.210702,
        "date": "2019-04-04 17:13",
        "address": "R. Gaspar Martins, 395 - Floresta, Porto Alegre - RS, 90220-160, Brasil"
      },
      "end": {
        "latitude": -30.029277,
        "longitude": -51.234226,
        "date": "2019-04-04 17:24",
        "address": "Rua General João Manoel, 50, Centro Histórico, Porto Alegre - RS, Brasil"
      },
      "realStart": {
        "latitude": -30.021714,
        "longitude": -51.210759,
        "date": "2019-04-04 17:13",
        "address": "Floresta - R. São Carlos, 545 - Floresta, Porto Alegre - RS, 90220-121, Brasil"
      },
      "realEnd": {
        "latitude": -30.029156,
        "longitude": -51.233957,
        "date": "2019-04-04 17:24",
        "address": "R. Gen. João Manoel, 127 - Centro Histórico, Porto Alegre - RS, 90010-030, Brasil"
      },
      "duration": 11,
      "driver": {
        "name": "Motorista",
        "phone": "11999999999",
        "car": "Fiat Siena",
        "carYear": 2015,
        "carPlate": "EJF-3931"
      },
      "city": "Porto Alegre",
      "callPlatform": "web",
      "requesterName": "COLABORADOR",
      "taxiCategoryName": "Taxi 30% OFF"
    }
    ```

    **Status Code:** 404

    > O recibo não existe ou não está disponível. Em caso de corrida finalizada, deve-se aguardar o tempo de sincronização e geração do recibo.
        
-----

### Busca do recibo pelo identificador do recibo

* **URL**

  `/receipts/receipt-id/{receiptId}`

* **Método**

  `GET`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | receiptId    | alfanumérico     | Identificador do recibo                      | sim             | -                | 1              |


* **Retorno**
  
  **Status Code:** 200

  Descrição: Recibo gerado e disponível.
  
  **Exemplo de retorno**
  
    ```json
    {
      "receiptId": "8382932932",
      "company": {
        "id": 9311,
        "name": "99 Tecnologia"
      },
      "employee": {
        "id": 434343,
        "name": "COLABORADOR",
        "phone": "11999999999",
        "phoneCountry": "BRA",
        "email": "colaborador@99taxis.com",
        "nationalId": "63313137709",
        "externalId": 329932
      },
      "costCenter": {
        "id": 8483843,
        "name": "99Integracao"
      },
      "project": {
        "id": 433902,
        "name": "99Projeto"
      },
      "fare": 25.4,
      "note": "justificativa",
      "odometer": 7593,
      "start": {
        "latitude": -30.021647,
        "longitude": -51.210702,
        "date": "2019-04-04 17:13",
        "address": "R. Gaspar Martins, 395 - Floresta, Porto Alegre - RS, 90220-160, Brasil"
      },
      "end": {
        "latitude": -30.029277,
        "longitude": -51.234226,
        "date": "2019-04-04 17:24",
        "address": "Rua General João Manoel, 50, Centro Histórico, Porto Alegre - RS, Brasil"
      },
      "realStart": {
        "latitude": -30.021714,
        "longitude": -51.210759,
        "date": "2019-04-04 17:13",
        "address": "Floresta - R. São Carlos, 545 - Floresta, Porto Alegre - RS, 90220-121, Brasil"
      },
      "realEnd": {
        "latitude": -30.029156,
        "longitude": -51.233957,
        "date": "2019-04-04 17:24",
        "address": "R. Gen. João Manoel, 127 - Centro Histórico, Porto Alegre - RS, 90010-030, Brasil"
      },
      "duration": 11,
      "driver": {
        "name": "Motorista",
        "phone": "11999999999",
        "car": "Fiat Siena",
        "carYear": 2015,
        "carPlate": "EJF-3931"
      },
      "city": "Porto Alegre",
      "callPlatform": "app",
      "taxiCategoryName": "99TAXI "
    }
    ```

    **Status Code:** 404

    > O recibo não existe ou não está disponível. Em caso de corrida finalizada, deve-se aguardar o tempo de sincronização e geração do recibo.
        
-----

## Corridas

#### Categorias por colaborador e estimativa de valor por corrida

- **URL**

  `/rides/estimate/{employeeId}`

- **Método**

  `GET`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                      | Obrigatório | Valor padrão | Exemplo     |
  | -------- | ------------ | ------------------------------ | ----------- | ------------ | ----------- |
  | id       | numérico     | Identificador do colaborador   | sim         | -            | 10          |
  | fromLat  | alfanumérico | Latitude do ponto de origem    | sim         | -            | -23.5986665 |
  | fromLng  | alfanumérico | Longitude do ponto de origem   | sim         | -            | -46.6907445 |
  | toLat    | alfanumérico | Latitude do ponto de destino   | sim         | -            | -23.6822    |
  | toLng    | alfanumérico | Longitude do ponsto de destino | sim         | -            | -46.6896    |

* **Retorno**

  **Status Code:** 200

  ```json
  [
    {
      "category": {
        "id": "turbo-taxi",
        "name": "99TAXI",
        "description": "Agora: 30% OFF",
        "eta": 2
      },
      "estimate": {
        "lowerFare": 23,
        "upperFare": 32,
        "currencyCode": "R$",
        "fareText": "R$23",
        "fareTextComplement": "ou mais"
      },
      "optionals": [
        {
          "id": "accessible-taxi",
          "title": "Veículo adaptado para cadeirante",
          "description": "Veículo acessível"
        },
        {
          "id": "big-trunk",
          "title": "Porta-malas grande",
          "description": "Porta-malas grande"
        },
        {
          "id": "accept-animals",
          "title": "Permite animais",
          "description": "Permite animais"
        },
        {
          "id": "air-conditioner",
          "title": "Ar-condicionado",
          "description": "Ar-condicionado"
        }
      ]
    },
    {
      "category": {
        "id": "regular-taxi",
        "name": "Táxi Comum",
        "description": "Tarifa cheia",
        "eta": 4
      },
      "estimate": {
        "lowerFare": 34,
        "upperFare": 45,
        "currencyCode": "R$",
        "fareText": "R$34",
        "fareTextComplement": "ou mais"
      },
      "optionals": [
        {
          "id": "accessible-taxi",
          "title": "Veículo adaptado para cadeirante",
          "description": "Veículo acessível"
        },
        {
          "id": "big-trunk",
          "title": "Porta-malas grande",
          "description": "Porta-malas grande"
        },
        {
          "id": "accept-animals",
          "title": "Permite animais",
          "description": "Permite animais"
        },
        {
          "id": "air-conditioner",
          "title": "Ar-condicionado",
          "description": "Ar-condicionado"
        }
      ]
    }
  ]
  ```
---

## Usuários

#### Listar usuários com os perfis de acesso

- **URL**

  `/users`

- **Método**

  `GET`

- **Retorno**

  **Status Code:** 200

  ```json
  [
    {
      "name": "Colaborador 1",
      "email": "colaborador1@99taxis.com",
      "profile": "CorporateUser"
    },
    {
      "name": "Colaborador 2",
      "email": "colaborador2@99taxis.com",
      "profile": "EmployeeUser"
    }
  ]
  ```

---

#### Criar um usuário

- **URL**

  `/users`

- **Método**

  `POST`

- **Parâmetros via body**

  | Atributo        | Tipo do dado    | Descrição           | Obrigatório | Valor padrão | Exemplo           |
  | --------------- | --------------- | ------------------- | ----------- | ------------ | ----------------- |
  | user.name       | alfanumérico    | Nome do usuário     | sim         | -            | José Santos       |
  | user.email      | alfanumérico    | E-mail do usuário   | sim         | -            | jose@empresa.com  |
  
- **Exemplo de envio**

    ```json
    {
      "email": "José Santos",
      "name": "jose@empresa.com"
    }
    ```

- **Retorno**

  **Status Code:** 201

  ```json
  [
    {
      "name": "José Santos",
      "email": "jose@empresa.com",
      "profile": "EmployeeUser"
    }
  ]
  ```

---

#### Remover usuário

- **URL**

  `/users`

- **Método**

  `DELETE`

- **Parâmetros via url**

  | Atributo   | Tipo do dado | Descrição           | Obrigatório | Valor padrão | Exemplo                  |
  | ---------- | ------------ | ------------------- | ----------- | ------------ | ------------------------ |
  | email      | alfanumérico | email do usuário    | sim         | -            | colaborador@empresa.com  |

  > Para permitir carateres especiais, é preciso fazer o _encode_ do parâmetro `email` antes de efetuar a requisição.  
  ex: `jose+marketing@empresa.com` -> `jose%2Bmarketing%40empresa.com`

- **Retorno**

  **Status Code:** 204

---

#### Adicionar o perfil de CorporateUser ao usuário
 
- **URL**

  `/users/admin`

- **Método**

  `PUT`

- **Parâmetros via url**

  | Atributo   | Tipo do dado | Descrição           | Obrigatório | Valor padrão | Exemplo                  |
  | ---------- | ------------ | ------------------- | ----------- | ------------ | ------------------------ |
  | email      | alfanumérico | email do usuário    | sim         | -            | colaborador@empresa.com  |

  > Para permitir carateres especiais, é preciso fazer o _encode_ do parâmetro `email` antes de efetuar a requisição.  
  ex: `jose+marketing@empresa.com` -> `jose%2Bmarketing%40empresa.com`

- **Retorno**

  **Status Code:** 200

  ```json
  {
    "name": "Colaborador",
    "email": "colaborador@empresa.com",
    "profile": "CorporateUser"
  }
  ```

---

#### Remover o perfil de CorporateUser ao usuário
 
- **URL**

  `/users/admin`

- **Método**

  `DELETE`

- **Parâmetros via url**

  | Atributo   | Tipo do dado | Descrição           | Obrigatório | Valor padrão | Exemplo                  |
  | ---------- | ------------ | ------------------- | ----------- | ------------ | ------------------------ |
  | email      | alfanumérico | email do usuário    | sim         | -            | colaborador@empresa.com  |

  > Para permitir carateres especiais, é preciso fazer o _encode_ do parâmetro `email` antes de efetuar a requisição.  
  ex: `jose+marketing@empresa.com` -> `jose%2Bmarketing%40empresa.com`

- **Retorno**

  **Status Code:** 200

  ```json
  {
    "name": "Colaborador",
    "email": "colaborador@empresa.com",
    "profile": "EmployeeUser"
  }
  ```

---

#### Atualizar o perfil do usuário
 
- **URL**

  `/users/profile`

- **Método**

  `PUT`

- **Retorno**

  **Status Code:** 200

  ```json
  {
    "name": "Colaborador",
    "email": "colaborador@empresa.com"
  }
  ```

---

## Suporte da API

Caso tenha mais dúvidas ou esteja com problemas na integração, mande um e-mail para _help-corp-api@99taxis.com_
