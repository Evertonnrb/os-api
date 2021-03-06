create table ordem_servicos(
	id serial,
	cliente_id bigint not null,
	descricao text not null,
	preco decimal(10,2) not null,
	status varchar(50) not null,
	data_abertura timestamp not null,
	data_finalizacao timestamp,
	primary key (id)
);
alter table ordem_servicos add constraint fk_ordem_servico_cliente foreign key (cliente_id) references clientes (id)
