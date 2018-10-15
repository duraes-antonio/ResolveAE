# encoding: utf-8
from typing import List, Optional

from micro_dao.subtipo_servico import SubtipoServico
from micro_dao.tabela_model import Tabela
from micro_dao.tipo_servico import TipoServico
from micro_dao.tipo_servico_tabela import TabelaTipoServico


class TabelaSubtipoServico(Tabela):
    _subtipos: dict

    def __init__(self, tab_t_serv: TabelaTipoServico, preencher: bool = False):
        super().__init__("subtipo_servico", SubtipoServico.get_id)
        self._subtipos = {}
        self.add_ID(SubtipoServico.get_id)
        self.add_VARCHAR(SubtipoServico.get_nome, "nome", 50, True)
        self.add_FK(SubtipoServico.get_fk_tipo_servico, "fk_tipo_servico", tab_t_serv)
        self.__inserted__ = False
        if preencher: self.__preencher__(tab_t_serv)

    def __preencher__(self, tab_tipo_serv: TabelaTipoServico):

        for tipo_servico in tab_tipo_serv.get_all():

            nome_serv = tipo_servico.get_nome()

            if nome_serv == tab_tipo_serv.str_analise():
                self._subtipos[nome_serv] = [
                    "Levantamento de Requisitos", "Elaboração de Documentação",
                    "Modelagem de Sistemas"]

            elif nome_serv == tab_tipo_serv.str_banco_de_dados():
                self._subtipos[nome_serv] = [
                    "Administração de Banco de Dados", "Análise de Banco de Dados",
                    "Análise de Business Intelligence", "Ciência de Dados",
                    "Modelagem de Banco de Dados"]

            elif nome_serv == tab_tipo_serv.str_desenvolvimento():
                self._subtipos[nome_serv] = [
                    "Desenvolvimento Mobile", "Desenvolvimento Web",
                    "Desenvolvimento Desktop", "Sistemas Embarcados",
                    "Frontend", "Fullstack"]

            elif nome_serv == tab_tipo_serv.str_design():
                self._subtipos[nome_serv] = [
                    "Web Design", "Design Gráfico", "Design de Interface"]

            elif nome_serv == tab_tipo_serv.str_rede_de_computadores():
                self._subtipos[nome_serv] = [
                    "Administração de Rede de Computadores", "Otimização e Análise",
                    "Levantamento e Análise de Riscos", "Recuperação e Plano de Contingência"]

            elif nome_serv == tab_tipo_serv.str_manutencao_e_suporte():
                self._subtipos[nome_serv] = [
                    "Manutenção Preventiva e Corretiva",
                    "Configuração e Gerencimento de Software",
                    "Configuração, Substituição e Análise de Hardware",
                    "Limpeza e Otimização de Computadores"]

            elif nome_serv == tab_tipo_serv.str_seguranca_da_informacao():
                self._subtipos[nome_serv] = [
                    "Backup e Recuperação de Dados", "Auditoria e Análise de Riscos",
                    "Gerenciamento de Redes", "Controle de Acesso à Infraestrutura",
                    "Análise de Processos"]

            [self.insert(
                SubtipoServico(
                    subtipo, tipo_servico.get_id()
                )
            ) for subtipo in self._subtipos[nome_serv]]

        self.__inserted__ = True
        return self

    def get_all(self) -> List[SubtipoServico]:
        return super().get_all()

    def get_by_indice(self, i: int) -> Optional[SubtipoServico]:
        return super().get_by_indice(i)