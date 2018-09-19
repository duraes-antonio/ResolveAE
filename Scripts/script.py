# encoding: utf-8
import re
import psycopg2
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT

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

    def executar(self, string_sql):
        print(string_sql)
        
        # Se não estiver conectado ao Banco, conecte, mas sinalize que fechará a conexão após terminar;
        if not self.is_conected():
            self.conectar()
            self._fechar_apos_terminar = True

        self._cursor.execute(string_sql)

        if self._fechar_apos_terminar: self.desconectar()

        return 0

    def criar_database(self, nome_database):
        self.executar("CREATE DATABASE {0};".format(nome_database))

    def criar_tabela(self, nome_database, nome_tabela, dic_nomeTipo):

        self.conectar(nome_database)

        str_col_tipo = ", ".join([nome + " " + dic_nomeTipo[nome] for nome in dic_nomeTipo])
        sql_create = "CREATE {0}({1});".format(nome_tabela, str_col_tipo)
        print(sql_create)
        return 0

def main():

    bd = UtilBD("postgres", "postgres")
    bd.criar_database("DBTeste_3")
    dic = {"nome": "varchar(50)", "idade": "int", "status": "boolean"}
    bd.criar_tabela("postgres", "teste", dic)

    return 0


main()
