# encoding: utf-8
import json
from micro_dao.tabela_base import Tabela
from micro_dao.estado import Estado
from typing import List

class TabelaEstado(Tabela):


    __estados__ = {'Acre': 'AC', 'Alagoas': 'AL', 'Amapá': 'AP', 'Amazonas': 'AM',
                'Bahia': 'BA', 'Ceará': 'CE', 'Distrito Federal': 'DF',
                'Espírito Santo': 'ES', 'Goiás': 'GO', 'Maranhão': 'MA',
                'Mato Grosso': 'MT', 'Mato Grosso do Sul': 'MS',
                'Minas Gerais': 'MG', 'Pará': 'PA', 'Paraíba': 'PB', 'Paraná': 'PR',
                'Pernambuco': 'PE', 'Piauí': 'PI', 'Rio de Janeiro': 'RJ',
                'Rio Grande do Norte': 'RN', 'Rio Grande do Sul': 'RS',
                'Rondônia': 'RO', 'Roraima': 'RR', 'Santa Catarina': 'SC',
                'São Paulo': 'SP', 'Sergipe': 'SE', 'Tocantins': 'TO'
                }

    def __init__(self):
        super().__init__("estado", Estado.get_id)
        self.add_ID()
        self.add_VARCHAR("nome", 40)
        self.add_CHAR("sigla", 2)
        self.__inserted__ = False
        self.__preencher__()

    def __preencher__(self):
        [self.insert_VALUE(Estado(chave, self.__estados__[chave]))
         for chave in self.__estados__]
        self.__inserted__ = True
        return self

    def get_ALL(self) -> List[Estado]:
        return super().get_ALL()

    def get_BY_SIGLA(self, sigla: str) -> Estado:
        return super().get_BY_CUSTOM(Estado.get_sigla, sigla)

    def get_BY_NOME(self, nome: str) -> Estado:
        return super().get_BY_CUSTOM(Estado.get_nome, nome)
