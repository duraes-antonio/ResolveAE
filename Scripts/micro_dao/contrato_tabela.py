# encoding: utf-8
import random
from datetime import datetime
from typing import List, Optional

import time

from micro_dao.contrato import Contrato
from micro_dao.tabela_model import Tabela
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaContrato(Tabela):

    _fmt = "%Y-%m-%d"

    # As datas de início da prestação estarão entre "1/3/2016" e "1/3/2017"
    _dt_min_i = "2016-3-1"
    _dt_min_f = "2017-3-1"

    # As datas de fim da prestação estarão entre "1/3/2018" e a data atual;
    _dt_max_i = "2018-3-1"
    _dt_max_f = datetime.now().strftime(_fmt)

    def __init__(self, tab_usu: TabelaUsuario, preencher: bool = True):
        super().__init__("contrato", Contrato.get_id)
        self.add_ID(Contrato.get_id)
        self.add_DATE(Contrato.get_data_contrato, "data_contrato")
        self.add_DATE(Contrato.get_data_inicio_prestacao, "data_inicio_prestacao")
        self.add_DATE(Contrato.get_data_fim_prestacao, "data_fim_prestacao")
        self.add_DATE(Contrato.get_data_ultima_modif, "data_ult_modif")
        self.add_VARCHAR(Contrato.get_descricao, "descricao", 1000)
        self.add_FK(Contrato.get_fk_usuario, "fk_usuario", tab_usu)
        self.__inserted__ = False

        if preencher: self.__preencher__(tab_usu)

    def __preencher__(self, tab_usu: TabelaUsuario):

        for i, usuario in enumerate(tab_usu.get_all()):

            if (i % 2 == 0 or i % 3 == 0):

                self.insert(
                    Contrato(
                        datetime.strptime(self._dt_min_i, self._fmt),
                        datetime.strptime(self.__randomDate__(self._dt_min_i, self._dt_min_f), self._fmt),
                        datetime.strptime(self.__randomDate__(self._dt_max_i, self._dt_max_f), self._fmt),
                        datetime.strptime(self.__randomDate__(self._dt_min_i, self._dt_max_i), self._fmt),
                        "Imagine uma descrição bonita aqui",
                        usuario.get_id()
                    )
                )
                usuario.prestador = True

        self.__inserted__ = True
        return self

    def get_all(self) -> List[Contrato]:
        return super().get_all()

    def get_by_indice(self, i: int) -> Optional[Contrato]:
        return super().get_by_indice(i)

    def __strTimeProp__(self, start, end, format, prop):

        stime = time.mktime(time.strptime(start, format))
        etime = time.mktime(time.strptime(end, format))

        ptime = stime + prop * (etime - stime)

        return time.strftime(format, time.localtime(ptime))

    def __randomDate__(self, start, end):
        return self.__strTimeProp__(start, end, self._fmt, random.random())
