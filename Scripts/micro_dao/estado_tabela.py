# encoding: utf-8
from micro_dao.tabela_model import Tabela
from micro_dao.estado import Estado
from typing import List


class TabelaEstado(Tabela):

    __estados__ = {'Acre': 'AC', 'Alagoas': 'AL', 'Amazonas': 'AM', 'Amapá': 'AP',
                   'Bahia': 'BA', 'Ceará': 'CE', 'Distrito Federal': 'DF',
                   'Espírito Santo': 'ES', 'Goiás': 'GO', 'Maranhão': 'MA',
                   'Minas Gerais': 'MG', 'Mato Grosso do Sul': 'MS', 'Mato Grosso': 'MT',
                   'Pará': 'PA', 'Paraíba': 'PB', 'Pernambuco': 'PE', 'Piauí': 'PI',
                   'Paraná': 'PR', 'Rio de Janeiro': 'RJ', 'Rio Grande do Norte': 'RN',
                   'Rondônia': 'RO', 'Roraima': 'RR', 'Rio Grande do Sul': 'RS',
                   'Santa Catarina': 'SC', 'Sergipe': 'SE', 'São Paulo': 'SP', 'Tocantins': 'TO'
                   }

    def __init__(self):
        super().__init__("estado", Estado.get_id)
        self.add_ID(Estado.get_id)
        self.add_VARCHAR(Estado.get_nome, "nome", 40, True)
        self.add_CHAR(Estado.get_sigla, "sigla", 2, True)
        self.__inserted__ = False
        self.__preencher__()

    def __preencher__(self):
        [self.insert(Estado(chave, self.__estados__[chave]))
         for chave in self.__estados__]
        self.__inserted__ = True
        return self

    def get_all(self) -> List[Estado]:
        return super().get_all()

    def get_by_sigla(self, sigla: str) -> Estado:
        return super().buscar(Estado.get_sigla, sigla)

    def get_by_nome(self, nome: str) -> Estado:
        return super().buscar(Estado.get_nome, nome)

