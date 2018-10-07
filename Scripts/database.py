from typing import List

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
from micro_dao.servico_prestacao_tabela import TabelaServicoPrestacao
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

    tab_usuario: TabelaUsuario = None

    tab_estado: TabelaEstado = None
    tab_cidade: TabelaCidade = None
    tab_bairro: TabelaBairro = None
    tab_endereco: TabelaEndereco = None

    tab_tipo_contato: TabelaTipoContato = None
    tab_contato: TabelaContato = None

    tab_tipo_info_pro: TabelaTipoInfoProfissional = None
    tab_info_pro: TabelaInfoProfissional = None

    tab_contrato: TabelaContrato = None
    tab_tipo_servico: TabelaTipoServico = None
    tab_subtipo_serv: TabelaSubtipoServico = None
    tab_serv_prest: TabelaServicoPrestacao = None
    tab_avaliacao: TabelaAvaliacao = None

    tab_dia_semana: TabelaDiaSemana = None
    tab_horario_prest: TabelaHorarioPrestacao = None

    def __init__(self, usuario: str = "postgres", senha: str = "postgres",
                 porta: str = "5432", qtd_user: int = 50):
        self._conexao = UtilBD(usuario, senha, porta)
        self._qtd_user = qtd_user

    # TABELA USUARIO ---> TABELA MESTRE
    def builder_usuario(self):
        self.tab_usuario = TabelaUsuario(num_registros=self._qtd_user)

    #TABELAS ENDEREÇO
    def builder_endereco(self):
        self.tab_estado = TabelaEstado()
        self.tab_cidade = TabelaCidade(self.tab_estado)
        self.tab_bairro = TabelaBairro(self.tab_cidade, self.tab_estado)
        self.tab_endereco = TabelaEndereco(self.tab_bairro, self.tab_usuario)

    # TABELAS CONTATO
    def builder_contato(self):
        self.tab_tipo_contato = TabelaTipoContato()
        self.tab_contato = TabelaContato(self.tab_tipo_contato, self.tab_usuario)

    # TABELAS INFORMAÇÃO PROFISSIONAL
    def builder_informacao(self):
        self.tab_tipo_info_pro = TabelaTipoInfoProfissional()
        self.tab_info_pro = TabelaInfoProfissional(
            self.tab_usuario, self.tab_tipo_info_pro)

    # # TABELAS SERVIÇO PRESTACAO
    def builder_servico(self):
        self.tab_contrato = TabelaContrato(self.tab_usuario)
        self.tab_tipo_servico = TabelaTipoServico()
        self.tab_subtipo_serv = TabelaSubtipoServico(self.tab_tipo_servico)
        self.tab_serv_prest = TabelaServicoPrestacao(
            self.tab_contrato, self.tab_tipo_servico, self.tab_subtipo_serv,
            self.tab_usuario)
        self.tab_avaliacao = TabelaAvaliacao(self.tab_serv_prest, self.tab_usuario)

    # TABELAS HORARIO PRESTACAO
    def builder_horario(self):
        self.tab_dia_semana = TabelaDiaSemana()
        self.tab_horario_prest = TabelaHorarioPrestacao(
            self.tab_usuario, self.tab_contrato, self.tab_dia_semana)

    def builder_all(self):
        self.builder_usuario()
        self.builder_endereco()
        self.builder_contato()
        self.builder_informacao()
        self.builder_servico()
        self.builder_horario()

    def to_arq_sql_all(self, caminho: str = None):

        for atrib in self.__dict__:

            if isinstance(self.__dict__[atrib], Tabela):

                if caminho: (self.__dict__[atrib]).gerar_arq_SQL(caminho)

                else: (self.__dict__[atrib]).gerar_arq_SQL()

    def create_all(self):
        [self.create(self.__dict__[atrib]) for atrib in self.__dict__
         if isinstance(self.__dict__[atrib], Tabela)]

    def insert_all(self):

        for atrib in self.__dict__:

            if isinstance(self.__dict__[atrib], Tabela):
                self.insert(self.__dict__[atrib])

    def drop_all(self, cascade: bool = True):
        [self.drop_table(self.__dict__[atrib]) for atrib in self.__dict__
         if isinstance(self.__dict__[atrib], Tabela)]


    def create(self, *tabelas: Tabela):

        for tabela in tabelas:
            self._conexao.executar_sql(tabela.get_SQL_create())

    def insert(self, tabela: Tabela, quantidade: int = 0):
        self._conexao.executar_sql("\n".join(tabela.get_SQL_insert(quantidade)))

    def drop_table(self, tabela: Tabela):
        self._conexao.executar_sql("DROP TABLE IF EXISTS {} CASCADE;".format(tabela.get_nome()))
