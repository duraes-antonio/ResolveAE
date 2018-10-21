# encoding: utf-8
from random import choice, randint
from typing import List

from micro_dao.avaliacao import Avaliacao
from micro_dao.servico_tabela import TabelaServico
from micro_dao.tabela_model import Tabela
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaAvaliacao(Tabela):
    _num_min_aval = 1
    _num_max_aval = 3
    _nota_min = 1
    _nota_max = 5

    def __init__(self, tab_serv_prest: TabelaServico, tab_usu: TabelaUsuario,
                 preencher: bool = False):
        super().__init__("avaliacao", Avaliacao.get_id)
        self.add_ID(Avaliacao.get_id)
        self.add_INT(Avaliacao.get_nota, "nota")
        self.add_FK(Avaliacao.get_fk_usuario, "fk_usuario", tab_usu)
        self.add_FK(Avaliacao.get_fk_servico_prestacao, "fk_servico", tab_serv_prest)
        self.__inserted__ = False
        if preencher: self.__preencher__(tab_serv_prest, tab_usu)

    def __preencher__(self, tab_serv_prest: TabelaServico, tab_usu: TabelaUsuario):

        # Para cada serviço, faça uma ou nenhuma avaliação;
        for serv_prest in tab_serv_prest.get_all():

            for aval in range(randint(0, 1)):

                self.insert(
                    Avaliacao(
	                    randint(self._nota_min, self._nota_max),
	                    serv_prest.get_id(),
	                    serv_prest.get_fk_usuario()
                    )
                )

        self.__inserted__ = True
        return self

    def get_all(self) -> List[Avaliacao]:
        return super().get_all()