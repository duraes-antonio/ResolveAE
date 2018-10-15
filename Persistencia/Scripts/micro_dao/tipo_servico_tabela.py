# encoding: utf-8
from typing import List

from micro_dao.tabela_model import Tabela
from micro_dao.tipo_servico import TipoServico


class TabelaTipoServico(Tabela):
    _tipos = ["MANUTENÇÃO E SUPORTE", "REDE DE COMPUTADORES", "BANCO DE DADOS",
              "DESENVOLVIMENTO", "DESIGN", "ANÁLISE DE SISTEMA",
              "SEGURANÇA DA INFORMAÇÃO"]

    def __init__(self, preencher: bool = False):
        super().__init__("tipo_servico", TipoServico.get_id)
        self.add_ID(TipoServico.get_id)
        self.add_VARCHAR(TipoServico.get_nome, "nome", 30, True)
        self.__inserted__ = False
        if preencher: self.__preencher__()

    def __preencher__(self):
        [self.insert(TipoServico(tipo)) for tipo in self._tipos]
        self.__inserted__ = True
        return self

    def get_all(self) -> List[TipoServico]:
        return super().get_all()

    def get_by_nome(self, nome: str) -> TipoServico:
        return super().buscar(TipoServico.get_nome, nome)

    def str_manutencao_e_suporte(self) -> str:
        return self._tipos[0]

    def str_rede_de_computadores(self) -> str:
        return self._tipos[1]

    def str_banco_de_dados(self) -> str:
        return self._tipos[2]

    def str_desenvolvimento(self) -> str:
        return self._tipos[3]

    def str_design(self) -> str:
        return self._tipos[4]

    def str_analise(self) -> str:
        return self._tipos[5]

    def str_seguranca_da_informacao(self) -> str:
        return self._tipos[6]