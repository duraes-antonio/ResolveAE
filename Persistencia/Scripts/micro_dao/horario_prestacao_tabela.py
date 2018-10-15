# encoding: utf-8
import random
import time
from datetime import datetime, timedelta
from typing import List

from micro_dao.contrato import Contrato
from micro_dao.contrato_tabela import TabelaContrato
from micro_dao.dia_semana_tabela import TabelaDiaSemana
from micro_dao.horario_prestacao import HorarioPrestacao
from micro_dao.tabela_model import Tabela
from micro_dao.usuario_tabela import TabelaUsuario


class TabelaHorarioPrestacao(Tabela):
    _fmt = "%H:%M:%S"

    # Horário para sorteio, serão acrescentadas 2, 4, 6 ou 8 horas;
    _hor_min = "08:00:00"
    _hor_max = "14:00:00"

    def __init__(self, tab_usu: TabelaUsuario, tab_contrato: TabelaContrato,
                 tab_dia_sem: TabelaDiaSemana, preencher: bool = True):
        super().__init__("horario_prestacao", HorarioPrestacao.get_id)
        self.add_ID(HorarioPrestacao.get_id)
        self.add_TIME(HorarioPrestacao.get_horario_prest_ini, "inicio")
        self.add_TIME(HorarioPrestacao.get_horario_prest_fim, "fim")
        self.add_BOOLEAN(HorarioPrestacao.get_disponibilidade, "disponivel")
        self.add_FK(HorarioPrestacao.get_fk_usuario, "fk_usuario", tab_usu)
        self.add_FK(HorarioPrestacao.get_fk_dia_semana, "fk_dia_semana", tab_dia_sem)
        self.__inserted__ = False

        if preencher: self.__preencher__(tab_usu, tab_contrato, tab_dia_sem)

    def __preencher__(self, tab_usu: TabelaUsuario, tab_contrato: TabelaContrato,
                      tab_dia_sem: TabelaDiaSemana):

        horas = [1, 2, 4, 6, 8]

        for usuario in tab_usu.get_all():

            if not usuario.prestador:
                continue

            hora_inicio = datetime.strptime(self.__randomDate__(self._hor_min, self._hor_max), self._fmt)

            self.insert(
                HorarioPrestacao(
                    hora_inicio,
                    hora_inicio + timedelta(hours=random.choice(horas)),
                    True,
                    usuario.get_id(),
                    tab_dia_sem.get_by_indice(random.randint(0, 6)).get_id()
                )
            )

        self.__inserted__ = True
        return self

    def get_all(self) -> List[Contrato]:
        return super().get_all()

    def __strTimeProp__(self, start, end, format, prop) -> str:
        stime = time.mktime(time.strptime(start, format))
        etime = time.mktime(time.strptime(end, format))

        ptime = stime + prop * (etime - stime)

        return time.strftime(format, time.localtime(ptime))

    def __randomDate__(self, start, end) -> str:
        return self.__strTimeProp__(start, end, self._fmt, random.random())
