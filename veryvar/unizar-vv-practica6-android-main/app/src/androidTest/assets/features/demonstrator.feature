 Feature: La aplicación es capaz de cambiar el texto de forma automática
    Se actualiza la caja de texto de MainActivity con el texto 'Lalala'  al pulsar el botón 'Change text'

    Scenario Outline: Teclear texto nuevo, pulsar botón 'Change text'
        Given Abro la aplicación Demonstrator
        When Tecleo "<textoTecleado>" en la caja de texto
        And Pulso el botón "<etiqueta>"
        Then Debería ver "<textoCambiado>"

    Examples:
        | textoTecleado | etiqueta | textoCambiado |
        | Hola | Change Text | Lalala |
        | Adios | Change Text | Lalala |