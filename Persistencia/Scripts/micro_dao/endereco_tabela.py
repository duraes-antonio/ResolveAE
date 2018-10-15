# encoding: utf-8

import json
from random import choice, randint
from typing import List

from micro_dao.bairro_tabela import TabelaBairro
from micro_dao.endereco import Endereco
from micro_dao.tabela_model import Tabela
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaEndereco(Tabela):

    _num_registros = 1500000

    def __init__(self, tab_bairro: TabelaBairro, tab_usu: TabelaUsuario,
                 preencher: bool = True):
        super().__init__("endereco", Endereco.get_id)
        self.add_ID(Endereco.get_id)
        self.add_CHAR(Endereco.get_cep, "cep", 8)
        self.add_FK(Endereco.get_fk_bairro, "fk_bairro", tab_bairro)
        self.add_FK(Endereco.get_fk_usuario, "fk_usuario", tab_usu)
        self.__inserted__ = False
        if preencher: self.__preencher__(tab_bairro, tab_usu)

    def __parse_JSON__(self, caminho: str):
        with open(caminho, 'r') as arq:
            return json.load(arq)

    def __preencher__(self, tab_bai: TabelaBairro, tab_usu: TabelaUsuario):

        # TODO cep não condiz com a localização, MUDAR quando a apliação estiver ESTÁVEL
        # se conectando com o serviço de obter localização via CEP
        cep_min = 29160001
        cep_max = 29184999

        [self.insert(
            Endereco(
                str(randint(cep_min, cep_max)),
                choice(tab_bai.get_all()).get_id(),
                usuario.get_id()
            )
        ) for usuario in tab_usu.get_all()]

        self.__inserted__ = True

        return self

    def get_all(self) -> List[Endereco]:
        return super().get_all()

    def get_by_cep(self, cep: str) -> List[Endereco]:
        return super().get_by_custom(Endereco.get_cep, cep)
