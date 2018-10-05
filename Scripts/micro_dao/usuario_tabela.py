# encoding: utf-8
from micro_dao.tabela_base import Tabela
from micro_dao.usuario import Usuario
from typing import List
import json
from faker import Faker

class TabelaUsuario(Tabela):

    faker_gen = Faker("pt_br")

    def __init__(self, preencher_base: bool = True):
        super().__init__("usuario", Usuario.get_id)
        self.add_ID()
        self.add_VARCHAR("nome", 150)
        self.add_VARCHAR("email", 100)
        self.add_VARCHAR("senha", 64)
        self.add_VARCHAR("sobre", 1000)
        # self.add_FOREIGN_KEY("fk_informacoes", tab_info)
        self.__inserted__ = False
        if preencher_base: self.__preencher__(1500000)

    def __parse_JSON__(self, caminho: str):

        with open(caminho, 'r') as arq:
            return json.load(arq)


    def __preencher__(self, qtd: int):
        # [self.insert_VALUE(Usuario("IMAGINE UM NOME QUALQUER", "EMAIL_QUALQUER@email.com", "null", "...", -1))
        #  for i in range(qtd)]
        [self.insert_VALUE(Usuario(self.faker_gen.name(), self.faker_gen.email(), "null", "...", -1))
         for i in range(qtd)]
        self.__inserted__ = True
        return self

    def get_ALL(self) -> List[Usuario]:
        return super().get_ALL()
