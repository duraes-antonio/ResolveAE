# encoding: utf-8
import re
import psycopg2
from psycopg2.extensions import ISOLATION_LEVEL_AUTOCOMMIT

class Tabela():

    _nome = str
    _pkey = str

    # Exemplo de dicionário para tabela 'Pessoa'
    # {'id': {'tipo': 'SERIAL', 'pk': true, ...}, 'fk_endereco': {'tipo': 'INT', 'pk': False, 'fk': True, ''}}
    _dic_tabela = dict

    def __init__(self, nome_tabela):
        self._dic_tabela = {}
        self._nome = nome_tabela
        self._pkey = ""

    def _add_atributo(self, nome, tipo):

        if (nome in self._dic_tabela):
            raise ValueError("O atributo '{0}' já consta na tabela!".format(nome))

        self._dic_tabela[nome] = tipo.upper()
        return self

    def get_nome(self):
        return self._nome

    def get_nome_cols(self):
        return self._dic_tabela.keys()

    def get_pkey(self):
        return self._pkey

    def add_ID(self, nome_coluna):
        self.add_SERIAL(nome_coluna)
        self.set_PRIMARY_KEY(nome_coluna)
        return self

    def add_CHAR(self, nome_atributo, tamanho: int):

        if (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self._add_atributo(nome_atributo, "CHAR({0})".format(tamanho))

    def add_VARCHAR(self, nome_atributo, tamanho):

        if not tamanho.isdigit():
            raise ValueError("O tamanho da coluna deve ser um inteiro!")

        elif (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self._add_atributo(nome_atributo, "VARCHAR({0})".format(tamanho))

    def add_DATE(self, nome_atributo):
        return self._add_atributo(nome_atributo, "DATE")

    def add_SMALLINT(self, nome_atributo):
        return self._add_atributo(nome_atributo, "SMALLINT")

    def add_INT(self, nome_atributo):
        return self._add_atributo(nome_atributo, "INT")

    def add_BIGINT(self, nome_atributo):
        return self._add_atributo(nome_atributo, "BIGINT")

    def add_FLOAT(self, nome_atributo):
        return self._add_atributo(nome_atributo, "FLOAT")

    def add_SERIAL(self, nome_atributo):
        return self._add_atributo(nome_atributo, "SERIAL")

    def add_BOOLEAN(self, nome_atributo):
        return self._add_atributo(nome_atributo, "BOOLEAN")

    def add_JSON(self, nome_atributo):
        return self._add_atributo(nome_atributo, "JSON")

    def set_PRIMARY_KEY(self, nome_coluna):

        achou = False

        for chave in self._dic_tabela:
            
            if chave.lower() == nome_coluna.lower():
                self._dic_tabela[chave] = self._dic_tabela[chave] + " PRIMARY KEY"
                self._pkey = chave
                achou = True

        if not achou:
            raise ValueError("A coluna '{0}' não existe na tabela '{1}'!"
                             .format(nome_coluna, self._nome))

        return self

    def set_FOREIGN_KEY(self, nome_col_local, tab_estrang):

        if tab_estrang.get_pkey():
            self._dic_tabela[nome_col_local] = "INT REFERENCES {0} ({1})".format(
                tab_estrang._nome, tab_estrang.get_pkey())

        return self

    def get_SQL_CREATE(self):

        if not self._dic_tabela:
            raise ValueError("Tabela vazia!")

        return "CREATE TABLE {0}({1});".format(
            self._nome, ", ".join(
                [col + " " + self._dic_tabela[col] for col in self._dic_tabela]
            )
        )

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

def get_arquivo(caminho):

    with open(caminho, 'r') as arq:
        return arq.read()

def main():

    bd = UtilBD("postgres", "postgres")
    bd.criar_tabela(Tabela("pessoa").add_ID("id_xyz").add_INT("inteiro").add_CHAR("sexo", 1))
    #TODO resolver erro "psycopg2.ProgrammingError: column "horarioprestacaoinicio" specified more than once"
    bd.executar_sql(get_arquivo("/home/x/Área de Trabalho/resolveAE.sql"))


    return 0


main()
