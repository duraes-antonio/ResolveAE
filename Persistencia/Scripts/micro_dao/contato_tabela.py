# encoding: utf-8
from random import choice, randint
from typing import List

from micro_dao.contato import Contato
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_contato import TipoContato
from micro_dao.tipo_contato_tabela import TabelaTipoContato
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaContato(Tabela):

	_num_contato_por_usuario = 1
	__ddd = [11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 27, 28, 31, 32,
	         33, 34, 35, 37, 38, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51, 53,
	         54, 55, 61, 62, 63, 64, 65, 66, 67, 68, 69, 71, 73, 74, 75, 77,
	         79, 81, 82, 83, 84, 85, 86, 87, 88, 89, 91, 92, 93, 94, 95, 96,
	         97, 98, 99]
	__num = [3000, 99000, 98000, 97000]

	def __init__(self, tab_tipo_cont: TabelaTipoContato, tab_usu: TabelaUsuario,
				 preencher: bool = False):
		super().__init__("contato", Contato.get_id)
		self.add_ID(Contato.get_id)
		self.add_VARCHAR(Contato.get_descricao, "descricao", 100)
		self.add_FK(Contato.get_fk_usuario, "fk_usuario", tab_usu)
		self.add_FK(Contato.get_fk_tipo_cont, "fk_tipo_contato", tab_tipo_cont)
		self.__inserted__ = False
		if preencher: self.__preencher__(tab_tipo_cont, tab_usu)

	def __preencher__(self, tab_tipo_cont: TabelaTipoContato, tab_usu: TabelaUsuario):

		opcoes = tab_tipo_cont.get_all()
		tipos_numericos = ["Celular", "Telefone", "Telegram", "Whatsapp"]
		topos_email = ["Lattes", "Linkedin"]

		for usuario in tab_usu.get_all():

			for i in range(randint(1, 3)):
				opcao = opcoes[randint(0, len(opcoes) - 1)]
				valor = ""

				if opcao.get_nome() in tipos_numericos:
					valor = self.__gerar_num_rand()

				elif opcao.get_nome() in topos_email:
					valor = usuario.get_email()

				else:
					valor = usuario.get_email().split("@")[0]

				self.insert(Contato(opcao.get_id(), valor, usuario.get_id()))

		self.__inserted__ = True
		return self

	def __gerar_num_rand(self):
		primeiros = choice(self.__num) + randint(0, 999)
		ultimos = randint(0, 9999)

		if ultimos < 10:
			ultimos = "000" + str(ultimos)

		elif ultimos < 100:
			ultimos = "00" + str(ultimos)

		elif ultimos < 1000:
			ultimos = "0" + str(ultimos)

		return "({}) {}-{}".format(choice(self.__ddd), primeiros, ultimos)

	def get_all(self) -> List[TipoContato]:
		return super().get_all()

	def get_by_nome(self, nome: str) -> TipoContato:
		return super().buscar(TipoContato.get_nome, nome)
