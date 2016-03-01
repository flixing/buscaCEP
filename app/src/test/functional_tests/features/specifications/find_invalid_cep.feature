@invalid_cep
Feature: Find invalid CEP

  Scenario: As a user, I want find a invalid CEP

    Given I press the Buscar Cep button
    When entering the invalid zip code data
    And I press the buscar button
    Then I wait for "Por favor informe um CEP válido." to appear
