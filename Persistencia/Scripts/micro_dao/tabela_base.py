# encoding: utf-8
from typing import List
from micro_dao.objeto_modelo import ObjetoModelo
import json

class Tabela():

    tabela = {}
    __cont_id__ = 1
    _tipos_sem_aspa = {'BOOLEAN', 'FLOAT', 'REAL', 'INT', 'BIGINT', 'SMALLINT',
                       'SERIAL'}

    def __init__(self, nome_tabela: str, get_metodo: classmethod):
        self.tabela = {"nome_tabela": nome_tabela, "pk": str,
                       "get_metodo": get_metodo, "colunas": {}, "values": []}

    #Adiciona uma coluna com determinado NOME e determinado TIPO (char, varchar, int, ...)
    def __add_atributo__(self, nome: str, tipo: str):
        self.tabela["colunas"][nome] = tipo
        return self

    #GET: Nome da tabela
    def get_nome(self) -> str:
        return self.tabela["nome_tabela"]

    #SET: Nome da tabela
    def set_nome(self, nome_tabela):
        self.tabela["nome_tabela"] = nome_tabela
    
    # Retorna nome da coluna que é CHAVE PRIMÁRIA
    def get_nome_pk(self) -> str:
        return self.tabela["pk"]


    # ADIÇÃO DE COLUNAS E TIPOS

    def add_FOREIGN_KEY(self, nome_col_local: str, tab_estrang):

        if tab_estrang.get_nome_pk():
            self.tabela["colunas"][nome_col_local] = "INT REFERENCES {0} ({1})".format(
                tab_estrang.get_nome(), tab_estrang.get_nome_pk())

        return self

    def add_PRIMARY_KEY(self, nome_coluna):
        return self.__add_atributo__(nome_coluna, "SERIAL PRIMARY KEY")

    def add_ID(self):
        self.add_PRIMARY_KEY("id")
        self.tabela["pk"] = "id"
        return self

    def add_CHAR(self, nome_atributo: str, tamanho: int):

        if (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self.__add_atributo__(nome_atributo, "CHAR(%d)" %tamanho)

    def add_VARCHAR(self, nome_atributo: str, tamanho: int):

        if (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self.__add_atributo__(nome_atributo, "VARCHAR(%d)" %tamanho)

    def add_DATE(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "DATE")

    def add_TIME(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "TIME")

    def add_SMALLINT(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "SMALLINT")

    def add_INT(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "INT")

    def add_BIGINT(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "BIGINT")

    def add_FLOAT(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "FLOAT")

    def add_REAL(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "REAL")

    def add_SERIAL(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "SERIAL")

    def add_BOOLEAN(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "BOOLEAN")

    def add_JSON(self, nome_atributo: str):
        return self.__add_atributo__(nome_atributo, "JSON")


    #----- OBTER STRING COM O SQL PRONTO -----#

    # Retorna '('João', 12, NULL)'
    def __get_SQL_VALUE_FORMAT__(self, value: dict) -> str:

        sql_valores = []

        for coluna in self.tabela["colunas"]:

            tipo = self.tabela["colunas"][coluna]

            if (tipo in self._tipos_sem_aspa or value[coluna] in {'NULL', 'null'}):
                sql_valores.append(str(value[coluna]))

            elif (coluna != self.tabela["pk"]):
                sql_valores.append("'{0}'".format(value[coluna]))

            else:
                sql_valores.append(str(value["id"]))

        return "({0})".format(", ".join(sql_valores))

    def get_SQL_INSERT(self, inicio_dados: int = 0, fim_dados: int = 0) -> str:

        tam_tab = len(self.tabela["values"])

        if not self.tabela:
            raise ValueError("Tabela vazia!")

        if not fim_dados:
            im_dados = tam_tab

        return "INSERT INTO {0}({1}) VALUES\n\t{2};".format(
            self.get_nome(),
            ", ".join([col for col in self.tabela["colunas"]]),
            ",\n\t".join(
                [self.__get_SQL_VALUE_FORMAT__(self.tabela["values"][i].get_dic()) for i in range(tam_tab)])
        )

    def get_SQL_CREATE(self) -> str:

        if not self.tabela:
            raise ValueError("Tabela vazia!")

        return "CREATE TABLE {0}({1});".format(
            self.get_nome(), ", ".join(
                [col + " " + self.tabela["colunas"][col]
                 for col in self.tabela["colunas"]]
            )
        )


    #----- FUNÇÕES PARA OPERAÇÕES DE BUSCA E INSERÇÃO -----#

    def __busca_binaria__(self, get_metodo: classmethod, item_buscado: object, lista: list) -> ObjetoModelo:
        inicio = 0
        fim = len(lista) - 1

        while inicio <= fim:

            meio = (inicio + fim) // 2

            if get_metodo(lista[meio]) == item_buscado:
                return lista[meio]

            else:

                if item_buscado < get_metodo(lista[meio]):
                    fim = meio - 1

                else:
                    inicio = meio + 1

        return None

    def insert_VALUE(self, value: ObjetoModelo):
        value.set_id(self.__cont_id__)
        self.tabela["values"].append(value)
        self.__cont_id__ += 1
        return self

    def get_BY_CUSTOM(self, get_metodo: classmethod, valor_buscado: object):
        return self.__busca_binaria__(get_metodo, valor_buscado, self.tabela["values"])

    def get_BY_ID(self, id: int):
        return self.__busca_binaria__(self.tabela["get_metodo"], id, self.tabela["values"])

    def get_ALL(self):
        return self.tabela["values"]

    def get_JSON(self):
        return json.dumps(self.tabela)