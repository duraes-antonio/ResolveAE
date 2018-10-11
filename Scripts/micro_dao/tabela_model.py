# encoding: utf-8
import datetime
from os import sep
from typing import List, Optional

import time

from micro_dao.objeto_modelo import ObjetoModelo

class Coluna():

    _nome: str
    _tipo: str
    _tamanho: int
    _unique: bool
    _nao_nulo: bool
    _chave_primaria: bool
    _chave_estrangeira: bool
    _coluna_estrang: str
    _tabela_estrang: str

    # O método de outra classe não pode ficar solto, imposição da linguagem
    _lista_metodo_get: List[classmethod] = None

    def __init__(self, nome: str, get_metodo: classmethod, tipo: str,
                 unique: bool, tamanho: int = None, not_null: bool = False):
        self.set_nome(nome)
        self.set_tipo(tipo)
        self._unique = unique
        self._nao_nulo = not_null
        self._chave_primaria = False
        self._chave_estrangeira = False
        self._coluna_estrang = None
        self._tabela_estrang = None
        self.set_metodo_get_atrib(get_metodo)

        if (tipo.upper() == "VARCHAR" or tipo.upper() == "CHAR"):
            self.set_tamanho(tamanho)

        else:
            self._tamanho = None

    def get_nome(self) -> str:
        return self._nome

    def set_nome(self, nome: str):
        self._nome = nome
        return self

    def get_tipo(self) -> str:
        return self._tipo

    def set_tipo(self, tipo: str):
        self._tipo = tipo.upper()
        return self

    def get_tamanho(self) -> int:
        return self._tamanho

    def set_tamanho(self, tamanho: int):
        self._tamanho = tamanho
        return self

    def is_chave_primaria(self) -> bool:
        return self._chave_primaria

    def is_chave_estrangeira(self) -> bool:
        return self._chave_estrangeira

    def set_chave_primaria(self):
        self._chave_primaria = True
        return self

    def set_chave_estrangeira(self, nome_coluna_estrang: str, nome_tabela_estrang: str):

        if self._chave_primaria:
            raise ValueError("Uma chave primária não pode ser sua própria chave estrangeira!")

        self._coluna_estrang = nome_coluna_estrang
        self._tabela_estrang = nome_tabela_estrang
        self._chave_estrangeira = True
        return self

    def to_string(self):
        tamanho = "({0})".format(self._tamanho) if self._tamanho else ""
        nao_nulo = "NOT NULL" if self._nao_nulo else ""
        unique = "UNIQUE" if self._unique and not self._chave_primaria else ""
        pk = "PRIMARY KEY" if self._chave_primaria else ""
        fk = "REFERENCES {0}({1})".format(
            self._tabela_estrang, self._coluna_estrang) if self._chave_estrangeira else ""

        return " ".join([col for col in [self._nome, self._tipo, tamanho, unique,
                                         nao_nulo, pk, fk] if col])

    def set_metodo_get_atrib(self, metodo_get: classmethod):
        self._lista_metodo_get = [metodo_get]
        return self

    def get_metodo_get_atributo(self):
        return self._lista_metodo_get[0]

class Tabela():


    _nome: str
    _pk_nome: str
    _valores: List[object]
    _colunas: List[Coluna]
    _list_get_metodo: List[classmethod]
    _cont_id: int = 1

    _tipos_sem_aspa = {'BOOLEAN', 'FLOAT', 'REAL', 'INT', 'BIGINT', 'SMALLINT',
                       'SERIAL'}

    def __init__(self, nome_tabela: str, metodo_get_para_comparacoes: classmethod):
        self._valores = []
        self._colunas = []
        self._list_get_metodo = None
        self.set_nome(nome_tabela)
        self.set_comparacao_metodo(metodo_get_para_comparacoes)

    #GET: Nome da tabela
    def get_nome(self) -> str:
        return self._nome

    #SET: Nome da tabela
    def set_nome(self, nome_tabela: str):
        self._nome = nome_tabela

    #Define o método usado para comparar os valores em caso de orndenação e busca
    def set_comparacao_metodo(self, get_metodo: classmethod):
        self._list_get_metodo = [get_metodo]

    # Adiciona uma coluna com determinado NOME e determinado TIPO (char, varchar, int, ...)
    def __add_coluna__(self, get_metodo: classmethod, nome: str, tipo: str, unique: bool = False,
                       tamanho: int = None, not_null: bool = False):
        self._colunas.append(Coluna(nome, get_metodo, tipo, unique, tamanho, not_null))
        return self._colunas[-1]

    # Retorna nome da coluna que é CHAVE PRIMÁRIA
    def get_nome_pk(self) -> str:
        return self._pk_nome


    # ADIÇÃO DE COLUNAS E TIPOS

    def add_ID(self, get_metodo: classmethod, nome_coluna: str = None):

        if not nome_coluna: nome_coluna = "id"

        return self.add_SERIAL(get_metodo, nome_coluna).set_chave_primaria()

    def add_FK(self, get_metodo: classmethod, nome_coluna: str, tabela_estrang):

        colunas_estrang = [col for col in tabela_estrang.get_colunas() if col.is_chave_primaria()]

        if colunas_estrang:
            tipo = colunas_estrang[0].get_tipo()
            nome = colunas_estrang[0].get_nome()
            return self.__add_coluna__(get_metodo, nome_coluna, tipo).set_chave_estrangeira(nome, tabela_estrang.get_nome())

        else:
            raise ValueError("A tabela estrangeira não possui uma chave primária!")

    def __add_CHARS__(self, get_metodo: classmethod, nome_coluna: str, tamanho: int, tipo: str, unique: bool = False):

        if (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self.__add_coluna__(get_metodo, nome_coluna, tipo, unique, tamanho)

    def add_CHAR(self, get_metodo: classmethod, nome_coluna: str, tamanho: int, unique: bool = False):
        return self.__add_CHARS__(get_metodo, nome_coluna, tamanho, "CHAR", unique)

    def add_VARCHAR(self, get_metodo: classmethod, nome_coluna: str, tamanho: int, unique: bool = False):
        return self.__add_CHARS__(get_metodo, nome_coluna, tamanho, "VARCHAR", unique)

    def add_DATE(self, get_metodo: classmethod, nome_coluna: str, unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "DATE", unique)

    def add_TIME(self, get_metodo: classmethod, nome_coluna: str, unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "TIME", unique)

    def add_SMALLINT(self, get_metodo: classmethod, nome_coluna: str, unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "SMALLINT", unique)

    def add_INT(self, get_metodo: classmethod, nome_coluna: str, unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "INT", unique)

    def add_BIGINT(self, get_metodo: classmethod, nome_coluna: str, unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "BIGINT", unique)

    def add_FLOAT(self, get_metodo: classmethod, nome_coluna: str):
        return self.__add_coluna__(get_metodo, nome_coluna, "FLOAT")

    def add_REAL(self, nome_coluna: str):
        return self.__add_coluna__(nome_coluna, "REAL")

    def add_SERIAL(self, get_metodo: classmethod, nome_coluna: str, unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "SERIAL", unique)

    def add_BOOLEAN(self, get_metodo: classmethod, nome_coluna: str):
        return self.__add_coluna__(get_metodo, nome_coluna, "BOOLEAN")

    def add_JSON(self, get_metodo: classmethod, nome_coluna: str,
                 unique: bool = False):
        return self.__add_coluna__(get_metodo, nome_coluna, "JSON", unique)

    #Retorna o nome de todas as colunas
    def get_nome_cols(self, incluir_pk: bool = False) -> List[str]:

        if not incluir_pk:
            return [col.get_nome() for col in self._colunas
                    if not col.is_chave_primaria()]

        else:
            return [col.get_nome() for col in self._colunas]

    def get_colunas(self) -> List[Coluna]:
        return self._colunas


    #----- OPERAÇÕES COM A STRING DO SQL -----#

    # Retorna '('João', 12, NULL)'
    def __get_fmt_val__(self, instancia: object, ignorar_pk: bool = True) -> str:

        sql_valores = []

        for col in self._colunas:

            metodo_get = col.get_metodo_get_atributo()

            valor_sem_aspas: bool = False

            # Se for uma chave primária E não for para ignorar as chaves;
            if col.is_chave_primaria() and not ignorar_pk:
                valor_sem_aspas = True

            # Senão, se a coluna for do tipo numérico/lógico;
            elif col.get_tipo() in self._tipos_sem_aspa and not col.is_chave_primaria():
                valor_sem_aspas = True

            # Senão, se o valor for nulo;
            elif metodo_get(instancia) in {'NULL', 'null'}:
                valor_sem_aspas = True

            # Se o valor for sem aspas, apenas converta-o para string;
            if valor_sem_aspas:
                sql_valores.append(str(metodo_get(instancia)))

            # Senão, é de algum tipo textual (que envolve aspas simples);
            elif not col.is_chave_primaria():

                if col.get_tipo() == "TIME":
                    sql_valores.append("'{0}'".format(
                        datetime.datetime.strftime(metodo_get(instancia),"%H:%M:%S")))

                elif col.get_tipo() == "DATE":
                    sql_valores.append("'{0}'".format(
                        datetime.datetime.strftime(metodo_get(instancia),"%Y-%m-%d")))

                else:
                    sql_valores.append("'{0}'".format(metodo_get(instancia)))

        return "({0})".format(", ".join(sql_valores))

    def get_SQL_insert(self, quantidade: int = 200000, ignorar_pk: bool = True) -> List[str]:

        sqls = []
        tam_tab = len(self._valores)

        # Se for todos os valores OU a qtd. for maior do que o total existente;
        if not quantidade or quantidade > tam_tab: quantidade = tam_tab

        if not self._valores:
            raise ValueError("Tabela não possui valores!")

        ini = 0
        fim = quantidade

        while ini < tam_tab:

            sqls.append(
                "INSERT INTO {0}({1}) VALUES\n\t{2};\n".format(
                    self.get_nome(),
                    ", ".join(self.get_nome_cols()),
                    ",\n\t".join([
                        self.__get_fmt_val__(self._valores[i], ignorar_pk) for i in range(ini, fim)
                    ])
                ))
            ini += quantidade
            fim = (ini + quantidade) if (ini + quantidade) < tam_tab else (tam_tab - 1)

        return sqls

    def get_SQL_create(self) -> str:
        return "CREATE TABLE {0}(\n\t{1}\n);".format(
            self.get_nome(), ", \n\t".join([col.to_string() for col in self._colunas]))

    def gerar_arq_SQL(self, nome_arq_saida: str = None, outro_dir: bool = False, ignorar_pk: bool = False):

        if not nome_arq_saida:
            nome_arq_saida = self._nome + ".sql"

        elif outro_dir:
            nome_arq_saida = nome_arq_saida if nome_arq_saida[-1] == sep else nome_arq_saida + sep
            nome_arq_saida = nome_arq_saida + self._nome + ".sql"

        with open(nome_arq_saida, 'w') as arq:
            arq.write(self.get_SQL_create())
            arq.write('\n')

            for sql in self.get_SQL_insert(ignorar_pk=ignorar_pk):
                arq.write(sql)
                arq.write("\n\n")

        return self

    #----- FUNÇÕES PARA OPERAÇÕES DE BUSCA -----#

    def get_by_custom(self, get_metodo: classmethod, valor_buscado: object):
        return self.buscar(get_metodo, valor_buscado)

    # Retorna o primeiro elemento que possuir o item buscado;
    def buscar(self, get_metodo: classmethod, item_buscado: object,
               ignorar_case: bool = True):

        # Se o atributo buscado for do tipo texto, converta o item buscado
        # e o cada item comparado para minúsculo p/ evitar problemas de case sensitive
        if (type(item_buscado) == str and ignorar_case):

            for item in self._valores:

                if (get_metodo(item).lower() == item_buscado.lower()):
                    return item

        else:

            for item in self._valores:

                if (get_metodo(item) == item_buscado):
                    return item

        return None

    # Retorna todos elementos que possuírem o item buscado;
    def buscar_todos(self, get_metodo: classmethod, item_buscado: object):

        result = []
        self._valores.sort(key=get_metodo)

        if type(item_buscado) == str:
            result = [item for item in self._valores
                      if get_metodo(item).lower() == item_buscado.lower()]

        else:
            result = [item for item in self._valores if get_metodo(item) == item_buscado]

        return result if result else None

    # Retorna todos elementos que possuírem o item buscado;
    def buscar_todos_contem(self, get_metodo: classmethod, item_buscado: str):

        self._valores.sort(key=get_metodo)
        result = [item for item in self._valores
                  if get_metodo(item).lower() in item_buscado.lower()]

        return result if result else None

    def insert(self, valor: ObjetoModelo):
        valor.set_id(self._cont_id)
        self._valores.append(valor)
        self._cont_id += 1

        return self

    def get_coluna_por_nome(self, nome_coluna: str):

        coluna = [col for col in self._colunas
                  if nome_coluna.lower() == col.get_nome().lower()]

        return coluna[0] if coluna else None

    def get_by_id(self, id: int):
        return self.buscar(self._list_get_metodo[0], id)

    def get_by_indice(self, i: int) -> Optional[ObjetoModelo]:
        return self._valores[i] if i < len(self._valores) else None

    def get_all(self):
        return self._valores

    def __str__(self):
        return self.get_SQL_create()
