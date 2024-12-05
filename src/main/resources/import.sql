insert into Estado(nome, sigla) values 
('tocantins', 'to'),
('goias', 'go'),
('distrito federal', 'df'),
('minas gerais', 'mg'),
('sao paulo', 'sp'),
('rio de janeiro', 'rj'),
('parana', 'pr'),
('santa catarina', 'sc'),
('rio grande do sul', 'rs'),
('mato grosso', 'mt'),
('mato grosso do sul', 'ms'),
('pará', 'pa'),
('amazonas', 'am'),
('rondonia', 'ro'),
('acre', 'ac');

insert into Municipio(nome, id_estado) values
('Palmas', 1),
('Araguaina', 1),
('Inhumas', 2),
('Goiania', 2),
('Brasilia', 3),
('Belo Horizonte', 4),
('Uberlandia', 4),
('Sao Paulo', 5),
('Campinas', 5),
('Rio de Janeiro', 6),
('Niteroi', 6),
('Curitiba', 7),
('Londrina', 7),
('Florianopolis', 8),
('Joinville', 8),
('Porto Alegre', 9),
('Caxias do Sul', 9),
('Cuiaba', 10),
('Campo Grande', 11),
('Belem', 12),
('Manaus', 13),
('Porto Velho', 14),
('Rio Branco', 15);

insert into Fabricante(nome, cnpj, email) values 
('Samsung', '12345678901234', 'samsung@fabricante.com'),
('Apple', '12345678901234', 'apple@fabricante.com'),
('Dell', '12345678901234', 'dell@fabricante.com'),
('LG', '12345678901234', 'lg@fabricante.com'),
('Sony', '12345678901234', 'sony@fabricante.com'),
('Nokia', '12345678901234', 'nokia@fabricante.com'),
('Asus', '12345678901234', 'asus@fabricante.com'),
('Lenovo', '12345678901234', 'lenovo@fabricante.com'),
('Xiaomi', '12345678901234', 'xiaomi@fabricante.com'),
('Huawei', '12345678901234', 'huawei@fabricante.com');


insert into TelefoneFabricante(fabricante_id, codigoArea, numero) values 
(1,'63', '999999999'), 
(1,'63', '999999991'),  
(2,'62', '888888888'), 
(2,'62', '888888887'),  
(3,'61', '777777777'), 
(4, '31', '666666666'),  
(5, '11', '555555555'), 
(6, '41', '444444444'), 
(7, '48', '333333333'), 
(8, '51', '222222222'); 



insert into TamanhoMonitor(tamanho, altura, largura, peso) values 
('13.3', 0.3, 0.3, 0.5),
('15.6', 0.4, 0.4, 0.7), 
('17.3', 0.5, 0.5, 0.9), 
('18.5', 0.6, 0.6, 1.1), 
('19.5', 0.7, 0.7, 1.3), 
('21.5', 0.8, 0.8, 1.5), 
('23.8', 0.9, 0.9, 1.7), 
('24.5', 1.0, 1.0, 1.9),  
('27', 1.1, 1.1, 2.1),
('29', 1.2, 1.2, 2.3),
('30', 1.3, 1.3, 2.5);

insert into Fornecedor(nome, cnpj, email) values 
('Fujioka s.a', '12345678901234', 'fujioka@fornecedor.com'), 
('Samsung', '12345678901234', 'samsung@fornecedor.com'), 
('Ching xong', '12345678901234', 'chingxong@fornecedor.com');


insert into telefoneFornecedor(fornecedor_id, codigoArea, numero) values
(1,'63', '999999999'),
(1,'63', '999999991'),
(2,'62', '888888888'),
(2,'62', '888888887'),
(3,'61', '777777777'),
(3, '31', '666666666');



insert into Monitor(nome, marca, modelo, preco, taxaAtualizacao, 
tempoResposta, AnoLancamento, tamanho_monitor_id, fabricante_id) values 
('Odyssey 1', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 2', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 3', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 4', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 5', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 6', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 7', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 8', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 9', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1),
('Odyssey 10', 'Samsung', 'S24F350F', 500, 60, 4, 2017, 8, 1);


insert into Lote(id_monitor, data, quantidade, id_fornecedor, codigo) values 
(1, '2020-01-01', 10, 1, 'MONITORf47ac10b-58cc-4372-a567-0e02b2c3d479'),
(1, '2019-01-01', 5, 1, 'MONITORf47ac10b-58cc-4372-a567-0e02b2c3d479'),
(2, '2020-01-01', 10, 1, 'MONITOR23a4567e-9abc-4f12-b7d9-9c8baf1023cd'),
(3, '2020-01-01', 10, 2, 'MONITOR1d78e94a-5234-4a6c-a293-8f284dc391af'),
(4, '2020-01-01', 10, 2, 'MONITOR345cd0c4-0d53-4d34-a067-7acbe2088c6f'),
(5, '2020-01-01', 10, 2, 'MONITORfbd34a34-bdf3-4a8c-a8e3-2e2f813d920d'),
(6, '2020-01-01', 10, 3, 'MONITOR15ec9e80-0c89-4f90-9ebd-8163c89143a4'),
(7, '2020-01-01', 10, 3, 'MONITOR2a71c3b1-4e22-4126-8511-9c2e48df7dc3'),
(8, '2020-01-01', 10, 3, 'MONITORc65e1a45-26ef-4379-8f73-9ac13898d5e7'),
(9, '2020-01-01', 10, 3, 'MONITOR9e0e2a1d-1269-482a-85bf-7a1234905e8a'),
(10, '2020-01-01', 10, 3, 'MONITORae3291c2-2a42-4c7f-a23e-9b21348df4e1');


insert into Usuario(username, senha, perfil) values ('janio', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 1);
insert into Usuario(username, senha, perfil) values ('carloshenrique', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 2);

-- senha 123456
-- 0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==

insert into Usuario(username, perfil, senha) values (
'admin', '1', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ=='
);

insert into EnderecoUser(logradouro, numero, complemento, bairro, cep, id_municipio, id_estado)values
('Rua 1', 1, 'Apto 1', 'Centro', '12345678', 1, 1);

insert into Cliente(username, nome, cpf, email, sexo, id_endereco_user, id_usuario, nomeImagem) values
('janio', 'Janio', '12345678901', 'janio@cliente.com', 2, 1, 1, 'C:\Users\italo\quarkus\images\usuario\7bc22f6d-50de-4b78-848e-5d29cf92218e.jpeg');

insert into Funcionario(username, nome, cpf, email, sexo, id_endereco_funcionario, id_usuario) values
('janio', 'Janio', '12345678901', 'janio@cliente.com', 2, 1, 1);
