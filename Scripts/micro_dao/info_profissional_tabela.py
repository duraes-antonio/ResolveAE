# encoding: utf-8
from random import randint, choice
import random
from typing import List

import time

import datetime

from micro_dao.tabela_model import Tabela
from micro_dao.info_profissional import InfoProfissional
from micro_dao.tipo_info_profissional import TipoInfoProfissional
from micro_dao.tipo_info_profissional_tabela import TabelaTipoInfoProfissional
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaInfoProfissional(Tabela):

    _dt_info_ini = "1994-01-01"
    _dt_info_fim = "2004-01-01"
    _fmt = "%Y-%m-%d"

    def __init__(self, tab_usu: TabelaUsuario, tab_info: TabelaTipoInfoProfissional,
                 preencher: bool = False):
        super().__init__("info_profissional", InfoProfissional.get_id)
        self.add_ID(InfoProfissional.get_id)
        self.add_VARCHAR(InfoProfissional.get_descricao, "descricao", 200)
        self.add_DATE(InfoProfissional.get_data_inicio, "data_inicio")
        self.add_DATE(InfoProfissional.get_data_fim, "data_fim")
        self.add_FK(InfoProfissional.get_fk_usuario, "fk_usuario", tab_usu)
        self.add_FK(InfoProfissional.get_fk_tipo_info_prof, "fk_tipo_info_prof", tab_info)
        self.__inserted__ = False
        if preencher: self.__preencher__(tab_usu, tab_info)

    def __preencher__(self, tab_usu: TabelaUsuario, tab_t_info: TabelaTipoInfoProfissional):

        desc_info = ""
        duracao = 0
        atv: TipoInfoProfissional = None

        cursos_tec = ["Técnico em Informática", "Técnico em Manutenção e Suporte",
                           "Técnico em Rede de Computadores",
                           "Técnico em Informática para Internet"]
        lings_sgbd = ["Java", "Python", "C", "C#", ".Net", "MySQL", "PostgreSQL",
                      "Javascript", "GO", "Django", "PHP", "Perl", "Assembly",
                      "Visual Basic", "Pascal", "Objective-C", "R", "Lua",
                      "Scala", "Typescript", "MongoDB", "Oracle", "Cassandra"]
        cursos_grad = ["Sistemas de Informação", "Ciência da Computação",
                       "Engenharia da Computação", "Análise e Desenvolvimento de Sistemas",
                       "Informática na Educação", "Ciência da Informação", "Big Data",
                       "Segurança da Informação"]
        tit_pos_grad = ["Mestre", "Doutor(a)"]
        instituicoes = ["Universidade Federal de Santa Catarina",
                        "Universidade Presbiteriana Mackenzie",
                        "Universidade Federal de Minas Gerais",
                        "Pontifícia Universidade Católica do Rio de Janeiro",
                        "Instituto Federal do Espírito Santo",
                        "Universidade Federal do Espírito Santo",
                        "Faculdade Multivix-Cachoeiro de Itapemirim",
                        "Centro de Ensino Superior Fabra",
                        "Faculdade UCL", "Universidade Vila Velha"]
        organizacoes = ["Nexa Tecnologia", "Globalsys Soluções em TI", "Ilha Tecnologia",
                        "AEVO", "Gênesis Tecnologia", "ETAURE", "Phidelis", "PicPay",
                        "Wine", "PD Case Informática"]
        cargos = ["Cientista de dados", "Analista de Business Intelligence",
                  "Engenheiro de Software", "Analista de Sistemas", "Designer de Interfaces",
                  "Webdesigner", "Administrador de Banco de Dados",
                  "Engenheiro de Testes", "Gerente de projeto", "Gerente de qualidade",
                  "Auditor de TI", "Analista de segurança", "Analista de telecomunicações",
                  "Suporte técnico", "Programador"]
        complem_ling = ["Trabalho com {} há vários anos.",
                       "Já desenvolvi vários projeto usando {}.",
                       "Desde criança já brincava com {}.",
                       "{} é uma de minhas especialidades.",
                       "Uso {} para ter ganho em produtividade.",
                       "Sem {}, o desenvolvimento não seria tão performático.",
                       "Ou você é desenvolvedor ou você usa {}.",
                       "90% dos desenvolvedores usam {}, os outros 10% não fazem gambiarra."]

        for usuario in tab_usu.get_all():

            for i in range(randint(1, 3)):
                ind = randint(0, 10)

                if ind >= (len(tab_t_info.get_all()) - 1):
                    continue

                else:

                    atv = tab_t_info.get_by_indice(ind)

                    if atv.get_nome() == tab_t_info.str_curso_tecnico():
                        desc_info = " - ".join([choice(cursos_tec), choice(instituicoes)])
                        duracao = 2

                    elif atv.get_nome() == tab_t_info.str_graduacao():
                        desc_info = " - ".join([choice(cursos_grad), choice(instituicoes)])
                        duracao = randint(4, 6)

                    elif atv.get_nome() == tab_t_info.str_pos_graduacao():
                        desc_info = "{} em {} - {}".format(
                            choice(tit_pos_grad), choice(cursos_grad), choice(instituicoes))
                        duracao = randint(2, 4)

                    elif atv.get_nome() == tab_t_info.str_dominio_tecnologico():
                        desc_info = choice(complem_ling).format(choice(lings_sgbd))
                        duracao = randint(2, 15)

                    elif atv.get_nome() == tab_t_info.str_trabalho():
                        desc_info = " - ".join([choice(organizacoes), choice(cargos)])
                        duracao = randint(1, 10)

                    dt_ini = datetime.datetime.strptime(self.__randomDate__(
                        self._dt_info_ini, self._dt_info_fim), self._fmt)
                    dt_fim = dt_ini + (datetime.timedelta(days=duracao*365.25))

                    self.insert(
                        InfoProfissional(
                            desc_info, dt_ini, dt_fim,
                            usuario.get_id(), atv.get_id()
                        )
                    )

        self.__inserted__ = True

        return self

    def __strTimeProp__(self, start, end, format, prop) -> str:
        stime = time.mktime(time.strptime(start, format))
        etime = time.mktime(time.strptime(end, format))

        ptime = stime + prop * (etime - stime)

        return time.strftime(format, time.localtime(ptime))

    def __randomDate__(self, start, end) -> str:
        return self.__strTimeProp__(start, end, self._fmt, random.random())

    def get_all(self) -> List[InfoProfissional]:
        return super().get_all()