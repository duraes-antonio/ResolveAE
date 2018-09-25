# encoding: utf-8
from micro_dao.estado_tabela import TabelaEstado
from micro_dao.tabela_base import Tabela
from micro_dao.cidade import Cidade
from typing import List
import json

class TabelaCidade(Tabela):

    __path_est_cid__ = "/home/x/Área de Trabalho/estado_cidade.json"

    def __init__(self, tab_est: TabelaEstado):
        super().__init__("cidade", Cidade.get_id)
        self.add_ID()
        self.add_VARCHAR("nome", 40)
        self.add_FOREIGN_KEY("fk_estado", tab_est)
        self.__inserted__ = False
        self.__preencher__(tab_est)

    def __parse_JSON__(self, caminho: str):

        with open(caminho, 'r') as arq:
            return json.load(arq)

    def __preencher__(self, tab_est: TabelaEstado):

        dic_es_cid = self.__parse_JSON__(self.__path_est_cid__)
        [print(x.get_dic()) for x in tab_est.get_ALL()]
        for est in dic_es_cid:

            for cid in dic_es_cid[est]:

                try:
                    self.insert_VALUE(Cidade(cid, tab_est.get_BY_SIGLA(est).get_id()))

                except:
                    print("--> '{}'".format(est))

            # [self.insert_VALUE(Cidade(cid, tab_est.get_BY_NOME(est).get_id())) for cid in dic_es_cid[est]]
        self.__inserted__ = True
        return self

    def get_ALL(self) -> List[Cidade]:
        return super().get_ALL()
