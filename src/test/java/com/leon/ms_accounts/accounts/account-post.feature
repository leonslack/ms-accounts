Feature: Account management

  Background:
    * url 'http://localhost:8082'

  Scenario: Create a Cuenta for a Cliente and perform a Movimiento
    Given path '/cuentas'
    And request { accountNumber: '123456', accountType: 'Ahorros', initialBalance: 500.00, status: true }
    When method post
    Then status 201
    And def cuentaId = response.id