package com.igorgrs.cursomc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.igorgrs.cursomc.domain.Categoria;
import com.igorgrs.cursomc.domain.Cidade;
import com.igorgrs.cursomc.domain.Cliente;
import com.igorgrs.cursomc.domain.Endereco;
import com.igorgrs.cursomc.domain.Estado;
import com.igorgrs.cursomc.domain.ItemPedido;
import com.igorgrs.cursomc.domain.Pagamento;
import com.igorgrs.cursomc.domain.PagamentoComBoleto;
import com.igorgrs.cursomc.domain.PagamentoComCartao;
import com.igorgrs.cursomc.domain.Pedido;
import com.igorgrs.cursomc.domain.Produto;
import com.igorgrs.cursomc.domain.enums.EstadoPagamento;
import com.igorgrs.cursomc.domain.enums.TipoCliente;
import com.igorgrs.cursomc.repository.CategoriaRepository;
import com.igorgrs.cursomc.repository.CidadeRepository;
import com.igorgrs.cursomc.repository.ClienteRepository;
import com.igorgrs.cursomc.repository.EnderecoRepository;
import com.igorgrs.cursomc.repository.EstadoRepository;
import com.igorgrs.cursomc.repository.ItemPedidoRepository;
import com.igorgrs.cursomc.repository.PagamentoRepository;
import com.igorgrs.cursomc.repository.PedidoRepository;
import com.igorgrs.cursomc.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository catRep;

	@Autowired
	private ProdutoRepository prodRep;

	@Autowired
	private CidadeRepository cidRep;

	@Autowired
	private EstadoRepository estRep;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática", new ArrayList<Produto>());
		catRep.save(c1);

		Produto p1 = new Produto(null, "Mouse", 20.0, new ArrayList<Categoria>());
		prodRep.save(p1);

		c1.getProdutos().add(p1);
		p1.getCategorias().add(c1);

		catRep.save(c1);
		prodRep.save(p1);

		Estado estado1 = new Estado(null, "Minas Gerais", new ArrayList<Cidade>());
		estRep.save(estado1);

		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		cidRep.save(cidade1);

		Cidade cidade2 = new Cidade(null, "Unaí", estado1);
		cidRep.save(cidade2);

		Cliente cli1 = new Cliente(null, "Maria", "maria@hotmail.com", "11094463655", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("5538998669907", "5538998669908"));

		Endereco end1 = new Endereco(null, "Antiga Rua 15", "1348", "Apto 203", "Santa Mônica", "38406064", cli1,
				cidade1);
		cli1.getEnderecos().addAll(Arrays.asList(end1));

		clienteRepository.save(cli1);
		enderecoRepository.save(end1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("03/09/2019 13:00"), cli1, cli1.getEnderecos().get(0));
		Pedido ped2 = new Pedido(null, sdf.parse("02/09/2019 12:00"), cli1, cli1.getEnderecos().get(0));

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO.getCodigo(), ped1, 5);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE.getCodigo(), ped2, sdf.parse("10/10/2019 12:00"),
				sdf.parse("03/09/2019 12:00"));
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 10, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped2, p1, 0.00, 20, 4000.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2));
		
	}
}
