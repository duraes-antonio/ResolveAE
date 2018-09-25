# encoding: utf-8

import psycopg2
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT
from tabela_dic import Tabela

class UtilBD():

    _usuario = str
    _senha = str
    _porta = int
    _db_padrao = "postgres"

    _conexao = None
    _cursor = None

    _fechar_apos_terminar = False

    def __init__(self, usuario, senha, porta="5432"):
        self._usuario = usuario
        self._senha = senha
        self._porta = porta

    def conectar(self, database="postgres"):
        self._conexao = psycopg2.connect(host="localhost", database=database,
                                         user=self._usuario, port=self._porta, password=self._senha)
        self._conexao.set_isolation_level(ISOLATION_LEVEL_AUTOCOMMIT)
        self._cursor = self._conexao.cursor()

    def is_conected(self):
        return (self._conexao and not self._conexao.closed)

    def desconectar(self):
        if self.is_conected(): self._conexao.close()
        return self

    def executar_sql(self, string_sql):

        # Se não estiver conectado ao Banco, conecte, mas sinalize que fechará a conexão após terminar;
        if not self.is_conected():
            self.conectar()
            self._fechar_apos_terminar = True

        self._cursor.execute(string_sql)

        if self._fechar_apos_terminar: self.desconectar()

        return self

    def criar_database(self, nome_database):
        self.executar_sql("CREATE DATABASE {0};".format(nome_database))
        return self

    def criar_tabela(self, tabela: Tabela):
        self.executar_sql(tabela.get_SQL_CREATE())
        return self
