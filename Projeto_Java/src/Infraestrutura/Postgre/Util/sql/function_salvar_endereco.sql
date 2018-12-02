CREATE OR REPLACE FUNCTION salvar_endereco
(bairro_nome VARCHAR, cidade_nome VARCHAR, estado VARCHAR, cep_novo INTEGER, usuario_id INTEGER)
RETURNS INTEGER AS $$

	DECLARE
		ESTADO_ID INTEGER;
		CIDADE_ID INTEGER;
		BAIRRO_ID INTEGER;
		ENDERECO_ID INTEGER;
		
	BEGIN

		--Obtenha o ID do estado recebido;
		SELECT e.id FROM estado e WHERE (e.sigla ILIKE estado OR e.nome ILIKE estado) INTO estado_id;
		
		--Obtenha o ID da cidade com nome recebido;
		SELECT c.id FROM cidade c WHERE c.nome ILIKE cidade_nome AND c.fk_estado = ESTADO_ID INTO CIDADE_ID;
		
		--Obtenha o ID do bairro com nome recebido e da cidade buscada;
		SELECT b.id FROM bairro b WHERE b.nome ILIKE bairro_nome AND b.fk_cidade = CIDADE_ID INTO BAIRRO_ID;
		
		--Se o ID da cidade for nulo, insira a nova cidade e guarde seu id na 'cidade_id';
		IF cidade_id ISNULL THEN
			INSERT INTO cidade(nome, fk_estado)
				VALUES(cidade_nome, ESTADO_ID)
				RETURNING cidade.id INTO CIDADE_ID;
		END IF;
		
		--Se o ID do bairro for nulo, insira o novo bairro e guarde seu id na 'bairro_id';
		IF BAIRRO_ID ISNULL THEN
			INSERT INTO bairro(nome, fk_cidade)
				VALUES(bairro_nome, CIDADE_ID)
				RETURNING bairro.id INTO BAIRRO_ID;
		END IF;
		
		--Se o usuário NÃO estiver na tabela de endereço, insira-o;
		IF (SELECT id FROM endereco e WHERE e.fk_usuario = usuario_id) ISNULL THEN
			INSERT INTO endereco(cep, fk_bairro, fk_usuario)
			VALUES(cep_novo, BAIRRO_ID, usuario_id)
			RETURNING endereco.id INTO ENDERECO_ID;
		
		--Senão, atualize seu endereço;
		ELSE
			UPDATE endereco SET cep = cep_novo, fk_bairro = BAIRRO_ID
			WHERE fk_usuario = usuario_id
			RETURNING id INTO ENDERECO_ID;
		END IF;
		
		RETURN ENDERECO_ID;
	END;

$$ LANGUAGE plpgsql;