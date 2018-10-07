# encoding: utf-8
from micro_dao.estado_tabela import TabelaEstado
from micro_dao.tabela_model import Tabela
from micro_dao.cidade import Cidade
from typing import List
import json


class TabelaCidade(Tabela):
    __path_est_cid__ = "./backup_temp_dados_txts_jsons/estado_cidade_bairro.json"

    def __init__(self, tab_est: TabelaEstado):
        super().__init__("cidade", Cidade.get_id)
        self.add_ID(Cidade.get_id)
        self.add_VARCHAR(Cidade.get_nome, "nome", 40)
        self.add_FK(Cidade.get_fk_estado, "fk_estado", tab_est)
        self.__inserted__ = False
        self.__preencher__(tab_est)

    def __parse_JSON__(self, caminho: str):
        with open(caminho, 'r') as arq:
            return json.load(arq)

    def __preencher__(self, tab_est: TabelaEstado):
        dic_es_cid = self.__parse_JSON__(self.__path_est_cid__)

        # Para cada sigla de estado;
        for est in dic_es_cid:
            [self.insert(Cidade(cid, tab_est.get_by_sigla(est).get_id())) for cid in dic_es_cid[est]]

        self.__inserted__ = True

        return self

    def get_all(self) -> List[Cidade]:
        return super().get_all()

    def get_by_nome(self, nome: str) -> List[Cidade]:
        return super().buscar_todos(Cidade.get_nome, nome)
