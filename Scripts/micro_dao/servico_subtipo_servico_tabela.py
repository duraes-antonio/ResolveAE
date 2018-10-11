# encoding: utf-8
from random import uniform, choice
from typing import List

from micro_dao.contrato_tabela import TabelaContrato
from micro_dao.servico import Servico
from micro_dao.servico_subtipo_servico import ServicoSubtipoServico
from micro_dao.servico_tabela import TabelaServico
from micro_dao.subtipo_servico import SubtipoServico
from micro_dao.subtipo_servico_tabela import TabelaSubtipoServico
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_servico_tabela import TabelaTipoServico
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaServicoSubtipoServico(Tabela):

    def __init__(self, tab_serv: TabelaServico, tab_subt_serv: TabelaSubtipoServico):
        super().__init__("servico_subtipo_servico", ServicoSubtipoServico.get_id)
        self.add_ID(ServicoSubtipoServico.get_id)
        self.add_FK(ServicoSubtipoServico.get_fk_servico, "fk_servico", tab_serv)
        self.add_FK(ServicoSubtipoServico.get_fk_subtipo_servico, "fk_subtipo_servico", tab_subt_serv)
        self.__inserted__ = False
        self.__preencher__(tab_serv, tab_subt_serv)

    def __preencher__(self, tab_serv: TabelaServico, tab_subt_serv: TabelaSubtipoServico):

        for i, servico in enumerate(tab_serv.get_all()):

            self.insert(
                ServicoSubtipoServico(
                    servico.get_id(),
                    choice(tab_subt_serv.get_all()).get_id()
                )
            )

        self.__inserted__ = True
        return self

    def get_all(self) -> List[Servico]:
        return super().get_all()