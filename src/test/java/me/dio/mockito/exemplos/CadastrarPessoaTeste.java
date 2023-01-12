package me.dio.mockito.exemplos;

//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
//import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.anyString;

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


    /*    @Test
    void cadastrarPessoa() {

        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("PR", "Curitiba", "Rua Castro Alves", "Casa", "Pilarzinho");

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(dadosLocalizacao);

        Pessoa priscila = cadastrarPessoa.cadastrarPessoa("Priscila", "28578527976", LocalDate.of(1947, 11, 12), "82120340");

        DadosLocalizacao enderecoJose = priscila.getEndereco();
        assertEquals(dadosLocalizacao.getBairro(), enderecoJose.getBairro());
        assertEquals(dadosLocalizacao.getCidade(), enderecoJose.getCidade());
        assertEquals(dadosLocalizacao.getUf(), enderecoJose.getUf());
    }

    @Test
    void tentaCadastrarPessoaMasSistemaDosCorreiosFalha() {

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(RuntimeException.class);

        Assertions.assertThrows(RuntimeException.class, () -> cadastrarPessoa.cadastrarPessoa("Priscila", "28578527976", LocalDate.of(1979, 11, 12), "82120340"));
    } */

}
