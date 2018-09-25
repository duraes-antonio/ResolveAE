# encoding: utf-8
from tabela_dic import Tabela

class GeradorTabela():

    @staticmethod
    def get_tab_BAIRRO(tabela_cidade: Tabela):
        tabela = Tabela("bairro")
        tabela.add_ID("id")
        tabela.add_VARCHAR("nome", 75)
        tabela.add_FOREIGN_KEY("fk_cidade", tabela_cidade)
        return tabela

    @staticmethod
    def get_tab_CIDADE(tabela_estado: Tabela):
        tabela = Tabela("cidade")
        tabela.add_ID("id")
        tabela.add_VARCHAR("nome", 75)
        tabela.add_FOREIGN_KEY("fk_estado", tabela_estado)
        return tabela

    # Tabela EXTRA/auxiliar [VALORES PRÉ-ESTABELECIDOS]
    @staticmethod
    def get_tab_ESTADO():
        tabela = Tabela("estado")
        tabela.add_ID("id")
        tabela.add_VARCHAR("nome", 40)
        tabela.add_CHAR("sigla", 2)
        return tabela

    @staticmethod
    def get_tab_ENDERECO(tabela_bairro: Tabela):
        tabela = Tabela("endereco")
        tabela.add_ID("id")
        tabela.add_VARCHAR("cep", 11)
        tabela.add_FOREIGN_KEY("fk_bairro", tabela_bairro)
        return tabela

    # Tabela EXTRA/auxiliar [VALORES PRÉ-ESTABELECIDOS]
    @staticmethod
    def get_tab_TIPO_CONTATO():
        return Tabela("tipo_contato").add_ID("id").add_VARCHAR("nome", 20)

    @staticmethod
    def get_tab_CONTATO(tabela_info: Tabela, tabela_tipo_contato: Tabela):
        tabela = Tabela("contato")
        tabela.add_ID("id")
        tabela.add_VARCHAR("descricao", 100)
        tabela.add_FOREIGN_KEY("fk_tipo_contato", tabela_tipo_contato)
        tabela.add_FOREIGN_KEY("fk_informacoes", tabela_info)
        return tabela

    @staticmethod
    def get_tab_INFORMACOES(tabela_endereco: Tabela):
        tabela = Tabela("informacoes")
        tabela.add_ID("id")
        tabela.add_FOREIGN_KEY("fk_endereco", tabela_endereco)
        return tabela

    # Tabela EXTRA/auxiliar [VALORES PRÉ-ESTABELECIDOS]
    @staticmethod
    def get_tab_TIPO_INFOPRO():
        return Tabela("tipo_info_profissional").add_ID("id").add_VARCHAR("nome", 20)

    @staticmethod
    def get_tab_INFO_PROFISSIONAL(tab_info: Tabela, tab_tipo_infopro: Tabela):
        tabela = Tabela("info_profissional")
        tabela.add_ID("id")
        tabela.add_DATE("data_inicio")
        tabela.add_DATE("data_fim")
        tabela.add_VARCHAR("descricao", 150)
        tabela.add_FOREIGN_KEY("fk_tipo_infopro", tab_tipo_infopro)
        tabela.add_FOREIGN_KEY("fk_informacoes", tab_info)
        return tabela

    @staticmethod
    def get_tab_USUARIO(tab_info: Tabela):
        tabela = Tabela("usuario")
        tabela.add_ID("id")
        tabela.add_VARCHAR("email", 128)
        tabela.add_VARCHAR("nome", 150)
        tabela.add_CHAR("senha", 64)
        tabela.add_VARCHAR("sobre", 1000)
        tabela.add_FOREIGN_KEY("fk_informacoes", tab_info)
        return tabela

    @staticmethod
    def get_tab_AVALIACAO(tab_usuario: Tabela, tab_servico: Tabela):
        tabela = Tabela("avaliacao")
        tabela.add_ID("id")
        tabela.add_INT("nota")
        tabela.add_VARCHAR("comentario", 150)
        tabela.add_FOREIGN_KEY("fk_usuario", tab_usuario)
        tabela.add_FOREIGN_KEY("fk_servico", tab_servico)
        return tabela

    @staticmethod
    def get_tab_AGENDA_PRESTACAO(tab_usuario: Tabela):
        tabela = Tabela("agenda_prestacao")
        tabela.add_ID("id")
        tabela.add_FOREIGN_KEY("fk_usuario", tab_usuario)
        return tabela

    # Tabela EXTRA/auxiliar [VALORES PRÉ-ESTABELECIDOS]
    @staticmethod
    def get_tab_DIAS_SEMANA():
        tabela = Tabela("dias_semana")
        tabela.add_ID("id")
        tabela.add_VARCHAR("dia", 14)
        return tabela

    @staticmethod
    def get_tab_HORARIO_PRESTACAO(tab_agenda: Tabela, tab_dia_semana: Tabela):
        tabela = Tabela("horario_prestacao")
        tabela.add_ID("id")
        tabela.add_TIME("horario_prestacao_inicio")
        tabela.add_TIME("horario_prestacai_fim")
        tabela.add_BOOLEAN("disponivel")
        tabela.add_FOREIGN_KEY("fk_dia_semana", tab_dia_semana)
        tabela.add_FOREIGN_KEY("fk_agenda", tab_agenda)
        return tabela

    @staticmethod
    def get_tab_SERVICO_PRESTACAO(tab_agenda: Tabela):
        tabela = Tabela("servico_prestacao")
        tabela.add_ID("id")
        tabela.add_VARCHAR("titulo", 100)
        tabela.add_VARCHAR("descricao", 400)
        tabela.add_REAL("valor")
        tabela.add_FOREIGN_KEY("fk_agenda", tab_agenda)
        return tabela

    # Tabela auxiliar [VALORES PRÉ-ESTABELECIDOS]
    @staticmethod
    def get_tab_TIPO_SERVICO():
        tabela = Tabela("tipo_servico")
        tabela.add_ID("id")
        tabela.add_VARCHAR("nome", 50)
        return tabela

    # Tabela auxiliar [VALORES PRÉ-ESTABELECIDOS]
    @staticmethod
    def get_tab_SUBTIPO_SERVICO(tab_servico: Tabela):
        tabela = Tabela("subtipo_servico")
        tabela.add_ID("id")
        tabela.add_VARCHAR("nome", 50)
        tabela.add_FOREIGN_KEY("fk_tipo_servico", tab_servico)
        return tabela

    @staticmethod
    def get_tab_SUBTIPO_PRESTACAO(tab_servico: Tabela, tab_subtipo_servico: Tabela):
        tabela = Tabela("subtipo_prestacao")
        tabela.add_ID("id")
        tabela.add_FOREIGN_KEY("fk_servico_prestacao", tab_servico)
        tabela.add_FOREIGN_KEY("fk_subtipo_servico", tab_subtipo_servico)
        return tabela

    @staticmethod
    def get_tab_CONTRATO(tab_usuario: Tabela, tab_servico: Tabela):
        tabela = Tabela("contrato")
        tabela.add_ID("id")
        tabela.add_DATE("data_contrato")
        tabela.add_DATE("data_inicio_prestacao")
        tabela.add_DATE("data_fim_prestacao")
        tabela.add_DATE("data_ult_modif")
        tabela.add_FOREIGN_KEY("fk_usuario", tab_usuario)
        tabela.add_FOREIGN_KEY("fk_servico_prestacao", tab_servico)
        return tabela