from typing import List, Dict
from os import sep

from micro_dao.avaliacao_tabela import TabelaAvaliacao
from micro_dao.bairro_tabela import TabelaBairro
from micro_dao.cidade_tabela import TabelaCidade
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
        1: None, 2: None, 3: None,
        4: None, 5: None, 6: None,
        7: None, 8: None, 9: None,
        10: None,11: None, 12: None,
        13: None, 14: None, 15: None,
        16: None
    }

    def __init__(self, usuario: str = "postgres", senha: str = "postgres",
                 porta: int = 5432, qtd_user: int = 50, database: str = "postgres"):
        self._conexao = UtilBD(usuario, senha, porta, database)
        self._qtd_user = qtd_user

    def builder_all(self, criar_e_executar: bool = False):

        # USUÁRIO
        self.__dic_tabs[1] = TabelaUsuario(num_registros=self._qtd_user)

        # ENDEREÇO
        self.__dic_tabs[2] = TabelaEstado()
        self.__dic_tabs[3] = TabelaCidade(self.__dic_tabs[2])
        self.__dic_tabs[4] = TabelaBairro(self.__dic_tabs[3], self.__dic_tabs[2])
        self.__dic_tabs[5] = TabelaEndereco(self.__dic_tabs[4], self.__dic_tabs[1])

        # CONTATO
        self.__dic_tabs[6] = TabelaTipoContato()
        self.__dic_tabs[7] = TabelaContato(self.__dic_tabs[6], self.__dic_tabs[1])

        # TABELAS INFORMAÇÃO PROFISSIONAL
        self.__dic_tabs[8] = TabelaTipoInfoProfissional()
        self.__dic_tabs[9] = TabelaInfoProfissional(self.__dic_tabs[1], self.__dic_tabs[8])

        # TABELAS SERVIÇO PRESTACAO
        self.__dic_tabs[10] = TabelaContrato(self.__dic_tabs[1])
        self.__dic_tabs[11] = TabelaTipoServico()
        self.__dic_tabs[12] = TabelaSubtipoServico(self.__dic_tabs[11])
        self.__dic_tabs[13] = TabelaServico(
            self.__dic_tabs[10], self.__dic_tabs[11], self.__dic_tabs[12], self.__dic_tabs[1])
        self.__dic_tabs[14] = TabelaServicoSubtipoServico(
            self.__dic_tabs[13], self.__dic_tabs[12])
        self.__dic_tabs[15] = TabelaAvaliacao(self.__dic_tabs[13], self.__dic_tabs[1])

        # TABELAS HORARIO PRESTACAO
        self.__dic_tabs[16] = TabelaDiaSemana()
        self.__dic_tabs[17] = TabelaHorarioPrestacao(
            self.__dic_tabs[1], self.__dic_tabs[10], self.__dic_tabs[16])

        if criar_e_executar:
            self.create_all()
            self.insert_all()

    def to_arq_sql_all(self, caminho: str = None):

        for atrib in self.__dict__:

            if isinstance(self.__dict__[atrib], Tabela):

                if caminho:
                    caminho = caminho if caminho[-1] == sep else caminho + sep
                    (self.__dict__[atrib]).gerar_arq_SQL(caminho + self.__dict__[atrib].get_nome())

                else: (self.__dict__[atrib]).gerar_arq_SQL()

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