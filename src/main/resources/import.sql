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





insert into TelefoneFabricante (codigoArea, numero) values 
('63', '999999999'),
('62', '888888888'),
('61', '777777777'),
('31', '666666666'),
('11', '555555555'),
('41', '444444444'),
('48', '333333333'),
('51', '222222222'),
('65', '111111111'),
('67', '000000000'),
('91', '123456789'),
('92', '987654321'),
('69', '123123123'),
('68', '321321321');

insert into Usuario(username, senha, perfil) values ('janio', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 1);
insert into Usuario(username, senha, perfil) values ('carloshenrique', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==', 2);

-- senha 123456
-- 0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ==

insert into Usuario(username, perfil, senha) values (
'admin', '1', '0cctg7WgpEz7kC/AzVC+KX+bZLPXDtgJDqWWZWnmzHH+7Na2YVxYYSFPxcf7ImAjqfNckx0aT4n5qKM7WEoeEQ=='
);


