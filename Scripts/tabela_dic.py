# encoding: utf-8

# Usa um dicionário para simbolizar a estrutura de uma tabela;
# Cada nome de chave representa o nome de uma coluna;
# O valor 'dentro' de cada chave é o tipo da coluna ('CHAR', 'INT', 'SERIAL')...
class Tabela():
    _nome = str
    _pkey = str
    _sql_insert = list
    _tipos_sem_aspa = {'BOOLEAN', 'FLOAT', 'REAL', 'INT', 'BIGINT', 'SMALLINT',
                       'SERIAL'}

    # Exemplo de dicionário para tabela 'Pessoa'
    _dic_tabela = dict

    def __init__(self, nome_tabela: str):
        self._dic_tabela = {}
        self._nome = nome_tabela
        self._sql_insert = []
        self._pkey = ""

    def _add_atributo(self, nome: str, tipo: str):

        if (nome in self._dic_tabela):
            raise ValueError("O atributo '{0}' já consta na tabela!".format(nome))

        self._dic_tabela[nome] = tipo.upper()
        return self

    def get_nome(self):
        return self._nome

    def get_nome_cols(self):
        return [coluna for coluna in self._dic_tabela]

    def get_pkey(self):
        return self._pkey

    def __set_PRIMARY_KEY__(self, nome_coluna: str):

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

    def add_FOREIGN_KEY(self, nome_col_local: str, tab_estrang):

        if tab_estrang.get_pkey():
            self._dic_tabela[nome_col_local] = "INT REFERENCES {0} ({1})".format(
                tab_estrang._nome, tab_estrang.get_pkey())

        return self

    def add_ID(self, nome_coluna: str):
        self.add_SERIAL(nome_coluna)
        self.__set_PRIMARY_KEY__(nome_coluna)
        return self

    def add_CHAR(self, nome_atributo: str, tamanho: int):

        if (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self._add_atributo(nome_atributo, "CHAR({0})".format(tamanho))

    def add_VARCHAR(self, nome_atributo: str, tamanho: int):

        if (tamanho <= 0 or tamanho > 8000):
            raise ValueError("O tamanho da coluna deve estar entre 1 e 8000 chars!")

        return self._add_atributo(nome_atributo, "VARCHAR({0})".format(tamanho))

    def add_DATE(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "DATE")

    def add_TIME(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "TIME")

    def add_SMALLINT(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "SMALLINT")

    def add_INT(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "INT")

    def add_BIGINT(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "BIGINT")

    def add_FLOAT(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "FLOAT")

    def add_REAL(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "REAL")

    def add_SERIAL(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "SERIAL")

    def add_BOOLEAN(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "BOOLEAN")

    def add_JSON(self, nome_atributo: str):
        return self._add_atributo(nome_atributo, "JSON")

    # Retorna '('João', 12, NULL)'
    def __get_SQL_VALUE_FORMAT__(self, value: dict):

        sql_valores = []

        for coluna in self._dic_tabela:

            tipo = self._dic_tabela[coluna]

            if (coluna == self._pkey or tipo in self._tipos_sem_aspa or value[coluna] in {'NULL', 'null'}):
                sql_valores.append(str(value[coluna]))

            else:
                sql_valores.append("'{0}'".format(value[coluna]))

        return "({0})".format(", ".join(sql_valores))

    def get_SQL_INSERT(self, inicio_dados=0, fim_dados=0):

        if not self._dic_tabela:
            raise ValueError("Tabela vazia!")

        if not fim_dados: fim_dados = len(self._sql_insert)

        return "INSERT INTO {0}({1}) VALUES\n\t{2};".format(
            self._nome, ", ".join(self.get_nome_cols()),
            ",\n\t".join(self._sql_insert[inicio_dados: fim_dados])
        )

    def get_SQL_CREATE(self):

        if not self._dic_tabela:
            raise ValueError("Tabela vazia!")

        return "CREATE TABLE {0}({1});".format(
            self._nome, ", ".join(
                [col + " " + self._dic_tabela[col] for col in self._dic_tabela]
            )
        )

    def insert_VALUE(self, value: dict):
        self._sql_insert.append(self.__get_SQL_VALUE_FORMAT__(value))
        return self

    def set_nome(self, nome):
        self._nome = nome