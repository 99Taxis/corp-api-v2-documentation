## 99 CORP - Documentação API

Documentação para uso dos endpoints da API para clientes corporativos da 99.

Endereço de acesso: https://api-corp.99app.com/v2

## Swagger

É possível realizar testes online utilizando a interface do Swagger. Para isso, acesse o endereço abaixo:

https://api-corp.99app.com/v2/docs/swagger-ui.html

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
- [Projetos](#projetos)
- [Colaboradores](#colaboradores)
- [Colaboradores com Identificador Externo](#colaboradores-com-identificador-externo)
- [Recibos](#recibos)
- [Webhook](#webhook)
- [Corridas](#corridas)
- [Usuários](#usuários)
- [Testes em Sandbox](#testes-em-sandbox)

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

#### Busca de parâmetro de ativação de envio de emails de recibo de corrida

* **URL**

  `/companies/receiptEmail`

* **Método**

  `GET`
  
* **Retorno**
  
  **Status Code:** 200
  
    ```json
    true
    ``` 
    
-----

#### Alteração de parâmetro de ativação de envio de emails de recibo de corrida

* **URL**

  `/companies/receiptEmail`

* **Método**

  `PUT`

- **Parâmetros via body**

  | Atributo   | Tipo do dado   | Descrição                             | Obrigatório | Valor padrão | Exemplo                                             |
  | -----------| -------------- | ------------------------------------- | ----------- | ------------ | --------------------------------------------------- |
  | enable     | booleano       | Flag para ativar ou desativar o envio | sim         | -            | true                                                |
  
* **Exemplo de envio**

    ```json
    false
    ```

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    false
    ``` 
    
-----

#### Ativação de obrigatoriedade de informação do identificador do projeto para chamar corrida.

* **URL**

  `/companies/enable`

* **Método**

  `PUT`
  

* **Retorno**
    
  **Status Code:** 200
  
    Solicitação efetuada com sucesso

  **Status Code:** 304

    Empresa já possui obrigatoriedade de informação do identificador do projeto.
    
-----

#### Desativação de obrigatoriedade de informação do identificador do projeto para chamar corrida.

* **URL**

  `/companies/disable`

* **Método**

  `PUT`
  
* **Retorno**
    
  **Status Code:** 200
  
    Solicitação efetuada com sucesso

  **Status Code:** 304

    Obrigatoriedade de informação do identificador do projeto já está desabilitada.
    
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
        }
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
> O atributo `intervals.dayOfWeek` identifica os dias da semana, sendo `0` domingo e `6` o sábado. Qualquer número diferente desse intervalo de 0 à 6 não é aceito.  
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
>  O atributo `intervals.dayOfWeek` identifica os dias da semana, sendo `0` domingo e `6` o sábado. Qualquer número diferente desse intervalo de 0 à 6 não é aceito.  
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
  | lista         | conjunto de numéricos | Identificadores de centros de custo | sim         | -            | 100, 200 |

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

## Projetos

#### Busca de projetos

* **URL**

  `/projects`

* **Method**

  `GET`
  
*  **Parâmetros via query**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | search       | alfanumérico     | Busca pelo nome do centro de custo           | não             | -                | financeiro     |
   | page        | numérico         | Índice da página no sistema de paginação     | não             | 1                | 0              |
   | limit        | numérico         | Quantidade de registros por página           | não             | 50               | 20             |

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    [
      {
        "id": 1,
        "name": "evento brasil 2019",
        "company": {
          "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
          "name": "99"
        }
      },
      {
        "id": 2,
        "name": "project financeiro",
        "company": {
          "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
          "name": "99"
        }
      }
    ]
    ``` 
    
-----

#### Busca de projeto por identificador

* **URL**

  `/projects/{id}`

* **Method**

  `GET`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do project                   | sim             | -                | 1             |

* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
      "id": 1,
      "name": "evento brasil 2019",
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      }
    }
    ```
    
-----

#### Cadastro de projeto

* **URL**

  `/projects`

* **Method**

  `POST`
  
*  **Parâmetros via body**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo            |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------        |
   | name       | alfanumérico     | Nome do projecto                           | sim             | -                | expo 2019         |
   
*  **Exemplo de envio**
   
   ```json
   {
     "name": "recursos humanos"
   }
   ```

* **Retorno**
  
  **Status Code:** 201
  
    ```json
    {
      "id": 5,
      "name": "expo 2019",
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      }
    }
    ```
    
-----

#### Atualizar os dados de um projeto

* **URL**

  `/projects/{id}`

* **Method**

  `PUT`
  
*  **Parâmetros via url**


    | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
    |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
    | id           | numérico         | Identificador do project                   | sim             | -                | 1             |
  
*  **Parâmetros via body**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo            |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------        |
   | name       | alfanumérico     | Nome do projeto                           | sim             | -                | expo 2017         |
   
*  **Exemplo de envio**
   
   ```json
   {
     "name": "expo 2017"
   }
   ```

* **Retorno**
  
  **Status Code:** 201
  
    ```json
    {
      "id": 5,
      "name": "expo 2017",
      "company": {
        "id": "47a3083b-5d03-4e05-ad9d-9fd6fddd613e",
        "name": "99"
      }
    }
    ```
    
-----

#### Desativar um projeto

* **URL**

  `/projects/{id}`

* **Method**

  `DELETE`
  
*  **Parâmetros via body**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | id           | numérico         | Identificador do projeto                   | sim             | -                | 20             |

* **Retorno**
  
  **Status Code:** 204
    
-----

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

#### Salvar termos de aceite do colaborador

- **URL**

  `/employees/{id}/agreement`

- **Método**

  `POST`

- **Parâmetros via url**

  | Atributo | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo |
  | -------- | ------------ | ---------------------------- | ----------- | ------------ | ------- |
  | id       | numérico     | Identificador do colaborador | sim         | -            | 20      |

- **Parâmetros via body**

  | Atributo               | Tipo do dado              | Descrição                                     | Obrigatório | Valor padrão | Exemplo                      |
  | ---------------------- | ------------------------- | --------------------------------------------- | ----------- | ------------ | ---------------------------- |
  | term.link              | alfanumérico              | Link do termo de aceite.                      | sim         | -            | https://meu.termo.aceite.com |

* **Exemplo de envio**

  ```json
  {
    "term": {
      "link": "https://meu.termo.aceite.com"
    }
  }
  ```

- **Retorno**

  **Status Code:** 200

  ```json
  {
    "agreementId": "8a08818e-7d80-4b97-9f36-0a0617a8ca90"
  }
  ```

---

## Colaboradores com Identificador Externo

- **Observação:** O campo `externalId` não é único, portanto, caso você atribua um `externalId` a mais de um colaborador, ao fazer operações (com exceção do cadastro) com esse `externalId`, a mesma estará sendo feita para todos os colaboradores que o possuem.

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

  > O `supervisorExternalId` só irá funcionar corretamente caso possua apenas um colaborador com o `externalId` informado, caso o campo `externalId` seja usado para identificar múltiplos colaboradores, recomenda-se usar o `supervisorId`

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
  [{
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
  }]
  ```

  > O campo retornado `supervisorId` é o id interno e não representa o `supervisorExternalId`.

---

#### Atualizar os dados de colaborador por identificador externo e telefone

- **URL**

  `/employees/external-id/{externalId}/{phone}`

- **Método**

  `PUT`

- **Parâmetros via url**

| Atributo   | Tipo do dado | Descrição                    | Obrigatório | Valor padrão | Exemplo    |
| ---------- | ------------ | ---------------------------- | ----------- | ------------ | -----------|
| externalId | numérico     | Identificador do colaborador | sim         | -            | 125        |
| phone      | numérico     | Telefone do colaborador      | sim         | -            | 11988778877|

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

#### Desativar colaboradores por identificador externo

- **Observação:** Todos os colaboradores com o `externalId` informado será desativado

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

#### Busca de centros de custos de colaboradores por identificador externo

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

#### Atualizar os dados de centro de custos dos colaboradores por identificador externo

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

#### Remover centro de custo de colaboradores por identificador externo

- **Observação:** Todos os centros de custo, associados a algum colaboador com o `externalId` informado serão removidos

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

#### Busca de política de uso dos colaboradores por identificador externo

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
  [{
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
  }]
  ```

---

#### Atualizar política de uso dos colaboradores por identificador externo

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

#### Remover politica de uso dos colaboradores por identificador externo

- **Observação:** Todos as politicas de uso, associadas a algum colaboador com o `externalId` informado serão removidas

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

#### Remover supervisor dos colaboradores por identificador externo

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



## Recibos

#### Busca de recibos

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
            "email": "colaborador@99app.com",
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
        "email": "colaborador@99app.com",
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
        "email": "colaborador@99app.com",
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

## Webhook

Webhook permite que seu sistema receba notificações de eventos originados de corridas da 99.
Quando um desses eventos ocorrer, iremos enviar um HTTP POST com o payload do evento para a URL configurada no webhook. Webhooks podem ser utilizados para receber o status e a posição do motorista durante uma corrida em andamento. É possível receber eventos de mudança de status de corrida, assim como posição atual do motorista durante uma corrida em andamento.

#### Segurança

Toda comunicação deve ser feita com HTTPS e a url de recebimento do evento deve estar configurada na porta 443.

Para garantir a integridade do evento, e que o mesmo está sendo enviado através dos servidores da 99, um header no padrão Basic Authentication será acrescentado em toda requisição. As credenciais podem ser configuradas através do endpoint de Criação de Webhook abaixo. Seu servidor deve validar o header afim de garantir a segurança da informação.

#### Estratégia de tentativas 
O webhook espera que sua aplicação responda com o http status code de sucesso 2xx (aceita-se 200, 201, 202, 203, 204, 205 ou 206) com um corpo vazio para todo evento gerado, e iremos aguardar até 10 segundos para receber a resposta. Caso qualquer uma dessas regras não sejam atingidas, o evento será re-transmitido para sua aplicação de acordo com um algoritmo de compensação exponencial (exponential back-off) com multiplicador de 15 segundos, até um limite máximo de 10 tentativas. Isso significa que iremos tentar enviar o mesmo evento para o seu servidor durante um período de 2 horas (por exemplo: 15, 30, 60, 120, 240 segundos e assim sucessivamente).

De acordo com as regras acima, um evento pode ser transmitido mais de uma única vez para os seus servidores, portanto você deve decidir em ignorar caso receba eventos repetidos. O atributo `event.id` garante a chave única do evento, e você pode utilizá-lo para validar eventuais duplicidades.

#### Status de corridas

Os seguintes status de corrida serão notificados quando ocorrerem.
Os status marcados como **final** significam que não sofrerão alterações futuras.

| Status     | Descrição | Status final? |
|----------    |--------------    |---- |
| finding           | Buscando motorista | não |
| no-drivers-available | Nenhum motorista foi encontrado para sua solicitação| sim |
| canceled-by-passenger | Corrida cancelada pelo passageiro | sim |
| canceled-by-driver | Corrida cancelada pelo motorista | sim |
| on-the-way | Motorista encontrado e a caminho | não |
| arrived | Motorista chegou e está aguardando o passageiro | não |
| in-progress | Corrida iniciada com passageiro a bordo | não |
| finished | Corrida encerrada | sim |

#### Obter configuração do Webhook

* **URL**

  `/webhook`

* **Método**

  `GET`

* **Retorno**
  
  **Status Code:** 200

  Descrição: Configuração do webhook cadastrada

  ```json
  {
    "url": "https://app.99app.com/v1/webhook",
    "authentication": {
        "username": "username",
        "password": "password123&&"
    },
    "subscriptions": [
        "ride-status",
        "ride-driver-location"
    ]
  }
  ```

#### Criação do Webhook

* **URL**

  `/webhook`

* **Método**

  `PUT`
  
*  **Parâmetros via body**


   | Atributo                 | Tipo do dado     | Descrição                                            | Obrigatório     | Valor padrão     | Exemplo        |
   |----------                |--------------    |------------------------------------------            |-------------    |--------------    |------------    |
   | url                      | alfanumérico     | URL https de recebimento dos eventos do seu servidor | sim             | não possui       | https://seudominio.com.br/ninenine/webhooks |
   | authentication.username  | alfanumérico | Credencial do usuário de Basic Authentication            | sim             | não possui       | username |
   | authentication.password  | alfanumérico | Credencial de senha de Basic Authentication              | sim             | não possui       | password |
   | subscriptions            | conjunto de alfanuméricos | Lista com subscrições para recebimento de webhooks. Valores aceitos: ride-status, ride-driver-location | sim | não possui | ["ride-status", "ride-driver-location"] |

* **Regras**

- A senha informada para o sistema de segurança Basic Authentication deve conter as seguintes premissas:
  
  - Conter ao menos 8 caracteres
  - Conter ao menos 1 dígito numérico
  - Conter ao menos 1 caractere especial

* **Exemplo de envio**

  ```json
  {
    "url": "https://app.99app.com/v1/webhook",
    "authentication": {
        "username": "username",
        "password": "password123&&"
    },
    "subscriptions": [
        "ride-status",
        "ride-driver-location"
    ]
  }
  ```

* **Retorno**
  
  **Status Code:** 200

  Descrição: Configuração de webhook atualizada.

  **Status Code:** 422

  Descrição: Ocorreram um ou mais erros de validação. Neste status, a seguinte estrutura json será retornada:

  ```json
  {
    "errors": [
        {
            "code": "required-url",
            "field": "url",
            "message": "Url must be informed"
        },
        {
            "code": "invalid-url",
            "field": "url",
            "message": "Url is not valid"
        },
        {
            "code": "required-authentication",
            "field": "authentication",
            "message": "Authentication must be informed"
        }
    ]
  }
  ```

  Segue mapeamento de erros completos que podem ser retornados caso algum dado inválido seja informado ao se definir o webhook.

  | Code                     | Field                   | Message                                                               | Descrição  |
  |----------                |-------                  |---------                                                              | ---------  |
  | required-url             | url                     | Url must be informed                                                  | Url não foi informada |
  | invalid-url              | url                     | Url is not valid                                                      | Url informada não é válida |
  | invalid-url              | url                     | Url must have secure https scheme                                     | Url precisa ser segura e usar https |
  | required-authenticationl | authentication          | Authentication must be informed                                       | Dados de autenticação não foram informados |
  | required-username        | authentication.username | Authentication username must be informed                              | Usuário para autenticação não foi informado |
  | required-password        | authentication.password | Authentication password must be informed                              | Senha para autenticação não foi informado |
  | invalid-password         | authentication.password | Authentication password should contain at least eight digits          | Senha de autenticação deve conter ao menos 8 caracteres |
  | invalid-password         | authentication.password | Authentication password should contain at least one digit             | Senha de autenticação deve conter ao menos 1 número |
  | invalid-password         | authentication.password | Authentication password should contain at least one special character | Senha de autenticação deve conter ao menos 1 caracter especial |
  | required-subscriptions   | subscriptions           | At least one subscription must be informed                            | Subscrição de eventos não foi definida |
  | invalid-subscriptions    | subscriptions           | Allowed subscriptions: ride-status, ride-driver-location              | Subscrição de evento selecionada não é válida |

#### Desativação do uso do Webhook

* **URL**

  `/webhook`

* **Método**

  `DELETE`

* **Retorno**
  
  **Status Code:** 204

  Descrição: Desativado uso do webhook.

---

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

#### Busca de corridas em andamento

* **URL**

  `/rides`

* **Method**

  `GET`

* **Parâmetros via query**

  | Atributo | Tipo do dado | Descrição                                | Obrigatório | Valor padrão | Exemplo     |
    | -------- | ------------ | ---------------------------------------- | ----------- | ------------ | ----------- |
  | phone    | numérico     | Telefone do colaborador atrelado a corrida. Números de 10 à 11 dígitos. (DDD) obrigatório.       | não         | - |11934234234
  
* **Retorno**
  
  **Status Code:** 200 se houver corridas, 204 se não houver
  
  **Estrutura de retorno**
  
  | Atributo                              | Descrição                                                                         |
  |-------------------------------------  |-------------------------------------------------------------------------------    |
  | employeeID                            | Identificador do colaborador atrelado a corrida                                   |
  | from.latitude                         | Latitude do endereço de origem                                                    |
  | from.longitude                        | Longitude do endereço de origem                                                   |
  | from.street                           | Endereço de origem                                                                |
  | phoneNumber                           | Telefone do colaborador atrelado a corrida                                        |
  | costCenterID                          | Identificador do centro de custo                                                  |
  | projectID                             | Identificador do projeto                                                          |
  | categoryID                            | Categoria da corrida. Valores possíveis: regular-taxi, turbo-taxi, top99 ou pop99 |
  | to.latitude                           | Latitude do endereço de destino                                                   |
  | to.longitude                          | Longitude do endereço de destino                                                  |
  | to.street                             | Endereço de destino                                                               |
  | optionals                             | Opcionais da corrida                                                              |
  | status                                | Estado da corrida. Os valores possíveis estão listados na tabela abaixo.          |
  | running.rideID                        | Identificador da corrida em andamento                                             |
  | running.jobID                         | Identificador da corrida finalizada                                               |
  | running.driver.driverId               | Identificador do motorista                                                        |
  | running.driver.fullName               | Nome completo do motorista                                                        |
  | running.driver.phoneNumber            | Telefone do motorista                                                             |
  | running.driver.carModel               | Modelo do carro usado pelo motorista                                              |
  | running.driver.carPlate               | Placa do veículo usado na corrida                                                 |
  | running.driver.carColor               | Cor do veículo usado na corrida                                                   |
  | running.driver.img                    | Endereço com foto do motorista                                                    |
  | running.driver.position.latitude      | Latitude do endereço onde o motorista está localizado                             |
  | running.driver.position.longitude     | Longitude do endereço onde o motorista está localizado                            |
  | running.route.time                    | Tempo restante para chegada do motorista para início da corrida (em segundos)     |
  | running.route.distance                | Distância já percorrida na corrida (em metros)                                    |
  | running.smsStartedSent                | Notifica se o SMS com os dados do motorista foi enviado ao passageiro             |
  | running.smsDriverCanceledSent         | Notifica se o SMS informando o cancelamento da corrida pelo motorista foi enviado |
  | receiver.name                         | Nome do destinatário da corrida do tipo Entrega99                                 | 
  | receiver.phone                        | Telefone do destinatário da corrida do tipo Entrega99                             | 
  
  **Retornos possíveis para o estado da corrida**
  
  | Código                             | Descrição                                           |
  |--------------------------------    |-------------------------------------------------    |
  | WAITING_DRIVERS_ANSWERS            | Aguardando resposta dos motoristas                  | 
  | COULDNT_FIND_AVAILABLE_DRIVERS     | Nenhum motorista disponível                         |
  | DRIVERS_REJECTED                   | Nenhum motorista aceitou a corrida                  |
  | CAR_ON_THE_WAY                     | Motorista está a caminho do endereço solicitado     |
  | WAITING_FOR_PASSENGER              | Motorista chegou e está aguardando passageiro       | 
  | CAR_ARRIVED                        | A corrida está em andamento                         |
  | CANCELED_BY_DRIVER                 | Corrida cancelada pelo motorista                    |
  | CANCELED_BY_PASSENGER              | Corrida cancelada pelo passageiro                   |
  | RIDE_ENDED                         | Corrida finalizada                                  |

  **Exemplo de retorno**
  
    ```json
    [{
      "employeeID": 884373,
      "from": {
        "latitude": -23.564758,
        "longitude": -46.651850,
        "street": "Av Paulista, 1000, São Paulo - SP, Brasil"
      },
      "phoneNumber": "11999999999",
      "costCenterID": 43431,
      "categoryID": "delivery99",
      "to": {
        "latitude": -23.590760,
        "longitude": -46.682129,
        "street": "Av. Faria Lima, 3000, São Paulo - SP, Brasil"
      },
      "projectID": 394932,
      "status": "CAR_ARRIVED",
      "running": {
        "rideID": "12219921932",
        "jobID": "12219921932",
        "driver": {
          "driverId": 6988,
          "fullName": "Claudo Moreira Cruz",
          "phoneNumber": "11999999999",
          "carModel": "Toyota Etios Sedan",
          "carPlate": "EAN-0165",
          "img": "https://xxx.99app.com/images/xxx.jpg",
          "position": {
            "latitude": -23.582894,
            "longitude": -46.683991
          }
        },
        "route": {
          "time": 0,
          "distance": 507
        },
        "smsStartedSent": true,
        "smsDriverCanceledSent": false
        },
        "receiver": {
                "name": "João da Silva",
                "phone": "+5511999998888"
        }
    }]
    ```
    
-----

#### Busca de corrida em andamento ou recentes por identificador

* **URL**

  `/rides/{id}`

* **Method**

  `GET`
  
*  **Parâmetros via url**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | id           | alfanumérico     | Identificador da corrida                     | sim             | -                | 1              |

* **Retorno**
  
  **Status Code:** 200
  
  **Exemplo de retorno**
  
    ```json
    {
        "employeeID": 884373,
        "from": {
            "latitude": -23.564758,
            "longitude": -46.651850,
            "street": "Av Paulista, 1000, São Paulo - SP, Brasil"
        },
        "phoneNumber": "11999999999",
        "costCenterID": 43431,
        "categoryID": "delivery99",
        "to": {
            "latitude": -23.590760,
            "longitude": -46.682129,
            "street": "Av. Faria Lima, 3000, São Paulo - SP, Brasil"
        },
        "projectID": 394932,
        "status": "CAR_ARRIVED",
        "running": {
            "rideID": "12219921932",
            "jobID": "12219921932",
            "driver": {
                "driverId": 6988,
                "fullName": "Claudo Moreira Cruz",
                "phoneNumber": "11999999999",
                "carModel": "Toyota Etios Sedan",
                "carPlate": "EAN-0165",
                "img": "https://xxx.99app.com/images/xxx.jpg",
                "position": {
                    "latitude": -23.582894,
                    "longitude": -46.683991
                }
            },
            "route": {
                "time": 0,
                "distance": 507
            },
            "smsStartedSent": true,
            "smsDriverCanceledSent": false
        },
        "receiver": {
            "name": "João da Silva",
            "phone": "+5511999998888"
        }
    }
    ```
    
-----

    
#### Solicitar corrida

* **URL**

  `/rides`

* **Method**

  `POST`
  
*  **Parâmetros via body**


    | Atributo         | Tipo do dado                | Descrição                                                                                                                  | Obrigatório                                            | Valor padrão | Exemplo                                   |
    |------------      |---------------------------  |--------------------------------------------------------------------------------------------------------------------------  |--------------------------------------------------------|--------------|------------------------------------------ |
    | employeeID       | numérico                    | Identificador do colaborador                                                                                               | sim                                                    | -            | 884373                                    |
    | from.latitude    | alfanumérico                | Latitude do endereço de origem                                                                                             | sim                                                    | -            |  -23.564758                               |
    | from.longitude   | alfanumérico                | Longitude do endereço de origem                                                                                            | sim                                                    | -            | -46.651850                                |
    | from.street      | alfanumérico                | Endereço de origem                                                                                                         | sim                                                    | -            | Av Paulista, 1000, São Paulo - SP, Brasil |
    | from.number      | alfanumérico                | Endereço de origem                                                                                                         | sim                                                    | -            | Av Paulista, 1000, São Paulo - SP, Brasil |
    | from.reference   | alfanumérico                | Ponto de referência para endereço de origem                                                                                | não                                                    | -            | Próximo a estação de metrô                |
    | to.latitude      | alfanumérico                | Latitude do endereço de destino                                                                                            | sim                                                    | -            |  -23.564758                               |
    | to.longitude     | alfanumérico                | Longitude do endereço de destino                                                                                           | sim                                                    | -            | -46.651850                                |
    | to.street        | alfanumérico                | Endereço de destino                                                                                                        | sim                                                    | -            | Av Paulista, 1000, São Paulo - SP, Brasil |
    | to.reference     | alfanumérico                | Ponto de referência para destino de origem                                                                                 | não                                                    | -            | Próximo a estação de metrô                |
    | phoneNumber      | alfanumérico                | Número de telefone do colaborador a ser exibido para o motorista                                                           | sim                                                    | -            | 11999999999                               |
    | costCenterID     | numérico                    | Identificador do centro de custo                                                                                           | sim                                                    | -            | 43431                                     |
    | categoryID       | alfanumérico                | Categoria a ser usada na corrida. Valores aceitos: regular-taxi, turbo-taxi, top99, pop99, comfort99, poupa99, delivery99  | sim                                                    | -            | pop99                                     |
    | projectID        | numérico                    | Identificador do projeto                                                                                                   | não                                                    | -            | 394932                                    |
    | notes            | alfanumérico                | Justificativa da corrida                                                                                                   | não                                                    | -            | reunião com cliente                       |
    | optionals        | conjunto de alfanuméricos   | Opcionais da corrida                                                                                                       | não                                                    | -            | -                                         |
    | receiver.name    | conjunto de alfanuméricos   | Nome do destinatário da corrida do tipo Entrega99                                                                          | apenas quando a corrida for da categoria delivery99    | -            | João da Silva                           |
    | receiver.phone   | conjunto de alfanuméricos   | Número de telefone do destinatário da corrida do tipo Entrega99                                                            | apenas quando a corrida for da categoria delivery99    | -            | 11999999999                             |
    

 *  **Exemplo de envio**

    ```json
    {
      "employeeID": 884373,
      "from": {
        "latitude": -23.564758,
        "longitude": -46.651850,
        "street": "Av Paulista, 1000, São Paulo - SP, Brasil",
        "number": "1000",
        "reference": ""
      },
      "phoneNumber": "11999999999",
      "costCenterID": 43431,
      "categoryID": "pop99",
      "to": {
        "latitude": -23.590760,
        "longitude": -46.682129,
        "street": "Av. Faria Lima, 3000, São Paulo - SP, Brasil",
        "number": "0",
        "reference": ""
      },
      "notes": "",
      "projectID": 394932,
      "optionals": [
        "air-conditioner",
        "female-driver"
      ]
    }
    ``` 


* **Retorno**
  
  **Status Code:** 200
  
    ```json
    {
        "rideID": "12219921932",
        "smsStartedSent": false,
        "smsDriverCanceledSent": false
    }
    ```
    
-----

#### Cancelar uma corrida em andamento

* **URL**

  `/rides/{id}`

* **Method**

  `DELETE`
  
*  **Parâmetros via body**


   | Atributo     | Tipo do dado     | Descrição                                    | Obrigatório     | Valor padrão     | Exemplo        |
   |----------    |--------------    |------------------------------------------    |-------------    |--------------    |------------    |
   | id           | alfanumérico     | Identificador da corrida                     | sim             | -                | 20             |

* **Retorno**
  
  **Status Code:** 204
    
---

#### Rechamada

A função de rechamada não existe explicitamente, porém é possível fazê-la de forma manual, verificando o status da corrida. Na pasta **recall** há um código de exemplo em Java.

- [Ver código Java](https://github.com/99app/corp-api-v2-documentation/tree/master/recall)

-----


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
      "email": "colaborador1@99app.com",
      "profile": "CorporateUser"
    },
    {
      "name": "Colaborador 2",
      "email": "colaborador2@99app.com",
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
      "email": "jose@empresa.com",
      "name": "José Santos"
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

## Testes em Sandbox
Endereço

https://sandbox-api-corp.99app.com/v2

O ambiente de sandbox tem como objetivo prover uma estrutura para realizar os testes de gerenciamento de recursos como colaboradores (employee), centros de custo (cost center) e projetos (project) dentre outros, exceto a opção /rides onde não é possível criar e testar corridas em sandbox, os demais testes podem ser realizados normalmente.


## Suporte da API

Caso tenha mais dúvidas ou esteja com problemas na integração, mande um e-mail para _help-corp-api@99app.com_
