package me.dio.mockito.exemplos;

import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doThrow;;

/**
 * Teste da classe {@link CadastrarPessoa} apresentando cenários básicos de uso do Mockito, usando o recurso
 * de mocks e a manipulação de retornos, da forma mais simples e com manipulação de erros
 */
@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {

    @Mock
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;
    
    @Test
    void validarDadosDeCadastro() {
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("PR", "Curitiba", "Rua Castro Alves", "Casa", "Pilarzinho");

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("82120340")).thenReturn(dadosLocalizacao);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("Priscila", "28578527976", LocalDate.of(1947, 11, 12), "82120340");

        assertEquals("Priscila", pessoa.getNome());
        assertEquals("28578527976", pessoa.getDocumento());
        assertEquals("PR", pessoa.getEndereco().getUf());
        assertEquals("Casa", pessoa.getEndereco().getComplemento());
    }

    @Test
    void cadastrarPessoa() {

        /*DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("PR", "Curitiba", "Rua Castro Alves", "Casa", "Pilarzinho");*/

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(null);

        Pessoa priscila = cadastrarPessoa.cadastrarPessoa("Priscila", "28578527976", LocalDate.of(1947, 11, 12), "82120340");

        assertNull(priscila.getEndereco());
    }

    @Test
    void lancarExceptionQuandoChamarApiCorreios() {

        doThrow(IllegalArgumentException.class)
            .when(apiDosCorreios)
                .buscaDadosComBaseNoCep(anyString());


        Assertions.assertThrows(IllegalArgumentException.class, () -> cadastrarPessoa.cadastrarPessoa("Priscila", "28578527976", LocalDate.of(1947, 11, 12), "82120340"));
        
    }

    /*
    @Test
    void tentaCadastrarPessoaMasSistemaDosCorreiosFalha() {

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> cadastrarPessoa.cadastrarPessoa("Priscila", "28578527976", LocalDate.of(1979, 11, 12), "82120340"));
    } 
     */
}
