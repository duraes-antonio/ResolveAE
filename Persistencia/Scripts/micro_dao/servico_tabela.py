# encoding: utf-8
from random import uniform, choice
from typing import List

from micro_dao.contrato_tabela import TabelaContrato
from micro_dao.servico import Servico
from micro_dao.subtipo_servico import SubtipoServico
from micro_dao.subtipo_servico_tabela import TabelaSubtipoServico
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_servico_tabela import TabelaTipoServico
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaServico(Tabela):
    _subtipos: dict
    _valor_min = 300.00
    _valor_max = 1500.00

    def __init__(self, tab_contrato: TabelaContrato, tab_tipo_serv: TabelaTipoServico,
                 tab_subt_serv: TabelaSubtipoServico, tab_usu: TabelaUsuario,
                 preencher: bool = False):
        super().__init__("servico", Servico.get_id)
        self._subtipos = {}
        self.add_ID(Servico.get_id)
        self.add_FLOAT(Servico.get_valor, "valor")
        self.add_VARCHAR(Servico.get_titulo, "titulo", 150)
        self.add_FK(Servico.get_fk_contrato, "fk_contrato", tab_contrato)
        self.add_FK(Servico.get_fk_subtipo_servico, "fk_subtipo_servico", tab_subt_serv)
        self.add_FK(Servico.get_fk_tipo_servico, "fk_tipo_servico", tab_tipo_serv)
        self.add_FK(Servico.get_fk_usuario, "fk_usuario", tab_usu)
        self.__inserted__ = False
        if preencher: self.__preencher__(tab_contrato, tab_usu, tab_subt_serv)

    def __preencher__(self, tab_contrato: TabelaContrato, tab_usu: TabelaUsuario,
                      tab_subt_serv: TabelaSubtipoServico):

        for i, contrato in enumerate(tab_contrato.get_all()):

            if tab_usu.get_by_indice(i).prestador:
                subt_serv: SubtipoServico = choice(tab_subt_serv.get_all())
                self.insert(
                    Servico(
                        uniform(self._valor_min, self._valor_max),
                        "Imagine um título da hora.",
                        contrato.get_fk_usuario(),
                        subt_serv.get_fk_tipo_servico(),
                        subt_serv.get_id(),
                        contrato.get_id()
                    )
                )

        self.__inserted__ = True
        return self

    def get_all(self) -> List[Servico]:
        return super().get_all()