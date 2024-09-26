CREATE DATABASE jogo_textAdventure;

USE jogo_textAdventure;

CREATE TABLE Cena (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    comando_correto VARCHAR(255) NOT NULL,
    id_proxima_cena INT,
    FOREIGN KEY (id_proxima_cena) REFERENCES Cena(id)
);

CREATE TABLE Item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    pode_ser_coletado BOOLEAN NOT NULL,
    id_cena INT,
    FOREIGN KEY (id_cena) REFERENCES Cena(id)
);

select * from item 


CREATE TABLE Inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT,
    id_jogo INT,
    FOREIGN KEY (id_item) REFERENCES Item(id)
);

CREATE TABLE Jogo_Salvo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cena_atual INT,
    data_salvamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cena_atual) REFERENCES Cena(id)
);

select * from jogo_salvo js 

select * from cena

----------------------------------------------------------

-- Inserindo as cenas sem a referência à próxima cena
INSERT INTO Cena (descricao, comando_correto, id_proxima_cena) VALUES 
('Você está na frente de uma mansão. O PORTÃO está entreaberto.', 'use portao', NULL),
('Você entrou no hall de entrada. Há uma PORTA à sua frente.', 'use porta', NULL),
('Você vê uma escada que leva ao segundo andar. Use a ESCADA.', 'suba escada', NULL),
('Você chegou à biblioteca antiga. Examine a ESTANTE.', 'examine estante', NULL),
('Você encontrou uma porta secreta atrás da estante. Use a PORTA SECRETA.', 'use porta secreta', NULL),
('Você finalmente chegou ao tesouro escondido. Pegue o TESOURO.', 'pegue tesouro', NULL);

-- Atualizando as cenas para definir o id_proxima_cena
UPDATE Cena SET id_proxima_cena = 5 WHERE id = 4;
UPDATE Cena SET id_proxima_cena = 6 WHERE id = 5;
UPDATE Cena SET id_proxima_cena = 7 WHERE id = 6;
UPDATE Cena SET id_proxima_cena = 8 WHERE id = 7;
UPDATE Cena SET id_proxima_cena = 9 WHERE id = 8;


------------------------------------------------------------

select * from item i 


ALTER TABLE Item
MODIFY COLUMN pode_ser_coletado TINYINT DEFAULT 1;

-- Inserindo os itens com o valor de pode_ser_coletado
INSERT INTO Item (nome, descricao, pode_ser_coletado, id_cena) VALUES
('PORTÃO', 'Um grande portão de madeira que range ao ser aberto.', 0, NULL),
('PORTA', 'Uma porta robusta, com sinais de desgaste.', 0, NULL),
('ESCADA', 'Uma escada antiga, que leva ao segundo andar.', 0, NULL),
('ESTANTE', 'Uma estante cheia de livros empoeirados.', 0, NULL),
('CHAVE', 'Uma chave antiga que pode abrir portas.', 1, NULL),  -- Item coletável
('TESOURO', 'Um baú antigo cheio de ouro e joias.', 1, NULL);  -- Item coletável

-- Atualizando os itens para definir o id_cena
UPDATE Item SET id_cena = 4 WHERE nome = 'PORTÃO';
UPDATE Item SET id_cena = 5 WHERE nome = 'PORTA';
UPDATE Item SET id_cena = 6 WHERE nome = 'ESCADA';
UPDATE Item SET id_cena = 7 WHERE nome = 'ESTANTE';
UPDATE Item SET id_cena = 8 WHERE nome = 'CHAVE';   -- CHAVE na cena 5
UPDATE Item SET id_cena = 9 WHERE nome = 'TESOURO';  -- TESOURO na cena 6

DELETE FROM Cena;


INSERT INTO Cena (descricao, comando_correto, id_proxima_cena) VALUES
('Você está parado em frente à imponente Mansão Ravenwood. O portão de ferro está enferrujado e entreaberto. O vento sopra, e as folhas mortas voam pelo pátio. Algo sobre o lugar faz seus pelos arrepiarem.', 'use portao', NULL),
('Você se encontra no hall da mansão. Há uma grande escadaria à sua frente, mas uma porta ao lado parece levar a outro cômodo. Tudo está empoeirado, e o silêncio é ensurdecedor.', 'use porta', NULL),
('A sala de estar está cheia de móveis antigos cobertos por panos. Há uma lareira apagada no canto e, ao lado dela, uma chave brilhando na penumbra. Parece importante.', 'pegue chave', NULL),
('Você está em um corredor longo e escuro. Há uma porta trancada no fim do corredor. Parece que você precisará de algo para abri-la.', 'use chave', NULL),
('Você entra na antiga biblioteca da mansão. As prateleiras estão cheias de livros empoeirados. Há uma estante que parece diferente das outras.', 'examine estante', NULL),
('Atrás da estante, uma passagem secreta se revela. Ela leva para o porão da mansão. Ao descer, você encontra uma lanterna apagada jogada no chão.', 'pegue lanterna', NULL),
('O porão é frio e escuro. Você mal consegue ver à sua frente. Precisa de luz para continuar explorando.', 'use lanterna', NULL),
('No canto do porão, você encontra um cofre antigo. Está trancado, mas há marcas no chão sugerindo que foi mexido recentemente.', 'use livro', NULL),
('O cofre se abre lentamente, revelando o tão falado tesouro dos Ravenwood. Dentro, há joias, ouro e uma carta. A carta revela que a família Ravenwood
 fugiu para escapar de uma perseguição injusta, deixando o tesouro para trás como isca.', 'pegue tesouro', NULL),
('Você está de volta à entrada da mansão. Com o tesouro nas mãos e a história revelada, você sente o peso da vitória, mas também o mistério sombrio que ainda cerca os Ravenwood.', 'use portao', NULL);
























