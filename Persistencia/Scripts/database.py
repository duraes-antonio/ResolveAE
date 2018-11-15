from os import sep, path
import gc

from micro_dao.avaliacao_tabela import TabelaAvaliacao
from micro_dao.bairro_tabela import TabelaBairro
from micro_dao.cidade_tabela import TabelaCidade
from micro_dao.comentario_tabela import TabelaComentario
from micro_dao.contato_tabela import TabelaContato
from micro_dao.contrato_tabela import TabelaContrato
from micro_dao.dia_semana_tabela import TabelaDiaSemana
from micro_dao.endereco_tabela import TabelaEndereco
from micro_dao.estado_tabela import TabelaEstado
from micro_dao.horario_prestacao_tabela import TabelaHorarioPrestacao
from micro_dao.info_profissional_tabela import TabelaInfoProfissional
from micro_dao.servico_subtipo_servico_tabela import TabelaServicoSubtipoServico
from micro_dao.servico_tabela import TabelaServico
from micro_dao.subtipo_servico_tabela import TabelaSubtipoServico
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_contato_tabela import TabelaTipoContato
from micro_dao.tipo_info_profissional_tabela import TabelaTipoInfoProfissional
from micro_dao.tipo_servico_tabela import TabelaTipoServico
from micro_dao.usuario_tabela import TabelaUsuario
from util_bd import UtilBD


class Database():
	_conexao: UtilBD = None
	_qtd_user: int

	__dic_tabs = {
		1: Tabela, 2: Tabela, 3: Tabela,
		4: Tabela, 5: Tabela, 6: Tabela,
		7: Tabela, 8: Tabela, 9: Tabela,
		10: Tabela, 11: Tabela, 12: Tabela,
		13: Tabela, 14: Tabela, 15: Tabela,
		16: Tabela, 17: Tabela, 18: Tabela
	}

	def __init__(self, usuario: str = "postgres", senha: str = "postgres",
	             porta: int = 5432, qtd_user: int = 500000, database: str = "postgres"):
		self._conexao = UtilBD(usuario, senha, porta, database)
		self._qtd_user = qtd_user

	def builder_all(self, criar_e_executar: bool = False, preencher: bool = False):

		# USUÁRIO
		self.__dic_tabs[1] = TabelaUsuario(preencher_base=preencher,
		                                   num_registros=self._qtd_user)

		# ENDEREÇO
		self.__dic_tabs[2] = TabelaEstado(preencher=preencher)
		self.__dic_tabs[3] = TabelaCidade(self.__dic_tabs[2], preencher=preencher)
		self.__dic_tabs[4] = TabelaBairro(self.__dic_tabs[3], self.__dic_tabs[2],
		                                  preencher=preencher)
		self.__dic_tabs[5] = TabelaEndereco(self.__dic_tabs[4], self.__dic_tabs[1],
		                                    preencher=preencher)

		# CONTATO
		self.__dic_tabs[6] = TabelaTipoContato(preencher=preencher)
		self.__dic_tabs[7] = TabelaContato(self.__dic_tabs[6], self.__dic_tabs[1],
		                                   preencher=preencher)

		# TABELAS INFORMAÇÃO PROFISSIONAL
		self.__dic_tabs[8] = TabelaTipoInfoProfissional(preencher=preencher)
		self.__dic_tabs[9] = TabelaInfoProfissional(self.__dic_tabs[1],
		                                            self.__dic_tabs[8],
		                                            preencher=preencher)

		# TABELAS SERVIÇO PRESTACAO
		self.__dic_tabs[10] = TabelaContrato(self.__dic_tabs[1], preencher=preencher)
		self.__dic_tabs[11] = TabelaTipoServico(preencher=preencher)
		self.__dic_tabs[12] = TabelaSubtipoServico(self.__dic_tabs[11],
		                                           preencher=preencher)
		self.__dic_tabs[13] = TabelaServico(self.__dic_tabs[10], self.__dic_tabs[11],
		                                    self.__dic_tabs[1], preencher=preencher)
		self.__dic_tabs[14] = TabelaServicoSubtipoServico(
			self.__dic_tabs[13], self.__dic_tabs[12], preencher=preencher)
		self.__dic_tabs[15] = TabelaAvaliacao(self.__dic_tabs[13], self.__dic_tabs[1],
		                                      preencher=preencher)
		self.__dic_tabs[16] = TabelaComentario(self.__dic_tabs[15], preencher=preencher)

		# TABELAS HORARIO PRESTACAO
		self.__dic_tabs[17] = TabelaDiaSemana(preencher=preencher)
		self.__dic_tabs[18] = TabelaHorarioPrestacao(self.__dic_tabs[1], self.__dic_tabs[10],
		                                             self.__dic_tabs[17], preencher=preencher)

		if criar_e_executar:
			self.create_all()
			self.insert_all()

	def to_arq_all_sql(self, arq_unico = False, multiplos_arq = False,
	                   qtd_bloco=500000, caminho: str=None, ign_pk = True):

		for chave in self.__dic_tabs:

			if caminho and not arq_unico:
				caminho = caminho if caminho[-1] == sep else caminho + sep
				caminho + self.__dic_tabs[chave].get_nome() + ".sql"

			self.__dic_tabs[chave].gerar_arq_SQL(
				arq_unico=arq_unico, dividir_bloco_em_arq=multiplos_arq,
				qtd_bloco=qtd_bloco, path_saida=caminho,
				ignorar_pk=ign_pk)

	def to_arq_all_creates(self, caminho: str = None):
		"""Gera o modelo físico contendo o create de todas tabelas."""

		def gerar_arq(conteudo: str, caminho_nome: str):
			with open(caminho_nome, 'w') as arq:
				arq.write(conteudo)

		self.builder_all(criar_e_executar=False, preencher=False)

		sql = "\n\n".join([self.__dic_tabs[chave].get_SQL_create() for chave in self.__dic_tabs])

		if not caminho:
			caminho = "modelo_fisico.sql"

		elif path.isdir(caminho):
			caminho = path.join(caminho, "modelo_fisico.sql")

		gerar_arq(sql, caminho)

	def create_all(self):
		[self.create(self.__dic_tabs[i]) for i in sorted(list(self.__dic_tabs.keys()))]

	def insert_all(self):
		[self.insert(self.__dic_tabs[i]) for i in sorted(list(self.__dic_tabs.keys()))]

	def drop_all(self):
		[self.drop_table(self.__dic_tabs[i]) for i in sorted(list(self.__dic_tabs.keys()))]

	def create(self, *tabelas: Tabela):

		for tabela in tabelas:
			self._conexao.executar_sql(tabela.get_SQL_create())

	def insert(self, *tabelas: Tabela, quantidade: int = 0):

		for tabela in tabelas:
			self._conexao.executar_sql("\n".join(tabela.get_SQL_insert(quantidade)))

	def drop_table(self, tabela: Tabela):
		self._conexao.executar_sql("DROP TABLE IF EXISTS {} CASCADE;".format(tabela.get_nome()))

	def execute(self, SQL: str):
		self._conexao.executar_sql(SQL)

	def del_mem_table(self):

		for chave in self.__dic_tabs:
			self.__dic_tabs[chave] = None
			gc.collect()
