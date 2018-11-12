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

			nome_tipo = tipo_servico.get_nome()

			if nome_tipo == tab_tipo_serv.analise:
				self._subtipos[nome_tipo] = sorted([
					"Levantamento de Requisitos", "Elaboração de Documentação",
					"Modelagem de Sistemas"])

			elif nome_tipo == tab_tipo_serv.banco_dados:
				self._subtipos[nome_tipo] = sorted([
					"Administração de Banco de Dados", "Análise de Banco de Dados",
					"Análise de Business Intelligence", "Ciência de Dados",
					"Modelagem de Banco de Dados"])

			elif nome_tipo == tab_tipo_serv.desenvolvimento:
				self._subtipos[nome_tipo] = sorted([
					"Desenvolvimento Mobile", "Desenvolvimento Web",
					"Desenvolvimento Desktop", "Sistemas Embarcados",
					"Frontend", "Fullstack"])

			elif nome_tipo == tab_tipo_serv.design:
				self._subtipos[nome_tipo] = sorted([
					"Web Design", "Design Gráfico", "Design de Interface"])

			elif nome_tipo == tab_tipo_serv.redes:
				self._subtipos[nome_tipo] = sorted([
					"Administração de Rede de Computadores", "Otimização e Análise",
					"Levantamento e Análise de Riscos",
					"Recuperação e Plano de Contingência"])

			elif nome_tipo == tab_tipo_serv.manutencao_suporte:
				self._subtipos[nome_tipo] = sorted([
					"Manutenção Preventiva e Corretiva",
					"Configuração e Gerencimento de Software",
					"Configuração, Substituição e Análise de Hardware",
					"Limpeza e Otimização de Computadores"])

			elif nome_tipo == tab_tipo_serv.seguranca:
				self._subtipos[nome_tipo] = sorted([
					"Backup e Recuperação de Dados", "Auditoria e Análise de Riscos",
					"Gerenciamento de Redes", "Controle de Acesso à Infraestrutura",
					"Análise de Processos"])

			elif nome_tipo == tab_tipo_serv.infraestrutura:
				self._subtipos[nome_tipo] = sorted([
					"Auditoria", "Monitoramento", "Gerenciamento de Firewall",
					"Atualização de Drivers/Firmware", "Hospedagem de Sistemas",
					"Problemas de Desempenho", "Problemas com Conexão",
					"Instalação de Servidores ou Dispositivos de Rede"])

			[self.insert(
				SubtipoServico(
					subtipo, tipo_servico.get_id()
				)
			) for subtipo in self._subtipos[nome_tipo]]

		self.__inserted__ = True
		return self

	def get_all(self) -> List[SubtipoServico]:
		return super().get_all()

	def get_by_indice(self, i: int) -> Optional[SubtipoServico]:
		return super().get_by_indice(i)

	def get_by_fk_tipo_servico(self, fk_tipo_servico: int):
		return [subtipo for subtipo in self.get_all()
		        if subtipo.get_fk_tipo_servico() == fk_tipo_servico]