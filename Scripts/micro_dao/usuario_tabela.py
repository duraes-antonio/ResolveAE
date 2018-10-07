# encoding: utf-8
from random import randint, choice

from micro_dao.tabela_model import Tabela
from micro_dao.usuario import Usuario
from typing import List, Optional
from text_unidecode import unidecode

class TabelaUsuario(Tabela):

    # Número de usuário que serão gerados;
    _num_registros: int

    # Caminho do arquivo com nomes e sobrenomes dos usuários;
    _path_arq_fem = "./backup_temp_dados_txts_jsons/nomes_fem.txt"
    _path_arq_masc = "./backup_temp_dados_txts_jsons/nomes_masc.txt"
    _path_arq_sobrenomes = "./backup_temp_dados_txts_jsons/sobrenomes.txt"

    _nomes = List[str]
    _sobrenomes = List[str]
    _senha = "senha"
    _email = ["gmail.com", "yahoo.com", "live.com", "outlook.com",
             "bol.com.br", "r7.com", "live.com.br", "hotmail.com",
             "fastmail.com", "icloud.com", "hushmail.com", "protonmail.com"]

    def __init__(self, preencher_base: bool = True, num_registros: int = 1500000):
        super().__init__("usuario", Usuario.get_id)
        self.add_ID(Usuario.get_id)
        self.add_VARCHAR(Usuario.get_nome, "nome", 150)
        self.add_VARCHAR(Usuario.get_email, "email", 100)
        self.add_VARCHAR(Usuario.get_senha, "senha", 64)
        self.add_VARCHAR(Usuario.get_sobre, "sobre", 1000)
        self.__inserted__ = False
        self._num_registros = num_registros

        if preencher_base: self.__preencher__()


    def __preencher__(self):

        # Carregue os arquivos de nomes femininos, masculinos e sobrenomes;
        self._nomes = self.__get_linhas_arq__(self._path_arq_fem)
        self._nomes += self.__get_linhas_arq__(self._path_arq_masc)
        self._sobrenomes = self.__get_linhas_arq__(self._path_arq_sobrenomes)

        # Para cada usuário a ser gerado;
        for i in range(self._num_registros):

            nome = self.__get_nome_random__()

            # Descomente a linha abaixo para evitar nomes duplicados**
            # A criação da tabela demorará bastante (tempo > 20 minutos)
            # while (self.buscar(Usuario.get_nome, nome, False)):
            #     nome = self.__get_nome_random__()

            email = self.__get_email_random__(nome)
            self.insert(Usuario(email, nome, self._senha, "..."))

        self.__inserted__ = True
        return self

    def __remover_acentos__(self, string):
        return unidecode(string)

    def __get_nome_random__(self) -> str:

        sobrenome = []

        for j in range(randint(1, 3)):
            sobrenome_temp = choice(self._sobrenomes)

            if not sobrenome_temp in sobrenome:
                sobrenome.append(sobrenome_temp)

        return "{0} {1}".format(choice(self._nomes), " ".join(sobrenome))

    def __get_email_random__(self, nome) -> str:
        nomes = self.__remover_acentos__(nome).strip().lower().split(" ")
        num_rand = randint(1, 100)
        separador = choice(['.', '-', '_']) if len(nomes) > 1 else ""
        iniciais = "".join([nomes[i][0] for i in range(1, len(nomes))])

        return "{0}{1}{2}{3}@{4}".format(
            nomes[0], separador, iniciais, num_rand, choice(self._email))

    def __get_linhas_arq__(self, caminho: str) -> List[str]:

        with open(caminho, 'r') as arq:
            return [linha.strip() for linha in arq.readlines()]

    def get_all(self) -> List[Usuario]:
        return super().get_all()

    def get_by_id(self, id: int) -> Usuario:
        return super().get_by_id(id)

    def get_by_contem_nome(self, nome: str) -> List[Usuario]:
        return super().buscar_todos_contem(Usuario.get_nome, nome)

    def get_by_indice(self, i: int) -> Optional[Usuario]:
        return super().get_by_indice(i)

