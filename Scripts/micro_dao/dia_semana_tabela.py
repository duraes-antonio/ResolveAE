# encoding: utf-8
from typing import List

from micro_dao.dia_semana import DiaSemana
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_contato import TipoContato


class TabelaDiaSemana(Tabela):

    _dias = ["DOMINGO", "SEGUNDA-FEIRA", "TERÇA-FEIRA", "QUARTA-FEIRA",
              "QUINTA-FEIRA", "SEXTA-FEIRA", "SÁBADO"]

    def __init__(self):
        super().__init__("dia_semana", DiaSemana.get_id)
        self.add_ID(DiaSemana.get_id)
        self.add_VARCHAR(DiaSemana.get_dia, "nome", 15, True)
        self.__inserted__ = False
        self.__preencher__()

    def __preencher__(self):
        [self.insert(DiaSemana(nome_dia)) for nome_dia in self._dias]
        self.__inserted__ = True
        return self

    def get_all(self) -> List[DiaSemana]:
        return super().get_all()

    def get_by_nome(self, nome: str) -> DiaSemana:
        return super().buscar(TipoContato.get_nome, nome)

