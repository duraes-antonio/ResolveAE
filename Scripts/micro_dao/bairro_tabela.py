# encoding: utf-8
from micro_dao.cidade_tabela import TabelaCidade
from micro_dao.estado_tabela import TabelaEstado
from micro_dao.tabela_model import Tabela
from micro_dao.bairro import Bairro
from typing import List
import json


class TabelaBairro(Tabela):
    __path_est_cid__ = "./backup_temp_dados_txts_jsons/estado_cidade_bairro.json"

    def __init__(self, tab_cid: TabelaCidade, tab_est: TabelaEstado):
        super().__init__("bairro", Bairro.get_id)
        self.add_ID(Bairro.get_id)
        self.add_VARCHAR(Bairro.get_nome, "nome", 60)
        self.add_FK(Bairro.get_fk_cidade, "fk_cidade", tab_cid)
        self.__inserted__ = False
        self.__preencher__(tab_cid, tab_est)

    def __parse_JSON__(self, caminho: str):
        with open(caminho, 'r') as arq:
            return json.load(arq)

    def __preencher__(self, tab_cid: TabelaCidade, tab_est: TabelaEstado):
        dic_est_cid_bai = self.__parse_JSON__(self.__path_est_cid__)

        siglas_estados_ord = [estado for estado in dic_est_cid_bai]
        siglas_estados_ord.sort()

        # Para cada sigla de estado;
        for est in siglas_estados_ord:
            cidades_ord: List[str] = [cidade for cidade in dic_est_cid_bai[est]]
            cidades_ord.sort()

            # Para cada nome de cidade da lista de nomes;
            for cid in cidades_ord:

                #Ordene a lista de bairros
                bairros_ord = [bairro for bairro in dic_est_cid_bai[est][cid]]
                bairros_ord.sort()

                fk_est = tab_est.get_by_sigla(est).get_id()

                cidade = [cidade for cidade in tab_cid.get_by_nome(cid)
                          if cidade.get_fk_estado() == fk_est][0]

                [self.insert(Bairro(bai, cidade.get_id())) for bai in bairros_ord]

        self.__inserted__ = True

        return self

    def get_all(self) -> List[Bairro]:
        return super().get_all()
