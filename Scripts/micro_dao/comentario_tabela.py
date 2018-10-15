# encoding: utf-8
from random import choice, randint
from typing import List

from micro_dao.avaliacao import Avaliacao
from micro_dao.avaliacao_tabela import TabelaAvaliacao
from micro_dao.comentario import Comentario
from micro_dao.tabela_model import Tabela


class TabelaComentario(Tabela):

	def __init__(self, tab_aval: TabelaAvaliacao):
		super().__init__("comentario", Comentario.get_id)
		self.add_ID(Comentario.get_id)
		self.add_VARCHAR(Comentario.get_comentario, "comentario", 500)
		self.add_FK(Comentario.get_fk_avaliacao, "fk_avaliacao", tab_aval)
		self.__inserted__ = False
		self.__preencher__(tab_aval)

	def __preencher__(self, tab_aval: TabelaAvaliacao):

		coments = {
			1: ["Péssimo {}.", "Não gostei do {}.", "Se não fosse o {}.",
				"O {} é bem ruim.", "É pelo {} que o Brasil não vai para frente."],
			2: ["Não gostei do {}.", "Poderia melhorar o {}.",
				"O que estraga é o {}.", "Se melhorar o {}, tem futuro."],
			3: ["Dentro do combinado.", "Tudo okay.", "Dentro do prazo",
				"Preço compátivel.", "Regular.", "Como esperado."],
			4: ["Ótimo.", "Gostei!", "Show.", "Melhor.", "Recomendo!",
				"Muito bom!"],
			5: ["Melhor {}.", "Não tem igual {}", "Não achei nenhum {} semelhante.",
				"Só pelo {}, já vale.", "O que mais compensa é o {}."]
		}
		atributos = ["atendimento", "prazo", "custo-benefício", "preço", "marketing"]

		# Para cada avaliação, adicione um ou nenhum comentário;
		for aval in tab_aval.get_all():

			for i in range(randint(0, 1)):
				self.insert(
					Comentario(
						choice(coments[aval.get_nota()]).format(choice(atributos)),
						aval.get_id()
					)
				)

		self.__inserted__ = True
		return self

	def get_all(self) -> List[Comentario]:
		return super().get_all()