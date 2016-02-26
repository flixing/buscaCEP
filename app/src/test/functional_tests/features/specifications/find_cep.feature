Feature: Test

  Scenario: As a user, I want find a valid CEP

    Given I press the Buscar Cep button
    When entering the zip code data
    And I press the buscar button
    Then I see the text "Rua Girassol"
    And I see the text "Vila Madalena"
    And I see the text "São Paulo / SP"
