# encoding: utf-8
from typing import List

from micro_dao.agenda_prestacao import AgendaPrestacao
from micro_dao.tabela_model import Tabela
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaAgendaPrestacao(Tabela):
    _num_registros = 1500000

    def __init__(self, tab_usu: TabelaUsuario, preencher: bool=True):
        super().__init__("agenda", AgendaPrestacao.get_id)
        self.add_ID(AgendaPrestacao.get_id)
        self.add_FK(AgendaPrestacao.get_fk_usuario, "fk_usuario", tab_usu)
        self.__inserted__ = False

        if preencher: self.__preencher__(tab_usu)

    def __preencher__(self, tab_usu: TabelaUsuario):
        [self.insert(AgendaPrestacao(tab_usu.get_by_indice(i).get_id()))
         for i in range(self._num_registros)]
        self.__inserted__ = True

        return self

    def get_all(self) -> List[AgendaPrestacao]:
        return super().get_all()