# encoding: utf-8
from typing import List

from micro_dao.tabela_model import Tabela
from micro_dao.tipo_contato import TipoContato


class TabelaTipoContato(Tabela):

    _tipos = ["CELULAR", "FACEBOOK", "GITHUB", "LATTES", "LINKEDIN", "SKYPE",
              "TELEFONE", "TELEGRAM", "TWITTER", "WHATSAPP"]

    def __init__(self, preencher: bool = False):
        super().__init__("tipo_contato", TipoContato.get_id)
        self.add_ID(TipoContato.get_id)
        self.add_VARCHAR(TipoContato.get_nome, "nome", 15, True)
        self.__inserted__ = False
        if preencher: self.__preencher__()

    def __preencher__(self):
        [self.insert(TipoContato(tipo)) for tipo in self._tipos]
        self.__inserted__ = True
        return self

    def get_all(self) -> List[TipoContato]:
        return super().get_all()

    def get_by_nome(self, nome: str) -> TipoContato:
        return super().buscar(TipoContato.get_nome, nome)

