from datetime import datetime, time

from micro_dao.objeto_modelo import ObjetoModelo

class HorarioPrestacao(ObjetoModelo):

    _id: int
    _horario_prestacao_inicio: datetime
    _horario_prestacao_fim: datetime
    _disponivel: bool
    _fk_usuario: int
    _fk_dia_semana: int

    def __init__(self, horario_inicio: datetime, horario_fim: datetime, disponivel: bool,
                 fk_usuario: int, fk_dia_semana: int):
        self.set_horario_prest_ini(horario_inicio)
        self.set_horario_prest_fim(horario_fim)
        self.set_disponibilidade(disponivel)
        self.set_fk_usuario(fk_usuario)
        self.set_fk_dia_semana(fk_dia_semana)

    def get_id(self) -> int:
        return self._id

    def set_id(self, id: int):
        self._id = id
        return self

    def set_horario_prest_ini(self, horario_inicio: datetime):
        self._horario_prestacao_inicio = horario_inicio

    def get_horario_prest_ini(self) -> datetime:
        return self._horario_prestacao_inicio

    def set_horario_prest_fim(self, horario_fim: datetime):
        self._horario_prestacao_fim = horario_fim

    def get_horario_prest_fim(self) -> datetime:
        return self._horario_prestacao_fim

    def set_disponibilidade(self, disponivel: bool):
        self._disponivel = disponivel

    def get_disponibilidade(self) -> bool:
        return self._disponivel

    def set_fk_usuario(self, fk_usuario: int):
        self._fk_usuario = fk_usuario

    def get_fk_usuario(self) -> int:
        return self._fk_usuario

    def set_fk_dia_semana(self, fk_dia_semana: int):
        self._fk_dia_semana = fk_dia_semana
        return self

    def get_fk_dia_semana(self) -> int:
        return self._fk_dia_semana