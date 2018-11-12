# encoding: utf-8
from random import uniform, choice, randint
from typing import List

from micro_dao.contrato_tabela import TabelaContrato
from micro_dao.fabrica_servicos import FabricaServico
from micro_dao.servico import Servico
from micro_dao.subtipo_servico import SubtipoServico
from micro_dao.subtipo_servico_tabela import TabelaSubtipoServico
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_servico import TipoServico
from micro_dao.tipo_servico_tabela import TabelaTipoServico
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaServico(Tabela):

    def __init__(self, tab_contrato: TabelaContrato, tab_tipo_serv: TabelaTipoServico,
                 tab_usu: TabelaUsuario,
                 preencher: bool = False):
        super().__init__("servico", Servico.get_id)
        self.add_ID(Servico.get_id)
        self.add_VARCHAR(Servico.get_titulo, "titulo", 150)
        self.add_VARCHAR(Servico.get_titulo, "descricao", 1500)
        self.add_FLOAT(Servico.get_valor, "valor")
        self.add_FK(Servico.get_fk_contrato, "fk_contrato", tab_contrato)
        self.add_FK(Servico.get_fk_tipo_servico, "fk_tipo_servico", tab_tipo_serv)
        self.add_FK(Servico.get_fk_usuario, "fk_usuario", tab_usu)

        self.__inserted__ = False

        if preencher:
            self.__preencher__(tab_contrato, tab_usu, tab_tipo_serv)


    def __preencher__(self, tab_contrato: TabelaContrato, tab_usu: TabelaUsuario,
                      tab_tipo_serv: TabelaTipoServico):

        tipos: List[TipoServico] = tab_tipo_serv.get_all()
        fabrica: FabricaServico = FabricaServico()

        for i, contrato in enumerate(tab_contrato.get_all()):

            if tab_usu.get_by_indice(i).prestador:

                ind_tipo_serv = randint(1, 5)

                if (ind_tipo_serv == 1):
                    self.insert(fabrica.get_serv_analise(contrato.get_id(), contrato.get_fk_usuario()))

                elif (ind_tipo_serv == 2):
                    self.insert(fabrica.get_serv_banco_dados(contrato.get_id(), contrato.get_fk_usuario()))

                elif (ind_tipo_serv == 3):
                    self.insert(fabrica.get_serv_desenvolvimento(contrato.get_id(), contrato.get_fk_usuario()))

                elif (ind_tipo_serv == 4):
                    self.insert(fabrica.get_serv_design(contrato.get_id(), contrato.get_fk_usuario()))

                elif (ind_tipo_serv == 5):
                    self.insert(fabrica.get_serv_redes(contrato.get_id(), contrato.get_fk_usuario()))

        self.__inserted__ = True

        return self

    def get_all(self) -> List[Servico]:
        return super().get_all()