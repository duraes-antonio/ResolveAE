# encoding: utf-8

import psycopg2
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT

class UtilBD():

    _usuario: str
    _senha: str
    _porta: int
    _database: str

    _conexao = None
    _cursor = None

    _fechar_apos_terminar: bool = False

    def __init__(self, usuario: str, senha: str, porta: int=5432, database: str = "postgres"):
        self._usuario = usuario
        self._senha = senha
        self._porta = porta
        self._database = database

    def conectar(self):
        self._conexao = psycopg2.connect(host="localhost", database=self._database,
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
